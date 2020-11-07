package jeu;

public class Plateau {
    private Case[] cases = new Case[15];

    private Carte carteCachee;


    public boolean peutPoserCarte(Case emplacement) {
		return false;
    }

    void setCarteCachee(Carte value) {
        this.carteCachee = value;
    }

    Carte getCarteCachee() {
        return this.carteCachee;
    }

}
