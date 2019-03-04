package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Cavalier pour les pieces de type Cavalier
 * 
 * @author Loic
 *
 */
public class Cavalier extends Piece {

    /**
     * Constructeur de la classe
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Cavalier(Couleur color) {
    	super("Cavalier", color);
    }

    @Override
    public boolean deplacementEstValide(Deplacement dep) {
    	return (Math.abs(dep.getDeplacementX()) / Math.abs(dep.getDeplacementY()) == 2) || (Math.abs(dep.getDeplacementX()) / Math.abs(dep.getDeplacementY()) == 0.5); 
    }


}
