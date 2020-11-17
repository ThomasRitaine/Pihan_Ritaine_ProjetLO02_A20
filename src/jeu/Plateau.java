package jeu;
import java.util.*;

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
	
	//permet de savoir quand la premiere carte est pos�e
	public boolean isVide() {
		boolean resultat =false;
		int i = 0;
		while (i<this.cases.size() && this.cases.get(i).isVide()) {
			if(i==this.cases.size()-1) {
				resultat = true;
			}
			i++;
		}
		return resultat;
	}
	
	public boolean peutPoserCarte(Case emplacement) {
		boolean resultat=false;
		if(this.isVide()) {
			resultat=true;
		}
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

	public Case rechercheCase(int x, int y) {		
		Case caseCherchee = null;
		int i = 0;
		while ((this.cases.get(i).getCoordX() != x || this.cases.get(i).getCoordY() != y) && i<this.cases.size()) {//i<15
				if (i == this.cases.size()-1) {//14 = derni�re case
					System.out.println("[RechercheCase]:Aucune case ne poss�de ces coordonn�es");					
				}
				i++;
		}
		caseCherchee = this.cases.get(i);
		return caseCherchee;
	}
	/*		
	
	2)	Si on cherche la premi�re case : this.cases[0], on ne va pas rentrer dans le while
		et ca va retourner la valeur null.
		
			=> Yaya : maintenant si ? 
		
	3) 	Je te conseille de faire un bool�en "caseTrouvee" et une case "caseCherchee"
		Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
		fais un if � la fin de ton while o� tu checkes si la case coincide 
		
			=> Yaya : Je pense que j'ai trouv� une solution alternative en rajoutant la condition sur le i, non?

*/
	
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
		
	//		=> OK d'apr�s le test �a a l'air d'�tre bon, au moins quand le plateau est vide cf main partie j'ai mis * au lieu de /
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
				sb.append(this.cases.get(i).getCarte().getCode() );	
			}else {
				sb.append('*');
			}
			
			i++;
		}
		System.out.println(sb);
		
			//	En dessous : test, tu peux supprimer
		Carte testCarte = new Carte(false, Carte.FormesCarte.TRIANGLE, Carte.CouleursCarte.ROUGE);
		System.out.println(testCarte.getCode());
	}

}


