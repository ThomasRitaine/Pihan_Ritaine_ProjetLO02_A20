package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.Plateau.FormesPlateau;
/**
 * Partie est la classe définissant une partie de Jeu.
 * Elle admet un constructeur permettant de mettre en place les paramètres d'une partie et d'initialiser les points. 
 * Elle contient la fonction main du projet qui assure l'appel des différentes fonctions permettant de jouer ainsi que d'autres fonctions.
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 *
 */
public class Partie {
	
//	Attribut de classe
	private static boolean cree = false;
	public static Partie uniqueInstance = null;
	
//	Attributs
    private Joueur[] joueurHumain;
    private JoueurIA[] joueurIA;
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
		
    	//	Récupération des paramètres
    	this.parametre = parametre;
    	
    	//	Initialisation des joueurs
		this.joueurHumain = new Joueur[this.parametre.getNbJoueurHumain()];
		this.joueurIA = new JoueurIA[this.parametre.getNbJoueurIA()];
		
		if (this.parametre.getNbJoueurHumain() > 0) {
			for (int i = 0; i < this.parametre.getNbJoueurHumain(); i++) {
				this.joueurHumain[i] = new Joueur(i, this.parametre.getNomJoueurs(i), this.parametre.getModeJeu());
			}
		}
		
		if (this.parametre.getNbJoueurIA() > 0) {
			for (int i = this.parametre.getNbJoueurHumain(); i < this.parametre.getNbJoueur(); i++) {
				this.joueurIA[i-this.parametre.getNbJoueurHumain()] = new JoueurIA(i, this.parametre.getNomJoueurs(i), this.parametre.getModeJeu());
			}
		}
		
		//	Initialisation des points
		this.calculateurPts = new CalculePointsVisitor();
		this.pointsTotaux = new int[this.parametre.getNbJoueur()][this.parametre.getNbManche()];
	}
    
   
//	Méthodes static
    
    //	Créer une instance du singleton
    /**
     * Crée la partie.
     * Une partie est une instance d'un singleton. 
     * @param parametre - Les paramètres de la partie.
     * @return La partie créée.
     */
    public static Partie createPartie(Parametre parametre) {
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

    
//	Méthodes
    /*
     * Permet l'execution d'une partie. 
     * En pratique une partie se caractérise par une sucession de plusieurs manches.
     * Lorsqu'il ne reste plus de manche à jouer, la méthode affiche les scores.
     */
    public void jouerPartie() {
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
    		System.out.println(nomJoueur + " possède " + points + " points.");
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
		
		//	Affichage de séparateur et du meilleur score
		System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	if (egalite) {
			System.out.println("\n\tIl y a égalité !");
			System.out.println("\tPas de vainqueur, on en refait une ?");
			
		} else {
			System.out.println("\n\t"+this.getJoueurParId(idJoueurGagnant).getNom() + " gagne avec "+ meilleurScore + " points !");
			AsciiArt.medal();
		}
		System.out.println("\n\n");
	}
    
    /**
     * Récupère un joueur par son identifiant.
     * @param id - L'identifiant du joueur.
     * @return Le joueur récupéré.
     */
    public Joueur getJoueurParId(int id) {
    	Joueur joueur;
    	if (id < this.parametre.getNbJoueurHumain()) {
			joueur = this.joueurHumain[id];
		} else {
			joueur = this.joueurIA[id - this.parametre.getNbJoueurHumain()];
		}
		return joueur;
	}
    
    /**
     * Récupère les paramètres de la partie.
     * @return Les paramètres de la partie.
     */
    public Parametre getParametre() {
		return this.parametre;
    }
    
    /**
     * Récupère le calculateur de points. 
     * @return CalculePointsVisitor
     */
    public CalculePointsVisitor getCalculateurPts() {
		return this.calculateurPts;
    }
    
    /**
     * Initialise le tableau des points totaux d'une partie.
     * @param idJoueur - L'identifiant du joueur.
     * @param manche - La manche considérée.
     * @param points - les points gagnés par le joueur au total.
     */
	public void setPointsTotaux(int idJoueur, int manche, int points) {
		this.pointsTotaux[idJoueur][manche] = points;
	}
	
	/**
	 * Récupère les points d'une manche.
	 * @param idJoueur - l'identifiant du joueur.
	 * @param manche - La manche considérée.
	 * @return Les points gagnés par un joueur lors d'une manche.
	 */
	public int getPointsManche(int idJoueur, int manche) {
		return this.pointsTotaux[idJoueur][manche];
	}
	
	/**
	 * Récupère les points totaux d'un joueur.
	 * @param idJoueur - L'indentifiant du joueur.
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
	 * Récupère le numéro de la manche actuelle d'une partie : la manche en cours de jeu.
	 * @return Le numéro de la manche actuelle.
	 */
	public int getNumMancheActuelle() {
		return this.mancheActuelle;
	}
	
	/**
	 * Récupère la manche actuelle d'une partie : la manche en cours de jeu.
	 * @return La manche actuelle.
	 */
	public Manche getMancheActuelle() {
		return this.manches[this.mancheActuelle];
	}
	

 

/**
 * La fonction main dans laquelle sont appelées une à une toutes les fonctions assurant le déroulement d'une partie.
 * Elle crée notamment une instance de Paramètre et appelle la fonction pour l'initialiser ainsi qu'une instance de Partie pour ensuite lancer la partie.   
 * @param args
 */
	//	Main
    public static void main(String[] args) {
    	//System.out.println("\n\t---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	AsciiArt.welcome();

    	//	Demande de paramètres à l'utilisateur
    	Parametre parametre = new Parametre();
    	parametre.parametrerPartie();
    	
    	//	Paramétrage rapide, sans passer par le formulaire

    	//String[] noms = {"Roméo", "Juliette", "Léa l'IA"};
		//Parametre parametre = new Parametre(3, 0, noms, FormesPlateau.ECHELLE, ModeJeu.NORMAL, 2);

    	
    	Partie maPartie = Partie.createPartie(parametre);
    	maPartie.jouerPartie();
    	AsciiArt.thanks();
    }

}
