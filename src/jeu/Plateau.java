package jeu;

public class Plateau {
	
//	Enum�ration
	public enum FormesPlateau {
    	RECTANGLE,
    	ROND,
    	TRIANGLE;
    }
	
//	Attributs
    private Case[] cases = new Case[15];
    private Carte carteCachee;


//	M�thodes
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
