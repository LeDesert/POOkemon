package Pokemon.Sort;

import Pokemon.Entite.Entite;
import Pokemon.Joueur.Joueur;

import java.util.ArrayList;

public class Confusion implements Sort{

    private final boolean m_aEteUtilise = false; //Final car ne doit pas etre modifié. Cette variable doit etre passé en true
                                                    // si et seulement si le sort est a utilisation unique

    public String getDescription() {
        return "Confusion, utilisable à chaque tour : \nle joueur adverse doit défausser toutes les cartes de sa main dans sa pioche, mélanger sa pioche et piocher 5 Pokémons.";
    }

    public void action(Joueur joueur, Joueur ennemie, int choixPokemon){
        if(!ennemie.getM_lstMain().isEmpty()){
        ArrayList<Entite> entiteJoueurEnnemie = new ArrayList<>(ennemie.getM_lstMain()); //On copie la main du joueur ennemie
        ennemie.addM_lstPioche(entiteJoueurEnnemie); //On ajoute les cartes de la main du joueur ennemie dans sa pioche
        ennemie.clearM_lstMain(); //On vide la main du joueur ennemie
        ennemie.shuffleM_lstPioche(); //On mélange la pioche du joueur ennemie
            System.out.println("Le sort de confusion a été utilisé ! 🌀🌀🌀");
        }else{
            System.out.println("Impossible car la main est vide");
        }
    }

    public boolean getAEteUtilise(){
        return m_aEteUtilise;
    }

    public boolean getUseOnMe(){
        return false;
    }

    @Override
    public Entite getCible() {
        return null;
    }

    public String getNom(){return "confusion";}
}
