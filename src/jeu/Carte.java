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
    private boolean remplie;
    private CouleursCarte couleur;

    
//	Constructeur
    public Carte(boolean remplie, FormesCarte forme, CouleursCarte couleur) {
		this.remplie = remplie;
		this.forme = forme;
		this.couleur = couleur;
	}
    

//	Méthode
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.forme.toString());
		sb.append(" ");
		sb.append(this.couleur.toString());
		sb.append(" ");
		if (this.remplie) {
			sb.append("REMPLIE");
		} else {
			sb.append("VIDE");
		}
		return sb.toString();
    }
    
    public String getCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(this.forme.toString().charAt(0)));
		sb.append(String.valueOf(this.couleur.toString().charAt(0)));
		if (this.remplie) {
			sb.append("R");
		} else {
			sb.append("V");
		}
		return sb.toString();
    } 
    

//	Getter
    boolean estRemplie() {
        return this.remplie;
    }

    FormesCarte getForme() {
        return this.forme;
    }
    
    CouleursCarte getCouleur() {
        return this.couleur;
    }

}
