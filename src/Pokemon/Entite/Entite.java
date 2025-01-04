package Pokemon.Entite;

import Pokemon.Sort.Sort;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Classe abstraite "mere" de Feu, Eau, Terre, Air.
 * */
public abstract class Entite {
    protected String m_nom;
    protected int m_pointDeVieMax;
    protected int m_pointDeVie;
    protected int m_forceAttaque;
    protected Sort m_sort = null;

    public Entite(){

    }


    /**
     * "Getter" qui permet d'avoir le nom du Pokemon (Entite actuelle)
    * @return String : renvoie le nom du Pokemon actuel
    * */
    public String getM_nom() {
        return m_nom;
    }

    /**
     * "Getter" qui permet d'avoir le sort du Pokemon (Entite actuelle)
     * @return Sort : renvoie le sort du Pokemon actuel
     * */ 
    public Sort getM_sort(){
        if(this==null){
            return null;
        }else{
            return m_sort;
        }
    }
    /**
     * "Getter" qui permet d'avoir les points de vie Max de l'Entite (Pokemon)
     * @return Integer : Les points de vie max du Pokemon
     * */

    public int getM_pointDeVieMax() {
        return m_pointDeVieMax;
    }

    /**
     * "Getter" qui permet d'avoir les points de vie actuels de l'Entite (Pokemon)
     * @return Integer : Les points de vie actuels du Pokemon
     * */
    public int getM_pointDeVie() {
        return m_pointDeVie;
    }

    /**
     * @param pv : Les points de vie actuels du Pokemon
     * Permet de modifier les points de vie actuels du Pokemon
     */
    public void setM_pointDeVie(int pv) {
        if(this.m_pointDeVieMax >= pv){
        this.m_pointDeVie = pv;
        }
    }
    
    /**
     * "Getteur" Permet de connaitre l'affinite d'un pokemon
     * 
     * @return Enum<Type> : Renvoie le type du Pokémon (affinite)
     **/
    public abstract Enum<Type> getAffinite();

    /**
     * "Getteur" Permet de connaitre l'affinite de force et de faiblesse d'un pokemon
     * 
     * @return Enum<Type> : Renvoie le type du Pokémon (affinite)
     */
    public abstract Enum<Type> getM_affiniteForce();
    
    /**
     * "Getteur" Permet de connaitre l'affinite de force et de faiblesse d'un pokemon
     * 
     * @return Enum<Type> : Renvoie le type du Pokémon (affinite)
     */
    public abstract Enum<Type> getM_affiniteFaiblesse();


    /**
     * "Getter" qui permet d'avoir la force d'attaque de l'Entite (Pokemon)
     * @return Integer : La force d'attaque de l'Entite
     * */
    public int getM_forceAttaque() {
        return m_forceAttaque;
    }

    /**
     * @param m_forceAttaque : La force d'attaque de l'Entite
     * Permet de modifier la force d'attaque de l'Entite (Pokemon)
     */
    public void setM_forceAttaque(int m_forceAttaque) {
            this.m_forceAttaque = m_forceAttaque;
    }

    /**
     * Permet d'attaquer un autre pokémon en fonction de l'affinite du pokemon vise (Force et Faiblesse)
     * @param pokemon : est le pokemon vise par l'attaque de type
     * @return boolean : renvoie true si l'attaque a pu se faire, false sinon
     * **/
    public abstract boolean attaque(Entite pokemon);


    /**
     * Permet de renvoyer sous format d'une chaine presentant l'ensemble des informations de l'Entite
     * @return String : Les informations utiles du Pokemon
     * */
    public String toString(){
        if(this == null){
            return null;
        }else{
        return m_nom+", "+getAffinite()+", Vie : "+getM_pointDeVie()+", Attaque : "+getM_forceAttaque() + (getM_sort() != null ? ", Sort : " + getM_sort().getNom() : "");
        }
    }
}
