package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Pion pour les pieces de type Pion
 * 
 * @author Loic
 *
 */
public class Pion extends Piece {

    /**
     * Constructeur de la classe
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Pion (Couleur color) {
    	super("Pion", color);
    }

    @Override
    public boolean deplacementEstValide (Deplacement dep) {	
		if (dep.getDeplacementX() == 0 && dep.getDeplacementY() != 0) {
		    if (this.getCouleur().equals(Couleur.BLANC)) {
			return (dep.getDepart().getLigne() == 6) ? (dep.getDeplacementY() >= -2) : (dep.getDeplacementY() >= -1);
		    }
		    return (dep.getDepart().getLigne() == 1) ? (dep.getDeplacementY() <= 2) : (dep.getDeplacementY() <= 1);
		}
		return false;
    }
}
