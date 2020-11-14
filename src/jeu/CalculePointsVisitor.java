package jeu;

import java.util.Random;

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }

    private int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	
    	//	To do
    	
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
