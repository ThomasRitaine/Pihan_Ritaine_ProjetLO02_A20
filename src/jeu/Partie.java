package jeu;

import jeu.Plateau.FormesPlateau;

public class Partie {
	
//	Attributs
    private Joueur[] joueurHumain;
    private JoueurIA[] joueurIA;
    private int[][] pointsTotaux = new int[3][4];
    private Manche[] manches = new Manche[4];
    private Parametre parametre;
 
    
    
//	Constructeur
    public Partie(int nbJoueur, int nbJoueurHumain, String[] noms, FormesPlateau formePlateau) {
		
    	//	Initialisation des paramètres
    	this.parametre = new Parametre(nbJoueur, nbJoueurHumain, formePlateau);
    	
    	//	Initialisation des joueurs
		this.joueurHumain = new Joueur[this.parametre.getNombreJoueurHumain()];
		this.joueurIA = new JoueurIA[this.parametre.getNombreJoueurIA()];
		
		if (this.parametre.getNombreJoueurHumain() > 0) {
			for (int i = 0; i < this.parametre.getNombreJoueurHumain(); i++) {
				this.joueurHumain[i] = new Joueur(i, noms[i]);
			}
		}
		
		if (this.parametre.getNombreJoueurIA() > 0) {
			for (int i = this.parametre.getNombreJoueurHumain(); i < this.parametre.getNombreJoueurs(); i++) {
				this.joueurIA[i] = new JoueurIA(i, noms[i]);
			}
		}
	}
    
    

    
//	Méthodes
    public void demarrer() {
    }

    public void mancheSuivante() {
    }

    public void afficherScores() {
    }

    public void finir() {
    }

    public Manche mancheActuelle() {
		return this.manches[0];
    }

    /*
    public Joueur[] getJoueurHumain() {
        return this.joueurHumain;
    }

    public void setJoueurHumain(Joueur[] value) {
        this.joueurHumain = value;
    }

    public void setJoueurIA(Joueur IA[] value) {
        this.joueurIA = value;
    }

    public Joueur IA[] getJoueurIA() {
        return this.joueurIA;
    }
    */
    
    
    public static void main(String[] args) {
    	System.out.println("---  Bienvenue dans le jeu Shape Up !  ---\n\n");
    	
    	Partie maPartie = new Partie();
    }

}
