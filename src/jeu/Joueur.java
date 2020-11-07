package jeu;

public class Joueur {
	
//	Attributs
    protected String nom;
    protected Carte carteVictoire;
    protected Carte carteAJouer;
    protected int id;
    protected InterfaceNatureJoueur typeJouer = new jouerHumain();

    
//	Constructeur
    public Joueur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

    
//	Méthodes
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
