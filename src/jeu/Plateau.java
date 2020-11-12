package jeu;

public class Plateau {

//	Enumération
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

		// Génération des cases du plateau et de 
		//leurs coordonnées
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit générer un rectangle de 3*5
			int c = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					this.cases[c] = new Case();			//	Tu peux mettre les coordonnées dans le constructeur,
					this.cases[c].setCoordX(j);			//	Ca serait plus lisible et plus modulable
					this.cases[c].setCoordY(i);
					c++;
				}
			}
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit générer un rectangle de 7*4 
			//pour y intégrer un triangle de 7 cases +
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
			//carte même si à la fin de la manche une
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

//	Méthodes
	public boolean peutPoserCarte(Case emplacement) {
		boolean resultat=false;
		if (emplacement.isVide() & emplacement.isCaseAdjacente(this) /* & emplacement.isCaseDansFormePlateau() */) {
			// résonnement pour le test isCaseDansFormePlateau() en bas de page, pas simple...
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
		
		/*	Commentaires du petit geek qui fait le projet avec toi :
			
			1)	Si on cherche une case, on va sortir du while dès que soit le x, soit le y coincident.
				Il faut remplacer le "&&" de ton expression par un "||"
				si tu veux sortir du while quand tu trouves une case avec le même x et le même y
				
			2)	Si on cherche la première case : this.cases[0], on ne va pas rentrer dans le while
				et ca va retourner la valeur null.
				
			3) 	Je te conseille de faire un booléen "caseTrouvee" et une case "caseCherchee"
				Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
				fais un if à la fin de ton while où tu checkes si la case coincide
		
		*/
		Case caseTrouvee=null;
		int c = 0;
		while (this.cases[c].getCoordX() != x & this.cases[c].getCoordY() != y) {
			if (c == 15) {
				System.out.println("[RechercheCase]:Aucune case ne possède ces coordonnées");
				//pour l'instant la boucle ne s'arrête pas si avant c=14 => c++ => c=15 le prg va boucler car la condition ne sera pas vérifiée!
			} else {
				c++;
			}
			caseTrouvee = this.cases[c];	
		}
		return caseTrouvee;
	}

	public Case rechercheCase2(int x, int y) {
		//	Celle d'au dessus est meilleure, on peut enlever celle-ci ;)
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
 * println("Veuillez choisir une autre case car celle ci contient déjà une carte, ou n'est pas adjacente à une carte déjà posée ou n'est pas inclue dans le plateau invisible"
 * ); }}
 * 
 * void bougerCarte(Case emplacement, Case carteABouger) {
 * this.poserCarte(emplacement, carteABouger.getCarte());
 * carteABouger.setCarte(null); }
 */

/*
 * pour jouer avec un contour de plateau invisible cas du rectangle: il faut
 * générer plus de cases que les 15 qui vont être remplies par exemple en
 * générant un plateau de 5*5 on aurait alors // Constructeur public
 * Plateau(FormesPlateau forme) { this.forme = forme;
 * 
 * //Génération des cases du plateau et de leurs coordonnées int c=0; for (int
 * i=0; i<5; i++) { for (int j=0; j<5; j++) { this.cases[c] = new Case();
 * cases[c].setCoordX(j); cases[c].setCoordY(i); c++; } }
 * 
 * } il faut ensuite tester isCaseDansFormePlateau()
 */
