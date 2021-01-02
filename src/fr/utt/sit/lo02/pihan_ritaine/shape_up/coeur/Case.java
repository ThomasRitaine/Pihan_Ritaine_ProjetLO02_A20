package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;
/**
 * Case est la classe définissant une case d'un plateau de jeu ShapeUp.
 * 
 *  
 * 
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 */
public class Case {

//Attributs
	private Carte carte;// si carte value = null alors case vide

/**
 * Constructeur, initialise une case sans carte. 
 */
	public Case() {
		this.setCarte(null);
	}
	
/**
 * Récupère la carte présente sur la case considérée. 
 * @return
 * La carte présente sur la case.
 */
	public Carte getCarte() {
		return this.carte;
	}
/**
 * Permet de définir la valeur de la carte présente sur la case considérée.
 * @param value Valeur de la carte qui doit être associée à la carte de la case.
 */
	public void setCarte(Carte value) {
		this.carte = value;
	}

	// Methodes	
	
/**
 * Test si une case contient une carte ou non. 
 * @return
 * Le résultat du test : vrai ou faux. 
 */
	boolean estVide() {
		boolean vide = false;
		if (this.getCarte() == null) {
			vide = true;
		}
		return vide;
	}

}
