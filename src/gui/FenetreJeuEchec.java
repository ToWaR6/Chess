package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import enums.Couleur;
import pieces.Cavalier;
import pieces.Fou;
import pieces.Piece;
import pieces.Pion;
import pieces.Reine;
import pieces.Roi;
import pieces.Tour;
import plateau.Deplacement;
import plateau.Echiquier;
import plateau.Position;
/**
 * classe Fenetre jeu servant a representer la GUI du jeu d'echec, contient la planche de jeu, les boutons debuter et reset,
 * et le champ texte informant le joueur sur le tour. Contient egalement deux jPanel contenant les pieces mangees.
 * 
 * @author Francois Allard
 */

@SuppressWarnings("serial")
public class FenetreJeuEchec extends JFrame {
	/**
	 * Echiquier du jeu, contient le tableau de case.
	 */
	private Echiquier e; // echiquier
	private JLabel[][] tab; // tableau de JLabels

	private JPanel panelControle = new JPanel(); // panel du haut
	private JPanel panelGrille = new JPanel(); // panel du bas ( grille )
	GridLayout gridLayout1 = new GridLayout();

	private JButton boutonDebuter = new JButton();
	private JTextField champTexte = new JTextField();
	private JButton boutonReset = new JButton();
	private JPanel panelblanc = new JPanel();
	private JPanel panelnoir = new JPanel();

	/**
	 * Constructeur, appelle methode JBInit
	 */
	public FenetreJeuEchec() 
	{
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * initialise la surface de jeu. Cree tous les elements et initialise leur position, leur couleur.. etc
	 */
	private void jbInit() throws Exception {

		tab = new JLabel[8][8]; // creation du tableau de JLabel
		e = new Echiquier(); // creation de l'echiquier

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(784, 585));
		this.setTitle("Jeu d'Echecs");
		panelControle.setBounds(new Rectangle(5, 10, 550, 45));
		panelControle.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelControle.setLayout(null);
		panelGrille.setBounds(new Rectangle(5, 65, 550, 465));
		panelGrille.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelGrille.setLayout(gridLayout1);
		gridLayout1.setColumns(8);
		gridLayout1.setRows(8);
		this.getContentPane().add(panelnoir, null);
		this.getContentPane().add(panelblanc, null);
		this.getContentPane().add(panelGrille, null);
		panelControle.add(boutonReset, null);
		panelControle.add(champTexte, null);
		panelControle.add(boutonDebuter, null);
		this.getContentPane().add(panelControle, null);
		boutonDebuter.setBounds(new Rectangle(15, 10, 130, 25));
		boutonDebuter.setText("DEBUTER");
		champTexte.setBounds(new Rectangle(160, 10, 250, 25));

		// les ecouteurs
		boutonReset.setText("REJOUER");
		boutonReset.setBounds(new Rectangle(425, 10, 100, 25));
		GestionnaireEvenement gest = new GestionnaireEvenement();
		boutonDebuter.addMouseListener(gest);
		boutonReset.addMouseListener(gest);
		boutonReset.setEnabled(false);

		//creation des labels
		panelblanc.setBounds(new Rectangle(570, 65, 75, 480));
		panelblanc.setBackground(new Color(255, 255, 255));
		panelblanc.setLayout(new FlowLayout());
		panelnoir.setBounds(new Rectangle(655, 65, 75, 475));
		panelnoir.setBackground(new Color(100, 100, 100));
		panelnoir.setLayout(new FlowLayout());

		//J'attribue la couleur aux JLabels
		int a = 1;
		for (int ligne = 0; ligne < 8; ligne++) {
			a = a == 1 ? 0 : 1;
			for (int colonne = 0; colonne < 8; colonne++) {
				tab[colonne][ligne] = new JLabel(); // creation du JLabel
				tab[colonne][ligne].setOpaque(true);
				panelGrille.add(tab[colonne][ligne]); // ajouter au Panel
				tab[colonne][ligne].setOpaque(true);
				tab[colonne][ligne].setHorizontalAlignment(SwingConstants.CENTER);
				tab[colonne][ligne].addMouseListener(gest);
				if ((colonne + 1) % 2 == a)
					tab[colonne][ligne].setBackground(new Color(100, 100, 100));
				else
					tab[colonne][ligne].setBackground(new Color(255, 255, 255));

			}
		}

	}

	/** classe privee pour la gestion des evenements de la souris.
	 * 
	 * @author Francois
	 *
	 */
	private class GestionnaireEvenement extends MouseAdapter {

		Piece pieceTampon = null;
		ImageIcon iconeTampon;
		int ligneClic;
		int colonneClic;
		Couleur couleurControle = Couleur.BLANC;
		Position temp = null;


		/** methode s'executant si l'on clique sur la surface de jeu. La methode determine ensuite ou est-ce que l'on cliquer
		 * et fait les action en consequence
		 *
		 */
		public void mouseClicked(MouseEvent eve) {
			// si on clique sur le bouton debuter
			if (eve.getSource() == boutonDebuter) {
				//initialise le champ texte, appelle la methode debuter, et initialise toute les variables 
				champTexte.setText("C'est le tour des " + couleurControle.toString().toLowerCase() + "s");
				boutonDebuter.setEnabled(false);
				boutonReset.setEnabled(true);
				e.misEnPlace();
				setIcon();
//				e.misEnPlace();

			}
			// si on clique sur le bouton reset
			else if (eve.getSource() == boutonReset) {
				//j'appelle la methode RAZ
				
				RAZ();
			}

			else if (eve.getSource() instanceof JLabel) // donc on a clique sur un Label
			{
				for (int i = 0; i < 8; i++)
					//je determine sur quelle Jlabel on a clique
					for (int j = 0; j < 8; j++) 
						if (eve.getSource() == tab[j][i]) {
							ligneClic = i;
							colonneClic = j;
						}
				//si on a clique sur une case non vide et que le tampon n'est pas null
				if((e.getCase(colonneClic, ligneClic).getPiece() != null | pieceTampon != null) )
				{
					//si le tampon est null
					if(pieceTampon == null )
					{			

						//si c'est au tour de la couleur de controle a jouer
						if(e.getCase(colonneClic, ligneClic).getPiece().getCouleur().equals(couleurControle)){
							//J'initialise la piece tampon a la piece sur laquelle on a clique
							pieceTampon = e.getCase(colonneClic, ligneClic).getPiece();
							iconeTampon = (ImageIcon)tab[colonneClic][ligneClic].getIcon();
							temp = new Position(colonneClic,ligneClic);
							tab[colonneClic][ligneClic].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255),5));
							e.setCaseMenace(temp);
							for (Position p : e.getCase(temp).getPiece().getPositionMenace()) {
								tab[p.getColonne()][p.getLigne()].setBorder(BorderFactory.createLineBorder(new Color(255, 255, 0), 5));
							}
						}

					}
					else
					{						
						//je cree un deplacement
						Deplacement deplacement = new Deplacement(temp, new Position(colonneClic,ligneClic));
						//je verifie si le deplacement est valide, si le chemin est possible et si il est possible, pour un pion de manger la piece
						if ((pieceTampon.deplacementEstValide(deplacement) && e.cheminPossible(deplacement)) | e.captureParPion(deplacement)) {

							if (e.transformPion(deplacement)) {
								if (pieceTampon.getCouleur().equals(Couleur.BLANC)) {
									pieceTampon = new Reine(Couleur.BLANC);
									iconeTampon = new ImageIcon("Icone/DB.gif");
								} else {
									pieceTampon = new Reine(Couleur.NOIR);
									iconeTampon = new ImageIcon("Icone/DN.gif");
								}
							}				

							if (e.captureParPion(deplacement) | e.getCase(colonneClic, ligneClic).getPiece() != null) {

								//je cree un jLabel avec l'icone de la piece mange
								JLabel manger = new JLabel(tab[colonneClic][ligneClic].getIcon());
								manger.setHorizontalAlignment(SwingConstants.CENTER);

								//je l'ajoute au bon jPanel
								if (couleurControle.equals(Couleur.BLANC))
									panelblanc.add(manger);
								else		
									panelnoir.add(manger);

								/* je verifie si la piece manger est un roi, si oui le jeu est termine et l'utilisateurs 
									peut choisir s'il veut continuer a jouer ou non*/
								if(e.getCase(colonneClic, ligneClic).getPiece() instanceof Roi) {
									if(JOptionPane.showConfirmDialog(null, "Partie terminee !\nDesirez-vous jouer de nouveau ?\n", "VICTOIRE DES " + couleurControle + "S", JOptionPane.YES_NO_OPTION) == 0){
										RAZ();
									}
									else {
										System.exit(0);
									}				
								}
							} 

							//on met le tampon sur la case vide et on vide le tampon apres
							tab[temp.getColonne()][temp.getLigne()].setIcon(null);
							e.getCase(temp.getColonne(), temp.getLigne()).setPiece(null);

							tab[colonneClic][ligneClic].setIcon(iconeTampon);
							e.getCase(colonneClic, ligneClic).setPiece(pieceTampon);
							
							for (int i=0; i<8; i++) {
								for (int j=0; j<8; j++) {
									tab[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
								}
							}
							
							pieceTampon = null;
							iconeTampon = null;
							temp = null;

							couleurControle = couleurControle.equals(Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
							champTexte.setText("C'est le tour des " + couleurControle.toString().toLowerCase() + "s");
						} else {
							for (int i=0; i<8; i++) {
								for (int j=0; j<8; j++) {
									tab[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
								}
							}
							pieceTampon = null;
							iconeTampon = null;
							temp = null;
						}
					}
				}
			}
		}
	}

	//Je remet tout les attributs de la classe a 0
	public void RAZ()
	{
		for (int ligne = 0; ligne < 8; ligne++) {
			for (int colonne = 0; colonne < 8; colonne++) {
				tab[colonne][ligne].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
				tab[colonne][ligne].setIcon(null);
				e.getCase(colonne, ligne).setPiece(null);
			}
		}
		
		champTexte.setText("");
		boutonDebuter.setEnabled(true);

		e.misEnPlace();

		panelblanc.removeAll();
		panelblanc.repaint();
		panelnoir.removeAll();
		panelnoir.repaint();

		System.out.print(EXIT_ON_CLOSE);

	}
	public void setIcon() {
		String dossierIcone = "Icone/";
		char[] ordrePiece = { 'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T' };
		int increment = 1;
		int ligne = 0;
		char couleur = 'N';
		Piece tempo = null;
		while (increment >= -1) {
			for (int ctr = 0; ctr < 8; ctr++) {
				tab[ctr][ligne].setIcon(new ImageIcon(dossierIcone + ordrePiece[ctr] + couleur + ".gif"));
				switch(ordrePiece[ctr])
				{
				case 'T':
					tempo = new Tour(ligne < 5 ? Couleur.NOIR : Couleur.BLANC);
					break;

				case 'C':
					tempo = new Cavalier(ligne < 5 ? Couleur.NOIR : Couleur.BLANC);
					break;

				case 'F':
					tempo = new Fou(ligne < 5 ? Couleur.NOIR : Couleur.BLANC);
					break;

				case 'D':
					tempo = new Reine(ligne < 5 ? Couleur.NOIR : Couleur.BLANC);
					break;

				case 'R':
					tempo = new Roi(ligne < 5 ? Couleur.NOIR : Couleur.BLANC);
					break;
				}
				e.getCase(ctr, ligne).setPiece(tempo);
				tab[ctr][ligne + increment].setIcon(new ImageIcon(dossierIcone + 'P' + couleur + ".gif"));
				e.getCase(ctr, ligne + increment).setPiece(new Pion(ligne < 5 ? Couleur.NOIR : Couleur.BLANC));

			}
			couleur = 'B';
			increment -= 2;
			ligne = 7;
		}
	}
}