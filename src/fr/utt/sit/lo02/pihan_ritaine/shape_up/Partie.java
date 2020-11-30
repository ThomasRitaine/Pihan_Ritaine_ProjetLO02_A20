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
		
    	//	Rï¿½cupï¿½ration des paramï¿½tres
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

    
//	Mï¿½thodes
    public void jouerPartie() {
    	//	Pour chacune des manches
    	for (this.mancheActuelle = 0; this.mancheActuelle < this.parametre.getNbManche(); this.mancheActuelle++) {
			this.manches[this.mancheActuelle] = new Manche(this);
			this.manches[this.mancheActuelle].jouerManche();
			this.afficherScores();
		}
    }
    

    public void afficherScores() {
    	String nomJoueur;
    	int points;
    	
    	System.out.println("Voici les points totaux :");
    	for (int idJoueur = 0; idJoueur < this.getParametre().getNbJoueur(); idJoueur++) {
    		nomJoueur = this.getJoueurParId(idJoueur).getNom();
    		points = this.getPointsTotaux(idJoueur);
    		System.out.println(nomJoueur + " possï¿½de " + points + " points.");
    	}
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
    	System.out.println("\n---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	
    	//	Demande de paramï¿½tres ï¿½ l'utilisateur
    	Parametre parametre = new Parametre();
    	parametre.parametrerPartie();
    	
    	//	Paramï¿½trage rapide, sans passer par le formulaire
    	//String[] noms = {"Romï¿½o", "Juliette", "Lï¿½a l'IA"};
		//Parametre parametre = new Parametre(3, 2, noms, FormesPlateau.ROND, ModeJeu.AVANCE, 2);
    	//Parametre parametre = new Parametre(3, 2, noms, FormesPlateau.RECTANGLE, ModeJeu.NORMAL, 2);
    	
    	Partie maPartie = Partie.createPartie(parametre);
    	maPartie.jouerPartie();
    	AsciiArt.thanks();
    }

}
