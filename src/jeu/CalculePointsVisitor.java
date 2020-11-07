package jeu;

public class CalculePointsVisitor implements Visitor {
	
    private int idJoueur;

    public int visit(Manche m) {
		return 0;
    }

    private int calculerPoints(int idJoueur, Plateau plateau) {
		return 0;
    }

    void setIdJoueur(int value) {
        
        this.idJoueur = value;
    }

}
