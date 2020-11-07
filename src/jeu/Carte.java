package jeu;

public class Carte {
	
	//	Enumérations
	public enum Forme {
    	CARRE,
    	ROND,
    	TRIANGLE;
    }
	
	public enum Couleur {
    	VERT,
    	BLEU,
    	ROUGE;
    }
	
	//	Attributs
    private Forme forme;
    private boolean rempli;
    private Couleur couleur;

    
    //	Constructeur
    public Carte(boolean rempli, Forme forme, Couleur couleur) {
		this.rempli = rempli;
		this.forme = forme;
		this.couleur = couleur;
	}
    

    //	Getter
    boolean estRempli() {
        return this.rempli;
    }

    Forme getForme() {
        return this.forme;
    }
    
    Couleur getCouleur() {
        return this.couleur;
    }

}
