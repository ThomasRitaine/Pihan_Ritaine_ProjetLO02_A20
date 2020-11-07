package jeu;

public class Carte {
	
	//	Enumérations
	public enum Formes {
    	CARRE,
    	ROND,
    	TRIANGLE;
    }
	
	public enum Couleurs {
    	VERT,
    	BLEU,
    	ROUGE;
    }
	
	//	Attributs
    private Formes forme;
    private boolean rempli;
    private Couleurs couleur;

    
    //	Constructeur
    public Carte(boolean rempli, Formes forme, Couleurs couleur) {
		this.rempli = rempli;
		this.forme = forme;
		this.couleur = couleur;
	}
    

    //	Getter
    boolean estRempli() {
        return this.rempli;
    }

    Formes getForme() {
        return this.forme;
    }
    
    Couleurs getCouleur() {
        return this.couleur;
    }

}
