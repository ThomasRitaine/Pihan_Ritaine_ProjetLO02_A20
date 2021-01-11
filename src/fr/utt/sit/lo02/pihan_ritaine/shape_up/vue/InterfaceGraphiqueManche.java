package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau;

public class InterfaceGraphiqueManche implements Observer {

	private JFrame frame;
	
	//	Déclaration zones textes
	private JLabel txtNumManche;
	private JLabel txtAuTourDe;
	private JLabel txtVotre;
	private JLabel txtVotreCarte;
	private JLabel txtMessage;
	
	/*D�claration boutons.*/
	private JButton btnFinirTour;
	private JButton btnPoserCarte;
	private JButton btnBougerCarte;
	private JPanel conteneurPlateau;
	
	//	Déclaration des membres du jeu initialis�s dans son constructeur.
	private Plateau plateau;
	
	/**
	 * Create the application.
	 */
	public InterfaceGraphiqueManche(Partie partieEnCours) {
		this.plateau = partieEnCours.getMancheActuelle().getPlateau();
		initialize();
		/*Cr�ation du controleur du jeu : lien entre le Modele et la vue.*/
		//new Controleur(btnFinirTour,btnPoserCarte,btnBougerCarte,txtMessage,txtAuTourDe);
		
		//	Ajout des observers
		this.plateau.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*Met en place la frame.*/
		frame = new JFrame("Shape Up!");
		
		//r�gler la taille de JFrame � la taille de l'�cran
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new GridLayout(1, 2));
		//frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		this.afficherPlateau();	
		this.afficherMessagesEtBoutons();
			
	}
	
	/**
	 * Affiche les messages.
	 */
	private void afficherMessagesEtBoutons() {
		
		//	On crée une zone de texte pour y mettre le numéro de la manche en haut de l'écran
		this.txtNumManche = new JLabel("Manche " + (Partie.getPartie().getNumMancheActuelle()+1) + " sur " + Partie.getPartie().getParametre().getNbManche());
		//	On met le texte au centre
		this.txtNumManche.setHorizontalAlignment(SwingConstants.CENTER);
		//	On agrandit la police d'écriture
		this.txtNumManche.setFont(new FontUIResource("Serif", Font.BOLD, 22));
		frame.getContentPane().add(txtNumManche, BorderLayout.NORTH);
		
		//	On crée un conteneur pour les messages et les boutons
		JPanel conteneurMessagesEtBoutons = new JPanel();
		//	On place ce conteneur en à droite de l'écran
		frame.getContentPane().add(conteneurMessagesEtBoutons, BorderLayout.EAST);
		//	On met un tableau dans ce conteneur
		conteneurMessagesEtBoutons.setLayout(new GridLayout(5, 2, 20, 20));
		
		txtAuTourDe = new JLabel("Au tour de ");
		conteneurMessagesEtBoutons.add(txtAuTourDe);
		
		txtVotre = new JLabel("Votre carte victoire est");
		conteneurMessagesEtBoutons.add(txtVotre);
		
		txtVotreCarte = new JLabel("Votre carte \u00E0 jouer est ");
		conteneurMessagesEtBoutons.add(txtVotreCarte);
		
		txtMessage = new JLabel("Message");
		conteneurMessagesEtBoutons.add(txtMessage);
		
		btnBougerCarte = new JButton("Bouger une carte");
		conteneurMessagesEtBoutons.add(btnBougerCarte);
		
		btnPoserCarte = new JButton("Poser une carte");	
		conteneurMessagesEtBoutons.add(btnPoserCarte);
		
		btnFinirTour = new JButton("Finir le tour");
		conteneurMessagesEtBoutons.add(btnFinirTour);
		
		//Normalement à mettre dans le controleur du mvc
		btnBougerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMessage.setText("Votre carte a bien �t� boug�e.");
				//rendreCaseCliquable("bouger");
				
			}
		});
			
			btnPoserCarte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtMessage.setText("Votre carte a bien �t� pos�e.");
					
				}
			});
			
			btnFinirTour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtAuTourDe.setText("A votre tour"+"nom joueur");
					/*r�cup�rer le nom du joueur.*/
					/*appeler la m�thode Fin tour.*/
				}
			});
		
	}
	/**
	 * Affiche le plateau de cartes.
	 */
	private void afficherPlateau() {
		this.conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.PlateauGraphique(this.plateau);
		getFrame().getContentPane().add(this.conteneurPlateau, BorderLayout.CENTER);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void update(Observable instanceObservable, Object arg) {
		
		if (instanceObservable instanceof Plateau) {
			this.afficherPlateau();
		}
		
		/*
		if(arg instanceof EventsObservable) {
			switch ((EventsObservable) arg) {
				case AFFICHER_PLATEAU:
				
					plateau.afficher();
					plateau.setCarte(o.getPosCarte());
					frame.revalidate();
					frame.repaint();
					break;
				case POSER_CARTE:
					plateau.poserCarte();
					frame.revalidate();
					frame.repaint();
					break;
				case BOUGER_CARTE:
					plateau.bougerCarte();
					frame.revalidate();
					frame.repaint();
					break;
				default:
					break;*/
		//	}
		//}
		
	}
}
