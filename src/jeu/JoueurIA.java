package jeu;

public class JoueurIA extends Joueur {
	
//	Attributs
    private int niveauDifficulte = 0;
    
//	Constructeur
    public JoueurIA(int id, String nom) {
		super(id, nom);
		this.typeJouer = new JouerIA(this);
	}

    int getNiveauDifficulte() {
        return this.niveauDifficulte;
    }

}
