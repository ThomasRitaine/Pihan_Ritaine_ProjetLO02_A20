package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;

public class Joueur {
	
//	Attributs
    protected String nom;
    protected Carte[] main = {null, null, null};
    protected int id;
    protected ModeJeu modeJeu;
    protected Jouer typeJouer = new JouerHumain(this);

    
//	Constructeur
    public Joueur(int id, String nom, ModeJeu modeJeu) {
		this.id = id;
		this.nom = nom;
		this.modeJeu = modeJeu;
	}

    
//	Méthodes

    public String getNom() {
        return this.nom;
    }
    
    public void setCarteVictoire(Carte value) {
    	this.main[0] = value;
    }

    public Carte getCarteVictoire() {
    	Carte carteVictoire = null;
    	
    	if (this.modeJeu == ModeJeu.NORMAL) {
    		//	Dans ce mode de jeu, la carte de victoire est à la case 0 de la main
    		carteVictoire = this.main[0];
		}
    	
    	if (this.modeJeu == ModeJeu.AVANCE) {
    		//	Dans ce mode de jeu, la dernière carte de la main est la carte de victoire
    		if (this.nbCarteDansMain() == 1) {
    			//	On prend la seule carte qu'il reste dans la main
    			for (int i = 0; i < 3; i++) {
    				if (this.getCarteDeMain(i) != null) {
    					carteVictoire = this.getCarteDeMain(i);
    				}
    			}
			}
		}
    	
        return carteVictoire;
    }

    public void setCarteAJouer(Carte value) {
        this.main[1] = value;
    }

    public Carte getCarteAJouer() {
        return this.main[1];
    }
    
    public boolean mainPleine() {
    	boolean plein = true;
    	for (int i = 0; i < 3; i++) {
    		if (this.main[i] == null) {
    			plein = false;
    		}
		}
        return plein;
    }
    
    public int nbCarteDansMain() {
    	int nbCarteDansMain = 0;
    	for (int i = 0; i < 3; i++) {
    		if (this.main[i] != null) {
    			nbCarteDansMain++;
    		}
		}
		return nbCarteDansMain;
	}
    
    public boolean ajouterCarteMain(Carte carte) {
    	boolean reussite = false;
    	for (int i = 0; i < 3; i++) {
    		if (this.main[i] == null) {
    			this.main[i] = carte;
    			reussite = true;
    		}
		}
        return reussite;
    }

    public Carte getCarteDeMain(int indice) {
        return this.main[indice];
    }
    
    public void setCarteDeMain(int indice, Carte carte) {
        this.main[indice] = carte;
    }

    public int getId() {
        return this.id;
    }
    
    public Jouer getTypeJouer() {
    	return this.typeJouer;
    }

}
