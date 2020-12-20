package fr.utt.sit.lo02.pihan_ritaine.shape_up;

/**
 * <b>CalculePointsVisitor est la classe impl�mentant l'interface Visitor, conform�ment au patron de conception Visitor, appliqu� � la fonctionnalit� du calcul des points des joueurs.</b>
 * <p>
 * Elle appelle la fonction calculerPointsLigneColonne qui contient un algorithme permettant de calculer les points gagn�s par un joueur en fonction de sa carte Victoire et des cartes pr�sentent sur le plateau.
 * Elle est utilis�e en fin de partie pour �tablir quel est le joueur vainqueur, mais aussi en pleine partie d�s lors que c'est au tour d'un joueur IA de jouer (cf la classe JouerIA).* 
 * </p>
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 */

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;
    
    /**
     * <b>Fonction visit.</b>
     * <p>
     * Conform�ment au patron de conception Visitor, la fonction visit prend en param�tre l'objet visit�, dans ce cas un objet de type Manche, et y appelle la m�thode � appliquer sur cet objet.
     * </p>
     * @param m Manche consid�r�e lors du calcul des points d'un joueur.
     * @return Le nombre de points gagn�s par le joueur.  
     * 
     * @see CalculePointsVisitor#calculerPoints(Carte, Plateau)     
     */
    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }
    
    /**
     * <b>Calcule les points d'un joueur.</b>
     * @param carteVictoire Carte du joueur pour lequel les points sont calcul�s.
     * @param plateau Plateau de la manche au moment o� cette fonction est appel�e.
     * @return Le nombre de points gagn�s par le joueur.
     *  
     * @see CalculePointsVisitor#calculerPointsLigneColonne(char, char, Carte, Plateau)     
     */ 
    public int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	int pointsTotaux = 0;
    	pointsTotaux += this.calculerPointsLigneColonne('y', 'x', carteVictoire, plateau);
    	pointsTotaux += this.calculerPointsLigneColonne('x', 'y', carteVictoire, plateau);
    	
    	return pointsTotaux;
    }
    
    /**
     * <b>Calcule les points d'un joueur par ligne et par colonne.</b>
     * @param boucleExt Caract�re d�terminant le sens de lecture du plateau. Si boucleExt = 'y' et boucleInt = 'x' alors l'algorithme calcule les points � compatbiliser par ligne, l'inverse calcule les colonnes.  
     * @param boucleInt Caract�re d�terminant le sens de lecture du plateau. Si boucleExt = 'y' et boucleInt = 'x' alors l'algorithme calcule les points � compatbiliser par ligne, l'inverse calcule les colonnes.
     * @param carteVictoire Carte du joueur pour lequel les points sont calcul�s.
     * @param plateau Plateau de la manche au moment o� cette fonction est appel�e.
     * @return Le nombre de points gagn�s par le joueur sur les lignes ou sur les colonnes en fonction des natures respectives des caract�res boucleInt et boucleExt.
     *  
     * @see CalculePointsVisitor#setCarteVictoire(Carte)    
     */
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
    				
    				if (!emplacement.estVide()) {
					
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
    
    /**
     * <b>Initialise la carte Victoire du joueur.</b>
     * @param carteVictoire CarteVictoire du joueur.
     */
    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
    
}
