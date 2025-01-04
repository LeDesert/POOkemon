package Pokemon.Entite;

import Pokemon.Sort.Sort;

import java.util.Random;

public class Air extends Entite{
        private final Enum<Type> m_affiniteForce = Type.TERRE;
        private final Enum<Type> m_affiniteFaiblesse = Type.FEU;

        /**
         * @param nom : nom du pokemon
         * @param pointDeVieMax : point de vie maximum du pokemon
         * @param forceAttaque : force d'attaque du pokemon
         * constructeur qui permet de creer un pokemon de type Air avec un nom, des points de vie et une force d'attaque donnée
         */
        public Air(String nom, int pointDeVieMax, int forceAttaque){
            this.m_nom = nom;
            this.m_pointDeVieMax = pointDeVieMax;
            this.m_forceAttaque = forceAttaque;
            this.m_pointDeVie = m_pointDeVieMax;
        }

        /**
         * @param nom : nom du pokemon
         * @param pointDeVieMax : point de vie maximum du pokemon
         * constructeur qui permet de creer un pokemon de type Air avec un nom, des points de vie donnée et une force d'attaque aléatoire
         */
        public Air(String nom, int pointDeVieMax){
            Random ran = new Random();
            this.m_nom = nom;
            this.m_pointDeVieMax = pointDeVieMax;
            // Permet d'avoir un chiffre entre 10 et 40 inclus étant un multiple de 10
            this.m_forceAttaque = 10 + 10 * ran.nextInt(4);
            this.m_pointDeVie = m_pointDeVieMax;
        }

        /**
         * @param nom : nom du pokemon
         * @param sort : sort du pokemon
         * constructeur qui permet de creer un pokemon de type Air avec un nom et un sort donnée et des points de vie et une force d'attaque aléatoire
         */
        public Air(String nom, Sort sort){
            Random ran = new Random();
            this.m_nom = nom;
            // Permet d'avoir un chiffre entre 100 et 200 inclus étant un multiple de 10
            this.m_pointDeVieMax = 100 + 10 * ran.nextInt(11);
            // Permet d'avoir un chiffre entre 10 et 40 inclus étant un multiple de 10
            this.m_forceAttaque = 10 + 10 * ran.nextInt(4);
            this.m_pointDeVie = m_pointDeVieMax;
            this.m_sort = sort;
        }

        /**
         * @param nom : nom du pokemon
         * constructeur qui permet de creer un pokemon de type Air avec un nom donnée et des points de vie et une force d'attaque aléatoire
         */
        public Air(String nom){
            Random ran = new Random();
            this.m_nom = nom;
            // Permet d'avoir un chiffre entre 100 et 200 inclus étant un multiple de 10
            this.m_pointDeVieMax = 100 + 10 * ran.nextInt(11);
            // Permet d'avoir un chiffre entre 10 et 40 inclus étant un multiple de 10
            this.m_forceAttaque = 10 + 10 * ran.nextInt(4);
            this.m_pointDeVie = m_pointDeVieMax;
            this.m_sort=null;
        }
        
        public Enum<Type> getAffinite() {
            return Type.AIR;
        }

        public Enum<Type> getM_affiniteForce(){
            return m_affiniteForce;
        }

        public Enum<Type> getM_affiniteFaiblesse(){
            return m_affiniteFaiblesse;
        }
        
        /**
         * Permet d'attaquer un autre pokémon en fonction de l'affinite du pokemon vise (Force et Faiblesse)
         * @param pokemon : est le pokemon vise par l'attaque de type
         * @return boolean : renvoie true si l'attaque a pu se faire, false sinon
         * **/
        public boolean attaque(Entite pokemon) {
            boolean resultatAttaque = false;
            if(pokemon.getAffinite().equals(m_affiniteForce)){ 
                pokemon.m_pointDeVie = pokemon.m_pointDeVie - (this.m_forceAttaque +  10);
                System.out.println("Attaque très efficace sur "+ pokemon.getM_nom() +" ! Infligeant "+(this.m_forceAttaque+10) + " dégats\n");
                resultatAttaque = true;
            } else if (pokemon.getAffinite().equals(this.m_affiniteFaiblesse)){
                pokemon.m_pointDeVie = pokemon.m_pointDeVie - (this.m_forceAttaque - 10);
                System.out.println("Attaque faible sur "+ pokemon.getM_nom() +"! : "+(this.m_forceAttaque-10) + " dégats\n(Tips : essayez d'attaquer des pokemons sur lequels vous avez un ascendant)\n(Exemple : Eau sur Feu)\n");
                resultatAttaque = true;
            }else{
                pokemon.m_pointDeVie = pokemon.m_pointDeVie - this.m_forceAttaque;
                System.out.println("Attaque classique sur "+pokemon.getM_nom()+" ! : "+(this.m_forceAttaque) + " dégats\n");
                resultatAttaque = true;
            }
            return resultatAttaque;
        }
}
