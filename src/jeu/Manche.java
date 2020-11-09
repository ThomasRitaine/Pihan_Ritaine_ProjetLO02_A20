package jeu;

public class Manche implements Visitable {
	
//	Attributs
    private Plateau plateau;
    private Pioche pioche;
    
//	Constructeur
    public Manche(Partie partieEnCours) {
		this.plateau = new Plateau(partieEnCours.getParametre().getFormePlateau());
		this.pioche = Pioche.getPioche();
		this.attribuerCarte(partieEnCours);
	}
    
    
//	M�thodes 
    public void attribuerCarte(Partie partieEnCours) {
		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
			Joueur joueur = partieEnCours.getJoueurParId(i);
			Carte cartePiochee = this.pioche.piocher();
			joueur.setCarteVictoire(cartePiochee);
		}
	}
    
    public void jouerManche(Partie partieEnCours) {
    	int tour = 0;
    	while (!this.pioche.estVide()) {
			this.jouerTour(partieEnCours, tour);
			tour++;
		}
		
    	//	Fin de la manche, on supprime la pioche
    	this.supprimerPioche();
	}
    
    private void jouerTour(Partie partieEnCours, int tour) {
    		//	Obtenir le joueur
    	int idJoueur = tour%partieEnCours.getParametre().getNbJoueur();
    	Joueur joueur = partieEnCours.getJoueurParId(idJoueur);
    	
			//	Donner une carte � un joueur
		Carte cartePiochee = this.pioche.piocher();
		joueur.setCarteAJouer(cartePiochee);
		
			//	Jouer le tour du joueur
		joueur.typeJouer.jouerTour();
	}
    
    public void supprimerPioche() {
    	//	Pour supprimer la pioche, on supprime sa seule r�f�rence
    	this.pioche = null;
    		//	Ensuite, on indique � la classe Pioche qu'on a supprim� l'�l�ment
    	Pioche.supprime();
	}

    public int accept(Visitor v) {
		return 0;
    }

    Plateau getPlateau() {
        return this.plateau;
    }

}
