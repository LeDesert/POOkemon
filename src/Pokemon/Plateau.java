package Pokemon;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.*;

import java.util.ArrayList;
import java.util.Scanner;

public class  Plateau {

    private int m_tour = 1;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public Plateau(){};

    public void game() throws InterruptedException {

        System.out.println();
        System.out.println();
        System.out.print("Lancement du jeu");

        for(int i=0;i<3;i++){

            System.out.print(ANSI_BLUE+"."+ANSI_RESET);
            Thread.sleep(250);
            System.out.print(ANSI_WHITE+"."+ANSI_RESET);
            Thread.sleep(250);
            System.out.print(ANSI_RED+"."+ANSI_RESET);
            Thread.sleep(250);
            System.out.print("\b");
            Thread.sleep(250);
            System.out.print("\b");
            Thread.sleep(250);
            System.out.print("\b");
        }

        for(int i=0;i<50;i++){

            System.out.println();
        }

        System.out.println(ANSI_YELLOW +"\n" +
                " ▄▄▄·            ▄ •▄ ▄▄▄ .• ▌ ▄ ·.        ▐ ▄ \n" +
                "▐█ ▄█▪     ▪     █▌▄▌▪▀▄.▀··██ ▐███▪▪     •█▌▐█\n" +
                " ██▀· ▄█▀▄  ▄█▀▄ ▐▀▀▄·▐▀▀▪▄▐█ ▌▐▌▐█· ▄█▀▄ ▐█▐▐▌\n" +
                "▐█▪·•▐█▌.▐▌▐█▌.▐▌▐█.█▌▐█▄▄▌██ ██▌▐█▌▐█▌.▐▌██▐█▌\n" +
                ".▀    ▀█▄▀▪ ▀█▄▀▪·▀  ▀ ▀▀▀ ▀▀  █▪▀▀▀ ▀█▄▀▪▀▀ █▪\n" + ANSI_RESET);

        // Initialisation des classes
        Arbitre arbt = new Arbitre();
        Joueur ordi = new Ordinateur(false);
        Joueur j1 = new Humain(false);
        Plateau p = new Plateau();

        //Affichage des règles
        p.reglement();
        // Choix de la difficulté
        int difficulte;
        difficulte = p.choixDifficulter();
        arbt.commence(j1, ordi); // Choix du joueur qui commence
        System.out.print("Roulement de tambours");

        for(int i=0;i<2;i++){
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
        System.out.println(j1.getM_commenceLaPartie() ?  "\n****************************************\nC'est le joueur qui commence ! 😊\n****************************************\n": "\n****************************************\nC'est l'ordinateur qui commence! 🤖\n****************************************\n");
        Thread.sleep(3000);
        // Initialisation des joueurs
        arbt.initialiser(ordi);
        arbt.initialiser(j1);
        // Initialisation des terrains
        ordi.initialisationTerrain();
        j1.initialisationTerrain();
        // Affichage du plateau de jeu
        p.affichagePlateau(j1, ordi);

        if(j1.getM_commenceLaPartie()){ 
            
            while(!arbt.aPerdu(ordi) && !arbt.aPerdu(j1)) { 

                j1.jouer(ordi,difficulte);
                Thread.sleep(100);
                Thread.sleep(100);
                System.out.println();
                System.out.println();
                System.out.print("L'ordinateur est en train de jouer");

                for(int i=0;i<2;i++){

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
                ordi.jouer(j1,difficulte);
                Thread.sleep(7000);
                p.affichagePlateau(j1, ordi);
            }

        }else{

            while(!arbt.aPerdu(ordi) && !arbt.aPerdu(j1)) {
            
                System.out.println();
                System.out.println();
                System.out.print("L'ordinateur est en train de jouer");
            
                for(int i=0;i<3;i++){
            
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
                ordi.jouer(j1,difficulte);
                Thread.sleep(5000);
                p.affichagePlateau(j1, ordi);
                Thread.sleep(100);
                j1.jouer(ordi,difficulte);
            }
        }
            if(arbt.aPerdu(ordi)){
            
                System.out.println("Vous avez gagné !");
        
            }else{
        
                System.out.println("Ordi a gagné !");
       
            }
        }

    /**
     * @param j1 : Joueur 1 
     * @param j2 : Joueur 2
     * Gerer l'affichage du plateau de jeu
     */
    public void affichagePlateau(Joueur j1, Joueur j2){
        StringBuilder affichage = new StringBuilder();
        affichage.append("*".repeat(80));
        affichage.append("\nTour "+m_tour+":\n");
        m_tour+=1;
        affichage.append("\n\t\t\t\t\t\t\t Joueur 1 ");
        affichage.append("\n\n");
        affichage.append("pioche : ").append(j2.getM_lstPioche().size()).append("\n");
        affichage.append("defausse : ").append(j2.getM_defausse().size()).append("\n");
        affichage.append(genererLigneCentre(j2.getM_lstTerrain()));
        affichage.append("\n\n");
        affichage.append("-".repeat(80));
        affichage.append("\n\t\t\t\t\t\t\t Joueur 2 (vous) \n ");
        affichage.append(genererLigneCentre(j1.getM_lstTerrain()));
        affichage.append("\n\n");
        affichage.append("pioche : ").append(j1.getM_lstPioche().size()).append("\n");
        affichage.append("defausse : ").append(j1.getM_defausse().size()).append("\n");
        affichage.append(genererMain(j1));

        System.out.println(affichage.toString());
    }

    /**
     * Permet de nettoyer le plateau de jeu
     */
    public void clearPlateau(){
        for(int i=0;i<50;i++){
            System.out.println("");
        }
    }

    /**
     * @param point : Liste des Pokémons
     * génére les lignes du plateau de jeu
     * @return String : Retourne les informations des Pokémons du joueur avec une taille différente en fonction de la taille des informations
     */
    public String genererLigneCentre(ArrayList<Entite> point){
        StringBuilder line = new StringBuilder();
        int compteur1 = 0;
        int compteur2= 0;

        while(compteur2 <= 5){
            if(point.get(compteur1) != null){
                int lengthAttaque = ("Attaque : " + point.get(compteur1).getM_forceAttaque()).length();
                int lengthVie = ("Vie : " + point.get(compteur1).getM_pointDeVie() + "/" + point.get(compteur1).getM_pointDeVieMax()).length();
                int lengthAffinite = ("Affinite : " + point.get(compteur1).getAffinite()).length();
                int lengthNom = point.get(compteur1).getM_nom().length();
                int maxLenght = Math.max(Math.max(lengthAttaque, lengthVie), Math.max(lengthAffinite, lengthNom)) +2;

                if(compteur2 == 0) {
                    line.append("\t*");
                    line.append("-".repeat(maxLenght));
                    line.append('*');

                }

                if(compteur2 == 1){
                    line.append("\t|").append(" ".repeat((maxLenght-lengthAttaque)/2)).append("Attaque : ")
                            .append(point.get(compteur1).getM_forceAttaque()).append(" ".repeat((maxLenght-lengthAttaque)/2))
                            .append("|");
                } else if (compteur2 == 2) {
                    line.append("\t|").append(" ".repeat((maxLenght-lengthVie)/2)).append("Vie : ")
                            .append(String.format(" %d/%d", point.get(compteur1).getM_pointDeVie(), point.get(compteur1).getM_pointDeVieMax()))
                            .append(" ".repeat((maxLenght - lengthVie)/2- (lengthVie % 2 == 1 ? 0 : 1))).append("|");
                } else if (compteur2 == 3) {
                    line.append("\t|").append(" ".repeat((maxLenght-lengthAffinite)/2))
                            .append("Affinite : ").append(point.get(compteur1).getAffinite())
                            .append(" ".repeat((maxLenght-lengthAffinite)/2 )).append("|");
                } else if (compteur2 == 4) {
                    line.append("\t|").append(" ".repeat((maxLenght-lengthNom)/2)).append(point.get(compteur1).getM_nom())
                            .append(" ".repeat(((maxLenght - lengthNom) / 2 + (lengthNom % 2 == 1 ? 1 : 0))
                            )).append("|");
                }

                if(compteur2 == 5 ){
                    line.append("\t*");
                    line.append("-".repeat(maxLenght));
                    line.append('*');
                }
            }

            compteur1++;
            if(compteur1==3){
                compteur2++;
                compteur1 = 0;
                line.append("\n");
            }

        }
        return line.toString();
    }

    /**
     * @param joueur : Joueur actuel
     * @return String : Retourne la main du joueur afin de l'afficher
     */
    public String genererMain(Joueur joueur){
        StringBuilder line = new StringBuilder();
        line.append("Main : \n");
        for (int i = 0; i < joueur.getM_lstMain().size(); i++) {
            line.append((i + 1)).append(". ").append(joueur.getM_lstMain().get(i).toString()).append("\n");
        }
        return line.toString();
    }

    /**
     * Permet d'afficher les règles du jeu et de les lire si le joueur le souhaite
     */
    public void reglement() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Voulez-vous lire les règles du jeu ? (o/n)");
            String rep = scan.nextLine();
            if (rep.equalsIgnoreCase("o")) {
                while (true) {
                    for (int i = 0; i < 30; i++) {
                        System.out.println("");
                    }
                    System.out.println("\nQuelle règle voulez-vous choisir ? (1, 2, 3)\n");
                    System.out.print("1.    Mise en place et objectif du jeu\n2.    Déroulement du jeu\n3.    Composition et caractéristiques des Pokémons\nSTOP. Quitter ce menu\n>");
                    rep = scan.nextLine();
                    if (rep.equalsIgnoreCase("stop")) {
                        break;
                    } else {
                        this.clearPlateau();
                        switch (rep) {
                            case "1":
                                System.out.println("#### 1. Mise en Place et Objectif du Jeu");
                                System.out.println("- Objectif : Éliminer tous les Pokémons de votre adversaire pour gagner.");
                                System.out.println("- Mise en Place :");
                                System.out.println("  - Le premier joueur est choisi aléatoirement.");
                                System.out.println("  - Le premier joueur a une pioche de 20 Pokémons, le second de 21.");
                                System.out.println("  - Une main est une seconde pioche de 5 cartes visibles par vous-même.");
                                System.out.println("    Les cartes arrivants dans la main sont tirées aléatoirement depuis votre pioche");
                                System.out.println("  - Pour placer des Pokémons sur le terrain, vous devrez les choisirs parmis ceux de votre main");
                                System.out.println("  - Le premier joueur commence à jouer.\n");
                                break;
                            case "2":
                                System.out.println("#### 2. Déroulement du Jeu");
                                System.out.println("- Tour du Joueur Humain :");
                                System.out.println("  - Piochez des Pokémons jusqu'à en avoir 5 en main ou que votre pioche soit vide.");
                                System.out.println("  - Placez un Pokémon de votre main sur chaque emplacement vide sur votre terrain.");
                                System.out.println("  - Attaquez une fois avec chacun de vos Pokémons.\n");

                                System.out.println("- Tour de l'Ordinateur :");
                                System.out.println("  - Pioche des Pokémons jusqu'à en avoir 5 en main ou que sa pioche soit vide.");
                                System.out.println("  - Place un Pokémon de sa main sur chaque emplacement vide sur son terrain.");
                                System.out.println("  - Attaque une fois avec chacun de ses Pokémons.\n");

                                System.out.println("- Attaque des Pokémons :");
                                System.out.println("  - Une attaque diminue les points de vie de l'adversaire de la valeur de sa force d'attaque.");
                                System.out.println("  - Si un Pokémon n'a plus de points de vie, il est placé dans la défausse.");
                                System.out.println("  - Avantage d'affinité : +10 à l'attaque.");
                                System.out.println("  - Désavantage d'affinité : -10 à l'attaque.\n");

                                System.out.println("- Stratégie d'Attaque de l'Ordinateur :");
                                System.out.println("  - Attaque prioritairement les Pokémons dont l'affinité lui donne un avantage.");
                                System.out.println("  - Si plusieurs choix, attaque le Pokémon avec le moins de points de vie.");
                                System.out.println("  - Si plusieurs choix, attaque un Pokémon aléatoirement.\n");
                                break;
                            case "3":
                                System.out.println("#### 3. Composition et Caractéristiques des Pokémons");
                                System.out.println("- Terrain et Cartes :");
                                System.out.println("  - Un terrain peut accueillir 3 Pokémons.");
                                System.out.println("  - Une main de 5 Pokémons maximum.");
                                System.out.println("  - Une pioche de 20 ou 21 Pokémons.");
                                System.out.println("  - Une défausse pour les Pokémons éliminés.\n");

                                System.out.println("- Avantages des Affinités :");
                                System.out.println("  - Terre > Eau");
                                System.out.println("  - Eau > Feu");
                                System.out.println("  - Feu > Air");
                                System.out.println("  - Air > Terre\n");
                                break;
                            default:
                                System.out.println("Choix invalide. Veuillez choisir 1, 2, 3 ou STOP.");
                        }
                        System.out.println("\nVoulez-vous lire une autre règle ? (o/n)");
                        rep = scan.nextLine();
                        if (!rep.equalsIgnoreCase("o")) {
                            this.clearPlateau();
                            System.out.println("\n" +
                                    " ▄▄▄·            ▄ •▄ ▄▄▄ .• ▌ ▄ ·.        ▐ ▄ \n" +
                                    "▐█ ▄█▪     ▪     █▌▄▌▪▀▄.▀··██ ▐███▪▪     •█▌▐█\n" +
                                    " ██▀· ▄█▀▄  ▄█▀▄ ▐▀▀▄·▐▀▀▪▄▐█ ▌▐▌▐█· ▄█▀▄ ▐█▐▐▌\n" +
                                    "▐█▪·•▐█▌.▐▌▐█▌.▐▌▐█.█▌▐█▄▄▌██ ██▌▐█▌▐█▌.▐▌██▐█▌\n" +
                                    ".▀    ▀█▄▀▪ ▀█▄▀▪·▀  ▀ ▀▀▀ ▀▀  █▪▀▀▀ ▀█▄▀▪▀▀ █▪\n");
                            break;
                        }
                    }
                }
            }
            Thread.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet au joueur de choisir la difficulté du jeu
     * @return int : Retourne le niveau de difficulté choisi par le joueur
     */
    public int choixDifficulter(){
        try{
            System.out.println("Choisissez votre difficulté (1,2) :\n\t " + ANSI_GREEN +"(1)Facile" + ANSI_RESET + ANSI_YELLOW+ "\n\t(2)Normal" + ANSI_RESET);
            System.out.print(">");
            Scanner scan = new Scanner(System.in);
            int niveau = scan.nextInt();
            if(niveau==2 || niveau ==1){
                System.out.print("Vous avez choisis le mode : ");
                System.out.println(niveau==1 ? (ANSI_GREEN + "Facile" + ANSI_RESET) : (ANSI_YELLOW + "Normal" + ANSI_RESET));
                return niveau;
            }else{
                System.out.println("Erreur lors de la saisie");
                this.choixDifficulter();
            }
        }catch (Exception ex){choixDifficulter();}
        return 0;
    }

}
