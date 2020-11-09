package jeu;

public class Plateau {
	
//	Enumération
	public enum FormesPlateau {
    	RECTANGLE,
    	ROND,
    	TRIANGLE;
    }
	
//	Attributs
    private Case[] cases = new Case[15];
    private Carte carteCachee;
    private FormesPlateau forme;

    
//	Constructeur
    public Plateau(FormesPlateau forme) {
		this.forme = forme;
		
		//	Génération des cases
		for (int i = 0; i < 15; i++) {
			this.cases[i] = new Case();
			
		}
	}

//	Méthodes
    public boolean peutPoserCarte(Case emplacement) {
    	//blaabla
		return false;
    }

    void setCarteCachee(Carte value) {
        this.carteCachee = value;
    }

    Carte getCarteCachee() {
        return this.carteCachee;
    }

}
