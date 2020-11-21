package jeu;

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	protected boolean poserCarte(int coordX, int coordY) {
		boolean reussite = true;
		Case emplacement = this.plateau.getCase(coordX, coordY);
		if(this.plateau.peutPoserCarte(emplacement)) {
			//	On met la carte du joueur sur la case
			emplacement.setCarte(this.joueur.getCarteAJouer());
			//	Puis on supprime la carte à jouer du joueur
			this.joueur.setCarteAJouer(null);
   	 	} else {
   	 		reussite = false;
   	 	}
		return reussite;
	}
	
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
