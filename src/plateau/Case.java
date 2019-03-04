package plateau;
import enums.Couleur;
import pieces.Piece;

/**
 * Classe Case qui formera l'echiquier et qui permettra de stocker les pieces
 * 
 * @author Loic
 *
 */
public class Case {

    /**
     * Piece que stocke la case (null si la case est vide)
     */
    private Piece piece;

    /**
     * Constructeur de la classe
     * Cree une case vide (this.piece = null)
     */
    public Case() {
    	this.piece = null;
    }

    /**
     * Constructeur de la classe
     * Cree une case non-vide (contenant une piece)
     * 
     * @param piece Piece stocker par la case 
     */
    public Case (Piece piece) {
    	this.setPiece(piece);
    }

    /**
     * Constructeur par recopie
     * 
     * @param c Case a recopier
     */
    public Case (Case c) {
    	this.setPiece(c.getPiece());
    }

    /**
     * Getteur de la piece
     * 
     * @return Piece stockee par la case, null si la case est vide
     */
    public Piece getPiece() {
    	return piece;
    }

    /**
     * Setteur de la piece
     * 
     * @param piece Piece a stocker dans la case
     */
    public void setPiece(Piece piece) {
    	this.piece = piece;
    }

    /**
     * Verifie si la case est occupe
     * 
     * @return true si la case est occupe, false si la case est vide
     */
    public boolean estOccupe() {
    	return this.piece != null;
    }

    /**
     * Verifie si la case est occupe par une piece d'un certaine couleur
     * 
     * @param color Couleur a verifier
     * @return true si la case est occupe par une piece de la couleur en parametre, false sinon
     */
    public boolean estOccupe(Couleur color) {
		if (!this.estOccupe()) {
		    return false;
		}
		return this.piece.getCouleur().equals(color);
    }

}
