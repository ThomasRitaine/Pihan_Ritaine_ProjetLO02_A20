package jeu;

public class Pioche {
    private Carte[] cartes = new Carte[18];

    private boolean cree;

    private Pioche() {
    }

    public void getPioche() {
    }

    public Carte piocher() {
		return this.cartes[0];
    }

}
