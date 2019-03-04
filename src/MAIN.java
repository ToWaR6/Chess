import javax.swing.JFrame;

import gui.FenetreJeuEchec;

@SuppressWarnings("serial")
public class MAIN extends JFrame {
	
	public static void main(String[] args) {
		FenetreJeuEchec j = new FenetreJeuEchec();
		j.setVisible(true);
		j.setLocation(100, 130);
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
