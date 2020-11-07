package jeu;

public class Manche implements Visitable {
    private Plateau plateau;

    public int accept(Visitor v) {
		return 0;
    }

    Plateau getPlateau() {
        return this.plateau;
    }

}
