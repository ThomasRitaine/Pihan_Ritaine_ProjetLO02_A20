package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;

import java.awt.EventQueue;
import java.util.Observer;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau.FormesPlateau;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.Jeu;
/**
 * Partie est la classe définissant une partie de Jeu.
 * Elle admet un constructeur permettant de mettre en place les paramètres d'une partie et d'initialiser les points. 
 * Elle contient la fonction main du projet qui assure l'appel des différentes fonctions permettant de jouer ainsi que d'autres fonctions.
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 *
 */
public class Partie implements Runnable {
	
//	Attribut de classe
	private static boolean cree = false;
	public static Partie uniqueInstance = null;
	
//	Attributs
    private Joueur[] joueurs;
    private int[][] pointsTotaux;
    private Manche[] manches = new Manche[10];
    private int mancheActuelle = 0;
    private Parametre parametre;
    private CalculePointsVisitor calculateurPts;
    
    
//	Constructeur
    /**
     * Le constructeur initialise les tableaux des joueurs IA et humains en fonction de leur nombre respectif, et crée les instances qui serviront au calcul des points.
     * @param parametre
     */
    public Partie(Parametre parametre) {
		
    	//	Rï¿½cupï¿½ration des paramï¿½tres
    	this.parametre = parametre;
    	
    	//	Initialisation des joueurs
    	int nbJoueurHumain = this.parametre.getNbJoueurHumain();
    	int nbJoueurIA = this.parametre.getNbJoueurIA();
		this.joueurs = new Joueur[nbJoueurHumain + nbJoueurIA];
		
		if (nbJoueurHumain > 0) {
			for (int i = 0; i < nbJoueurHumain; i++) {
				this.joueurs[i] = new Joueur(i, this.parametre.getNomJoueurs(i), this.parametre.getModeJeu());
			}
		}
		
		if (nbJoueurIA > 0) {
			for (int i = nbJoueurHumain; i < this.parametre.getNbJoueur(); i++) {
				this.joueurs[i] = new JoueurIA(i, this.parametre.getNomJoueurs(i), this.parametre.getModeJeu());
			}
		}
		
		//	Initialisation des points
		this.calculateurPts = new CalculePointsVisitor();
		this.pointsTotaux = new int[this.parametre.getNbJoueur()][this.parametre.getNbManche()];
	}
    
   
//	Mï¿½thodes static
    
    //	Méthode appelée par le main et qui crée et lance une partie
    public static void jouerPartie() {
    	
    		//	Démarrage de l'app en ligne de commande
    	
		AsciiArt.welcome();

    	//	Demande de paramï¿½tres ï¿½ l'utilisateur
    	//Parametre parametre = new Parametre();
    	//parametre.parametrerPartie();
    	
    	//	Paramï¿½trage rapide, sans passer par le formulaire
    	String[] noms = {"Romï¿½o", "Juliette", "Lï¿½a l'IA"};
		Parametre parametre = new Parametre(3, 1, noms, FormesPlateau.RECTANGLE, ModeJeu.NORMAL, 2);
    	
		Partie maPartie = Partie.creerPartie(parametre);
		
		Thread t = new Thread(maPartie);
		t.start();
    	
		
    		//	Démarrage de l'interface graphique
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu fenetre = new Jeu(Partie.getPartie());
					fenetre.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    }
    
    
    //	Lance le thread pour permettre à l'interface graphique de se lancer en parallèle.
	public void run() {
    	this.deroulerPartie();
    	AsciiArt.thanks();
    }
    
    //	Crï¿½er une instance du singleton
    /**
     * Crée la partie.
     * Une partie est une instance d'un singleton. 
     * @param parametre - Les paramètres de la partie.
     * @return La partie créée.
     */
    public static Partie creerPartie(Parametre parametre) {
    	Partie partie = null;
    	if (!Partie.cree) {
			partie = new Partie(parametre);
			Partie.cree = true;
			Partie.uniqueInstance = partie;
		}
    	return partie;
    }
    
    //	Obtenir une instance du singleton
    /**
     * Récupère la partie créée.
     * @return La partie créée.
     */
    public static Partie getPartie() {
    	Partie partie = null;
    	if (Partie.cree) {
			partie = Partie.uniqueInstance;
		}
    	return partie;
    }

    
//	Mï¿½thodes
    /*
     * Permet l'execution d'une partie. 
     * En pratique une partie se caracterise par une sucession de plusieurs manches.
     * Lorsqu'il ne reste plus de manche à jouer, la methode affiche les scores.
     */
    public void deroulerPartie() {
    	//	Pour chacune des manches
    	for (this.mancheActuelle = 0; this.mancheActuelle < this.parametre.getNbManche(); this.mancheActuelle++) {
			this.manches[this.mancheActuelle] = new Manche();
			this.manches[this.mancheActuelle].jouerManche();
			this.afficherScores();
		}
    	this.mancheActuelle--;
    	this.afficherVainqueur();
    }
    

    /**
     * Permet d'afficher les scores d'une partie.
     */
    public void afficherScores() {
    	String nomJoueur;
    	int points;
    	
    	//	Affichage séparateur et tableau score
    	System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	System.out.println("\n");
    	AsciiArt.score();
    	System.out.println("\n");
    	
    	for (int idJoueur = 0; idJoueur < this.getParametre().getNbJoueur(); idJoueur++) {
    		nomJoueur = this.getJoueurParId(idJoueur).getNom();
    		points = this.getPointsTotaux(idJoueur);
    		System.out.println(nomJoueur + " possï¿½de " + points + " points.");
    	}
    }
    
    /**
     * Permet d'afficher le nom du joueur vainqueur.
     */
    public void afficherVainqueur() {
		int meilleurScore = 0;
		int idJoueurGagnant = 0;
		boolean egalite = false;
		
		//	On parcourt les scores pour trouver le meilleur
		for (int idJoueur = 0; idJoueur < this.getParametre().getNbJoueur(); idJoueur++) {
			int points = this.getPointsTotaux(idJoueur);
    		if (points > meilleurScore) {
				meilleurScore = points;
				idJoueurGagnant = idJoueur;
				egalite = false;
			} else if (points == meilleurScore) {
    			egalite = true;
			}
    		
    	}
		
		//	Affichage de separateur et du meilleur score
		System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	if (egalite) {
			System.out.println("\n\tIl y a ï¿½galitï¿½ !");
			System.out.println("\tPas de vainqueur, on en refait une ?");
			
		} else {
			System.out.println("\n\t"+this.getJoueurParId(idJoueurGagnant).getNom() + " gagne avec "+ meilleurScore + " points !");
			AsciiArt.medal();
		}
		System.out.println("\n\n");
	}
    
    /**
     * Recupere un joueur par son identifiant.
     * @param id L'identifiant du joueur.
     * @return Le joueur recupere.
     */
    public Joueur getJoueurParId(int id) {
    	Joueur joueur = null;
    	if (id >= 0 && id <=this.getParametre().getNbJoueur()) {
			joueur = this.joueurs[id];
		}
		return joueur;
	}
    
    /**
     * Recupere les parametres de la partie.
     * @return Les parametres de la partie.
     */
    public Parametre getParametre() {
		return this.parametre;
    }
    
    /**
     * Recupere le calculateur de points. 
     * @return CalculePointsVisitor
     */
    public CalculePointsVisitor getCalculateurPts() {
		return this.calculateurPts;
    }
    
    /**
     * Initialise le tableau des points totaux d'une partie.
     * @param idJoueur L'identifiant du joueur.
     * @param manche La manche considérée.
     * @param points Les points gagnés par le joueur au total.
     */
	public void setPointsTotaux(int idJoueur, int manche, int points) {
		this.pointsTotaux[idJoueur][manche] = points;
	}
	
	/**
	 * Récupère les points d'une manche.
	 * @param idJoueur L'identifiant du joueur.
	 * @param manche La manche consideree.
	 * @return Les points gagnes par un joueur lors d'une manche.
	 */
	public int getPointsManche(int idJoueur, int manche) {
		return this.pointsTotaux[idJoueur][manche];
	}
	
	/**
	 * Recupere les points totaux d'un joueur.
	 * @param idJoueur L'indentifiant du joueur.
	 * @return Les points totaux du joueur.
	 */
	public int getPointsTotaux(int idJoueur) {
		int points = 0;
		for (int i = 0; i < (this.getNumMancheActuelle()+1); i++) {
			points += this.pointsTotaux[idJoueur][i];
		}
		return points;
	}
	
	/**
	 * Recupere le numero de la manche actuelle d'une partie : la manche en cours de jeu.
	 * @return Le numero de la manche actuelle.
	 */
	public int getNumMancheActuelle() {
		return this.mancheActuelle;
	}
	
	/**
	 * Recupere la manche actuelle d'une partie : la manche en cours de jeu.
	 * @return La manche actuelle.
	 */
	public Manche getMancheActuelle() {
		return this.manches[this.mancheActuelle];
	}
	

 

/**
 * La fonction main dans laquelle sont appelï¿½es une ï¿½ une toutes les fonctions assurant le déroulement d'une partie.
 * Elle crée notamment une instance de Paramï¿½tre et appelle la fonction pour l'initialiser ainsi qu'une instance de Partie pour ensuite lancer la partie.   
 * @param args
 */
	//	Main
    public static void main(String[] args) {
    	
    	Partie.jouerPartie();
    	
    }

}
