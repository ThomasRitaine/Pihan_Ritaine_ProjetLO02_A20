package jeu;


public class Case {

    private boolean vide;

    private Carte carte;

    private int coordX;

    private int coordY;

    public Carte Contient;

    boolean isVide() {
        return this.vide;
    }

    void setVide(boolean value) {
        this.vide = value;
    }

    void setCarte(Carte value) {
        this.carte = value;
    }

    Carte getCarte() {
        return this.carte;
    }

}
