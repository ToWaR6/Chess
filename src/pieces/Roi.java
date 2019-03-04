package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Roi pour les pieces de type Roi
 * 
 * @author Loic
 *
 */
public class Roi extends Piece{

    /**
     * Constructeur de la classe
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Roi (Couleur color) {
		super("Roi", color);
    }

    @Override
    public boolean deplacementEstValide(Deplacement dep) {
    	return ((Math.abs(dep.getDeplacementX()) <= 1) && (Math.abs(dep.getDeplacementY()) <= 1)) && !dep.deplacementNull(); 
    }

}
