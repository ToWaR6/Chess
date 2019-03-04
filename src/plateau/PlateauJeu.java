package plateau;

/**
 * Classe PlateauJeu permettant de creer des plateaux de jeu pour les echecs, les dames ou tout autre jeu de plateau
 * 
 * @author Loic
 *
 */
public abstract class PlateauJeu {

    /**
     * Tableau de Case a deux dimension formant le plateau du jeu 
     */
    protected Case[][] plateau;
    
    /**
     * Nombre de colonne du plateau de jeu
     */
    protected int colonne;
    
    /**
     * Nombre de ligne du plateau de jeu
     */
    protected int ligne;

    /**
     * Constructeur de la classe
     * 
     * @param col Nombre de colonne du plateau de jeu
     * @param li Nombre de ligne du plateau de jeu
     */
    public PlateauJeu (int col, int li) {
		this.colonne = col;
		this.ligne = li;
		plateau = new Case[this.colonne][this.ligne];
		for (int i = 0; i <= 7; i++) {
		    for (int j = 0; j <= 7; j++) {
			plateau[i][j] = new Case();
		    }
		}
    }

    /**
	 * Getteur d'une case du plateau
	 * 
	 * @param colonne Colonne de la case a recuperer
	 * @param ligne   Ligne de la case a recuperer
	 * @return Case a la colonne et a la ligne specifie dans les parametres
	 */
    public Case getCase(double colonne, double ligne) {
    	return plateau[(int)colonne][(int)ligne];
    }

    /**
     * Verifie si la position en parametre ne sort pas plateau de jeu
     * 
     * @param p Position a verifie
     * @return true si la position est dans le plateau, false sinon
     */
    public boolean isInBoard (Position p) {
    	return p.getColonne() >= 0 && p.getColonne() < this.colonne && p.getLigne() >= 0 && p.getLigne() < this.ligne;
    }

    /**
     * Classe permettant de mettre en place les pieces du plateau a leurs emplacements respectifs
     */
    public abstract void misEnPlace();
    
    /**
     * Verifie si le deplacement est possible en fonction de la position des autres pieces 
     * 
     * @param dep Deplacement a verifier
     * @return true si le deplacement est possible, false sinon
     */
    public abstract boolean cheminPossible(Deplacement dep);
}