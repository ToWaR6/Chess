package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Fou pour les pieces de type Fou
 * 
 * @author Loic
 *
 */
public class Fou extends Piece {

    /**
     * Constructeur de la classe
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Fou (Couleur color) {
    	super("Fou", color);
    }

    @Override
    public boolean deplacementEstValide(Deplacement dep) {
    	return Math.abs(dep.getDeplacementX()) == Math.abs(dep.getDeplacementY()) && !dep.deplacementNull(); 
    }


}
