package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;

public class Manche implements Visitable {
	
//	Attributs
    private Plateau plateau;
    private Pioche pioche;
    
//	Constructeur
    public Manche() {
    	Partie partieEnCours = Partie.getPartie();
    	this.pioche = Pioche.getPioche();
    	Carte carteCachee = this.pioche.piocher();
		this.plateau = new Plateau(partieEnCours.getParametre().getFormePlateau(), carteCachee);
		
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
    	
    	Partie partieEnCours = Partie.getPartie();
    	
    	if (partieEnCours.getParametre().getModeJeu() == ModeJeu.NORMAL) {
    		Joueur joueur;
    		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
    			joueur = partieEnCours.getJoueurParId(i);
    			Carte cartePiochee = this.pioche.piocher();
    			joueur.setCarteVictoire(cartePiochee);
    		}
    	}
    	
    	if (partieEnCours.getParametre().getModeJeu() == ModeJeu.AVANCE) {
    		Joueur joueur;
    		Carte cartePiochee;
    		for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
    			joueur = partieEnCours.getJoueurParId(i);
    			for (int j = 0; j < 2; j++) {
    				cartePiochee = this.pioche.piocher();
        			joueur.setCarteDeMain(j, cartePiochee);
				}
    		}
    	}
		
	}
    
    public void jouerManche() {
    	
    	Partie partieEnCours = Partie.getPartie();
    	System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	System.out.println("\n\t---   Bébut de la manche " + (partieEnCours.getNumMancheActuelle()+1) + " sur " + partieEnCours.getParametre().getNbManche() + "   ---");
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
		Partie partieEnCours = Partie.getPartie();
		ModeJeu modeJeu = partieEnCours.getParametre().getModeJeu();
		
		if (modeJeu == ModeJeu.NORMAL) {
			mancheFinie = this.pioche.estVide();
		}
		if (modeJeu == ModeJeu.AVANCE) {
			Joueur joueur;
			for (int i = 0; i < partieEnCours.getParametre().getNbJoueur(); i++) {
				joueur = partieEnCours.getJoueurParId(i);
				if (joueur.nbCarteDansMain() != 1) {
					mancheFinie = false;
				}
			}
		}
		
		return mancheFinie;
	}
    
    private void jouerTour(int tour) {
    	
    	Partie partieEnCours = Partie.getPartie();
    	
    		//	Obtenir le joueur
    	int idJoueur = tour%partieEnCours.getParametre().getNbJoueur();
    	Joueur joueur = partieEnCours.getJoueurParId(idJoueur);
    	
			//	Donner une carte au joueur
    	this.donnerCarte(joueur);
		
			//	Jouer le tour du joueur
		joueur.getTypeJouer().jouerTour();
	}
    
    public void donnerCarte(Joueur joueur) {
    	ModeJeu modeJeu = Partie.getPartie().getParametre().getModeJeu();
    	Carte cartePiochee = this.pioche.piocher();
    	
		if (modeJeu == ModeJeu.NORMAL) {
			joueur.setCarteAJouer(cartePiochee);
		}
		if (modeJeu == ModeJeu.AVANCE) {
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
    	Partie partieEnCours = Partie.getPartie();
    	
    	//	Pour chaque joueur
    	for (int idJoueur = 0; idJoueur < partieEnCours.getParametre().getNbJoueur(); idJoueur++) {
    		
    		//	On prend la carte de victoire
    		carteVictoire = partieEnCours.getJoueurParId(idJoueur).getCarteVictoire();
    		
    		//	On donne cette carte au calculateur de points
    		partieEnCours.getCalculateurPts().setCarteVictoire(carteVictoire);
    		
    		//	On lance le calculateur
    		points = this.accept(partieEnCours.getCalculateurPts());
    		
    		//	On sauvegarde les points dans le tableau des scores
    		partieEnCours.setPointsTotaux(idJoueur, partieEnCours.getNumMancheActuelle(), points);
		}
	}
    
    private void afficherScoresManche() {
    	String nomJoueur;
    	int points;
    	Partie partieEnCours = Partie.getPartie();
    	int mancheActuelle = partieEnCours.getNumMancheActuelle();
    	
    	System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	System.out.println("\n\t---   Fin de la manche " + (mancheActuelle+1) + " sur " + partieEnCours.getParametre().getNbManche() + "   ---\n\n");
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
