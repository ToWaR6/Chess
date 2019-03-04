package enums;

/**
 * Classe enum Couleur ne contenant que le Blanc et le Noir
 * 
 * @author Loic
 *
 */
public enum Couleur {
    BLANC,
    NOIR;

    /**
     * Methode permettant de d'avoir la couleur inverse a la couleur en parametre
     * 
     * @param c Couleur
     * @return Couleur inverse
     */
    public static Couleur getCouleurInverse(Couleur c) {
    	return c.equals(BLANC) ? Couleur.NOIR : Couleur.BLANC;
    }
}
