package fr.utt.sit.lo02.pihan_ritaine.shape_up;

public class Case {

//Attributs
	private Carte carte;// si carte value = null alors case vide


	public Case() {
		this.setCarte(null);
	}
	public Carte getCarte() {
		return this.carte;
	}

	public void setCarte(Carte value) {
		this.carte = value;
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
			System.out.println("[isCaseAdjacente] : Aucune carte déjà posée n'est adjacente à cette case.");
			System.out.println("Vous ne pouvez pas poser de carte ici pour le moment.");
		}
		return resultat;
	}*/
	// à développer que si l'on sait comment implémenter un plateau mobile
	/*
	 * public boolean isCaseDansPlateau() {
	 * 
	 * }
	 */

}
