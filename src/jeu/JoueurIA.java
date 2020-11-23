package jeu;

import jeu.Parametre.ModeJeu;

public class JoueurIA extends Joueur {
	
//	Attributs
    private int niveauDifficulte = 0;
    
//	Constructeur
    public JoueurIA(int id, String nom, ModeJeu modeJeu) {
		super(id, nom, modeJeu);
		this.typeJouer = new JouerIA(this);
	}

    int getNiveauDifficulte() {
        return this.niveauDifficulte;
    }

}
