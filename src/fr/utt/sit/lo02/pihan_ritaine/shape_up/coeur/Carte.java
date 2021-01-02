package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;
/**
 * Carte est la classe d�finissant une carte du jeu ShapeUp.
 * 
 * Elle est caract�ris�e par des membres d�finissant l'aspect des cartes du jeu :  forme, couleur et remplissage (nomm� "remplie" dans le code).
 * Elle poss�de un constructeur initialisant ces membres relatifs � l'aspect, ainsi que les getters associ�s, une fonction toString permettant l'affichage d'une carte et une fonction getCode qui propose un affichage plus conscis, utilis� lors de l'affichage d'un plateau. 
 * 
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 */
public class Carte {
	
//	Enum�rations
	/**
	 * Enumeration d�finissant les diff�rentes formes possibles d'une carte.
	 * 
	 *
	 */
	public enum FormesCarte {
    	CARRE,
    	ROND,
    	TRIANGLE;
    }
	
	/**
	 * Enumeration d�finissant les diff�rentes couleurs possibles d'une carte.
	 * 
	 *
	 */
	public enum CouleursCarte {
    	VERT,
    	BLEU,
    	ROUGE;
    }
	
//	Attributs
    private FormesCarte forme;
    private boolean remplie;
    private CouleursCarte couleur;

    
//	Constructeur
    /**
	 * Constructeur initialisant les 3 aspects d'une carte : sa forme, sa couleur, son remplissage.
	 * 
	 *@param remplie D�finit le remplissage de la carte.
	 *@param forme D�finit la forme de la carte.
	 *@param couleur D�finit la couleur de la carte.
	 */
    public Carte(boolean remplie, FormesCarte forme, CouleursCarte couleur) {
		this.remplie = remplie;
		this.forme = forme;
		this.couleur = couleur;
	}
    

//	M�thode
    /**
	 * D�finit l'affichage d'une carte.
	 * En pratique affiche FORME COULEUR REMPLISSAGE.
	 *
	 */
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.forme.toString());
		sb.append(" ");
		sb.append(this.couleur.toString());
		sb.append(" ");
		if (this.remplie) {
			sb.append("REMPLIE");
		} else {
			sb.append("VIDE");
		}
		return sb.toString();
    }
    
    /**
	 * D�finit l'affichage du code d'une carte.
	 * En pratique affiche FCR (premi�re lettres de forme, couleur et remplissage).
	 *
	 */
    public String getCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(this.forme.toString().charAt(0)));
		sb.append(String.valueOf(this.couleur.toString().charAt(0)));
		if (this.remplie) {
			sb.append("R");
		} else {
			sb.append("V");
		}
		return sb.toString();
    } 
    

//	Getter
    /**
	 * Retourne le remplissage d'une carte.
	 * 
	 *@return Le remplissage de la carte.
	 */
    boolean estRemplie() {
        return this.remplie;
    }

    /**
   	 * Retourne la forme d'une carte.
   	 * 
   	 *@return La forme de la carte.
   	 */
    FormesCarte getForme() {
        return this.forme;
    }
    
    /**
   	 * Retourne la couleur d'une carte.
   	 * 
   	 *@return La couleur de la carte.
   	 */
    CouleursCarte getCouleur() {
        return this.couleur;
    }

}
