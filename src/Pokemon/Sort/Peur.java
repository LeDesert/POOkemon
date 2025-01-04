package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class Peur implements Sort{

     private boolean m_utilise = false;
    
    @Override
    public String getDescription() {
        return " le Pokémon choisit un Pokémon du camp adverse. Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégats de moins.";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        m_utilise = true;
        ennemie.getM_lstTerrain().get(choixPokemon).setM_forceAttaque(ennemie.getM_lstTerrain().get(choixPokemon).getM_forceAttaque()-10); //diminue la force d'attaque du pokemon choisi de 10
        System.out.println(ennemie.getM_lstTerrain().get(choixPokemon).getM_nom() + " a une force dimminué de 10. Sa nouvelle force est " + ennemie.getM_lstTerrain().get(choixPokemon).getM_forceAttaque());
    }

    @Override
    public boolean getAEteUtilise() {
        return m_utilise;
    }

    @Override
    public boolean getUseOnMe() {
        return false;
    }

    @Override
    public Entite getCible() {
        return null;
    }
    
    public String getNom(){
        return "Peur";
    }
}


