package jeu;

public class Parametre {
    private int nombreJoueurs;

    private int nombreJoueurHumain;

    private int nombreJoueurIA;

    private String formePlateau;

    int getNombreJoueurs() {
        return this.nombreJoueurs;
    }

    void setNombreJoueurs(int value) {
        this.nombreJoueurs = value;
    }

    int getNombreJoueurHumain() {
        return this.nombreJoueurHumain;
    }

    void setNombreJoueurHumain(int value) {
        this.nombreJoueurHumain = value;
    }

    void setNombreJoueurIA(int value) {
        this.nombreJoueurIA = value;
    }

    int getNombreJoueurIA() {
        return this.nombreJoueurIA;
    }

    void setFormePlateau(String value) {
        this.formePlateau = value;
    }

    String getFormePlateau() {
        return this.formePlateau;
    }

}
