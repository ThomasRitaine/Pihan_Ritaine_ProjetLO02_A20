import java.util.*;
package jeu;

public class Plateau {

//	Enum�ration
	public enum FormesPlateau {
		RECTANGLE, ROND, TRIANGLE;
	}

//	Attributs
	private ArrayList<Case> cases = new ArrayList<Case>();
	private Carte carteCachee;
	private FormesPlateau forme;

	


//	Constructeur
	public Plateau(FormesPlateau forme) {
		this.forme = forme;

		// G�n�ration des cases du plateau et de 
		//leurs coordonn�es
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit g�n�rer un rectangle de 3*5	   
		      
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					this.cases.add(new Case(j,i));					
				}
			}
						
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit g�n�rer un rectangle de 7*4 
			//pour y int�grer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 7; j++) {
					this.cases.add(new Case(j,i));
					}
			}
			// interdiction des cases autour du triangle
			// en laissant la case[4] libre pour une 
			//carte m�me si � la fin de la manche une
			// case sera vide

			cases.get(1).setInterdite();
			cases.get(2).setInterdite();
			cases.get(3).setInterdite();
			cases.get(5).setInterdite();
			cases.get(6).setInterdite();
			cases.get(7).setInterdite();
			cases.get(8).setInterdite();
			cases.get(9).setInterdite();
			cases.get(13).setInterdite();
			cases.get(14).setInterdite();
			cases.get(15).setInterdite();
			cases.get(21).setInterdite();
		}

	}

//	M�thodes
	public boolean peutPoserCarte(Case emplacement) {
		boolean resultat=false;
		if (emplacement.isVide() && emplacement.isCaseAdjacente(this) /* & emplacement.isCaseDansFormePlateau() */) {
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
			
			1)	Si on cherche une case, on va sortir du while d�s que soit le x, soit le y coincident.
				Il faut remplacer le "&&" de ton expression par un "||"
				si tu veux sortir du while quand tu trouves une case avec le m�me x et le m�me y
				
			2)	Si on cherche la premi�re case : this.cases[0], on ne va pas rentrer dans le while
				et ca va retourner la valeur null.
				
			3) 	Je te conseille de faire un bool�en "caseTrouvee" et une case "caseCherchee"
				Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
				fais un if � la fin de ton while o� tu checkes si la case coincide
		
		*/
		Case caseTrouvee=null;
		int c = 0;
		while (this.cases[c].getCoordX() != x & this.cases[c].getCoordY() != y) {
			if (c == 15) {
				System.out.println("[RechercheCase]:Aucune case ne poss�de ces coordonn�es");				
			} else {
				c++;
			}	
		}
		caseTrouvee = this.cases[c];
		return caseTrouvee;
	}
	
	
	//	Cette fonction arrive avant de bouger une carte (qui est fait dans Jouer),
	//	elle sert � faire "dispara�tre" la carte de la case le temps 
	public boolean bougerCarte(Case depuis, Case vers) {
		boolean reussite = true;
		Carte carteBougee = depuis.getCarte();
		depuis.setCarte(null);
		vers.setCarte(carteBougee);
		return reussite;
	}
	
	
	//	Cette fonction sert � afficher le plateau dans la console pour choisir une case � jouer avec ses coordonn�es
	//	L'id�al serait un tableau avec les X et les Y, et dans chaque case, on met soit un "/" si il n'y a pas de carte
	//	soit un code du genre "RVV" si il y a une carte Ronde, Verte et Vide.
	//	Ce string est g�n�r� par la fonction getCode() d'une carte.
	public void afficher() {		
		StringBuffer sb =new StringBuffer();
		int i=0;
		sb.append("Plateau:\n");
		while(i<this.cases.size()) {
			if(i%5==0) {
				sb.append("\n");
			}
			sb.append('\t');
			if(!this.cases.get(i).isVide()) {
				sb.append(this.cases.get(i).getCarte().getCode());	
			}else {
				sb.append('*');
			}
			
			i++;
		}
			
		
		sb.append();
		System.out.println(sb);
		
			//	En dessous : test, tu peux supprimer
		Carte testCarte = new Carte(false, Carte.FormesCarte.TRIANGLE, Carte.CouleursCarte.ROUGE);
		System.out.println(testCarte.getCode());
	}

}


