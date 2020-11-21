package jeu;

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }

    private int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	this.setCarteVictoire(carteVictoire);
<<<<<<< Updated upstream
    	boolean estContinu;
    	int score=0;
    	int nbCaseLigneCouleur = 1;
    	int compteurLigneForme = 0;
    	int compteurLigneEstRemplie = 0;
    	HashMap<Integer,Integer> NbPointsParLigne = new HashMap<Integer, Integer>();
    	
    	for(int y=0; y<=4; y++) {
    		estContinu=false;
    		nbCaseLigneCouleur =1;
    		for(int x=0; x<=5; x++) {
    			if(!plateau.getCase(x, y).estVide() && 
    				plateau.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
    				if(estContinu==false) {
        				estContinu=true;
        			}else {
        				nbCaseLigneCouleur++;
        				score+=nbCaseLigneCouleur-1;
        			}
    			}else {
    				estContinu = false;
    				nbCaseLigneCouleur =1;
    			}
    			
    		}	
    	}
    		
    	
    	//	Test, on attribut des points aléatoires
    	Random r = new Random();
    	int max = 30;
    	int min = 2;
        return r.nextInt((max - min) + 1) + min;
=======
    	int scoreTotalManche = CalculerPointsLignesCouleur( carteVictoire, plateau) 
							  +CalculerPointsLignesForme( carteVictoire, plateau)
							  +CalculerPointsLignesRemplissage( carteVictoire, plateau);
    	return scoreTotalManche;
>>>>>>> Stashed changes
    }

    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
    
	public int CalculerPointsLignesCouleur(Carte carteVictoire, Plateau plateau) {
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
							&& !plateau.getCase(x, y).estInterdite() && !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())
					   ||k == 1 
					        && !plateau.getCase(y, x).estInterdite() && !plateau.getCase(y, x).estVide()
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
	
	public int CalculerPointsLignesForme(Carte carteVictoire, Plateau plateau) {
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
							&& !plateau.getCase(x, y).estInterdite() 
							&& !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getForme().equals(carteVictoire.getForme())
					   ||k == 1 
					        && !plateau.getCase(y, x).estInterdite() && !plateau.getCase(y, x).estVide()
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
	
	
	public int CalculerPointsLignesRemplissage(Carte carteVictoire, Plateau plateau) {
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
								&& !plateau.getCase(x, y).estInterdite() 
								&& !plateau.getCase(x, y).estVide()							
								&& (plateau.getCase(x, y).getCarte().estRemplie() && carteVictoire.estRemplie()
											|| !plateau.getCase(x, y).getCarte().estRemplie() && !carteVictoire.estRemplie()))
						||(k == 1 
							    && !plateau.getCase(y, x).estInterdite() && !plateau.getCase(y, x).estVide()
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
