package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;

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

}
