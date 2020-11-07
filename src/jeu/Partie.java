package jeu;

public class Partie {
	
    private Joueur[] joueurHumain = new Joueur[3];
    private JoueurIA[] joueurIA = new JoueurIA[3];
    private int[] pointsTotaux = new int[3];
    private Manche[] manches = new Manche[4];
    private Parametre parametre;
 

    public void demarrer() {
    }

    public void mancheSuivante() {
    }

    public void afficherScores() {
    }

    public void finir() {
    }

    public Manche mancheActuelle() {
		return this.manches[0];
    }

    /*
    public Joueur[] getJoueurHumain() {
        return this.joueurHumain;
    }

    public void setJoueurHumain(Joueur[] value) {
        this.joueurHumain = value;
    }

    public void setJoueurIA(Joueur IA[] value) {
        this.joueurIA = value;
    }

    public Joueur IA[] getJoueurIA() {
        return this.joueurIA;
    }
    */

}
