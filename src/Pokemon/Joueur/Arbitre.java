package Pokemon.Joueur;

import Pokemon.Entite.*;
import Pokemon.Sort.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Arbitre {
    private ArrayList<Sort> m_lstSort;

    private final ArrayList<String> m_pokemonList = new ArrayList<>(Arrays.asList( // Liste des noms de Pokemon
            "Ali", "Cyrilol", "MathieuXOXO", "CathyX", "VeroCROCS", "Salamouche", "ProfDeReseau", "Sandshrew",
            "MonsieurJeParlePas", "Nidoran", "Clefairy", "Vulpix", "Jigglypuff", "Zubat", "Meowth", "Psyduck",
            "Mankey", "Growlithe", "Poliwag", "Abra", "Machop", "Bellsprout", "Geodude", "Ponyta",
            "Slowpoke", "Magnemite", "Farfetch'd", "Doduo", "Seel", "Grimer", "Shellder", "Gastly",
            "Onix", "Drowzee", "Krabby", "Voltorb", "Exeggcute", "Cubone", "Lickitung", "Koffing",
            "Rhyhorn", "Tangela", "Kangaskhan", "Horsea", "Goldeen", "Staryu", "Mr. Mime", "Scyther",
            "Jynx", "Electabuzz", "Magmar", "Pinsir", "Tauros", "Magikarp", "Gyarados", "Lapras",
            "Ditto", "Eevee", "Vaporeon", "Jolteon", "Flareon", "Porygon", "Omanyte", "Kabuto",
            "Aerodactyl", "Snorlax", "Articuno", "Zapdos", "Moltres", "Dratini", "Dragonair", "Dragonite",
            "Mewtwo", "Mew", "Chikorita", "Cyndaquil", "Totodile", "Sentret", "Hoothoot", "Ledyba",
            "Spinarak", "Chinchou", "Pichu", "Cleffa", "Igglybuff", "Togepi", "Natu", "Xatu",
            "Mareep", "Flaaffy", "Ampharos", "Bellossom", "Marill", "Azumarill", "Sudowoodo", "Politoed",
            "Hoppip", "Skiploom", "Jumpluff", "Aipom", "Sunkern", "Yanma", "Wooper", "Quagsire",
            "Espeon", "Umbreon", "Murkrow", "Slowking", "Misdreavus", "Unown", "Wobbuffet", "Girafarig",
            "Pineco", "Forretress", "Dunsparce", "Gligar", "Steelix", "Snubbull", "Granbull", "Qwilfish"
    ));

    /**
     * Constructeur de la classe Arbitre qui permet de générer les sorts et de les mélanger
     */
    public Arbitre(){
        m_lstSort = new ArrayList<Sort>();
        Sort SoinZone = new SoinDeZone();
        Sort SoinPermanent = new SoinPermanent();
        Sort SoinTotal = new SoinTotal();
        Sort SoinSimple = new SoinSimple();
        Sort ferveurGuerrier = new FerveurGuerriere();
        Sort confusion = new Confusion();
        Sort kamikaze = new Kamikaze();
        Sort peur = new Peur();

        m_lstSort.add(SoinZone);
        m_lstSort.add(SoinPermanent);
        m_lstSort.add(SoinTotal);
        m_lstSort.add(SoinSimple);
        m_lstSort.add(ferveurGuerrier);
        m_lstSort.add(confusion);
        m_lstSort.add(kamikaze);
        m_lstSort.add(peur);
        Collections.shuffle(m_lstSort);

    }
    /**
     * @param joueur : Le joueur à initialiser
     * Cette méthode permet d'initialiser automatiquement les cartes que possede chaque joueur en pprocédant comme suit :
     * 1. On vérifie si le joueur n'est pas initialisé pour éviter les triches.
     * 2. On attribue le nombre de noms nécessaires aléatoirement
     * 3. On genere les cartes à partir des noms.
     * */
    public void initialiser(Joueur joueur){

        if (!joueur.m_estInitialise) {
            ArrayList<String> noms;
            if(joueur.m_commenceLaPartie){
                noms = genererNomsSansDoublons(m_pokemonList,20); //20 si il commence
            }else{
                noms = genererNomsSansDoublons(m_pokemonList,21); //21 sinon
            }
            // On attribue les cartes et les sorts
            for (String nom : noms) {
                int type = new Random().nextInt(4);
                int srt = new Random().nextInt(2);
                switch (type) {
                    case 0:
                        if(srt == 0 || m_lstSort.isEmpty()){
                            joueur.m_lstPioche.add(new Eau(nom));
                        }else{
                            joueur.m_lstPioche.add(new Eau(nom, m_lstSort.getLast()));
                            m_lstSort.removeLast();
                        }
                        break;
                    case 1:
                        if(srt == 0 || m_lstSort.isEmpty()){
                            joueur.m_lstPioche.add(new Feu(nom));
                        }else{
                            joueur.m_lstPioche.add(new Feu(nom, m_lstSort.getLast()));
                            m_lstSort.removeLast();
                        }
                        break;
                    case 2:
                        if(srt == 0 || m_lstSort.isEmpty()){
                            joueur.m_lstPioche.add(new Terre(nom));
                        }else{
                            joueur.m_lstPioche.add(new Terre(nom, m_lstSort.getLast()));
                            m_lstSort.removeLast();

                        }
                        break;
                    case 3:
                        if(srt == 0 || m_lstSort.isEmpty()){
                            joueur.m_lstPioche.add(new Air(nom));
                        }else{
                            joueur.m_lstPioche.add(new Air(nom, m_lstSort.getLast()));
                            m_lstSort.removeLast();
                        }
                        break;
                }

            }
            for(int i =0; i<5;i++){
                joueur.m_lstMain.add(joueur.m_lstPioche.getLast());
                joueur.m_lstPioche.remove(joueur.m_lstPioche.getLast());
            }
            joueur.m_estInitialise = true;
        }else{
            System.out.println("Ce joueur a déjà été initialisé !");
        }
    }

    /**
     * Fonction qui permet de generer une liste de noms d'Entite sans doublons
     * @param pokemonList : Liste de pokemon;
     * @param nbNoms : Nombre de noms qu'il faut generer
     * @return ArrayList<String> : La liste sans doublons
     * */
    public ArrayList<String> genererNomsSansDoublons(ArrayList<String> pokemonList, int nbNoms) {
        ArrayList<String> resultat = new ArrayList<String>();
        Random random = new Random();

        // Générer nbNoms noms aléatoires sans doublons
        while (resultat.size() < nbNoms) {
            String nom = pokemonList.get(random.nextInt(pokemonList.size()));
            if (!resultat.contains(nom)) {
                resultat.add(nom);
            }
        }

        return resultat;
    }

    /**
     * Cette méthode permet de definir quel joueur commence la Partie
     *
     * @param autreJoueur le joueur adversaire
     */
    public void commence(Joueur j1, Joueur autreJoueur){
        ArrayList<Joueur> recup = new ArrayList<Joueur>();
        recup.add(j1);
        recup.add(autreJoueur);

        Random rand = new Random();
        recup.get(rand.nextInt(2)).m_commenceLaPartie = true;
    }

    /**
     * Fonction qui permet de savoir si le Joueur a perdu (si sa main est vide, pioche vide et terrain vide)
     * @return Boolean : True si il a perdu, false sinon
     * */
    public boolean aPerdu(Joueur joueur) {
        boolean mainVide = joueur.m_lstMain.isEmpty(); // Si la main est vide
        boolean piocheVide = joueur.m_lstPioche.isEmpty(); // Si la pioche est vide
        boolean terrainVide = joueur.m_lstTerrain.get(0) == null &&
                joueur.m_lstTerrain.get(1) == null &&
                joueur.m_lstTerrain.get(2) == null; // Si les 3 emplacements sont vides

        return mainVide && piocheVide && terrainVide; // Si tout est vide, le joueur a perdu
    }
}
