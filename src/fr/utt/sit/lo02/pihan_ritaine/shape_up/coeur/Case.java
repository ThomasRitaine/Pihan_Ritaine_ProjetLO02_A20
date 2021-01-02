package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;
/**
 * Case est la classe d�finissant une case d'un plateau de jeu ShapeUp.
 * 
 *  
 * 
 * @author Ya�lle Pihan et Thomas Ritaine
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
 * R�cup�re la carte pr�sente sur la case consid�r�e. 
 * @return
 * La carte pr�sente sur la case.
 */
	public Carte getCarte() {
		return this.carte;
	}
/**
 * Permet de d�finir la valeur de la carte pr�sente sur la case consid�r�e.
 * @param value Valeur de la carte qui doit �tre associ�e � la carte de la case.
 */
	public void setCarte(Carte value) {
		this.carte = value;
	}

	// Methodes	
	
/**
 * Test si une case contient une carte ou non. 
 * @return
 * Le r�sultat du test : vrai ou faux. 
 */
	boolean estVide() {
		boolean vide = false;
		if (this.getCarte() == null) {
			vide = true;
		}
		return vide;
	}

}
