package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Reine pour les pieces de type Reine
 * 
 * @author Loic
 *
 */
public class Reine extends Piece {

    /**
     * Constructeur de la class
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Reine (Couleur color) {
    	super("Reine", color);
    }

    @Override
    public boolean deplacementEstValide(Deplacement dep) {
    	return ((dep.getDeplacementX() * dep.getDeplacementY() == 0) || (Math.abs(dep.getDeplacementX()) - Math.abs(dep.getDeplacementY()) == 0)) && !dep.deplacementNull();
    }

}
