package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.Plateau.FormesPlateau;

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
    public static Partie getPartie() {
    	Partie partie = null;
    	if (Partie.cree) {
			partie = Partie.uniqueInstance;
		}
    	return partie;
    }

    
//	Méthodes
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
    
    
    public Joueur getJoueurParId(int id) {
    	Joueur joueur;
    	if (id < this.parametre.getNbJoueurHumain()) {
			joueur = this.joueurHumain[id];
		} else {
			joueur = this.joueurIA[id - this.parametre.getNbJoueurHumain()];
		}
		return joueur;
	}
    
    public Parametre getParametre() {
		return this.parametre;
    }
    
    public CalculePointsVisitor getCalculateurPts() {
		return this.calculateurPts;
    }
    
    
	public void setPointsTotaux(int idJoueur, int manche, int points) {
		this.pointsTotaux[idJoueur][manche] = points;
	}
	
	public int getPointsManche(int idJoueur, int manche) {
		return this.pointsTotaux[idJoueur][manche];
	}
	
	public int getPointsTotaux(int idJoueur) {
		int points = 0;
		for (int i = 0; i < (this.getNumMancheActuelle()+1); i++) {
			points += this.pointsTotaux[idJoueur][i];
		}
		return points;
	}
	
	public int getNumMancheActuelle() {
		return this.mancheActuelle;
	}
	
	public Manche getMancheActuelle() {
		return this.manches[this.mancheActuelle];
	}
	

 


	//	Main
    public static void main(String[] args) {
    	//System.out.println("\n\t---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	AsciiArt.welcome();

    	//	Demande de paramètres à l'utilisateur
    	Parametre parametre = new Parametre();
    	parametre.parametrerPartie();
    	
    	//	Paramétrage rapide, sans passer par le formulaire
<<<<<<< Updated upstream
    	//String[] noms = {"Roméo", "Juliette", "Léa l'IA"};
		//Parametre parametre = new Parametre(3, 0, noms, FormesPlateau.ECHELLE, ModeJeu.NORMAL, 2);
=======
    	String[] noms = {"Roméo", "Juliette", "Léa l'IA"};
		Parametre parametre = new Parametre(2, 0, noms, FormesPlateau.COEUR, ModeJeu.NORMAL, 1);
>>>>>>> Stashed changes
    	
    	Partie maPartie = Partie.createPartie(parametre);
    	maPartie.jouerPartie();
    	AsciiArt.thanks();
    }

}
