package jeu;

import java.util.*;
import java.util.HashMap;

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }

    private int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	//	To do: parcourir lignes puis colonnes idee => hashmap key =y donc ligne et value = compteur incrémenté
    	this.setCarteVictoire(carteVictoire);
    	boolean estContinu;
    	int score=0;
    	int nbCaseLigneCouleur = 1;
    	int compteurLigneForme = 0;
    	int compteurLigneEstRemplie = 0;
    	HashMap<Integer,Integer> NbPointsParLigne = new HashMap<Integer, Integer>();
    	
    	for(int y=0; y<plateau.getdimYRectangle(); y++) {
    		estContinu=false;
    		nbCaseLigneCouleur =1;
    		for(int x=0; x< plateau.getdimXRectangle(); x++) {
    			if(!plateau.getCases().get(x).isVide() && 
    				plateau.getCases().get(x).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
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
    }

    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }

}
