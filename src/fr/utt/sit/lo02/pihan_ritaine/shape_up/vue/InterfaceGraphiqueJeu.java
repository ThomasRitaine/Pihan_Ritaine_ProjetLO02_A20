package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;




import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Joueur;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau;

public class InterfaceGraphiqueJeu implements Observer {

	private JFrame frame;
	
	/*D�claration zones textes.*/
	private JTextField txtAuTourDe;
	private JTextField txtVotre;
	private JTextField txtVotreCarte;
	private JTextField txtMessage;
	
	/*D�claration boutons.*/
	private JButton btnFinirTour;
	private JButton btnPoserCarte;
	private JButton btnBougerCarte;
	
	/*D�claration des membres du jeu initialis�s dans son constructeur.*/
	private Manche manche;
	private Plateau plateau;

	//r�cuperer la dimension de l'�cran
	private Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
	private int longueurEcran = tailleMoniteur.width;
	private int hauteurEcran = tailleMoniteur.height;
	
	/**
	 * Create the application.
	 */
	public InterfaceGraphiqueJeu(Partie partieEnCours) {
		this.manche = partieEnCours.getMancheActuelle();
		this.plateau = this.manche.getPlateau();
		initialize();
		/*Cr�ation du controleur du jeu : lien entre le Modele et la vue.*/
		//new Controleur(btnFinirTour,btnPoserCarte,btnBougerCarte,txtMessage,txtAuTourDe);
			
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*Met en place la frame.*/
		frame = new JFrame("Shape Up!");
		//frame.setBounds(100, 100, 719, 449);	
		
		//r�gler la taille de JFrame � la taille de l'�cran
		frame.setSize(longueurEcran, hauteurEcran);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		this.afficherBoutons();
		this.afficherMessages();
		this.afficherPlateau();		
	}
	
	/**
	 * Affiche les messages.
	 */
	private void afficherMessages() {
		JPanel conteneurMessage = new JPanel();
		conteneurMessage.setBounds(731, 11, 153, 352);
		frame.getContentPane().add(conteneurMessage);
		conteneurMessage.setLayout(null);
		
		txtAuTourDe = new JTextField();
		txtAuTourDe.setBounds(33, 5, 86, 20);
		txtAuTourDe.setText("A votre tour");
		conteneurMessage.add(txtAuTourDe);
		txtAuTourDe.setColumns(10);
		
		txtVotre = new JTextField();
		txtVotre.setBounds(10, 36, 133, 66);
		txtVotre.setText("Votre carte victoire est");
		conteneurMessage.add(txtVotre);
		txtVotre.setColumns(10);
		
		txtVotreCarte = new JTextField();
		txtVotreCarte.setBounds(10, 113, 133, 66);
		txtVotreCarte.setText("Votre carte \u00E0 jouer est");
		conteneurMessage.add(txtVotreCarte);
		txtVotreCarte.setColumns(10);
		
		txtMessage = new JTextField();
		txtMessage.setText("Message");
		txtMessage.setBounds(10, 190, 133, 151);
		conteneurMessage.add(txtMessage);
		txtMessage.setColumns(10);
		
	}
	/**
	 * Affiche le plateau de cartes.
	 */
	private void afficherPlateau() {
		/*Il faudrait r�ussir � mettre dans le conteneurPlateau le composant plateau. */
		//JPanel conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.Plateau(this.plateau);
//		On ajoute le panel qui contient le plateau
		JPanel conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.PlateauGraphique(this.plateau);
		frame.getContentPane().add(conteneurPlateau);
		//conteneurPlateau.setSize(longueurEcran*2/3,hauteurEcran*2/3);
		//JPanel conteneurPlateau = new JPanel();
		conteneurPlateau.setBounds(10, 11, 711, 451);
		frame.getContentPane().add(conteneurPlateau);
		conteneurPlateau.setLayout(null);
		frame.repaint();
	}
	
	/*private void rendreCaseCliquable(String commande) {
		for(JButton currentBt : plateau.getListButtons()) {
			currentBt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOUEURHUMAINAQUICLETOUR.jouerAvecCommande(commande + " " + X + " " + Y);
					plateau.afficher();
					
				}
			});
	}*/

	

	/**
	 * Affiche les boutons. 
	 * @param Message Le message explicant l'action r�alis�e par le bouton actionn�.
	 */
	private void afficherBoutons() {
		JPanel conteneurBoutons = new JPanel();
		conteneurBoutons.setBounds(731, 374, 153, 88);
		frame.getContentPane().add(conteneurBoutons);
		conteneurBoutons.setLayout(null);
		
		btnBougerCarte = new JButton("Bouger une carte");		
		btnBougerCarte.setBounds(19, 33, 115, 23);
		conteneurBoutons.add(btnBougerCarte);
		
		btnPoserCarte = new JButton("Poser une carte");		
		btnPoserCarte.setBounds(22, 5, 109, 23);
		conteneurBoutons.add(btnPoserCarte);
		
		
		btnFinirTour = new JButton("Finir le tour");		
		btnFinirTour.setBounds(33, 61, 87, 23);
		conteneurBoutons.add(btnFinirTour);
		
		//Normalement à mettre dans le controleur du mvc
		btnBougerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMessage.setText("Votre carte a bien �t� boug�e.");
				//rendreCaseCliquable("bouger");
				
			}
		});
		
		btnPoserCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//rendreCaseCliquable("poser");
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

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

	@Override
	public void update(Observable o, Object arg) {
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
