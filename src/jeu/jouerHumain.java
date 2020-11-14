package jeu;

public class JouerHumain extends Jouer{
	
	public JouerHumain(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de " + this.joueur.getNom());
    	System.out.println("Votre carte de victoire est " + joueur.getCarteVictoire().toString());
    	System.out.println("La carte à jouer est " + joueur.getCarteAJouer().toString());
    }
    
    

}
