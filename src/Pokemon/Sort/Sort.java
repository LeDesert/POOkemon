package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.*;

public interface Sort {


    /**
     * @return String la description
     */
    public String getDescription();


    /**
     * @param joueur : le joueur qui utilise le sort
     * @param ennemie : le joueur adverse
     * @param choixPokemon : le pokemon cible du sort
     */
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon);

    /**
     * @return si le sort a été utilisé
     */
    public boolean getAEteUtilise();

    /**
     * @return si le sort est utilisé sur le joueur qui l'utilise
     */
    public boolean getUseOnMe();

    /**
     * @return la cible du sort
     */
    public Entite getCible();

    /**
     * @return le nom du sort
     */
    public String getNom();
}

