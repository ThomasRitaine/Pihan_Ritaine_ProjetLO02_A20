package jeu;

import jeu.Parametre.ModeJeu;

public class Manche implements Visitable {
	
//	Attributs
    private Plateau plateau;
    private Pioche pioche;
    private Partie partie;
    
//	Constructeur
    public Manche(Partie partieEnCours) {
		this.plateau = new Plateau(partieEnCours.getParametre().getFormePlateau());
		this.pioche = Pioche.getPioche();
		this.partie = partieEnCours;
		
		this.attribuerCartes();
		
		//	Initialisation des Jouer, pour jouer un tour
		Joueur joueur = null;
		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
			joueur = partieEnCours.getJoueurParId(i);
			joueur.getTypeJouer().setPlateau(plateau);
		}
	}
    
    
//	Méthodes 
    public void attribuerCartes() {
    	
    	if (this.partie.getParametre().getModeJeu() == ModeJeu.NORMAL) {
    		Joueur joueur;
    		for (int i = 0; i < this.partie.getParametre().getNbJoueur(); i++) {
    			joueur = this.partie.getJoueurParId(i);
    			Carte cartePiochee = this.pioche.piocher();
    			joueur.setCarteVictoire(cartePiochee);
    		}
    	}
    	
    	if (this.partie.getParametre().getModeJeu() == ModeJeu.AVANCE) {
    		Joueur joueur;
    		Carte cartePiochee;
    		for (int i = 0; i < this.partie.getParametre().getNbJoueur(); i++) {
    			joueur = this.partie.getJoueurParId(i);
    			for (int j = 0; j < 2; j++) {
    				cartePiochee = this.pioche.piocher();
        			joueur.setCarteDeMain(j, cartePiochee);
				}
    		}
    	}
		
	}
    
    public void jouerManche() {
    	System.out.println("\n\n---   Bébut de la manche " + (this.partie.getMancheActuelle()+1) + " sur " + this.partie.getParametre().getNbManche() + "   ---");
    	int tour = 0;
    	while (!this.mancheFinie()) {
			this.jouerTour(tour);
			tour++;
		}
		
    	//	Fin de la manche
    		//	On supprime la pioche
    	this.supprimerPioche();
    		//	Puis on calcule les points
    	this.calculerPts();
    		//	Puis on affiche les scores
    	this.afficherScoresManche();
	}
    
    public boolean mancheFinie() {
		boolean mancheFinie = true;
		
		if (this.partie.getParametre().getModeJeu() == ModeJeu.NORMAL) {
			mancheFinie = this.pioche.estVide();
		}
		if (this.partie.getParametre().getModeJeu() == ModeJeu.AVANCE) {
			Joueur joueur;
			for (int i = 0; i < this.partie.getParametre().getNbJoueur(); i++) {
				joueur = this.partie.getJoueurParId(i);
				if (joueur.nbCarteDansMain() != 1) {
					mancheFinie = false;
				}
			}
		}
		
		return mancheFinie;
	}
    
    private void jouerTour(int tour) {
    		//	Obtenir le joueur
    	int idJoueur = tour%this.partie.getParametre().getNbJoueur();
    	Joueur joueur = this.partie.getJoueurParId(idJoueur);
    	
			//	Donner une carte au joueur
    	this.donnerCarte(joueur);
		
			//	Jouer le tour du joueur
		joueur.getTypeJouer().jouerTour();
	}
    
    public void donnerCarte(Joueur joueur) {
    	Carte cartePiochee = this.pioche.piocher();
    	
		if (this.partie.getParametre().getModeJeu() == ModeJeu.NORMAL) {
			joueur.setCarteAJouer(cartePiochee);
		}
		if (this.partie.getParametre().getModeJeu() == ModeJeu.AVANCE) {
			joueur.ajouterCarteMain(cartePiochee);
		}
	}
    
    public void supprimerPioche() {
    	//	Pour supprimer la pioche, on supprime sa seule référence
    	this.pioche = null;
    		//	Ensuite, on indique à la classe Pioche qu'on a supprimé l'élément
    	Pioche.supprime();
	}
    
    private void calculerPts() {
    	int points = 0;
    	Carte carteVictoire;
    	for (int idJoueur = 0; idJoueur < this.partie.getParametre().getNbJoueur(); idJoueur++) {
    		carteVictoire = this.partie.getJoueurParId(idJoueur).getCarteVictoire();
    		System.out.println("Carte de victoire de " + this.partie.getJoueurParId(idJoueur).getNom() + " = " + carteVictoire.toString());		// DEBUGG
    		this.partie.getCalculateurPts().setCarteVictoire(carteVictoire);
    		points = this.accept(this.partie.getCalculateurPts());
    		this.partie.setPointsTotaux(idJoueur, this.partie.getMancheActuelle(), points);
		}
	}
    
    private void afficherScoresManche() {
    	String nomJoueur;
    	int points;
    	int mancheActuelle = this.partie.getMancheActuelle();
    	
    	System.out.println("\n---   Fin de la manche " + (mancheActuelle+1) + " sur " + this.partie.getParametre().getNbManche() + "   ---");
    	for (int idJoueur = 0; idJoueur < this.partie.getParametre().getNbJoueur(); idJoueur++) {
    		nomJoueur = this.partie.getJoueurParId(idJoueur).getNom();
    		points = this.partie.getPointsManche(idJoueur, mancheActuelle);
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
