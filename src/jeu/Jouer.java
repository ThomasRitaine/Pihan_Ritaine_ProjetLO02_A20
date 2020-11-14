package jeu;

public abstract class Jouer implements InterfaceNatureJoueur{
	
	protected Joueur joueur;
	protected Plateau plateau;
	
	public Jouer(Joueur joueur) {
		this.joueur = joueur;
	}
	
	protected boolean poserCarte(Case emplacement) {
		boolean reussite = true;
		if(plateau.peutPoserCarte(emplacement)){
			//	On met la carte du joueur sur la case
			emplacement.setCarte(joueur.getCarteAJouer());
			//	Puis on supprime la carte à jouer du joueur
			joueur.setCarteAJouer(null);
   	 	} else {
   	 		System.out.println("Veuillez choisir une autre case car celle ci contient déjà une carte, ou n'est pas adjacente à une carte déjà posée ou n'est pas inclue dans le plateau invisible");
   	 		reussite = false;
   	 	}
		return reussite;
	}
	
	void bougerCarte(Case depuis, Case vers) {
		//this.poserCarte(emplacement, carteABouger.getCarte());
		depuis.setCarte(null);
	}
	
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
