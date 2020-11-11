package jeu;

public class Plateau {

//	Enum�ration
	public enum FormesPlateau {
		RECTANGLE, ROND, TRIANGLE;
	}

//	Attributs
	private Case[] cases = new Case[15];
	private Carte carteCachee;
	private FormesPlateau forme;

//	Constructeur
	public Plateau(FormesPlateau forme) {
		this.forme = forme;

		// G�n�ration des cases du plateau et de 
		//leurs coordonn�es
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit g�n�rer un rectangle de 3*5
			int c = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					this.cases[c] = new Case();
					this.cases[c].setCoordX(j);
					this.cases[c].setCoordY(i);
					c++;
				}
			}
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit g�n�rer un rectangle de 7*4 
			//pour y int�grer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			int c = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 7; j++) {
					this.cases[c] = new Case();
					this.cases[c].setCoordX(j);
					this.cases[c].setCoordY(i);
					c++;
				}
			}
			// interdiction des cases autour du triangle
			// en laissant la case[4] libre pour une 
			//carte m�me si � la fin de la manche une
			// case sera vide

			cases[1].setInterdite();
			cases[2].setInterdite();
			cases[3].setInterdite();
			cases[5].setInterdite();
			cases[6].setInterdite();
			cases[7].setInterdite();
			cases[8].setInterdite();
			cases[9].setInterdite();
			cases[13].setInterdite();
			cases[14].setInterdite();
			cases[15].setInterdite();
			cases[21].setInterdite();

		}

	}

//	M�thodes
	public boolean peutPoserCarte(Case emplacement) {
		boolean resultat=false;
		if (emplacement.isVide() & emplacement.isCaseAdjacente(this) /* & emplacement.isCaseDansFormePlateau() */) {
			// r�sonnement pour le test isCaseDansFormePlateau() en bas de page, pas simple...
			resultat = true;
		} 
		return resultat;
	}
	
	public void setCarteCachee(Carte value) {
		this.carteCachee = value;
	}

	public Carte getCarteCachee() {
		return this.carteCachee;
	}

	public Case rechercheCase(int x, int y) {//il faut controler les entrees pour controler while
		Case caseTrouvee=null;
		int c = 0;
		while (this.cases[c].getCoordX() != x & this.cases[c].getCoordY() != y) {
			if (c == 15) {
				System.out.println("[RechercheCase]:Aucune case ne poss�de ces coordonn�es");
				//pour l'instant la boucle ne s'arr�te pas si avant c=14 => c++ => c=15 le prg va boucler car la condition ne sera pas v�rifi�e!
			} else {
				c++;
			}
			caseTrouvee = this.cases[c];	
		}
		return caseTrouvee;
	}

	public Case rechercheCase2(int x, int y) {
		Case caseTrouvee = null;
		for (int c = 0; c < cases.length; c++) {
			if (this.cases[c].getCoordX() == x & this.cases[c].getCoordY() == y) {
				caseTrouvee = this.cases[c];
			}
		}
		return caseTrouvee;
	}

}

/*
 * dans jouerTour: void poserCarte(Case emplacement, Carte Value) {
 * if(Plateau.peutPoserCarte(emplacement)){ emplacement.setCarte(value); }else {
 * System.out.
 * println("Veuillez choisir une autre case car celle ci contient d�j� une carte, ou n'est pas adjacente � une carte d�j� pos�e ou n'est pas inclue dans le plateau invisible"
 * ); }}
 * 
 * void bougerCarte(Case emplacement, Case carteABouger) {
 * this.poserCarte(emplacement, carteABouger.getCarte());
 * carteABouger.setCarte(null); }
 */

/*
 * pour jouer avec un contour de plateau invisible cas du rectangle: il faut
 * g�n�rer plus de cases que les 15 qui vont �tre remplies par exemple en
 * g�n�rant un plateau de 5*5 on aurait alors // Constructeur public
 * Plateau(FormesPlateau forme) { this.forme = forme;
 * 
 * //G�n�ration des cases du plateau et de leurs coordonn�es int c=0; for (int
 * i=0; i<5; i++) { for (int j=0; j<5; j++) { this.cases[c] = new Case();
 * cases[c].setCoordX(j); cases[c].setCoordY(i); c++; } }
 * 
 * } il faut ensuite tester isCaseDansFormePlateau()
 */
