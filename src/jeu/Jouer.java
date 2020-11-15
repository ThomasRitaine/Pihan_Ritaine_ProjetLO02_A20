package jeu;

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	protected boolean poserCarte(int coordX, int coordY) {
		boolean reussite = true;
		Case emplacement = this.plateau.rechercheCase(coordX, coordY);
		if(this.plateau.peutPoserCarte(emplacement)) {
			//	On met la carte du joueur sur la case
			emplacement.setCarte(this.joueur.getCarteAJouer());
			//	Puis on supprime la carte � jouer du joueur
			this.joueur.setCarteAJouer(null);
   	 	} else {
   	 		reussite = false;
   	 	}
		return reussite;
	}
	
	protected boolean bougerCarte(int coordX1, int coordY1, int coordX2, int coordY2) {
		Case depuis = this.plateau.rechercheCase(coordX1, coordY1);
		Case vers = this.plateau.rechercheCase(coordX2, coordY2);
		boolean reussite = this.plateau.bougerCarte(depuis, vers);
		return reussite;
	}
	
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
