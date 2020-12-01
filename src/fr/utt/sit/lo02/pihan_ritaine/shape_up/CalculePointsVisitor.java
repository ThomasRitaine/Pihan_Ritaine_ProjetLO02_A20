package fr.utt.sit.lo02.pihan_ritaine.shape_up;


public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;
    

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }
    
    
    public int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	int pointsTotaux = 0;
    	pointsTotaux += this.calculerPointsLigneColonne('y', 'x', carteVictoire, plateau);
    	pointsTotaux += this.calculerPointsLigneColonne('x', 'y', carteVictoire, plateau);
    	
    	return pointsTotaux;
    }
    
    
    public int calculerPointsLigneColonne(char boucleExt, char boucleInt, Carte carteVictoire, Plateau plateau) {
    	
    	//	La variable qui stocke le score avant de le retourner
    	int pointsPartiels = 0;
    	
    	//	Les points attribu�s pour x cartes align�es.
    	//		Exemple : Pour avoir le nombre de points pour un alignement de 3 formes identiques,
    	//		on regarde la valeur de pointsAlignementForme[3]
    	int[] ptAlignementForme = {0, 0, 1, 2, 3, 4};
    	int[] ptAlignementRemplie = {0, 0, 0, 3, 4, 5};
    	int[] ptAlignementCouleur = {0, 0, 0, 4, 5, 6};
    	
    	
    	//	On parcourt le plateau de bas en haut
    	//	On fixe le y et on parcourt les x
    	for (int i = plateau.getExtremum(boucleExt+"Min"); i <= plateau.getExtremum(boucleExt+"Max"); i++) {
			
    		//	On ajoute des variables qui comptent le nombre d'attributs semblables d'affil�
    		int nbAlignementForme = 0;
    		int nbAlignementRemplie = 0;
    		int nbAlignementCouleur = 0;
    		
    		//	Sert � savoir quand il faut ajouter les points des s�ries en cours aux points totaux et r�initialiser les s�ries
    		boolean ajouterPoints = false;
    		
    		Case emplacement = null;
    		
    		//	Pour chaque rang�e, on compte le nombre d'attributs identiques d'affil�
    		for (int j = plateau.getExtremum(boucleInt+"Min"); j <= plateau.getExtremum(boucleInt+"Max"); j++) {
    			
    			//	On r�cup�re la case. En fonction de la variable de la boucle ext�rieur, on inverser les coordonn�es
    			if (boucleExt == 'x') {
    				emplacement = plateau.getCase(i, j);
				} else {
					emplacement = plateau.getCase(j, i);
				}
    			
    			if (emplacement != null) {
					
    				if (emplacement.getCarte().getCouleur() == carteVictoire.getCouleur()) {
        				nbAlignementCouleur++;
    				} else {
    					//	Si trop de cartes sont align�es, on limite � 5 pour ne pas d�passer du tableau des scores.
    					if (nbAlignementCouleur > 5) {
    						nbAlignementCouleur = 5;
    					}
    					//	On attibut les points
    					pointsPartiels += ptAlignementCouleur[nbAlignementCouleur];
    					nbAlignementCouleur = 0;
    				}
    				
    				if (emplacement.getCarte().getForme() == carteVictoire.getForme()) {
        				nbAlignementForme++;
    				} else {
    					//	Si trop de cartes sont align�es, on limite � 5 pour ne pas d�passer du tableau des scores.
    					if (nbAlignementForme > 5) {
    						nbAlignementForme = 5;
    					}
    					//	On attibut les points
    					pointsPartiels += ptAlignementForme[nbAlignementForme];
    					nbAlignementForme = 0;
    				}
    				
    				if (emplacement.getCarte().estRemplie() == carteVictoire.estRemplie()) {
        				nbAlignementRemplie++;
    				} else {
    					//	Si trop de cartes sont align�es, on limite � 5 pour ne pas d�passer du tableau des scores.
    					if (nbAlignementRemplie > 5) {
    						nbAlignementRemplie = 5;
    					}
    					//	On attibut les points
    					pointsPartiels += ptAlignementRemplie[nbAlignementRemplie];
    					nbAlignementRemplie = 0;
    				}
    				
				} else {
					ajouterPoints = true;
				}
    			
    			//	Si on est � la fin de la ligne, on arr�te le comptage et on attribu les points
    			if (j == plateau.getExtremum(boucleInt+"Max")) {
    				ajouterPoints = true;
				}
    			
    			if (ajouterPoints) {
    				//	Ajout des s�ries en cours aux points, puis r�initialisation
					
					//	Si trop de cartes sont align�es, on limite � 5 pour ne pas d�passer du tableau des scores.
					if (nbAlignementCouleur > 5) {
						nbAlignementCouleur = 5;
					}
					if (nbAlignementForme > 5) {
						nbAlignementForme = 5;
					}
					if (nbAlignementRemplie > 5) {
						nbAlignementRemplie = 5;
					}
					//	On attibut les points
					pointsPartiels += ptAlignementCouleur[nbAlignementCouleur];
					pointsPartiels += ptAlignementForme[nbAlignementForme];
					pointsPartiels += ptAlignementRemplie[nbAlignementRemplie];
					nbAlignementCouleur = 0;
					nbAlignementForme = 0;
					nbAlignementRemplie = 0;
				}
    		}
    		
		}
    	
    	return pointsPartiels;
	}
    
    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
    
}
