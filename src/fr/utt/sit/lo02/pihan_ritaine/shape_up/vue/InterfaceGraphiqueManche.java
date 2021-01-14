package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Joueur;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau;

public class InterfaceGraphiqueManche implements Observer, Runnable {

	//	Déclaration des membres du jeu initialis�s dans son constructeur.
	private Plateau plateau;
	private ModeJeu mode;
	
	//	Déclaration de la fenêtre
	private JFrame frame;
	
	//	Déclaration zones textes
	private JLabel txtNumManche;
	private JLabel txtAuTourDe;
	private JLabel imageCarte0;
	private JLabel imageCarte1;
	private JLabel imageCarte2;
	private JLabel txtMessage;
	
	/*D�claration boutons.*/
	private JButton btnFinirTour;
	private JButton btnPoserCarte;
	private JButton btnBougerCarte;
	private JPanel conteneurPlateau;
	
	/**
	 * Create the application.
	 */
	public InterfaceGraphiqueManche(Partie partieEnCours) {
		
		//	On récupère les données
		this.plateau = partieEnCours.getMancheActuelle().getPlateau();
		this.mode = partieEnCours.getParametre().getModeJeu();
		
		initialize();
		/*Cr�ation du controleur du jeu : lien entre le Modele et la vue.*/
		//new Controleur(btnFinirTour,btnPoserCarte,btnBougerCarte,txtMessage,txtAuTourDe);
		
		//	Ajout des observers
		this.plateau.addObserver(this);
		partieEnCours.getMancheActuelle().addObserver(this);
		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
			partieEnCours.getJoueurParId(i).addObserver(this);
		}
	}
	
	//	La fonction qui est appelée quand on crée lance un Thread de cette classe
	public void run() {
		try {
			//	On affiche juste la frame car l'initialisation a été faite avant d'appeler le Thread
			this.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		this.afficherPlateau();	
		this.afficherMessagesEtBoutons();
			
	}
	
	/**
	 * Affiche les messages.
	 */
	private void afficherMessagesEtBoutons() {
		
		Partie partieEnCours = Partie.getPartie();
		
		//	On crée une zone de texte pour y mettre le numéro de la manche en haut de l'écran
		this.txtNumManche = new JLabel("Manche " + (partieEnCours.getNumMancheActuelle()+1) + " sur " + partieEnCours.getParametre().getNbManche());
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
		conteneurMessagesEtBoutons.setLayout(new GridLayout(6, 2, 20, 20));
		
		txtAuTourDe = new JLabel("Au tour de "+partieEnCours.getJoueurParId(0).getNom());
		conteneurMessagesEtBoutons.add(txtAuTourDe);
		
		//	On laisse une case vide à côté du label précédent
		JLabel vide = new JLabel("");
		conteneurMessagesEtBoutons.add(vide);
		
		//	On affiche la main ou les cartes de victoire et à jouer en fonction du mode de jeu
		String messageTxtCarte0 = "";
		String messageTxtCarte1 = "";
		String messageTxtCarte2 = "";
		if(this.mode == ModeJeu.NORMAL) {
			messageTxtCarte0 = "Votre carte de victoire :";
			messageTxtCarte1 = "Votre carte à jouer :";
		}
		if(this.mode == ModeJeu.AVANCE) {
			messageTxtCarte0 = "Votre carte n°1 :";
			messageTxtCarte1 = "Votre carte n°2 :";
			messageTxtCarte2 = "Votre carte n°3 :";
		}
		
		//	On affiche le label avec le texte
		JLabel txtCarte0 = new JLabel(messageTxtCarte0);
		conteneurMessagesEtBoutons.add(txtCarte0);
		//	Avec la carte à côté
		this.imageCarte0 = new JLabel();
		this.imageCarte0.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+partieEnCours.getJoueurParId(0).getCarteDeMain(0).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		conteneurMessagesEtBoutons.add(this.imageCarte0);
		
		
		//	On affiche le label avec le texte
		JLabel txtCarte1 = new JLabel(messageTxtCarte1);
		conteneurMessagesEtBoutons.add(txtCarte1);
		//	Avec la carte à côté
		this.imageCarte1 = new JLabel();
		this.imageCarte1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+partieEnCours.getJoueurParId(0).getCarteDeMain(1).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		conteneurMessagesEtBoutons.add(this.imageCarte1);
		
		//	Si le mode de jeu est avancé, on affiche la troisième carte de la main
		if (this.mode == ModeJeu.AVANCE) {
			//	On affiche le label avec le texte
			JLabel txtCarte2 = new JLabel(messageTxtCarte2);
			conteneurMessagesEtBoutons.add(txtCarte2);
			//	Avec la carte à côté
			this.imageCarte2 = new JLabel();

			this.imageCarte2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+partieEnCours.getJoueurParId(0).getCarteDeMain(2).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
			conteneurMessagesEtBoutons.add(this.imageCarte2);
		}
		
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
		
		//	Si un plateau était déjà affiché, on l'enlève
		if (this.conteneurPlateau != null) {
			getFrame().getContentPane().remove(this.conteneurPlateau);
		}

		//	Puis on en recrée un à jour
		this.conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.PlateauGraphique(this.plateau);
		getFrame().getContentPane().add(this.conteneurPlateau, BorderLayout.CENTER);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void update(Observable instanceObservable, Object arg) {
		
		if (instanceObservable instanceof Plateau) {
			
			//	Le plateau a été modifié, on l'actualise
			this.afficherPlateau();
		}
		
		//	C'est au tour d'un nouveau joueur, on actualise l'affichage
		if (instanceObservable instanceof Manche) {
			
			//	L'argument donné est le joueur qui va jouer
			Joueur joueur = (Joueur) arg;
			
			//	On remplit l'interface avec les informations du joueur
			this.txtAuTourDe.setText("Au tour de "+joueur.getNom());
			
			//	On actualise les cartes en main affichées
			this.actualiserCarteEnMain(joueur);
		}
		
		if (instanceObservable instanceof Joueur) {
			//	On change instanceObservable en objet Joueur
			Joueur joueur = (Joueur) instanceObservable;
			//	La main du joueur a été modifiée, on actualise l'affichage
			this.actualiserCarteEnMain(joueur);
		}
		
	}
	
	private void actualiserCarteEnMain(Joueur joueur) {
		//	On affiche la carte de la main du joueur
		if (joueur.getCarteDeMain(0) != null) {
			this.imageCarte0.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+joueur.getCarteDeMain(0).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		} else {
			this.imageCarte0.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/vide.jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		}
		if (joueur.getCarteDeMain(1) != null) {
			this.imageCarte1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+joueur.getCarteDeMain(1).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		} else {
			this.imageCarte1.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/vide.jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
		}
		
		//	On fait pareil sur la 3ème carte de la main si on est en mode de jeu avancé
		if (this.mode == ModeJeu.AVANCE) {
			if (joueur.getCarteDeMain(2) != null) {
				this.imageCarte2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/"+joueur.getCarteDeMain(2).getCode()+".jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
			} else {
				this.imageCarte2.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/cartes/vide.jpg")).getImage().getScaledInstance(PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, 0)));
			}
		}
	}
}
