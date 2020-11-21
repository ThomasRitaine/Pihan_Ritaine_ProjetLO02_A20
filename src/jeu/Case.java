package jeu;

public class Case {

//Attributs
	private Carte carte;// si carte value = null alors case vide

	private boolean interdite;// Question de Yaya : est ce qu'on passe �a dans le constructeur de case?


	public Case() {
		this.setCarte(null);
		this.interdite=false;
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
	

	boolean estVide() {
		boolean vide = false;
		if (this.getCarte() == null) {
			vide = true;
		}
		return vide;
	}
	
	
	

	/*
	public boolean isCaseAdjacente(Plateau p) {
		boolean resultat = false;
		if (!p.rechercheCase(this.coordX + 1, this.coordY).isVide()
				|| !p.rechercheCase(this.coordX, this.coordY + 1).isVide()
				|| !p.rechercheCase(this.coordX - 1, this.coordY).isVide()
				|| !p.rechercheCase(this.coordX, this.coordY - 1).isVide()) {
			resultat = true;
			System.out.println("[isCaseAdjacente] : oui");
		} else {
			System.out.println("[isCaseAdjacente] : Aucune carte d�j� pos�e n'est adjacente � cette case.");
			System.out.println("Vous ne pouvez pas poser de carte ici pour le moment.");
		}
		return resultat;
	}*/
	// � d�velopper que si l'on sait comment impl�menter un plateau mobile
	/*
	 * public boolean isCaseDansPlateau() {
	 * 
	 * }
	 */

}
