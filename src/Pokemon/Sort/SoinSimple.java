package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class SoinSimple implements Sort{

    @Override
    public String getDescription() {
        return " le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne 30 points de vie (mais ne peut pas dépasser son nombre de points de vie initial).";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        try{

            joueur.getM_lstTerrain().get(choixPokemon).setM_pointDeVie(Math.min(joueur.getM_lstTerrain().get(choixPokemon).getM_pointDeVie() + 30, joueur.getM_lstTerrain().get(choixPokemon).getM_pointDeVieMax())); //soigne le pokemon de 30 pv
            System.out.println(joueur.getM_lstTerrain().get(choixPokemon).getM_nom() + " a été soigné !! ");

        }catch (Exception e){
            System.out.println("Erreur annulation du lancement du sort");
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
        return null;
    }
    
    public String getNom(){
        return "soin simple";
    }
}
