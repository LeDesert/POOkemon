package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class SoinDeZone implements Sort{

    @Override
    public String getDescription() {
        return "chaque Pokémon de son camp regagne 10 points de vie";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        for(int i = 0; i < joueur.getM_lstTerrain().size(); i++){ //soigne tout les pokemons du joueur
            if(joueur.getM_lstTerrain().get(i) != null){
                joueur.getM_lstTerrain().get(i).setM_pointDeVie(Math.min(joueur.getM_lstTerrain().get(i).getM_pointDeVie() + 10, joueur.getM_lstTerrain().get(i).getM_pointDeVieMax())); //soigne le pokemon de 10 pv
                System.out.println(joueur.getM_lstTerrain().get(i).getM_nom() + " a été soigné !! ");
            }
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
        return "soin de zone";
    }
}
