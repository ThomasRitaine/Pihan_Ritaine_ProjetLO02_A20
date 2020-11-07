package jeu;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;

    private Carte carteVictoire;
    private Carte carteAJouer;

    private int id;

    private InterfaceNatureJoueur typeJouer;

    //public List<Carte>  = new ArrayList<Carte> ();

    //public List<InterfaceNatureJoueur>  = new ArrayList<InterfaceNatureJoueur> ();

    public void bougerCarte() {
    }

    public void poserCarte() {
    }

    public void piocherCarte() {
    }

    String getNom() {
        return this.nom;
    }

    Carte getCarteVictoire() {
        return this.carteVictoire;
    }

    void setCarteAJouer(Carte value) {
        this.carteAJouer = value;
    }

    Carte getCarteAJouer() {
        return this.carteAJouer;
    }

    int getId() {
        return this.id;
    }

}
