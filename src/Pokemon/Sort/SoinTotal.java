package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class SoinTotal implements Sort{

    private Boolean m_utilise = false;

    @Override
    public String getDescription() {
        return "le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne toute sa vie.";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        try{
            joueur.getM_lstTerrain().get(choixPokemon).setM_pointDeVie(joueur.getM_lstTerrain().get(choixPokemon).getM_pointDeVieMax()); //soigne le pokemon totalement
            System.out.println(joueur.getM_lstTerrain().get(choixPokemon).getM_nom() + " a été soigné !! ");
            m_utilise = true;
        }catch (Exception e){
            System.out.println("Erreur annulation du lancement du sort");
        }
    }

    @Override
    public boolean getAEteUtilise() {
        return m_utilise;
    }

    @Override
    public boolean getUseOnMe() {
        return true;
    }

    @Override
    public Entite getCible() {
        return null;
    }
    
    public String getNom(){
        return "soin total";
    }
}
