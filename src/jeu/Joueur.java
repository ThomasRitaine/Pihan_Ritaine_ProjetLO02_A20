package jeu;

public class Joueur {
	
//	Attributs
    protected String nom;
    protected Carte carteVictoire;
    protected Carte carteAJouer;
    protected int id;
    protected Jouer typeJouer = new JouerHumain(this);

    
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

    public String getNom() {
        return this.nom;
    }
    
    public void setCarteVictoire(Carte value) {
        this.carteVictoire = value;
    }

    public Carte getCarteVictoire() {
        return this.carteVictoire;
    }

    public void setCarteAJouer(Carte value) {
        this.carteAJouer = value;
    }

    public Carte getCarteAJouer() {
        return this.carteAJouer;
    }

    public int getId() {
        return this.id;
    }
    
    public Jouer getTypeJouer() {
    	return this.typeJouer;
    }

}
