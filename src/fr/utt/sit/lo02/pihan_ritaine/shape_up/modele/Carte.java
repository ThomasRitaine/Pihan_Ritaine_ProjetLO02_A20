package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;
/**
 * <b>Carte est la classe définissant une carte du jeu ShapeUp.</b>
 * <p>
 * Elle est caractérisée par des membres définissant l'aspect des cartes du jeu :  forme, couleur et remplissage (nommé "remplie" dans le code).
 * Elle possède un constructeur initialisant ces membres relatifs à l'aspect, ainsi que les getters associés, une fonction toString permettant l'affichage d'une carte et une fonction getCode qui propose un affichage plus conscis, utilisé lors de l'affichage d'un plateau. 
 * </p>
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 */
public class Carte {
	
//	Enumérations
	/**
	 * <b>Enumeration définissant les différentes formes possibles d'une carte.</b>
	 * <p></p>
	 *
	 */
	public enum FormesCarte {
    	CARRE,
    	ROND,
    	TRIANGLE;
    }
	
	/**
	 * <b>Enumeration définissant les différentes couleurs possibles d'une carte.</b>
	 * <p></p>
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
	 * <b>Constructeur initialisant les 3 aspects d'une carte : sa forme, sa couleur, son remplissage.</b>
	 * <p></p>
	 *@param remplie Définit le remplissage de la carte.
	 *@param forme Définit la forme de la carte.
	 *@param couleur Définit la couleur de la carte.
	 */
    public Carte(boolean remplie, FormesCarte forme, CouleursCarte couleur) {
		this.remplie = remplie;
		this.forme = forme;
		this.couleur = couleur;
	}
    

//	Méthode
    /**
	 * <b>Définit l'affichage d'une carte.</b>
	 * <p>En pratique affiche FORME COULEUR REMPLISSAGE.</p>
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
	 * <b>Définit l'affichage du code d'une carte.</b>
	 * <p>En pratique retourne FCR (première lettres de forme, couleur et remplissage).</p>
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
	 * <b>Retourne le remplissage d'une carte.</b>
	 * <p></p>
	 *@return Le remplissage de la carte.
	 */
    boolean estRemplie() {
        return this.remplie;
    }

    /**
   	 * <b>Retourne la forme d'une carte.</b>
   	 * <p></p>
   	 *@return La forme de la carte.
   	 */
    FormesCarte getForme() {
        return this.forme;
    }
    
    /**
   	 * <b>Retourne la couleur d'une carte.</b>
   	 * <p></p>
   	 *@return La couleur de la carte.
   	 */
    CouleursCarte getCouleur() {
        return this.couleur;
    }

}
