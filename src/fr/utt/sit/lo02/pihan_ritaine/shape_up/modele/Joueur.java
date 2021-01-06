package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
/**
 * Joueur est la classe définissant un joueur. 
 * Elle associe a un joueur un nom, une main pouvant disposer de 3 cartes ou d'une en fonction du mode de jeu, d'un identifiant, d'un mode de jeu, et d'une nature : joueur humain ou IA.
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 */
public class Joueur {
	
//	Attributs
    protected String nom;
    protected Carte[] main = {null, null, null};
    protected int id;
    protected ModeJeu modeJeu;
    protected Jouer typeJouer = new JouerHumain(this);

    
//	Constructeur
    /**
     * Initialise l'indentifiant, le nom et le mode de jeu d'un joueur.
     * @param id - L'indentifiant du joueur.
     * @param nom - Le nom du joueur.
     * @param modeJeu - Le mode de jeu du joueur.
     */
    public Joueur(int id, String nom, ModeJeu modeJeu) {
		this.id = id;
		this.nom = nom;
		this.modeJeu = modeJeu;
	}

    
//	Méthodes
	/**
	 * Récupère le nom du joueur.
	 * @return Le nom du joueur.
	 */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Initialise la carte victoire d'un joueur.
     * @param value - La carte victoire du joueur.
     */
    public void setCarteVictoire(Carte value) {
    	this.main[0] = value;
    }
    
	/**
	 * Récupère la carte victoire d'un joueur.
	 * @return La carte victoire du joueur.
	 */
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

    /**
     * Initialise la carte à jouer d'un joueur.
     * @param value - La carte à jouer.
     */
    public void setCarteAJouer(Carte value) {
        this.main[1] = value;
    }

    /**
     * Récupère la carte à jouer d'un joueur.
     * @return La carte à jouer.
     */
    public Carte getCarteAJouer() {
        return this.main[1];
    }
    
    /**
     * Annonce si la main d'un joueur est pleine ou non.
     * @return Vrai ou faux selon si la main du joueur est pleine ou non.
     */
    public boolean mainPleine() {
    	boolean plein = true;
    	for (int i = 0; i < 3; i++) {
    		if (this.main[i] == null) {
    			plein = false;
    		}
		}
        return plein;
    }
    
    /**
     * Donne le nombre de cartes dans la main d'un joueur.
     * @return Le nombre de cartes de la main du joueur.
     */
    public int nbCarteDansMain() {
    	int nbCarteDansMain = 0;
    	for (int i = 0; i < 3; i++) {
    		if (this.main[i] != null) {
    			nbCarteDansMain++;
    		}
		}
		return nbCarteDansMain;
	}
    
    /**
     * Annonce s'il est possible ou non d'ajouter une carte à la main d'un joueur. 
     * Si cela est possible, la carte est ajoutée.
     * @param carte - La carte à ajouter.
     * @return Vrai ou faux selon la possibilité ou non d'ajouter la carte.
     */
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

    /**
     * Récupère une carte dans la main d'un joueur suivant sont indice.
     * @param indice - Le numéro attribué à la carte présente dans la main du joueur.
     * @return La carte associé à l'indice.
     */
    public Carte getCarteDeMain(int indice) {
        return this.main[indice];
    }
    
    /**
     * Place une carte dans la main d'un joueur.
     * @param indice - Le numéro attribué à la carte présente dans la main du joueur.
     * @param carte - La carte devant être ajoutée à la main.
     */
    public void setCarteDeMain(int indice, Carte carte) {
        this.main[indice] = carte;
    }

    /**
     * Récupère l'identifiant d'une carte.
     * @return L'indentifiant de la carte.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Récupère la nature d'un joueur : humain ou IA.
     * @return La nature du joueur.
     */
    public Jouer getTypeJouer() {
    	return this.typeJouer;
    }

}
