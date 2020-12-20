package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;
/**
 * JoueurIA est la classe héritant de la classe mère Joueur.
 * Elle spécifie le cas des joueurs IA qui possèdent en plus des caractéristiques d'un joueur normal (par défaut humain est normal) : les joueurs IA ont des niveaux de difficulté.
 * @author Yaëlle Pihan et Thomas Ritaine
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
     * Récupère le niveau de difficulté du joueur.
     * @return Le niveau de difficulté du joueur.
     */
    int getNiveauDifficulte() {
        return this.niveauDifficulte;
    }

}
