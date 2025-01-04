package Pokemon.Joueur;

import java.util.ArrayList;
import java.util.Scanner;
import Pokemon.Entite.Entite;

/**
 * Classe représentant un joueur humain.
 */
public class Humain extends Joueur{

    /**
     * Constructeur de la classe Humain
     * @param commencePartie : Détermine si le joueur commence la partie
     */
    public Humain(boolean commencePartie) {
        super();
        m_commenceLaPartie = commencePartie;
    }

    /**
     * Cette méthode permet de jouer un tour pour le joueur humain en suivant ces étapes :
     * 1. Compléter la main du joueur si nécessaire.
     * 2. Remplacer les Pokémons morts ou manquants sur le terrain.
     * 3. Demander au joueur de choisir les attaques à effectuer.
     *
     * @param adversaire le joueur adversaire
     */
    public final void jouer(Joueur adversaire, int difficulte){
        // 1. Vérifie si la main est complète et la complète si nécessaire
        if (m_lstMain.size() != 5 || m_nombreDeCarte == 0){
            // Ajoute des cartes de la pioche à la main jusqu'à ce que la main soit complète
            while((m_lstMain.size() != 5 || m_nombreDeCarte == 0)&&m_lstPioche.size()!=0) {
                m_lstMain.add(m_lstPioche.getLast());
                m_lstPioche.removeLast();
                m_nombreDeCarte = m_lstPioche.size();
            }
        }

        // 2. Vérifie si un Pokémon du terrain est mort ou s'il manque et le remplace si nécessaire

        Scanner scanner = new Scanner(System.in);
        int nbCarteTerrain = m_lstTerrain.size();

        for(int i = 0; i<nbCarteTerrain; i++){
            try{
                if(m_lstTerrain.get(i)!=null){
                    // Si le Pokémon a 0 PV ou moins, il est retiré du terrain et ajouté à la défausse
                    if (m_lstTerrain.get(i).getM_pointDeVie() <= 0 ){
                        m_defausse.add(m_lstTerrain.get(i));
                        if(!m_lstMain.isEmpty()){ // remplace le pokemon mort au bon emplacement
                            System.out.println("Entrer le numéro du pokemon en main a placer sur le terrain");
                            System.out.print(">");
                            int choix = scanner.nextInt()-1;
                            scanner.nextLine();
                            if(choix > m_lstMain.size() || choix<0){
                                System.out.println("Chiffre trop grand ! (>"+this.sizeSansNulls(m_lstMain)+")");
                                i-=1;// Recommence la sélection
                            }else{
                                m_lstTerrain.set(i, m_lstMain.get(choix));
                                m_lstMain.remove(choix);
                            }
                        }else { // Aucun Pokémon dans la main, l'emplacement est laissé vide
                            m_lstTerrain.set(i, null);
                        }
                    }
                }
            }catch (Exception e){
                i--;
            }

        }
        //integre a cette liste les pokemon possédant un sort
        ArrayList<Entite> DoSort = new ArrayList<>();
        for(Entite entite : m_lstTerrain){
            if(entite!=null){
                if(entite.getM_sort() != null && !entite.getM_sort().getAEteUtilise()){
                    if(entite.getM_sort().getCible() != null){ // verifie si le sort n'a pas deja une cible
                        entite.getM_sort().action(this, adversaire,-1);
                    }else {
                        DoSort.add(entite);
                    }
                }
            }
        }
        // Boucle action sort
        if(!DoSort.isEmpty()){
            while(!DoSort.isEmpty()) {
                System.out.println("Voulez vous utiliser un sort ? o/n : ");
                System.out.print(">");
                String res = scanner.nextLine().trim();


                if (res.equalsIgnoreCase("o") || res.equalsIgnoreCase("oui")) {
                    System.out.println("Voici les pokemon possédant un sort : ");
                    for (int i = 0; i < DoSort.size(); i++) {
                        System.out.println((i + 1) + ", " + DoSort.get(i).toString());
                    }
                    System.out.print(">");
                    if (scanner.hasNextInt()) {
                        int selection = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume the leftover newline
                        if (selection >= 0 && selection < DoSort.size() ) {
                            this.m_PokemonUtiliseSort = DoSort.get(selection);
                            if (m_PokemonUtiliseSort.getM_sort().getUseOnMe()) { // si le sort s'utilise sur soi
                                System.out.println("Sur quel Pokémon (ne s'utilsent que sur des pokémons allié) ? : ");
                                for (int i = 0; i < this.m_lstTerrain.size(); i++) {
                                    if (this.m_lstTerrain.get(i) != null) {
                                        System.out.println((i + 1) + ". " + this.m_lstTerrain.get(i).toString());
                                    }
                                }
                            }
                            if (!m_PokemonUtiliseSort.getM_sort().getUseOnMe()) { // si le sort s'utilise sur l'adversaire
                                System.out.println("Sur quel Pokémon (ne s'utilise que sur des pokemon adversaire) ? : ");
                                for (int i = 0; i < adversaire.m_lstTerrain.size(); i++) {
                                    if (adversaire.m_lstTerrain.get(i) != null) {
                                        System.out.println((i + 1) + ". " + adversaire.m_lstTerrain.get(i).toString());
                                    }
                                }
                            }
                            if (scanner.hasNextInt()) {
                                int cibleChoisis = scanner.nextInt();
                                scanner.nextLine(); // Consume the leftover newline
                                if (cibleChoisis > 0 && cibleChoisis <= 3) {
                                    DoSort.get(selection).getM_sort().action(this, adversaire, cibleChoisis - 1); // lance le sort
                                    DoSort.remove(selection);
                                } else {
                                    System.out.println("Sélection invalide. Veuillez réessayer.");
                                }
                            } else {
                                System.out.println("Sélection invalide. Veuillez réessayer.");
                                scanner.nextLine(); // consomme le reste de la ligne
                            }

                        }

                    } else {
                        System.out.println("Sélection invalide. Veuillez réessayer.");
                        scanner.nextLine(); // consomme le reste de la ligne
                    }
                } else if (res.equalsIgnoreCase("n") || res.equalsIgnoreCase("non")) {
                    break;
                } else {
                    System.out.println("Entrée invalide. Veuillez réessayer.");
                }
            }
        }else{
            System.out.println("Aucun sort utilisable !! ");
        }

        // 3. Demande au joueur de choisir les attaques à effectuer
        ArrayList<Entite> DoAttaque = new ArrayList<>();
        for (Entite entite : m_lstTerrain) {
            if (entite != null ) {
                DoAttaque.add(entite); // Ajoute les Pokémon vivants du terrain à la liste d'attaques
            }
        }
        while(!DoAttaque.isEmpty()){
            int compteur = DoAttaque.size();
            System.out.println("Quel Pokemon doit attaquer : ");
            for (int i = 0; i < DoAttaque.size(); i++) {
                if(DoAttaque.get(i)!=null) {
                    System.out.println((i + 1) + ", " + DoAttaque.get(i).toString());
                }else{
                    System.out.println((i+1) + ". " + "Cadavre de pokémon"); 
                }
            }
            System.out.print(">");
            boolean bon = false;
            int leQuel = 0;
            while(!bon){ // verifie si le choix est valide
                if (scanner.hasNextInt()) {
                    leQuel = scanner.nextInt() - 1;
                    bon = (leQuel >= 0 && leQuel < DoAttaque.size());
                }else{
                    System.out.println("Entrer un nombre valide : \n");
                    scanner.next();
                }
            }
            if(leQuel < DoAttaque.size() && leQuel>=0) {
                System.out.println("Quel Pokémon faut-il attaquer : ");
                for (int i = 0; i < adversaire.m_lstTerrain.size(); i++) {
                    if (adversaire.m_lstTerrain.get(i) != null) {
                        System.out.println((i + 1) + ". " + adversaire.m_lstTerrain.get(i).toString());
                    } else {
                        System.out.println((i + 1) + ". " + "Cadavre de pokémon");
                    }
                }
                System.out.print(">");
                bon = false;
                int onAttaque = 0;
                while (!bon) { // verifie si le choix est valide
                    if (scanner.hasNextInt()) {
                        onAttaque = scanner.nextInt() - 1;
                        bon = true;
                    } else {
                        System.out.println("Entrer un nombre valide : \n");
                        scanner.next();
                    }

                }
                if (onAttaque < adversaire.m_lstTerrain.size() && onAttaque >= 0) {
                    // Exécute l'attaque si les indices sont valides
                    if (DoAttaque.get(leQuel) != null && adversaire.m_lstTerrain.get(onAttaque) != null && onAttaque < adversaire.m_lstTerrain.size()) {
                        try { // affiche un message d'attente pour permettre à l'utilisateur de voir l'attaque
                            System.out.print("\n" + DoAttaque.get(leQuel).getM_nom() + " est en train d'attaquer");
                            for (int i = 0; i < 3; i++) {
                                System.out.print(".");
                                Thread.sleep(250);
                                System.out.print(".");
                                Thread.sleep(250);
                                System.out.print(".");
                                Thread.sleep(250);
                                System.out.print("\b");
                                Thread.sleep(250);
                                System.out.print("\b");
                                Thread.sleep(250);
                                System.out.print("\b");
                            }
                            System.out.println("\n");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        DoAttaque.get(leQuel).attaque(adversaire.m_lstTerrain.get(onAttaque)); // attaque
                    }
                    DoAttaque.remove(leQuel);
                } else {
                    System.out.println("Trop grand ou trop petit, on recommence");
                }

            }else{
                System.out.println("Trop grand !(>"+adversaire.sizeSansNulls(adversaire.m_lstTerrain)+") Ou trop petit ! (<0)");
            }
        }
    }


    /**
     * Permet d'initialiser le Terrain pour le premier tour. Methode necessaire car le terrain ne s'initialise pas de la
     * meme maniere entre Humain et Ordinateur lors du premier tour. Demande au joueur de selectionner ses cartes dans
     * sa main pour les placer sur son terrain.
     * */
    public void initialisationTerrain(){
        try {
            Scanner scan = new Scanner(System.in);
            int choix;
            for (int i = 1; i < 4; i++) {
                System.out.println("Entrer le numéro du pokemon en main a placer sur le terrain (" + i + "/3) :");
                int count = 1;
                for (Entite pokemon : m_lstMain) {
                    System.out.println(count + " - " + pokemon);
                    count++;
                }
                System.out.print(">");
                choix = scan.nextInt();
                if(choix<=m_lstMain.size() && choix!=0){
                m_lstTerrain.add(m_lstMain.get(choix - 1));
                m_lstMain.remove(choix - 1);
                }else{
                    i-=1;
                    System.out.println("Trop grand !(>5) Ou trop petit ! (<0)");
                }


            }
            if (m_lstMain.size() != 5 || m_nombreDeCarte == 0){
                // Ajoute des cartes de la pioche à la main jusqu'à ce que la main soit complète
                while((m_lstMain.size() != 5 || m_nombreDeCarte == 0)&&m_lstPioche.size()!=0) {
                    m_lstMain.add(m_lstPioche.getLast());
                    m_lstPioche.removeLast();
                    m_nombreDeCarte = m_lstPioche.size();
                }
            }
        }catch(Exception ex){
            System.out.println("Erreur lors de l'initialisation ("+ex.getMessage()+")");
            initialisationTerrain();
        }
    }

}
