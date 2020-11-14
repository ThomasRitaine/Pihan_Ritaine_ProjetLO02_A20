package jeu;

import jeu.Plateau.FormesPlateau;

public class Partie {
	
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
				this.joueurHumain[i] = new Joueur(i, this.parametre.getNomJoueurs(i));
			}
		}
		
		if (this.parametre.getNbJoueurIA() > 0) {
			for (int i = this.parametre.getNbJoueurHumain(); i < this.parametre.getNbJoueur(); i++) {
				this.joueurIA[i-this.parametre.getNbJoueurHumain()] = new JoueurIA(i, this.parametre.getNomJoueurs(i));
			}
		}
		
		//	Initialisation des points
		this.calculateurPts = new CalculePointsVisitor();
		this.pointsTotaux = new int[this.parametre.getNbJoueur()][this.parametre.getNbManche()];
	}
    
    

    
//	Méthodes
    public void jouerPartie() {
    	//	Pour chacune des manches
    	for (this.mancheActuelle = 0; this.mancheActuelle < this.parametre.getNbManche(); this.mancheActuelle++) {
			this.manches[this.mancheActuelle] = new Manche(this);
			this.manches[this.mancheActuelle].jouerManche(this);
			System.out.println("Fin de la manche " + (this.mancheActuelle+1) + " sur " + this.parametre.getNbManche()+"\n\n");
		}
    }
    

    public void afficherScores() {
    }


    public Manche mancheActuelle() {
		return this.manches[0];
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
	
	public int getMancheActuelle() {
		return this.mancheActuelle;
	}
	




	//	Main
    public static void main(String[] args) {
    	System.out.println("---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	
    	//	Demande de paramètres à l'utilisateur
    	//Parametre parametre = new Parametre();
    	//parametre.parametrerPartie();
    	
    	//	Paramétrage rapide, sans passer par le formulaire
    	String[] noms = {"Pierre", "Juliette", "Ordi_1"};
    	Parametre parametre = new Parametre(3, 2, noms, FormesPlateau.RECTANGLE, 2);
    	
    	Partie maPartie = new Partie(parametre);
    	maPartie.jouerPartie();
    	
    }

}
