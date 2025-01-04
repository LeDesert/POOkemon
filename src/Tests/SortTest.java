package Tests;

import Pokemon.Entite.*;
import Pokemon.Joueur.Humain;
import Pokemon.Joueur.Joueur;
import Pokemon.Joueur.Ordinateur;
import Pokemon.Sort.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Classe de test pour les différents sorts dans le jeu Pokémon.
 */
class SortTests {
    private static Sort m_sortTestSoinZone;
    private static Sort m_sortTestConfusion;
    private static Sort m_sortTestFerveurGuerriere;
    private static Sort m_sortTestKamikaze;
    private static Sort m_sortTestSoinPermanent;
    private static Sort m_sortTestSoinSimple;
    private static Sort m_sortTestSoinTotal;
    private  static Sort m_sortTestPeur;
    private static Joueur m_joueur1;
    private static Joueur m_joueur2;

    @BeforeEach
    /**
     * Initialise les sorts et les joueurs avant chaque test.
     */
    void setUp() {
        m_sortTestSoinZone = new SoinDeZone();
        m_sortTestConfusion = new Confusion();
        m_sortTestFerveurGuerriere = new FerveurGuerriere();
        m_sortTestKamikaze = new Kamikaze();
        m_sortTestSoinPermanent = new SoinPermanent();
        m_sortTestSoinSimple = new SoinSimple();
        m_sortTestSoinTotal = new SoinTotal();
        m_sortTestPeur  = new Peur();
        m_joueur1 = new Humain(true);
        m_joueur2 = new Ordinateur(false);
    }
    /**
     * Teste le sort SoinDeZone.
     */
    @Test
    void testSoinDeZone() {
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestSoinZone));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur1.getM_lstTerrain().add(new Terre("Bulbasaur"));
        m_joueur1.getM_lstTerrain().get(0).setM_pointDeVie(50);
        m_joueur1.getM_lstTerrain().get(1).setM_pointDeVie(20);
        m_joueur1.getM_lstTerrain().get(2).setM_pointDeVie(10);

        m_joueur2.getM_lstTerrain().add(new Air("Pidgey", m_sortTestSoinZone));
        m_joueur2.getM_lstTerrain().add(new Eau("Magikarp"));
        m_joueur2.getM_lstTerrain().add(new Terre("Geodude"));
        m_joueur2.getM_lstTerrain().get(0).setM_pointDeVie(30);
        m_joueur2.getM_lstTerrain().get(1).setM_pointDeVie(60);
        m_joueur2.getM_lstTerrain().get(2).setM_pointDeVie(80);

        m_sortTestSoinZone.action(m_joueur1, m_joueur2, -1);

        assertEquals(60, m_joueur1.getM_lstTerrain().get(0).getM_pointDeVie());
        assertEquals(30, m_joueur1.getM_lstTerrain().get(1).getM_pointDeVie());
        assertEquals(20, m_joueur1.getM_lstTerrain().get(2).getM_pointDeVie());

        // Vérifiez que les points de vie des Pokémon du joueur 2 n'ont pas changé
        assertEquals(30, m_joueur2.getM_lstTerrain().get(0).getM_pointDeVie());
        assertEquals(60, m_joueur2.getM_lstTerrain().get(1).getM_pointDeVie());
        assertEquals(80, m_joueur2.getM_lstTerrain().get(2).getM_pointDeVie());
    }
    /**
     * Teste le sort de Confusion AVEC la main pleine.
     */
    @Test
    void testConfusionMainPleine() {
        m_joueur2.getM_lstMain().add(new Eau("Squirtle"));
        m_joueur2.getM_lstMain().add(new Feu("Charmander"));
        m_sortTestConfusion.action(m_joueur1, m_joueur2, 0);

        assertEquals(0, m_joueur2.sizeSansNulls(m_joueur2.getM_lstMain()));
        assertEquals(2, m_joueur2.getM_lstPioche().size());
    }
    /**
     * Teste le sort de Confusion AVEC la main vide.
     */
    @Test
    void testConfusionMainVide() {
        m_joueur2.clearM_lstMain();

        m_sortTestConfusion.action(m_joueur1, m_joueur2, 0);

        assertEquals(0, m_joueur2.getM_lstMain().size());
        assertEquals(0, m_joueur2.getM_lstPioche().size()); // 5 Pokémons
    }
    /**
     * Teste le sort de FerveurGuerriere.
     */
    @Test
    void testFerveurGuerriere() {
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestFerveurGuerriere));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur1.getM_lstTerrain().add(new Terre("Bulbasaur"));

        int choixPokemon = 2;
        int ancienforceAttaque = m_joueur1.getM_lstTerrain().get(choixPokemon).getM_forceAttaque();
        m_sortTestFerveurGuerriere.action(m_joueur1, m_joueur2, choixPokemon);

        assertEquals(ancienforceAttaque+10, m_joueur1.getM_lstTerrain().get(choixPokemon).getM_forceAttaque());

        assertEquals("ferveur guerrière", m_sortTestFerveurGuerriere.getNom());
    }
    /**
     * Teste le sort de KAMIKAZEEEE.
     */
    @Test
    void testKamikaze(){
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestKamikaze));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur2.getM_lstTerrain().add(new Terre("Bulbasaur"));


        m_joueur1.setM_PokemonUtiliseSort(m_joueur1.getM_lstTerrain().getFirst());

        int choixDuPokemonVisee = 0;
        m_joueur1.getM_lstTerrain().get(0).getM_sort().action(m_joueur1, m_joueur2, choixDuPokemonVisee);


        assertEquals(-1000, m_joueur1.getM_lstTerrain().getFirst().getM_pointDeVie());
        assertEquals(-1000, m_joueur1.getM_lstTerrain().getFirst().getM_pointDeVie());
    }
    /**
     * Teste le sort de soin permanent.
     */
    @Test
    void testSoinPermanent(){
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestSoinPermanent));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur2.getM_lstTerrain().add(new Terre("Bulbasaur"));

        int choixDuPokemon = 0;

        m_joueur1.getM_lstTerrain().get(choixDuPokemon).setM_pointDeVie(30);

        m_sortTestSoinPermanent.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(40, m_joueur1.getM_lstTerrain().get(choixDuPokemon).getM_pointDeVie());

        m_sortTestSoinPermanent.action(m_joueur1, m_joueur2, choixDuPokemon);
        assertEquals(50, m_joueur1.getM_lstTerrain().get(choixDuPokemon).getM_pointDeVie());

        m_sortTestSoinPermanent.action(m_joueur1, m_joueur2, choixDuPokemon);
        assertEquals(60, m_joueur1.getM_lstTerrain().get(choixDuPokemon).getM_pointDeVie());

        for (int i = 0; i < 20; i++) {
            m_sortTestSoinPermanent.action(m_joueur1, m_joueur2, choixDuPokemon);
        }
        assertEquals(m_joueur1.getM_lstTerrain().get(choixDuPokemon).getM_pointDeVieMax(), m_joueur1.getM_lstTerrain().get(choixDuPokemon).getM_pointDeVie());
    }

    /**
     * Teste le sort de soin simple.
     */
    @Test
    void testSoinSimple() {
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestSoinSimple));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur1.getM_lstTerrain().add(new Terre("Bulbasaur"));

        m_joueur1.getM_lstTerrain().get(0).setM_pointDeVie(10);
        m_joueur1.getM_lstTerrain().get(1).setM_pointDeVie(50);
        m_joueur1.getM_lstTerrain().get(2).setM_pointDeVie(70);
        int choixDuPokemon = 0;
        m_sortTestSoinSimple.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(40, m_joueur1.getM_lstTerrain().get(0).getM_pointDeVie());

        choixDuPokemon = 1;
        m_sortTestSoinSimple.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(80, m_joueur1.getM_lstTerrain().get(1).getM_pointDeVie());

        choixDuPokemon = 2;
        m_sortTestSoinSimple.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(100, m_joueur1.getM_lstTerrain().get(2).getM_pointDeVie());
    }

    /**
     * Teste le sort de soin TOTALL.
     */
    @Test
    void testSoinTotal() {
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestSoinTotal));
        m_joueur1.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur1.getM_lstTerrain().add(new Terre("Bulbasaur"));

        m_joueur1.getM_lstTerrain().get(0).setM_pointDeVie(10);
        m_joueur1.getM_lstTerrain().get(1).setM_pointDeVie(50);
        m_joueur1.getM_lstTerrain().get(2).setM_pointDeVie(70);
        int choixDuPokemon = 0;
        m_sortTestSoinTotal.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(m_joueur1.getM_lstTerrain().get(0).getM_pointDeVieMax(), m_joueur1.getM_lstTerrain().get(0).getM_pointDeVie());

        choixDuPokemon = 1;
        m_sortTestSoinTotal.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(m_joueur1.getM_lstTerrain().get(1).getM_pointDeVieMax(), m_joueur1.getM_lstTerrain().get(1).getM_pointDeVie());

        choixDuPokemon = 2;
        m_sortTestSoinTotal.action(m_joueur1, m_joueur2, choixDuPokemon);

        assertEquals(m_joueur1.getM_lstTerrain().get(2).getM_pointDeVieMax(), m_joueur1.getM_lstTerrain().get(2).getM_pointDeVie());

        assertEquals(true, m_sortTestSoinTotal.getAEteUtilise());
    }

    /**
     * Teste le sort de Peur.
     */
    @Test
    void testPeur() {
        m_joueur1.getM_lstTerrain().add(new Eau("Squirtle", m_sortTestPeur));
        m_joueur2.getM_lstTerrain().add(new Feu("Charmander"));
        m_joueur2.getM_lstTerrain().add(new Terre("Bulbasaur"));

        int choixPokemon = 1;
        int ancienforceAttaque = m_joueur2.getM_lstTerrain().get(choixPokemon).getM_forceAttaque();
        m_sortTestPeur.action(m_joueur1, m_joueur2, choixPokemon);

        assertEquals(ancienforceAttaque-10, m_joueur2.getM_lstTerrain().get(choixPokemon).getM_forceAttaque());

        assertEquals("Peur", m_sortTestPeur.getNom());
    }
}
