package Pokemon.Joueur;

import java.util.ArrayList;
import java.util.Random;
import Pokemon.Entite.*;
import java.util.Collections;
/**
 * Classe mere de Humain et Ordinateur
 * */
public abstract class Joueur {

    protected boolean m_commenceLaPartie; // Si true : commence la partie et à 20 cartes, sinon 21

    protected boolean m_estInitialise = false; // Eviter que son jeu soit initialise 2 fois

    protected int m_nombreDeCarte; // Prend en valeur la size de la pioche

    protected Entite m_PokemonUtiliseSort;

    protected ArrayList<Entite> m_defausse;

    protected ArrayList<Entite> m_lstPioche;

    protected ArrayList<Entite> m_lstTerrain;

    protected ArrayList<Entite> m_lstMain;



    /**
     * Constructeur de la classe Joueur qui permet de générer les listes de cartes
     */
    public Joueur() {
        m_nombreDeCarte = 0;
        m_defausse = new ArrayList<>();
        m_lstPioche = new ArrayList<>();
        m_lstTerrain = new ArrayList<>();
        m_lstMain = new ArrayList<>();

    }


    /**
     * @param lst_Entite : La liste d'entite à ajouter à la pioche
     * Cette méthode permet d'ajouter des cartes à la pioche
     */
    public void addM_lstPioche(ArrayList<Entite> lst_Entite){
        this.m_lstPioche.addAll(lst_Entite);
    }


    /**
     * Enleve toutes les cartes de la main
     */
    public void clearM_lstMain(){
        this.m_lstMain.clear();
    }

    /**
     * @param nbreAClear : Le nombre de cartes à enlever de la main
     * Enleve nbreAClear cartes de la main
     */
    public void clearM_lstMain(int nbreAClear){
        for(int i=0;i<nbreAClear;i++){
            this.m_lstMain.remove(i);
        }
    }


    /**
     * Methode qui permet de melanger la pioche
     */
    public void shuffleM_lstPioche(){
        Collections.shuffle(this.m_lstPioche);
    }

    /**
     * @param liste : à compter sans les null
     * Fonction similaire au size, mais si l'element n de la liste est null, on ne le compte pas.
     * @return Integer : la taille de la liste sans Null
     * */
    public int sizeSansNulls(ArrayList<?> liste) {
        int nb = 0;
        for (Object e : liste) {
            if (e != null) {
                nb++;
            }
        }
        return nb;
    }

    /**
     * Permet de faire joueur le joueur Ordinateur ou Humain
     * @param adversaire : L'adversaire enface Joueur ou Ordinateur
     * @param difficulte : La difficulte de l'ordinateur
     * */
    public abstract void jouer(Joueur adversaire, int difficulte);

    /**
     * Permet d'intialiser le terrain.
     * Abstrait car le terrain n'est pas initialise de la meme maniere entre Ordinateur et Joueur
     * */
    public abstract void initialisationTerrain();


    /**
     *"Getter" permet d'avoir la defausse du joueur
     * @return ArrayList<Entite> : La défausse du joueur
     * */
    public ArrayList<Entite> getM_defausse(){
        return m_defausse;
    }

    /**
     *"Getter" permet d'avoir la pioche du joueur
     * @return ArrayList<Entite> : La pioche du joueur
     * */
    public ArrayList<Entite> getM_lstPioche(){
        return m_lstPioche;
    }

    /**
     *"Getter" permet d'avoir le terrain du joueur
     * @return ArrayList<Entite> : Le terrain du joueur
     * */
    public ArrayList<Entite> getM_lstTerrain(){
        return m_lstTerrain;
    }

    /**
     *"Getter" permet d'avoir la main du joueur
     * @return ArrayList<Entite> : La main du joueur
     * */
    public ArrayList<Entite> getM_lstMain(){
        return m_lstMain;
    }

    /**
     *"Getter" permet de savoir si le joueur commence la partie
     * */
    public boolean getM_commenceLaPartie() {
        return m_commenceLaPartie;
    }

    /**
     *"Getter" permet de savoir si un pokemon a utilisé son sort
     * */
    public Entite getM_PokemonUtiliseSort(){
        return m_PokemonUtiliseSort;
    }

    /**
     * @param ent : L'entite qui a utilise son sort
     *"Setter" permet de definir si lun pokemon a utilisé son sort
     * */
    public void setM_PokemonUtiliseSort(Entite ent){
        m_PokemonUtiliseSort = ent;
    }
}
