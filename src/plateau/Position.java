package plateau;
/**
 * Classe Position permettant d'avoir une position dans un tableau a deux dimensions
 * 
 * @author Loic
 *
 */
public class Position {

    /**
     * Colonne du tableau
     */
    private int colonne;
    
    /**
     * Ligne du tableau
     */
    private int ligne;

    /**
     * Constructeur de la classe
     * 
     * @param colonne Colonne du tableau
     * @param ligne Ligne du tableau
     */
    public Position (int colonne, int ligne) {
		this.setColonne(colonne);
		this.setLigne(ligne);
    }

    /**
     * Getteur de la colonne
     * 
     * @return Colonne du tableau
     */
    public int getColonne() {
    	return colonne;
    }

    /**
     * Setteur de la colonne
     * 
     * @param x Colonne du tableau
     */
    public void setColonne(int x) {
    	this.colonne = x;
    }

    /**
     * Getteur de la ligne
     * 
     * @return Ligne du tableau
     */
    public int getLigne() {
    	return ligne;
    }

    /**
     * Setteur de la ligne
     * 
     * @param y Ligne du tableau
     */
    public void setLigne(int y) {
    	this.ligne = y;
    }

    /**
     * Reecriture de la methode equals
     * 
     * @param pos Position a verifier
     * @return true si la position this et la position en parametre sont egales, false sinon
     */
    public boolean equals (Position pos) {
    	return this.colonne == pos.getColonne() && this.ligne == pos.getLigne();
    }

}
