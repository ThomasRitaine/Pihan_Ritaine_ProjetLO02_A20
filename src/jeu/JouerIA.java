package jeu;

public class JouerIA extends Jouer{
	
	public JouerIA(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom());
    	
    	
    	
    	//	Test, on simule que le joueur pose sa carte
    	this.afficherMain();
    	
    	if (this.joueur.getCarteDeMain(0) == null) {
    		this.joueur.setCarteDeMain(1, null);
		} else {
			this.joueur.setCarteDeMain(0, null);
		}
    	
    }

}
