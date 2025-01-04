package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class SoinPermanent implements Sort{
    private Entite m_cible;
    /**
     * @return
     */
    @Override
    public String getDescription() {
        return "le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne au début de chaque tour 10 points de vie (mais ne peut pas dépasser son nombre de points de vie initial).";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        if(m_cible == null){

            m_cible = joueur.getM_lstTerrain().get(choixPokemon); // designe la cible
            System.out.println(m_cible.getM_nom() + " est soigné en permanence de 10 pv \n");
        }
        if(m_cible.getM_pointDeVie() > 0 ){
            m_cible.setM_pointDeVie(Math.min(m_cible.getM_pointDeVie() + 10, m_cible.getM_pointDeVieMax())); //soigne le pokemon de 10 pv 
            System.out.println(m_cible.getM_nom() + " a été soignée. Ses pv sont actuellement de " + m_cible.getM_pointDeVie()+ "/" +m_cible.getM_pointDeVieMax()+"\n");
        }
    }

    @Override
    public boolean getAEteUtilise() {
        return false;
    }

    @Override
    public boolean getUseOnMe() {
        return true;
    }

    @Override
    public Entite getCible() {
        return m_cible;
    }

    public String getNom(){
        return "soin permanent";
    }
}
