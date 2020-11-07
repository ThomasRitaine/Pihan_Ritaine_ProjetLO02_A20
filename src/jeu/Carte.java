package jeu;

public class Carte {
	
//	Enumérations
	public enum FormesCarte {
    	CARRE,
    	ROND,
    	TRIANGLE;
    }
	
	public enum CouleursCarte {
    	VERT,
    	BLEU,
    	ROUGE;
    }
	
//	Attributs
    private FormesCarte forme;
    private boolean rempli;
    private CouleursCarte couleur;

    
//	Constructeur
    public Carte(boolean rempli, FormesCarte forme, CouleursCarte couleur) {
		this.rempli = rempli;
		this.forme = forme;
		this.couleur = couleur;
	}
    

//	Getter
    boolean estRempli() {
        return this.rempli;
    }

    FormesCarte getForme() {
        return this.forme;
    }
    
    CouleursCarte getCouleur() {
        return this.couleur;
    }

}
