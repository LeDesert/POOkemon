package Pokemon.Joueur;


import Pokemon.Entite.Air;
import Pokemon.Entite.Entite;

import java.util.ArrayList;
import java.util.Random;
import Pokemon.Entite.Type;
/**
 *  Classe représentant un joueur Ordinateur.
 * **/

public class Ordinateur extends Joueur{

    /**
     * Constructeur de la classe Ordinateur
     * @param commencePartie : Si true, l'ordinateur commence la partie avec 20 cartes, sinon 21
     */
    public Ordinateur(boolean commencePartie){
        super();
        m_commenceLaPartie = commencePartie;
    }

    /**
     * Cette méthode permet de jouer un tour contre un adversaire en suivant ces étapes :
     * 1. Compléter la main du joueur si nécessaire.
     * 2. Remplacer les Pokémons morts ou manquants sur le terrain.
     * 3. Attaquer les Pokémons adverses disponibles.
     *
     * @param adversaire le joueur adversaire
     */
    public final void jouer(Joueur adversaire, int difficulte){

        // 1. verifie si la main est complete
        System.out.println("*************************************************************");
        if (m_lstMain.size() < 5 || m_nombreDeCarte != 0){
            while((m_lstMain.size() != 5 || m_nombreDeCarte == 0)&&m_lstPioche.size()!=0) { // Ajoute des cartes de la pioche à la main jusqu'à ce que la main soit complète
                m_lstMain.add(m_lstPioche.getLast());
                m_lstPioche.removeLast();
                m_nombreDeCarte = m_lstPioche.size();
            }
        }
        // 2. verifie si un pokemon du terrain est mort ou s'il manque
        int nbCarteTerrain = m_lstTerrain.size();
        if(!m_lstTerrain.isEmpty()){
            for(int i = 0; i<nbCarteTerrain; i++){
                if(m_lstTerrain.get(i)!=null){ // null = Pokemon mort. SI il y a un pokemon a cet emplacement
                    if (m_lstTerrain.get(i).getM_pointDeVie() <= 0){ // Si sa vie est <0
                        m_defausse.add(m_lstTerrain.get(i)); // on le rajoute a la defausse
                        if(!m_lstMain.isEmpty()){ // remplace le pokemon mort au bon emplacement
                            m_lstTerrain.set(i, m_lstMain.getLast());
                            m_lstMain.removeLast();
                        }else { // aucune entite alors, on remplace par l'element null
                            m_lstTerrain.set(i, null);
                        }
                    }
                }
            }
        }
        //integre a cette liste les pokemon possédant un sort
        ArrayList<Entite> DoSort = new ArrayList<>();
        for(Entite entite : m_lstTerrain){
            if(entite!=null){
                if(entite.getM_sort() !=null && !entite.getM_sort().getAEteUtilise()){
                    if(entite.getM_sort().getCible() != null){ // verifie si le sort n'a pas deja une cible
                        entite.getM_sort().action(this, adversaire, -1);
                    }else{
                        DoSort.add(entite);
                    }
                }
            }
        }
        // Boucle action sort
        if (!DoSort.isEmpty()) {
            Random ran = new Random();
            while (!DoSort.isEmpty()) {
                int useSort = ran.nextInt(3);
                if (useSort == 1) { // 1 chance sur 3 d'utiliser un sort
                    int pokeSelect = ran.nextInt(DoSort.size());
                    if (pokeSelect >= 0 && pokeSelect < DoSort.size()) { // verifie si le pokemon est toujours present
                        this.m_PokemonUtiliseSort = DoSort.get(pokeSelect);
                        int cibleChoisis = 0;
                        if (m_PokemonUtiliseSort.getM_sort().getUseOnMe()) { // si le sort est utilisable sur soi
                            DoSort.get(pokeSelect).getM_sort().action(this, adversaire, cibleChoisis);
                        } else { // si le sort est utilisable sur l'adversaire
                            DoSort.get(pokeSelect).getM_sort().action(this, adversaire, cibleChoisis);
                        }
                        System.out.println("Joueur 1 a utilisé le sort " + DoSort.get(pokeSelect).getM_sort().getNom() + " avec " + DoSort.get(pokeSelect).getM_nom());
                        DoSort.remove(pokeSelect);
                    }
                } else {
                    break;
                }
            }
        }


        // 3. Attaque ce qu'il y a de disponible sur le terrain en attaquant toujours le "premier" disponible
        if(difficulte==1){
            for (int i = 0; i < 3; i++) {
                if (m_lstTerrain.get(i) != null) { // Si le pokemon est present
                    for (int j = 0; j < 3; j++) {
                        if (adversaire.m_lstTerrain.get(j) != null && adversaire.m_lstTerrain.get(j).getM_pointDeVie() > 0) {
                            m_lstTerrain.get(i).attaque(adversaire.m_lstTerrain.get(j));
                            // Permet d'arrêter la recherche de l'attaque de i (ordinateur) dans j (joueur), sinon on continue
                            break;
                        }
                    }
                }
            }
        }
        else if (difficulte == 2) {
            boolean attaqueEffectuee = false;
            ArrayList<Entite> terrainAdverseTMP = new ArrayList<>();
            ArrayList<Entite> terrainOrdiTMP = new ArrayList<>();
            ArrayList<Entite> vieTrier = new ArrayList<>();
            for(Entite entite : adversaire.m_lstTerrain){
                if(entite!=null){
                    terrainAdverseTMP.add(entite);
                }
            }
            for(Entite entite : m_lstTerrain) {
                if (entite != null) {
                    terrainOrdiTMP.add(entite);
                }
            }
            for (Entite entite : terrainOrdiTMP) {
                boolean attaqueEff = false;
                for (Entite entiteJoueur : terrainAdverseTMP) {
                    Enum<Type> forceDeEntiteOrdi = entite.getM_affiniteForce();
                    if (entiteJoueur.getAffinite().equals(forceDeEntiteOrdi) && !attaqueEff) {
                        entite.attaque(entiteJoueur);
                        attaqueEff = true;
                        break;
                    }
                }
                trierEntiteParPointDeVie(terrainAdverseTMP);
                if (!attaqueEff) {
                    for (Entite entiteJoueur : terrainAdverseTMP) {
                        entite.attaque(entiteJoueur);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Permet d'initialiser le Terrain pour le premier tour. Methode necessaire car le terrain ne s'initialise pas de la
     * meme maniere entre Humain et Ordinateur lors du premier tour. Initialise simplement le terrain en prenant
     * le premier à chaque fois
     * */
    public void initialisationTerrain(){
        for(int i=0;i<3;i++){
            m_lstTerrain.add(m_lstMain.getFirst());
            m_lstMain.remove(m_lstMain.getFirst());
        }
        if (m_lstMain.size() != 5 || m_nombreDeCarte == 0){
            // Ajoute des cartes de la pioche à la main jusqu'à ce que la main soit complète
            while((m_lstMain.size() != 5 || m_nombreDeCarte == 0)&&m_lstPioche.size()!=0) {
                m_lstMain.add(m_lstPioche.getLast());
                m_lstPioche.removeLast();
                m_nombreDeCarte = m_lstPioche.size();
            }
        }
    }

    /**
     * Permet de trier les entites par point de vie
     * @param ent : La liste d'entites a trier
     * */
    private void trierEntiteParPointDeVie(ArrayList<Entite> ent) {
        for (int i = 0; i < ent.size() - 1; i++) {
            for (int j = i + 1; j < ent.size(); j++) {
                if (ent.get(i).getM_pointDeVie() > ent.get(j).getM_pointDeVie()) {
                    // Échanger les éléments i et j de la liste
                    Entite temp = ent.get(i);
                    ent.set(i, ent.get(j));
                    ent.set(j, temp);
                }
            }
        }
    }

}
