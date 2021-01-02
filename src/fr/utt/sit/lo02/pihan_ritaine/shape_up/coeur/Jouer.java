package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;
/**
 * Jouer est la classe m�re de JouerHumain et JouerIA. 
 *
 * Elle regroupe les membres communs des deux classes c'est � dire les fonctions communes aux joueurs quelque soit leur nature : 
 *  le constructeur initialisant le joueur,
 *  la m�thode poserCarte,
 *  la m�thode affichant la main du joueur pour le mode avanc�,
 *  la m�thode initialisant le plateau.
 * 
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 */

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	//Constructeur
	/**
	 *Initialise le joueur � qui le tour revient.
	 * 
	 * @param joueur Joueur � qui le tour revient.
	 */
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	/**
	 *Permet au joueur de poser une carte.
	 *   
	 * @param plateau Plateau de la manche au moment o� cette fonction est appel�e.
	 * @param coordX Coordonn�e X de la case du plateau sur laquelle est pos�e la carte.
	 * @param coordY Coordonn�e Y de la case du plateau sur laquelle est pos�e la carte.
	 * @param idCarte Identifiant de la carte.
	 * @return Le bool�en reussite, anno�ant si la carte a bien pu �tre poser.
	 * @see Joueur#getCarteDeMain(int)
	 * @see Joueur#poserCarte(int, int, Carte)
	 */
	protected boolean poserCarte(Plateau plateau, int coordX, int coordY, int idCarte) {
		boolean reussite = true;
		
		//	On r�cup�re la carte du joueur
		Carte carteAPoser = this.joueur.getCarteDeMain(idCarte);
		
		if(plateau.poserCarte(coordX, coordY, carteAPoser)) {
			
			//	Si la pose est une r�ussite, on enl�ve la carte au joueur
			this.joueur.setCarteDeMain(idCarte, null);
			
   	 	} else {
   	 		// 	Si la pose est un echec, on remet la carte dans la main du joueur
   	 		this.joueur.setCarteDeMain(idCarte, carteAPoser);
   	 		reussite = false;
   	 	}
		
		return reussite;
	}
	
	/**
	 * Affiche la main du joueur.
	 * @see Joueur#nbCarteDansMain()
	 * @see Joeur#getCarteDeMain(int)
	 */
	protected void afficherMain() {
		System.out.println("Vous avez " + this.joueur.nbCarteDansMain() + " cartes dans votre main :");
		for (int i = 0; i < 3; i++) {
			if (this.joueur.getCarteDeMain(i) != null) {
				System.out.println("\t" + (i+1) + ") " + this.joueur.getCarteDeMain(i).toString());
			}
			else {
				System.out.println("\t" + (i+1) + ") Pas de carte.");
			}
		}
	}
	
	/**
	 * Initialise le plateau.
	 * @param plateau Plateau de la manche au moment o� cette fonction est appel�e.
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
