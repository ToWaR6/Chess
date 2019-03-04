package plateau;
import enums.Couleur;
import pieces.Cavalier;
import pieces.Fou;
import pieces.Piece;
import pieces.Pion;
import pieces.Reine;
import pieces.Roi;
import pieces.Tour;

public class Echiquier extends PlateauJeu {

    public Echiquier() {
    	super(8, 8);
    }

    public void misEnPlace() {
		for (int i=0; i<8; i++) {
		    super.plateau[i][1].setPiece(new Pion(Couleur.NOIR));
		    super.plateau[i][6].setPiece(new Pion(Couleur.BLANC));
		}
		super.plateau[0][0].setPiece(new Tour(Couleur.NOIR));
		super.plateau[0][7].setPiece(new Tour(Couleur.BLANC));
		super.plateau[1][0].setPiece(new Cavalier(Couleur.NOIR));
		super.plateau[1][7].setPiece(new Cavalier(Couleur.BLANC));
		super.plateau[2][0].setPiece(new Fou(Couleur.NOIR));
		super.plateau[2][7].setPiece(new Fou(Couleur.BLANC));
		super.plateau[3][0].setPiece(new Reine(Couleur.NOIR));
		super.plateau[3][7].setPiece(new Reine(Couleur.BLANC));
		super.plateau[4][0].setPiece(new Roi(Couleur.NOIR));
		super.plateau[4][7].setPiece(new Roi(Couleur.BLANC));
		super.plateau[5][0].setPiece(new Fou(Couleur.NOIR));
		super.plateau[5][7].setPiece(new Fou(Couleur.BLANC));
		super.plateau[6][0].setPiece(new Cavalier(Couleur.NOIR));
		super.plateau[6][7].setPiece(new Cavalier(Couleur.BLANC));
		super.plateau[7][0].setPiece(new Tour(Couleur.NOIR));
		super.plateau[7][7].setPiece(new Tour(Couleur.BLANC));
    }

    public Case getCase (Position p) {
    	return this.getCase(p.getColonne(), p.getLigne());
    }

    public boolean captureParPion(Deplacement dep) {
		Case depart = this.getCase(dep.getDepart().getColonne(), dep.getDepart().getLigne());
		Case arrivee = this.getCase(dep.getArrivee().getColonne(), dep.getArrivee().getLigne());
	
		if (depart.getPiece() instanceof Pion) {
		    if (depart.getPiece().getCouleur().equals(Couleur.BLANC)) {
			return arrivee.estOccupe(Couleur.NOIR) && dep.getDeplacementY() == -1 && Math.abs(dep.getDeplacementX()) * Math.abs(dep.getDeplacementY()) == 1;
		    }
		    return arrivee.estOccupe(Couleur.BLANC) && dep.getDeplacementY() == 1 && Math.abs(dep.getDeplacementX()) * Math.abs(dep.getDeplacementY()) == 1;
		}
		return false;
    }
    
    public boolean cheminPossible(Deplacement dep) {
		Case depart = this.getCase(dep.getDepart().getColonne(), dep.getDepart().getLigne());
		Case arrivee = this.getCase(dep.getArrivee().getColonne(), dep.getArrivee().getLigne());
	
		if (arrivee.estOccupe(depart.getPiece().getCouleur()) || dep.deplacementNull()) {
		    return false;
		}
		if (!(depart.getPiece() instanceof Cavalier)) {
	
		    if (!(depart.getPiece() instanceof Pion)) {
	
			if (Math.abs(dep.getDeplacementX()) > 1 || Math.abs(dep.getDeplacementY()) > 1) {
			    int pasX;
			    int pasY;
	
			    if (dep.getDeplacementX() == 0) {
				pasX = 0;
			    } else {
				pasX = dep.getDeplacementX() > 0 ? 1 : -1;
			    }
			    if (dep.getDeplacementY() == 0) {
				pasY = 0;
			    } else {
				pasY = dep.getDeplacementY() > 0 ? 1 : -1;
			    }
	
			    int cptX = dep.getDepart().getColonne() + pasX;
			    int cptY = dep.getDepart().getLigne() + pasY;
	
			    for (cptX = dep.getDepart().getColonne() + pasX, cptY = dep.getDepart().getLigne() + pasY; cptX != dep.getArrivee().getColonne() || cptY != dep.getArrivee().getLigne(); cptX += pasX, cptY += pasY) {
				if (this.getCase(cptX, cptY).estOccupe()) {
				    return false;
				}
			    }
			    return true;
			}
			return true;
		    }
		    return !arrivee.estOccupe();
		}
		return true;
    }
    
    public boolean transformPion(Deplacement dep) {
		Piece piece = this.getCase(dep.getDepart().getColonne(), dep.getDepart().getLigne()).getPiece(); 
		if (piece instanceof Pion) {
		    return piece.getCouleur().equals(Couleur.BLANC) ? dep.getArrivee().getLigne() == 0 : dep.getArrivee().getLigne() == 7; 
		}
		return false;
    }

    public void setCaseMenace(Position p) {
		Piece piece = this.plateau[p.getColonne()][p.getLigne()].getPiece();
		piece.getPositionMenace().clear();
	
		if (piece instanceof Pion) {
		    //			Case menacees par un pion
		    //Pion Blanc
		    if (piece.getCouleur().equals(Couleur.BLANC)) {
			if (p.getLigne() == 6) {
			    if (!this.plateau[p.getColonne()][p.getLigne()-1].estOccupe()) {
				piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()-1));
				if (!this.plateau[p.getColonne()][p.getLigne()-2].estOccupe()) {
				    piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()-2));
				}
			    }
			} else {
			    if (!this.plateau[p.getColonne()][p.getLigne()-1].estOccupe()) {
				piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()-1));	
			    }
			}
	
			Position pGauche = new Position(p.getColonne()-1, p.getLigne()-1);
			Position pDroite = new Position(p.getColonne()+1, p.getLigne()-1);
			if (this.isInBoard(pGauche)) {
			    Deplacement depGauche = new Deplacement(p, pGauche);
			    if (this.captureParPion(depGauche)) {
				piece.addPositionMenace(pGauche);
			    }
			}
			if (this.isInBoard(pDroite)) {
			    Deplacement depDroite = new Deplacement(p, pDroite);
			    if (this.captureParPion(depDroite)) {
				piece.addPositionMenace(pDroite);
			    }
			}
		    }
		    //Pion Noir
		    else {
			if (p.getLigne() == 1) {
			    if (!this.plateau[p.getColonne()][p.getLigne()+1].estOccupe()) {
				piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()+1));
				if (!this.plateau[p.getColonne()][p.getLigne()+2].estOccupe()) {
				    piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()+2));
				}
			    }
			} else {
			    if (!this.plateau[p.getColonne()][p.getLigne()+1].estOccupe()) {
				piece.addPositionMenace(new Position(p.getColonne(), p.getLigne()+1));	
			    }
			}
	
			Position pGauche = new Position(p.getColonne()-1, p.getLigne()+1);
			Position pDroite = new Position(p.getColonne()+1, p.getLigne()+1);
			if (this.isInBoard(pGauche)) {
			    Deplacement depGauche = new Deplacement(p, pGauche);
			    if (this.captureParPion(depGauche)) {
				piece.addPositionMenace(pGauche);
			    }
			}
			if (this.isInBoard(pDroite)) {
			    Deplacement depDroite = new Deplacement(p, pDroite);
			    if (this.captureParPion(depDroite)) {
				piece.addPositionMenace(pDroite);
			    }
			}
		    }
		} else if (piece instanceof Cavalier) {
		    //			Case menacees par un cavalier
		    int a = 1;
		    int b = 2;
		    for (int i=0; i<4; i++) {
			Position P = new Position(p.getColonne() + a, p.getLigne() + b);
			Position nonP = new Position(p.getColonne() + b, p.getLigne() + a);
			if (this.isInBoard(P) && !this.getCase(P).estOccupe(piece.getCouleur())) {
			    piece.addPositionMenace(P);
			}
			if (this.isInBoard(nonP) && !this.getCase(nonP).estOccupe(piece.getCouleur())) {
			    piece.addPositionMenace(nonP);
			}
			a *= -1;
			if (i%2 == 1) {
			    b *= -1;
			}
		    }
		} else if (piece instanceof Roi) {
		    //			Case menacees par un roi
		    for (int i=-1; i<2; i++) {
			Position p1 = new Position(p.getColonne() + i, p.getLigne() - 1);
			Position p2 = new Position(p.getColonne() + i, p.getLigne());
			Position p3 = new Position(p.getColonne() + i, p.getLigne() + 1);
	
			if (this.isInBoard(p1) && !this.getCase(p1).estOccupe(piece.getCouleur())) {
			    piece.addPositionMenace(p1);
			}
			if (this.isInBoard(p2) && !this.getCase(p2).estOccupe(piece.getCouleur())) {
			    piece.addPositionMenace(p2);
			}
			if (this.isInBoard(p3) && !this.getCase(p3).estOccupe(piece.getCouleur())) {
			    piece.addPositionMenace(p3);
			}
		    }
		} else if (piece instanceof Tour) { 
		    //			Case menacees par une tour
		    Position pos;
		    for (int i=p.getColonne()+1; i<8; i++) {
			pos = new Position(i, p.getLigne());
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getColonne()-1; i>-1; i--) {
			pos = new Position(i, p.getLigne());
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getLigne()+1; i<8; i++) {
			pos = new Position(p.getColonne(), i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getLigne()-1; i>-1; i--) {
			pos = new Position(p.getColonne(), i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		} else if (piece instanceof Fou) {
		    Position pos;
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() + i, p.getLigne() + i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() + i, p.getLigne() - i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() - i, p.getLigne() + i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() - i, p.getLigne() - i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }			
		} else if (piece instanceof Reine) {
		    //			Case menacees par un cavalier
	
		    //			mouvement du fou
		    Position pos;
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() + i, p.getLigne() + i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() + i, p.getLigne() - i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() - i, p.getLigne() + i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=1; i<8; i++) {
			pos = new Position(p.getColonne() - i, p.getLigne() - i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
	
		    //			mouvement de la tour
		    for (int i=p.getColonne()+1; i<8; i++) {
			pos = new Position(i, p.getLigne());
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getColonne()-1; i>-1; i--) {
			pos = new Position(i, p.getLigne());
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getLigne()+1; i<8; i++) {
			pos = new Position(p.getColonne(), i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		    for (int i=p.getLigne()-1; i>-1; i--) {
			pos = new Position(p.getColonne(), i);
			if (this.isInBoard(pos) && !this.getCase(pos).estOccupe()) {
			    piece.addPositionMenace(pos);
			} else if (this.isInBoard(pos) && this.getCase(pos).estOccupe(piece.getCouleurInverse())) {
			    piece.addPositionMenace(pos);
			    break;
			} else {
			    break;
			}
		    }
		}
    }

    /**
     * Verifie si la position est menace par la couleur inverse a celle en parametre
     * 
     * @param p position a verifie
     * @param color couleur de la piece a verifie
     * @return true si la case est menacee, false sinon
     */
    public boolean isCaseMenace(Position p, Couleur color) {
		for (int i=0; i<8; i++) {
		    for (int j=0; j<8; j++) {
			if(this.getCase(i, j).estOccupe(Couleur.getCouleurInverse(color))) {
			    for (Position pos : this.getCase(i, j).getPiece().getPositionMenace()) {
				if (p.equals(pos)) {
				    return true;
				}
			    }
			}
		    }
		}
		return false;
    }

    public boolean isEchec(Couleur color) {
		// trouve le roi de la couleur choisie
		int colonneRoi = -1;
		int ligneRoi = -1;
		int a = 0;
		int b = 0;
		while (a<8 && colonneRoi<0) {
		    while (b<8 && ligneRoi<0) {
			if (this.getCase(a, b).getPiece() instanceof Roi && this.getCase(a, b).getPiece().getCouleur().equals(color)) {
			    colonneRoi = a;
			    ligneRoi = b;
			}
			b++;
		    }
		    a++;
		}
		return this.isCaseMenace(new Position(colonneRoi, ligneRoi), color);
    }

    public Position findPiece (Piece piece) {
		for (int i=0; i<8; i++) {
		    for (int j=0; j<8; j++) {
			if (this.getCase(i, j).getPiece().getNom().equals(piece.getNom()) && this.getCase(i, j).getPiece().getCouleur().equals(piece.getCouleur())) {
			    return new Position(i, j);
			}
		    }
		}
		return null;
    }
}

