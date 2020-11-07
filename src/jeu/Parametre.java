package jeu;

import jeu.Plateau.FormesPlateau;

public class Parametre {
	
//	Attributs
    private int nombreJoueurs;
    private int nombreJoueurHumain;
    private int nombreJoueurIA;
    private FormesPlateau formePlateau;

    
//	Constructeur
    public Parametre(int nbJoueur, int nbJoueurHumain, FormesPlateau formePlateau) {
    	this.nombreJoueurs = nbJoueur;
    	this.nombreJoueurHumain = nbJoueurHumain;
    	this.nombreJoueurIA = nbJoueur - nbJoueurHumain;
    	this.formePlateau = formePlateau;
    }
    
//	Getter et Setter
    public int getNombreJoueurs() {
        return this.nombreJoueurs;
    }

    public void setNombreJoueurs(int value) {
        this.nombreJoueurs = value;
    }

    public int getNombreJoueurHumain() {
        return this.nombreJoueurHumain;
    }

    public void setNombreJoueurHumain(int value) {
        this.nombreJoueurHumain = value;
    }

    public void setNombreJoueurIA(int value) {
        this.nombreJoueurIA = value;
    }

    public int getNombreJoueurIA() {
        return this.nombreJoueurIA;
    }

    public void setFormePlateau(FormesPlateau value) {
        this.formePlateau = value;
    }

    public FormesPlateau getFormePlateau() {
        return this.formePlateau;
    }

}
