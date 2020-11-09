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
 
    
    
//	Constructeur
    public Partie(Parametre parametre) {
		
    	//	Récupération des paramètres
    	this.parametre = parametre;
    	
    	//	Initialisation des joueurs
		this.joueurHumain = new Joueur[this.parametre.getNombreJoueurHumain()];
		this.joueurIA = new JoueurIA[this.parametre.getNombreJoueurIA()];
		
		if (this.parametre.getNombreJoueurHumain() > 0) {
			for (int i = 0; i < this.parametre.getNombreJoueurHumain(); i++) {
				this.joueurHumain[i] = new Joueur(i, this.parametre.getNomJoueurs(i));
			}
		}
		
		if (this.parametre.getNombreJoueurIA() > 0) {
			for (int i = this.parametre.getNombreJoueurHumain(); i < this.parametre.getNombreJoueurs(); i++) {
				this.joueurIA[i-this.parametre.getNombreJoueurHumain()] = new JoueurIA(i, this.parametre.getNomJoueurs(i));
			}
		}
		
		//	Initialisation des points
		pointsTotaux = new int[this.parametre.getNombreJoueurs()][this.parametre.getNbManche()];
	}
    
    

    
//	Méthodes
    public void joueurPartie() {
    	//	Pour chacune des manches
    	for (this.mancheActuelle = 0; this.mancheActuelle < this.parametre.getNbManche(); this.mancheActuelle++) {
			this.manches[this.mancheActuelle] = new Manche(this);
			this.manches[this.mancheActuelle].jouerManche(this);
			
		}
    }

    public void mancheSuivante() {
    }

    public void afficherScores() {
    }


    public Manche mancheActuelle() {
		return this.manches[0];
    }
    
    public Joueur getJoueurParId(int id) {
    	Joueur joueur;
    	if (id < this.parametre.getNombreJoueurHumain()) {
			joueur = this.joueurHumain[id];
		} else {
			joueur = this.joueurIA[id - this.parametre.getNombreJoueurHumain()];
		}
		return joueur;
	}
    
    public Parametre getParametre() {
		return this.parametre;
    }
   
    
    
    
    
//	Main
    public static void main(String[] args) {
    	System.out.println("---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	
    	Parametre parametre = new Parametre();
    	parametre.parametrerPartie();
    	
    	//	Paramétrage rapide, sans passer par le formulaire
    	//String[] noms = {"Pierre", "Juliette", "Ordi_1"};
    	//Parametre parametre = new Parametre(3, 2, noms, FormesPlateau.RECTANGLE, 4);
    	
    	Partie maPartie = new Partie(parametre);
    	maPartie.joueurPartie();
    	
    }

}
