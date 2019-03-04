package plateau;

/**
 * Classe Deplacement regroupant deux positions, une de depart et une d'arrive
 * 
 * @author Loic
 *
 */
public class Deplacement {

    /**
     * Position de depart
     */
    private Position depart;
    
    /**
     * Position d'arrive
     */
    private Position arrivee;

    /**
     * Constructeur de la classe
     * 
     * @param depart Position de depart
     * @param arrivee Position d'arrive
     */
    public Deplacement (Position depart, Position arrivee) {
		this.setDepart(depart);
		this.setArrivee(arrivee);
    }

    /**
     * Getteur de la position de depart
     * 
     * @return Position de depart
     */
    public Position getDepart() {
    	return depart;
    }

    /**
     * Setteur de la position de depart
     * 
     * @param depart Nouvelle position de depart
     */
    public void setDepart(Position depart) {
    	this.depart = depart;
    }

    /**
     * Getteur de la position d'arrive
     * 
     * @return Position d'arrive
     */
    public Position getArrivee() {
    	return arrivee;
    }

    /**
     * Setteur de la position d'arrive
     * 
     * @param arrivee Nouvelle position d'arrive
     */
    public void setArrivee(Position arrivee) {
    	this.arrivee = arrivee;
    }

    /**
     * Getteur du deplacement de colonne entre la position depart et la position d'arrive
     * 
     * @return Difference entre la colonne d'arrive et la colonne de depart
     */
    public double getDeplacementX() {
    	return this.arrivee.getColonne() - this.depart.getColonne();
    }

    /**
     * Getteur du deplacement de ligne entre la position depart et la position d'arrive
     * 
     * @return Difference entre la ligne d'arrive et la ligne de depart
     */
    public double getDeplacementY() {
    	return this.arrivee.getLigne() - this.depart.getLigne();
    }

    /**
     * Verifie si le deplacement est nul
     * 
     * @return true si le deplacement de colonne est 0 et que le deplacement de ligne est 0, false si un des deplacement n'est egal a 0
     */
    public boolean deplacementNull() {
    	return this.getDeplacementX() == 0 && this.getDeplacementY() == 0;
    }
}
