package fr.utt.sit.lo02.pihan_ritaine.shape_up;


public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	//Constructeur
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
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
	
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
