package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class Jeu implements Observer{
	
	private JFrame frame;
	private JPanel conteneurPlateau;
	private Manche manche;
	private Plateau plateau;

	/**
	 * Create the application.
	 */
	public Jeu(Partie partieEnCours) {
		this.manche = partieEnCours.getMancheActuelle();
		this.plateau = this.manche.getPlateau();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Shape Up!");
		getFrame().setBounds(100, 100, 719, 449);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		getFrame().getContentPane().setLayout(new BoxLayout(getFrame().getContentPane(), BoxLayout.X_AXIS));
		
		JLabel labelTour = new JLabel("C'est au tour de ");
		labelTour.setVerticalAlignment(SwingConstants.TOP);
		labelTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTour.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(labelTour);
		
		this.afficherTableau();
	}
	
	private void afficherTableau() {
		
		//	On ajoute le panel qui contient le plateau
		this.conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.Plateau(this.plateau);
		getFrame().getContentPane().add(this.conteneurPlateau);
		
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void update(Observable instanceObservable, Object arg) {
		
		/*if (instanceObservable instanceof Plateau) {
			this.afficherTableau();
		}*/
		
	}
}
