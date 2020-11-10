package jeu;

public class Plateau {
	
//	Enumération
	public enum FormesPlateau {
    	RECTANGLE,
    	ROND,
    	TRIANGLE;
    }
	
//	Attributs
    private Case[] cases = new Case[15];
    private Carte carteCachee;
    private FormesPlateau forme;
    
    

    
//	Constructeur
    public Plateau(FormesPlateau forme) {
		this.forme = forme;
		
		//Génération des cases du plateau et de leurs coordonnées
		if(forme==FormesPlateau.RECTANGLE) {//on doit générer un rectangle de 3*5		
		int c=0;
		for (int i=0; i<3; i++) {
			for (int j=0; j<5; j++) {
				this.cases[c] = new Case();
				this.cases[c].setCoordX(j);
				this.cases[c].setCoordY(i);
				c++;
			}
		}}else if(forme==FormesPlateau.TRIANGLE) {
			//on doit générer un rectangle de 7*4 pour y intégrer un triangle de 7 cases + 5 + 3 + 0 ou 1 
			//(si 0 on a la forme d'un trapèze si 1 on a une case qui sera vide car on propose 16 cases pouvant être remplies)
			//les 11 à 12 cases restantes ne peuvent pas être utilisées, leur valeur doit être null et être constante => cela rend le triangle static
			//comment optimiser le code des cases nulles ..?
			int c=0;
			for (int i=0; i<4; i++) {
				for (int j=0; j<7; j++) {
					this.cases[c] = new Case();
					this.cases[c].setCoordX(j);
					this.cases[c].setCoordY(i);
					c++;
				}
			}
			//en laissant la case[4] libre pour une carte même si à la fin de la manche une case sera vide
			
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
    	if(emplacement.isVide() & emplacement.isCaseAdjacente() /*& emplacement.isCaseDansFormePlateau()*/) {//résonnement pour le test isCaseDansFormePlateau() en bas de page, pas simple...
    		return true;
    	}else return false;
    }
    
    
    public void setCarteCachee(Carte value) {
        this.carteCachee = value;
    }

    public Carte getCarteCachee() {
        return this.carteCachee;
    }



	public Case rechercheCase(int x, int y) {
		int c= 0;
		while(this.cases[c].getCoordX()!=x & this.cases[c].getCoordY()!=y){
			if(c==15) {
			System.out.println("[RechercheCase]:Aucune case ne possède ces coordonnées");
			return null;//et break?
		}else c++;
		}return this.cases[c];
	}
	
	public Case rechercheCase2(int x, int y) {
		for(int c=0; c<15; c++) {
			if(this.cases[c].getCoordX()==x & this.cases[c].getCoordY()==y) {
				return this.cases[c];
			}
		}
		return null;
	}
	
}

/*dans jouerTour:
	void poserCarte(Case emplacement, Carte Value) {
	if(Plateau.peutPoserCarte(emplacement)){
		emplacement.setCarte(value);
	}else {
		System.out.println("Veuillez choisir une autre case car celle ci contient déjà une carte, ou n'est pas adjacente à une carte déjà posée ou n'est pas inclue dans le plateau invisible");
	}}
    
void bougerCarte(Case emplacement, Case carteABouger) {
	this.poserCarte(emplacement, carteABouger.getCarte());
	carteABouger.setCarte(null);
}*/

/*
pour jouer avec un contour de plateau invisible cas du rectangle: 
	il faut générer plus de cases que les 15 qui vont être remplies 
	par exemple en générant un plateau de 5*5 on aurait alors 
//	Constructeur
    public Plateau(FormesPlateau forme) {
		this.forme = forme;
		
		//Génération des cases du plateau et de leurs coordonnées
		int c=0;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				this.cases[c] = new Case();
				cases[c].setCoordX(j);
				cases[c].setCoordY(i);
				c++;
			}
		}
	
	}
	il faut ensuite tester isCaseDansFormePlateau()
	*/
