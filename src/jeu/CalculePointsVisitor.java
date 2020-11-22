package jeu;

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }

    private int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	this.setCarteVictoire(carteVictoire);
    	int scoreTotalManche = CalculerPointsLignesCouleur(plateau)
				    		  +CalculerPointsLignesForme(plateau)
				    		  +CalculerPointsLignesRemplissage(plateau);
    	return scoreTotalManche;

    }

    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
    
	public int CalculerPointsLignesCouleur(Plateau plateau) {
		boolean estContinu;
		int score = 0;
		int nbCaseLigneCouleur = 1;
		int const1 = 2;
		int const2 = 4;
		for (int k = 0; k < 2; k++) {
			for (int y = 0; y < const1; y++) {
				estContinu = false;
				nbCaseLigneCouleur = 1;
				for (int x = 0; x < const2; x++) {
					if ( k == 0 
							&& (plateau.getCase(x, y) != null) && !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())
					   ||k == 1 
					        && (plateau.getCase(x, y) != null) && !plateau.getCase(y, x).estVide()
							&& plateau.getCase(y, x).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
						if (estContinu == false) {
							estContinu = true;
						} else {
							nbCaseLigneCouleur++;
							score += nbCaseLigneCouleur +2;
						}
					} else {
						estContinu = false;
						nbCaseLigneCouleur = 1;
					}
				}
			}
			int pourInverser = const1;
			const1 = const2;
			const2 = pourInverser;
		}
		return score;
	}
	
	public int CalculerPointsLignesForme(Plateau plateau) {
		boolean estContinu;
		int score = 0;
		int nbCaseLigneForme = 1;
		int const1 = 2;
		int const2 = 4;
		for (int k = 0; k < 2; k++) {
			for (int y = 0; y < const1; y++) {
				estContinu = false;
				nbCaseLigneForme = 1;
				for (int x = 0; x < const2; x++) {
					if ( k == 0 
							&& (plateau.getCase(x, y) != null) 
							&& !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getForme().equals(carteVictoire.getForme())
					   ||k == 1 
					        && (plateau.getCase(x, y) != null) && !plateau.getCase(y, x).estVide()
						    && plateau.getCase(y, x).getCarte().getForme().equals(carteVictoire.getForme())) {
						if (estContinu == false) {
							estContinu = true;
						} else {
							nbCaseLigneForme++;
							score += nbCaseLigneForme-1;
						}
					} else {
						estContinu = false;
						nbCaseLigneForme = 1;
					}
				}
			}
			int pourInverser = const1;
			const1 = const2;
			const2 = pourInverser;
		}
		return score;
	}
	
	
	public int CalculerPointsLignesRemplissage(Plateau plateau) {
		boolean estContinu;
		int score = 0;
		int nbCaseLigneRemplissage = 1;
		int const1 = 2;
		int const2 = 4;
		for (int k = 0; k < 2; k++) {
			for (int y = 0; y < const1; y++) {
				estContinu = false;
				nbCaseLigneRemplissage = 1;
				for (int x = 0; x < const2; x++) {
					if ((  k == 0 
								&& (plateau.getCase(x, y) != null) 
								&& !plateau.getCase(x, y).estVide()							
								&& (plateau.getCase(x, y).getCarte().estRemplie() && carteVictoire.estRemplie()
											|| !plateau.getCase(x, y).getCarte().estRemplie() && !carteVictoire.estRemplie()))
						||(k == 1 
							    && (plateau.getCase(x, y) != null) && !plateau.getCase(y, x).estVide()
							    && (plateau.getCase(x, y).getCarte().estRemplie() && carteVictoire.estRemplie()
											|| !plateau.getCase(x, y).getCarte().estRemplie() && !carteVictoire.estRemplie()))) {
						if (estContinu == false) {
							estContinu = true;
						} else {
							nbCaseLigneRemplissage++;
							score += nbCaseLigneRemplissage + 1;
						}
					} else {
						estContinu = false;
						nbCaseLigneRemplissage = 1;
					}
				}
			}
			int pourInverser = const1;
			const1 = const2;
			const2 = pourInverser;
		}
		return score;
	}
}
