package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurMancheBougerCarte;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurMancheFinTour;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurManchePoserCarte;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Joueur;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau;

/**
 * InterfaceGraphiqueManche est la classe qui lance l'interface graphique pour jouer une manche.
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class InterfaceGraphiqueManche implements Observer, Runnable {

	//	Déclaration des membres du jeu initialis�s dans son constructeur.
	
	/**
     * Le plateau que devra représenter l'interface graphique.
     */
	private Plateau plateau;
	
	/**
     * Le mode de jeu de l'interface. Il va modifier l'interface graphique puisque les règles de jeu sont différentes.
     */
	private ModeJeu mode;
	
	/**
     * La fenêtre graphique de l'interface
     * 
     * @see InterfaceGraphiqueManche#getFrame()
     */
	private JFrame frame;
	
	//	Déclaration zones textes
	/**
     * Le zone de texte qui sera en haut de l'interface.
     * Elle sert à afficher le numéro de la manche et le nom du joueur qui doit jouer.
     */
	private JLabel txtNumMancheEtJoueur;
	
	/**
     * La zone qui sert à afficher l'image de la carte numéro 0 de la main du joueur.
     * En mode de jeu normal, cette carte sera la carte de victoire.
     */
	private JLabel imageCarte0;
	
	/**
     * La zone qui sert à afficher l'image de la carte numéro 1 de la main du joueur.
     * En mode de jeu normal, cette carte sera la carte à jouer.
     */
	private JLabel imageCarte1;
	
	/**
     * La zone qui sert à afficher l'image de la carte numéro 2 de la main du joueur.
     * En mode de jeu avancé, cette carte sera affichée. Sinon, elle sera cachée.
     */
	private JLabel imageCarte2;
	
	/**
     * La zone de texte qui va afficher les messages d'erreur et de feedback à l'utilisateur.
     */
	private JLabel txtMessage;
	
	//	Déclaration boutons.
	/**
     * Ce bouton va déclencher l'action de fin de tour.
     * 
     * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurMancheFinTour
     */
	private JButton btnFinirTour;
	
	/**
     * Ce bouton va déclencher l'action de poser une carte.
     * 
     * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurManchePoserCarte
     */
	private JButton btnPoserCarte;
	
	/**
     * Ce bouton va déclencher l'action de bouger une carte.
     * 
     * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur.ControleurMancheBougerCarte
     */
	private JButton btnBougerCarte;
	
	/**
     * Ce conteneur va contenir le plateau graphique
     * 
     * @see InterfaceGraphiqueManche#afficherPlateau()
     */
	private JPanel conteneurPlateau;
	
	/**
     * Cette zone de texte va récupérer la coordonnée X de la case sur laquelle poser la carte du joueur.
     */
	private JTextField coordXPoserInput;
	
	/**
     * Cette zone de texte va récupérer la coordonnée Y de la case sur laquelle poser la carte du joueur.
     */
	private JTextField coordYPoserInput;
	
	/**
     * Cette zone de texte va récupérer la coordonnée X de la case dont il faut bouger la carte.
     */
	private JTextField coordXDepuisBougerInput;
	
	/**
     * Cette zone de texte va récupérer la coordonnée Y de la case dont il faut bouger la carte.
     */
	private JTextField coordYDepuisBougerInput;
	
	/**
     * Cette zone de texte va récupérer la coordonnée X de la case vers laquelle il faut bouger la carte.
     */
	private JTextField coordXVersBougerInput;
	
	/**
     * Cette zone de texte va récupérer la coordonnée Y de la case vers laquelle il faut bouger la carte.
     */
	private JTextField coordYVersBougerInput;
	
	/**
     * Cette zone de texte va récupérer l'ID de la carte de la main qu'il faut jouer.
     * Cette zone ne s'affiche que dans le mode avancé.
     * 
     * @see InterfaceGraphiqueManche#mode
     */
	private JTextField idCarteInput;
	
	/**
	 * Crée l'application et ajouter les observers.
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
		Partie.getPartie().getMancheActuelle().addObserver(this);
		
		//	Création des Controleurs : lien entre le Modèle et la Vue
		new ControleurManchePoserCarte(this.txtMessage, this.btnPoserCarte, this.mode, this.idCarteInput, this.coordXPoserInput, this.coordYPoserInput);
		new ControleurMancheBougerCarte(this.txtMessage, this.btnBougerCarte, this.coordXDepuisBougerInput, this.coordYDepuisBougerInput, this.coordXVersBougerInput, this.coordYVersBougerInput);
		new ControleurMancheFinTour(this.txtMessage, this.btnFinirTour);
	}
	
	/**
	 * La fonction qui est appelée quand on crée lance un Thread de cette classe
	 */
	public void run() {
		try {
			//	On affiche juste la frame car l'initialisation a été faite avant d'appeler le Thread
			this.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * Initialise le contenu de la fenêtre.
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
	 * Affiche les mesages et les boutons sur la fenêtre de l'interface graphique.
	 */
	private void afficherMessagesEtBoutons() {
		
		Partie partieEnCours = Partie.getPartie();
		
		//	On crée une zone de texte pour y mettre le numéro de la manche en haut de l'écran
		this.txtNumMancheEtJoueur = new JLabel("Manche " + (partieEnCours.getNumMancheActuelle()+1) + " sur " + partieEnCours.getParametre().getNbManche()+" - Au tour de "+partieEnCours.getJoueurParId(0).getNom());
		//	On met le texte au centre
		this.txtNumMancheEtJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		//	On agrandit la police d'écriture
		this.txtNumMancheEtJoueur.setFont(new FontUIResource("Serif", Font.BOLD, 22));
		frame.getContentPane().add(txtNumMancheEtJoueur, BorderLayout.NORTH);
		
		//	On crée un conteneur pour les messages et les boutons
		JPanel conteneurMessagesEtBoutons = new JPanel();
		//	On place ce conteneur en à droite de l'écran
		frame.getContentPane().add(conteneurMessagesEtBoutons, BorderLayout.EAST);
		//	On met un tableau dans ce conteneur
		conteneurMessagesEtBoutons.setLayout(new GridLayout(6, 2, 20, 20));
		
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
		
		txtMessage = new JLabel();
		conteneurMessagesEtBoutons.add(txtMessage);
		
		JPanel poserCartePanel = new JPanel();
		poserCartePanel.setLayout(new BoxLayout(poserCartePanel, BoxLayout.Y_AXIS));
		conteneurMessagesEtBoutons.add(poserCartePanel);
		JLabel coordXLabel = new JLabel("Coordonnée X :");
		poserCartePanel.add(coordXLabel);
		this.coordXPoserInput = new JTextField();
		poserCartePanel.add(this.coordXPoserInput);
		JLabel coordYLabel = new JLabel("Coordonnée Y :");
		poserCartePanel.add(coordYLabel);
		this.coordYPoserInput = new JTextField();
		poserCartePanel.add(this.coordYPoserInput);
		if (this.mode == ModeJeu.AVANCE) {
			this.idCarteInput = new JTextField();
			poserCartePanel.add(this.idCarteInput);
		}
		this.btnPoserCarte = new JButton("Poser ma carte");
		poserCartePanel.add(this.btnPoserCarte);
		
		JPanel bougerCartePanel1 = new JPanel();
		bougerCartePanel1.setLayout(new BoxLayout(bougerCartePanel1, BoxLayout.Y_AXIS));
		conteneurMessagesEtBoutons.add(bougerCartePanel1);
		JLabel coordXDepuisLabel = new JLabel("Depuis X :");
		bougerCartePanel1.add(coordXDepuisLabel);
		this.coordXDepuisBougerInput = new JTextField();
		bougerCartePanel1.add(this.coordXDepuisBougerInput);
		JLabel coordYDepuisLabel = new JLabel("Depuis Y :");
		bougerCartePanel1.add(coordYDepuisLabel);
		this.coordYDepuisBougerInput = new JTextField();
		bougerCartePanel1.add(this.coordYDepuisBougerInput);
		
		JPanel bougerCartePanel2 = new JPanel();
		bougerCartePanel2.setLayout(new BoxLayout(bougerCartePanel2, BoxLayout.Y_AXIS));
		conteneurMessagesEtBoutons.add(bougerCartePanel2);
		JLabel coordXVersLabel = new JLabel("Vers X :");
		bougerCartePanel2.add(coordXVersLabel);
		this.coordXVersBougerInput = new JTextField();
		bougerCartePanel2.add(this.coordXVersBougerInput);
		JLabel coordYVersLabel = new JLabel("Vers Y :");
		bougerCartePanel2.add(coordYVersLabel);
		this.coordYVersBougerInput = new JTextField();
		bougerCartePanel2.add(this.coordYVersBougerInput);
		this.btnBougerCarte = new JButton("Bouger la carte");
		bougerCartePanel2.add(this.btnBougerCarte);
		
		
		btnFinirTour = new JButton("Finir le tour");
		conteneurMessagesEtBoutons.add(btnFinirTour);
		
		
	}
	
	
	/**
	 * Affiche le plateau de cartes.
	 * Cette méthode peut être appelée pour actualiser le plateau.
	 * 
	 * @see InterfaceGraphiqueManche#update(Observable, Object)
	 */
	private void afficherPlateau() {
		this.conteneurPlateau = new fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.PlateauGraphique(this.plateau);
		getFrame().getContentPane().add(this.conteneurPlateau, BorderLayout.CENTER);
	}

	/**
	 * Retourne la fenêtre.
	 * 
	 * @see InterfaceGraphiqueManche#frame
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}

	
	@Override
	/**
	 * La méthode qui sera appelée quand un objet observé lance une notification.
	 */
	public void update(Observable instanceObservable, Object arg) {
		
		if (instanceObservable instanceof Plateau) {
			
			//	Le plateau a été modifié, on l'actualise
			this.afficherPlateau();
			
			//	On réaffiche la main du joueur
			Joueur joueurQuiDoitJouer = Partie.getPartie().getMancheActuelle().getJoueurQuiJoueTour();
			this.actualiserCarteEnMain(joueurQuiDoitJouer);
		}
		
		//	C'est au tour d'un nouveau joueur, on actualise l'affichage
		if (instanceObservable instanceof Manche) {
			
			//	On récupère la partie
			Partie partieEnCours = Partie.getPartie();
			
			//	L'argument donné est le joueur qui va jouer
			Joueur joueur = (Joueur) arg;
			
			//	On remplit l'interface avec les informations du joueur
			this.txtNumMancheEtJoueur.setText("Manche " + (partieEnCours.getNumMancheActuelle()+1) + " sur " + partieEnCours.getParametre().getNbManche()+" - Au tour de "+partieEnCours.getJoueurParId(0).getNom());
			
			//	On actualise les cartes en main affichées
			this.actualiserCarteEnMain(joueur);
		}
		
	}
	
	
	/**
	 * Méthode qui actualise les cartes affichées dans la main du joueur.
	 */
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
