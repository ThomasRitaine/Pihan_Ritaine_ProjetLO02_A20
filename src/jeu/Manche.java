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
    
    
//	Méthodes 
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
		
    	//	Fin de la manche
    		//	On supprime la pioche
    	this.supprimerPioche();
    		//	Puis on calcule les points
    	this.calculerPts(partieEnCours);
	}
    
    private void jouerTour(Partie partieEnCours, int tour) {
    		//	Obtenir le joueur
    	int idJoueur = tour%partieEnCours.getParametre().getNbJoueur();
    	Joueur joueur = partieEnCours.getJoueurParId(idJoueur);
    	
			//	Donner une carte à un joueur
		Carte cartePiochee = this.pioche.piocher();
		joueur.setCarteAJouer(cartePiochee);
		
			//	Jouer le tour du joueur
		joueur.typeJouer.jouerTour();
	}
    
    public void supprimerPioche() {
    	//	Pour supprimer la pioche, on supprime sa seule référence
    	this.pioche = null;
    		//	Ensuite, on indique à la classe Pioche qu'on a supprimé l'élément
    	Pioche.supprime();
	}
    
    private void calculerPts(Partie partieEnCours) {
    	int points = 0;
    	Carte carteVictoire;
    	for (int idJoueur = 0; idJoueur < partieEnCours.getParametre().getNbJoueur(); idJoueur++) {
    		carteVictoire = partieEnCours.getJoueurParId(idJoueur).getCarteVictoire();
    		partieEnCours.getCalculateurPts().setCarteVictoire(carteVictoire);
    		points = this.accept(partieEnCours.getCalculateurPts());
			partieEnCours.setPointsTotaux(idJoueur, partieEnCours.getMancheActuelle(), points);
		}
	}

    public int accept(Visitor v) {
		return v.visit(this);
    }

    Plateau getPlateau() {
        return this.plateau;
    }

}
