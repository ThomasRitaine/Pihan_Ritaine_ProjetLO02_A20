package fr.utt.sit.lo02.pihan_ritaine.shape_up;

public class JouerIA extends Jouer{
	
	public JouerIA(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom());
    	
    //	Mise en place du calculateur de points
    	
    	//  On donne la carte de victoire du joueur au calculateur de points
    	Carte carteVictoire = this.joueur.getCarteVictoire();
		Partie.getPartie().getCalculateurPts().setCarteVictoire(carteVictoire);
		
		//	On récupère la manche
		Manche mancheEnCours = Partie.getPartie().getMancheActuelle();
		
		//	On récupère le calculateur
		CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
		
		//	On calcule les points
		int points = mancheEnCours.accept(calculateur);
    	
    	
    }

}
