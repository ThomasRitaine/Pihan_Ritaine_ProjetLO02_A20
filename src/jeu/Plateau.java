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
	private int dimXRectangle = 5;
	private int dimYRectangle = 3;
	private int dimXTriangle = 7;
	private int dimYTriangle = 4;
	//private int dimXRond;
	//private int dimYRond;
	//faire un tableau enregistrant toutes les dimensions?
	
//	Constructeur
	public Plateau(FormesPlateau forme) {
		this.forme = forme;
		// G�n�ration des cases du plateau et de 
		//leurs coordonn�es
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit g�n�rer un rectangle de 3*5	   
		      
			for (int y = 0; y < dimYRectangle; y++) {
				for (int x = 0; x < dimXRectangle; x++) {
					this.cases.add(new Case(x,y));					
				}
			}
						
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit g�n�rer un rectangle de 7*4 
			//pour y int�grer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			for (int y = 0; y < dimYTriangle; y++) {
				for (int x = 0; x < dimXTriangle; x++) {
					this.cases.add(new Case(x,y));
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

//get et set
	public int getdimXRectangle() {
		return this.dimXRectangle;
	}
	public int getdimYRectangle() {
		return this.dimYRectangle;
	}
	public int getdimXTriangle() {
		return this.dimXTriangle;
	}
	public int getdimYTriangle() {
		return this.dimYTriangle;
	}
	
	public void setCarteCachee(Carte value) {
		this.carteCachee = value;
	}

	public Carte getCarteCachee() {
		return this.carteCachee;
	}
	public ArrayList<Case> getCases(){
		return this.cases;
	}

//	M�thodes
	
	//permet de savoir quand la premiere carte est pos�e
	public boolean isVide() {
		//Version for
		boolean vide = true;
		for (int i = 0;  i< this.cases.size(); i++) {
			if( ! this.cases.get(i).isVide()) {
					vide = false;
			}
		}
		return vide;
	}
		//Version While			
		/*boolean vide =false;
			int i = 0;
			while (i<this.cases.size() && this.cases.get(i).isVide()) {
				if(i==this.cases.size()-1) {
					vide = true;
				}
				i++;
			}
			return vide;}
		*/
			
	
	public boolean peutPoserCarte(Case emplacement) {
		boolean peutPoserCarte=false;
		if(this.isVide()||(emplacement.isVide() && emplacement.isCaseAdjacente(this) /* & emplacement.isCaseDansFormePlateau() */)) {
			peutPoserCarte=true;			
		}		
		return peutPoserCarte;
	}	

	public Case rechercheCase(int x, int y) {		
		/*Case caseCherchee = null;
		int i = 0;
		while ((this.cases.get(i).getCoordX() != x || this.cases.get(i).getCoordY() != y) && i<this.cases.size()) {//i<15
			if (i == this.cases.size()-1) {//14 = derni�re case
				System.out.println("[RechercheCase]:Aucune case ne poss�de ces coordonn�es");					
			}
			i++;
		}
		caseCherchee = this.cases.get(i);
		return caseCherchee;
		*/
		Case caseCherchee = null;
		boolean caseTrouvee = false;
		Iterator<Case> it = cases.iterator();		
		while(it.hasNext()&& caseTrouvee == false) {
			Case i = it.next();
			if(i.getCoordX() == x && i.getCoordY() == y) {//i ou it � voir
				caseTrouvee = true;
				caseCherchee = i;
			}else {
				System.out.println("[RechercheCase]:"+ it);
			}
			}
		return caseCherchee;
		
	}
	/*		
	
	2)	Si on cherche la premi�re case : this.cases[0], on ne va pas rentrer dans le while
		et ca va retourner la valeur null.
		
			=> Yaya : maintenant si ? 
			=> Thomas : A toi de trouver un moyen de le tester ;)
						(mais indique bien avec des commentaires tes blocs de test)
			
		
	3) 	Je te conseille de faire un bool�en "caseTrouvee" et une case "caseCherchee"
		Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
		fais un if � la fin de ton while o� tu checkes si la case coincide 
		
			=> Yaya : Je pense que j'ai trouv� une solution alternative en rajoutant la condition sur le i, non?
			=> Thomas :	Tu vas avoir un probl�me si tu ne trouves pas la case. Tu vas faire get(16) et ca va faire
						une erreur.
						Test ton code et tu verras ce qui fonctionne et ne fonctionne pas.

*/
	/*
	 * Dans cette fonction : 
	 * - on v�rifie si la carte a bouger existe
	 * - on supprime la carte � bouger de sa case (depuis)
	 * - on v�rifie si la case o� la carte sera d�plac�e est vide
	 * - on pose ou non la carte dans cette nouvelle case (vers)
	 * - on renvoie un boolean indiquant si la carte a pu �tre boug�e
	 * 	
	 */
	public boolean bougerCarte(Case depuis, Case vers) {
		boolean peutBougerCarte = true;
		if(!depuis.isVide()) {
			Carte carteABougee = depuis.getCarte();
			depuis.setCarte(null);
			if(this.peutPoserCarte(vers)) {			
				vers.setCarte(carteABougee);
			}else {
				peutBougerCarte = false;
				depuis.setCarte(carteABougee);
				System.out.println("Il y a une carte sur la case s�lectionn�e");//il faudrait redemander si jamais r�ussite =false 
			}
		}else {
			peutBougerCarte=false;
			System.out.println("Il n'y a pas de carte sur la case s�lectionn�e");
		}
		return peutBougerCarte;
	}
	
	
	/*	Pour l'instant je ne sais pas comment afficher les axes c'est en r�flexion
		
		\ X		1		2		3		4		5
		Y		
		1		*		TKT		MDR		*		*
		2		*		*		LOL		TGV		*
		3				*		*				*
		4		*		*		*				*
	
		=> faire l'affichage pour les autres plateaux.
		
	 */
	public void afficher() {		
		StringBuffer sb =new StringBuffer();
		int i=0;
		sb.append("Voici le plateau :\n");
		switch(this.forme) {
		case RECTANGLE :
			while(i<this.cases.size()) {
				if(i%5==0) {
					sb.append("\n");
				}
				sb.append('\t');
				if(!this.cases.get(i).isVide()) {
					sb.append(this.cases.get(i).getCarte().getCode() );	
				}else {
					sb.append(" *");
				}
				
				i++;
			}
			break;
		case TRIANGLE: 
			break;
			
		case ROND:
			break;
			
		}
		System.out.println(sb);
		}
		

}


