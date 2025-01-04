import Pokemon.Plateau;

/***
 *     ▄▄▄·            ▄ •▄ ▄▄▄ .• ▌ ▄ ·.        ▐ ▄
 *    ▐█ ▄█▪     ▪     █▌▄▌▪▀▄.▀··██ ▐███▪▪     •█▌▐█
 *     ██▀· ▄█▀▄  ▄█▀▄ ▐▀▀▄·▐▀▀▪▄▐█ ▌▐▌▐█· ▄█▀▄ ▐█▐▐▌
 *    ▐█▪·•▐█▌.▐▌▐█▌.▐▌▐█.█▌▐█▄▄▌██ ██▌▐█▌▐█▌.▐▌██▐█▌
 *    .▀    ▀█▄▀▪ ▀█▄▀▪·▀  ▀ ▀▀▀ ▀▀  █▪▀▀▀ ▀█▄▀▪▀▀ █▪
 */


public class Pokemon
{
  public static void main(String args[])
  {
    try{
      
      Plateau p = new Plateau();
      p.game(); //Lance le jeu
      
    }catch(Exception ex){
      
      System.out.println("###########################################");
      System.out.println("#                  ERROR                  #");
      System.out.println("###########################################");
      System.out.println(ex.getMessage()+" "+ ex.getCause());
 
    }
  }
}
