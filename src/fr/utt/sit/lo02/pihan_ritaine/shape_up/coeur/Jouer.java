package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;
/**
 * Jouer est la classe mère de JouerHumain et JouerIA. 
 *
 * Elle regroupe les membres communs des deux classes c'est à dire les fonctions communes aux joueurs quelque soit leur nature : 
 *  le constructeur initialisant le joueur,
 *  la méthode poserCarte,
 *  la méthode affichant la main du joueur pour le mode avancé,
 *  la méthode initialisant le plateau.
 * 
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 */

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	//Constructeur
	/**
	 *Initialise le joueur à qui le tour revient.
	 * 
	 * @param joueur Joueur à qui le tour revient.
	 */
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	/**
	 *Permet au joueur de poser une carte.
	 *   
	 * @param plateau Plateau de la manche au moment où cette fonction est appelée.
	 * @param coordX Coordonnée X de la case du plateau sur laquelle est posée la carte.
	 * @param coordY Coordonnée Y de la case du plateau sur laquelle est posée la carte.
	 * @param idCarte Identifiant de la carte.
	 * @return Le booléen reussite, annoçant si la carte a bien pu être poser.
	 * @see Joueur#getCarteDeMain(int)
	 * @see Joueur#poserCarte(int, int, Carte)
	 */
	protected boolean poserCarte(Plateau plateau, int coordX, int coordY, int idCarte) {
		boolean reussite = true;
		
		//	On récupère la carte du joueur
		Carte carteAPoser = this.joueur.getCarteDeMain(idCarte);
		
		if(plateau.poserCarte(coordX, coordY, carteAPoser)) {
			
			//	Si la pose est une réussite, on enlève la carte au joueur
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
	 * @param plateau Plateau de la manche au moment où cette fonction est appelée.
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
