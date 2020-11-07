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
    
    
//	M�thodes 
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
