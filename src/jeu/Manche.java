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
		
		//	Initialisation des Jouer, pour jouer un tour
		Joueur joueur = null;
		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
			joueur = partieEnCours.getJoueurParId(i);
			joueur.getTypeJouer().setPlateau(plateau);
		}
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
    		//	Puis on affiche les scores
    	this.afficherScoresManche(partieEnCours);
	}
    
    private void jouerTour(Partie partieEnCours, int tour) {
    		//	Obtenir le joueur
    	int idJoueur = tour%partieEnCours.getParametre().getNbJoueur();
    	Joueur joueur = partieEnCours.getJoueurParId(idJoueur);
    	
			//	Donner une carte à un joueur
		Carte cartePiochee = this.pioche.piocher();
		joueur.setCarteAJouer(cartePiochee);
		
			//	Jouer le tour du joueur
		joueur.getTypeJouer().jouerTour();
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
    
    private void afficherScoresManche(Partie partieEnCours) {
    	String nomJoueur;
    	int points;
    	int mancheActuelle = partieEnCours.getMancheActuelle();
    	
    	System.out.println("\n---   Fin de la manche " + (mancheActuelle+1) + " sur " + partieEnCours.getParametre().getNbManche() + "   ---");
    	for (int idJoueur = 0; idJoueur < partieEnCours.getParametre().getNbJoueur(); idJoueur++) {
    		nomJoueur = partieEnCours.getJoueurParId(idJoueur).getNom();
    		points = partieEnCours.getPointsManche(idJoueur, mancheActuelle);
    		if(idJoueur == 0) {
    			System.out.println("Lors de cette manche, " + nomJoueur + " a marqué " + points + " points !");
    		} else if (idJoueur == 1) {
    			System.out.println("Quant à " + nomJoueur + ", c'est " + points + " points qui lui reviennent !");
			} else {
				System.out.println("Et pour finir, " + nomJoueur + " remporte " + points + " points, bravo !");
			}
    		
		}
	}

    public int accept(Visitor v) {
		return v.visit(this);
    }

    Plateau getPlateau() {
        return this.plateau;
    }

}
