package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;

import java.util.Observable;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
/**
 * Manche est la classe visitée par le visiteur "CalculePointsVisitor" conformément au patron de conception Visitor.
 * Elle permet la gestion d'une manche d'une partie. 
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 *
 */
public class Manche extends Observable implements Visitable {
	
//	Attributs
    private Plateau plateau;
    private Pioche pioche;
    
//	Constructeur
    /**
     * Initialise une manche : sa partie, ses joueurs, sa carte cachée (première carte de la pioche), son plateau, les cartes victoires des joueurs. 
     * 
     */
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
    /**
     * Attribue les cartes Victoires aux joueurs. 
     */
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
    
    /**
     * Assure le déroulement d'une manche.
     * Tant que le denier tour n'est pas joué, la manche continue.
     */
    public void jouerManche() {
    	
    	Partie partieEnCours = Partie.getPartie();
    	System.out.println("\n\n");
    	AsciiArt.bigDivider();
    	System.out.println("\n\t---   Début de la manche " + (partieEnCours.getNumMancheActuelle()+1) + " sur " + partieEnCours.getParametre().getNbManche() + "   ---");
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
    
    /**
     * Annonce si une manche est finie.
     * @return Vrai ou faux selon si la manche est finie ou non.
     */
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
    
    /**
     * Permet à un joueur de jouer son tour.
     * Donne la carte à jouer au joueur et lui permet de réaliser ses actions.
     * @param tour - Le numéro du tour joué.
     */
    private void jouerTour(int tour) {
    	
    	Partie partieEnCours = Partie.getPartie();
    	
    		//	Obtenir le joueur
    	int idJoueur = tour%partieEnCours.getParametre().getNbJoueur();
    	Joueur joueur = partieEnCours.getJoueurParId(idJoueur);
    	
			//	Donner une carte au joueur
    	this.donnerCarte(joueur);
    	
    		//	On notifie les observers
    	this.setChanged();
		this.notifyObservers(joueur);
		
			//	Jouer le tour du joueur
		joueur.getTypeJouer().jouerTour();
	}
    
    /**
     * Donne la carte à jouer au joueur pourlequel c'est le tour.
     * @param joueur - Le joueur qui doit jouer.
     */
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
    
    /**
     * Supprime la pioche.
     */
    public void supprimerPioche() {
    	//	Pour supprimer la pioche, on supprime sa seule référence
    	this.pioche = null;
    		//	Ensuite, on indique à la classe Pioche qu'on a supprimé l'élément
    	Pioche.supprime();
	}
    
    /**
     * Calcule les points da chaque joueurs lors d'une manche.
     */
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
   
    /**
     * Affiche le score des joueurs pour la manche.
     */
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

    /**
     * Méthode acceptant la visite du visitor "CalculPointsVisitor".
     * Conformément au patron de conception Visitor.
     */
    public int accept(Visitor v) {
		return v.visit(this);
    }

    /**
     * Récupère le plateau de la manche.
     * @return
     */
    public Plateau getPlateau() {
        return this.plateau;
    }

}
