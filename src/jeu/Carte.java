package jeu;

public class Carte {
	
    private String forme;
    private boolean rempli;
    private String couleur;


    String getCouleur() {
        return this.couleur;
    }


    boolean isRempli() {
        return this.rempli;
    }


    String getForme() {
        return this.forme;
    }

}
