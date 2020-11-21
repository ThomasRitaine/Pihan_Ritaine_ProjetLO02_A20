package jeu;

public class JouerIA extends Jouer{
	
	public JouerIA(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom());
    	
    	
    }

}
