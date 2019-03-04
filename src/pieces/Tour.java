package pieces;
import enums.Couleur;
import plateau.Deplacement;

/**
 * Classe Tour pour les pieces de type Tour
 * 
 * @author Loic
 *
 */
public class Tour extends Piece {

    /**
     * Constructeur de la classe
     * 
     * @param color Couleur de la piece, Blanc ou Noir
     */
    public Tour (Couleur color) {
    	super("Tour", color);
    }

    @Override
    public boolean deplacementEstValide(Deplacement dep) {
    	return (dep.getDeplacementX() * dep.getDeplacementY() == 0) && !dep.deplacementNull();
    }


}
