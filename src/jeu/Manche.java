package jeu;

public class Manche implements Visitable {
	
//	Attributs
    private Plateau plateau;
    private Pioche pioche;
    
//	Constructeur
    public Manche(Partie partieEnCours) {
		this.plateau = new Plateau();
		this.pioche = Pioche.getPioche();
		this.attribuerCarte(partieEnCours);
	}
    
    
//	Méthodes 
    public void attribuerCarte(Partie partieEnCours) {
		for (int i = 0; i < partieEnCours.getParametre().getNombreJoueurs(); i++) {
			Joueur joueur = partieEnCours.getJoueurParId(i);
			Carte cartePiocheeCarte = this.pioche.piocher();
			joueur.setCarteVictoire(cartePiocheeCarte);
		}
	}
    
    public void jouerManche() {
		
    	//	Fin de la manche, on supprime la pioche
    	this.supprimerPioche();
	}
    
    public void supprimerPioche() {
    	//	Pour supprimer la pioche, on supprime sa seule référence
    	this.pioche = null;
    		//	Ensuite, on indique à la classe Pioche qu'on a supprimé l'élément
    	Pioche.supprime();
	}

    public int accept(Visitor v) {
		return 0;
    }

    Plateau getPlateau() {
        return this.plateau;
    }

}
