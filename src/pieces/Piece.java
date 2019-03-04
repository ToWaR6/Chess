package pieces;

import java.util.ArrayList;

import enums.Couleur;
import plateau.Deplacement;
import plateau.Position;

/**
 * Classe abstraite Piece
 * 
 * @author Loic
 *
 */
public abstract class Piece {

	/**
	 * Nom de la piece
	 */
	private String nom;
	
	/**
	 * Couleur de la piece
	 */
	private Couleur couleur;
	
	/**
	 * Liste des positions ou la piece pourra bouger
	 */
	private ArrayList<Position> positionMenace;
	
	/**
	 * Constructeur de la classe
	 * 
	 * @param name Nom de la piece, en fonction de sa classe
	 * @param color Couleur de la piece, Blanc ou Noir
	 */
	public Piece (String name, Couleur color) {
		this.setNom(name);
		this.setCouleur(color);
		this.positionMenace = new ArrayList<Position>();
	}
	
	/**
	 * Constructeur par recopie
	 * 
	 * @param p Piece a copier
	 */
	public Piece (Piece p) {
		this.setNom(p.getNom());
		this.setCouleur(p.getCouleur());
		this.positionMenace = new ArrayList<Position>();
	}
	
	/**
	 * Getteur du nom
	 * 
	 * @return Nom de la piece
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Setteur du nom
	 * 
	 * @param name Nouveau nom de la piece
	 */
	public void setNom(String name) {
		this.nom = name;
	}
	
	/**
	 * Getteur de la couleur
	 * 
	 * @return Couleur de la piece
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Getteur de la couleur inverse
	 * 
	 * @return Blanc si la piece est Noire, et Noir si la piece est Blanche
	 */
	public Couleur getCouleurInverse() {
		return Couleur.getCouleurInverse(this.couleur);
	}
	
	/**
	 * Setteur de la couleur
	 * 
	 * @param color Nouvelle couleur de la piece
	 */
	public void setCouleur(Couleur color) {
		this.couleur = color;
	}

	/**
	 * Getteur des positions menacees
	 * 
	 * @return Liste des positions Menacees (vide tant que "echiquier . setPositionMenace (Position p)" n'a pas ete lance) 
	 */
	public ArrayList<Position> getPositionMenace() {
		return this.positionMenace;
	}
	
	/**
	 * Ajout de position a la liste des positions menacees
	 * 
	 * @param p Position a ajouter
	 */
	public void addPositionMenace (Position p) {
		this.positionMenace.add(p); 
	}
	
	/**
	 * Determine si le deplacement en parametre est valide en fonction de la classe de la piece
	 * 
	 * @param dep Deplacement a verifier
	 * @return true si le deplacement peut etre fait, false sinon
	 */
	public abstract boolean deplacementEstValide (Deplacement dep);
	
}
