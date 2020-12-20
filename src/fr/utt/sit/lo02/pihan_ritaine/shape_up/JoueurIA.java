package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;
/**
 * JoueurIA est la classe h�ritant de la classe m�re Joueur.
 * Elle sp�cifie le cas des joueurs IA qui poss�dent en plus des caract�ristiques d'un joueur normal (par d�faut humain est normal) : les joueurs IA ont des niveaux de difficult�.
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 *
 */
public class JoueurIA extends Joueur {
	
//	Attributs
    private int niveauDifficulte = 0;
    
//	Constructeur
    /**
     * Initialise un joueur IA.
     * Elle utilise le constructeur de la super classe Joueur et initialise le type de jeu comme celui d'une IA. 
     * @param id
     * @param nom
     * @param modeJeu
     */
    public JoueurIA(int id, String nom, ModeJeu modeJeu) {
		super(id, nom, modeJeu);
		this.typeJouer = new JouerIA(this);
	}

    /**
     * R�cup�re le niveau de difficult� du joueur.
     * @return Le niveau de difficult� du joueur.
     */
    int getNiveauDifficulte() {
        return this.niveauDifficulte;
    }

}
