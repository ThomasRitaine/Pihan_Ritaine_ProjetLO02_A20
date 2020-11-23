package jeu;

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	protected boolean poserCarte(int coordX, int coordY, int idCarte) {
		boolean reussite = true;
		Case emplacement = this.plateau.getCase(coordX, coordY);
		if (emplacement != null) {
			if(this.plateau.peutPoserCarte(emplacement)) {
				//	On met la carte du joueur sur la case
				emplacement.setCarte(this.joueur.getCarteDeMain(idCarte));
				//	Puis on supprime la carte à jouer du joueur
				this.joueur.setCarteDeMain(idCarte, null);
	   	 	} else {
	   	 		reussite = false;
	   	 	}
		} else {
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
