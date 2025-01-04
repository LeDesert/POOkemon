package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

import java.util.Random;
import java.util.Scanner;

public class FerveurGuerriere implements Sort{

    private boolean m_utilise = false; 
    
    @Override
    public String getDescription() {
        return " le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de plus.";
    }

    @Override
    public void action(Joueur joueur, Joueur ennemie, int choixPokemon) {
        m_utilise = true;
        joueur.getM_lstTerrain().get(choixPokemon).setM_forceAttaque(joueur.getM_lstTerrain().get(choixPokemon).getM_forceAttaque()+10); //augmente la force d'attaque du pokemon choisi de 10
        System.out.println(joueur.getM_lstTerrain().get(choixPokemon).getM_nom() + " a une force augmenté de 10. Sa nouvelle force est " + joueur.getM_lstTerrain().get(choixPokemon).getM_forceAttaque());
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
        return "ferveur guerrière";
    }
}
