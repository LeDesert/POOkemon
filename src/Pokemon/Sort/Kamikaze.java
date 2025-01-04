package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

public class Kamikaze implements Sort{

    private Boolean m_utilise = false;

    public String getDescription(){
        return "à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Les deux Pokémons sont alors éliminés.";
    }

    public void action(Joueur joueur, Joueur ennemie, int choixPokemon){
        if(ennemie.getM_lstTerrain().get(choixPokemon)!=null){ // Si le pokemon existe
            m_utilise=true;
            System.out.println("KABOUM, le sort Kamikaze a été utlisé !💥💥"); // Pour le fun
            joueur.getM_PokemonUtiliseSort().setM_pointDeVie(-1000); // On tue le pokemon qui utilise le sort
            ennemie.getM_lstTerrain().get(choixPokemon).setM_pointDeVie(-1000); // On tue le pokemon adverse
        }else{
            System.out.println("Sort non utilisé, erreur.");
        }
    }

    public boolean getAEteUtilise(){
        return m_utilise;
    }

    public boolean getUseOnMe(){
        return false;
    }

    public Entite getCible(){
        return null;
    }

    public String getNom(){
        return "Kamikaze";
    }
}
