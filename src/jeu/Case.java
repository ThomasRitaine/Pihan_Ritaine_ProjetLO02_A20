package jeu;

public class Case {

//Attributs
	private Carte carte;// si carte value = null alors case vide
	private int coordX;
	private int coordY;
	private boolean interdite = false;// Question de Yaya : est ce qu'on passe ça dans le constructeur de case?

//Constructeur
	public Case(int x, int y) {
		this.coordX = x;
		this.coordY = y;
	}

	// Methodes get et set
	public int getCoordX() {
		return this.coordX;
	}

	public void setCoordX(int x) {
		this.coordX = x;
	}

	public int getCoordY() {
		return this.coordY;
	}

	public void setCoordY(int y) {
		this.coordY = y;
	}

	public Carte getCarte() {
		return this.carte;
	}

	public void setCarte(Carte value) {
		this.carte = value;
	}

	public boolean getInterdite() {
		return this.interdite;
	}

	public void setInterdite() {
		this.interdite = true;
	}

	// Methodes
	// correction du return
	boolean isVide() {
		boolean isVide = false;
		if (this.getCarte() == null) {
			isVide = true;
			System.out.println("[isVide] : oui");
		}else {
			System.out.println("[isVide] : non");
		}
		return isVide;
	}

	public boolean isCaseAdjacente(Plateau p) {
		boolean resultat = false;
		if (!p.rechercheCase(this.coordX + 1, this.coordY).isVide()
				|| !p.rechercheCase(this.coordX, this.coordY + 1).isVide()
				|| !p.rechercheCase(this.coordX - 1, this.coordY).isVide()
				|| !p.rechercheCase(this.coordX, this.coordY - 1).isVide()) {
			resultat = true;
			System.out.println("[isCaseAdjacente] : oui");
		} else {
			System.out.println("[isCaseAdjacente] : Aucune carte déjà posée n'est adjacente à cette case.");
			System.out.println("Vous ne pouvez pas poser de carte ici pour le moment.");
		}
		return resultat;
	}
	// à développer que si l'on sait comment implémenter un plateau mobile
	/*
	 * public boolean isCaseDansPlateau() {
	 * 
	 * }
	 */

}
