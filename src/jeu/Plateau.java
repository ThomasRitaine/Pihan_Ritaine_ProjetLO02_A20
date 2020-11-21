package jeu;
import java.util.*;

public class Plateau {

//	Enumération
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
		// Génération des cases du plateau et de 
		//leurs coordonnées
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit générer un rectangle de 3*5	   
		      
			for (int y = 0; y < dimYRectangle; y++) {
				for (int x = 0; x < dimXRectangle; x++) {
					this.cases.add(new Case(x,y));					
				}
			}
						
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit générer un rectangle de 7*4 
			//pour y intégrer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			for (int y = 0; y < dimYTriangle; y++) {
				for (int x = 0; x < dimXTriangle; x++) {
					this.cases.add(new Case(x,y));
					}
			}
			// interdiction des cases autour du triangle
			// en laissant la case[4] libre pour une 
			//carte même si à la fin de la manche une
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

//	Méthodes
	
	//permet de savoir quand la premiere carte est posée
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
			if (i == this.cases.size()-1) {//14 = dernière case
				System.out.println("[RechercheCase]:Aucune case ne possède ces coordonnées");					
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
			if(i.getCoordX() == x && i.getCoordY() == y) {//i ou it à voir
				caseTrouvee = true;
				caseCherchee = i;
			}else {
				System.out.println("[RechercheCase]:"+ it);
			}
			}
		return caseCherchee;
		
	}
	/*		
	
	2)	Si on cherche la première case : this.cases[0], on ne va pas rentrer dans le while
		et ca va retourner la valeur null.
		
			=> Yaya : maintenant si ? 
			=> Thomas : A toi de trouver un moyen de le tester ;)
						(mais indique bien avec des commentaires tes blocs de test)
			
		
	3) 	Je te conseille de faire un booléen "caseTrouvee" et une case "caseCherchee"
		Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
		fais un if à la fin de ton while où tu checkes si la case coincide 
		
			=> Yaya : Je pense que j'ai trouvé une solution alternative en rajoutant la condition sur le i, non?
			=> Thomas :	Tu vas avoir un problème si tu ne trouves pas la case. Tu vas faire get(16) et ca va faire
						une erreur.
						Test ton code et tu verras ce qui fonctionne et ne fonctionne pas.

*/
	/*
	 * Dans cette fonction : 
	 * - on vérifie si la carte a bouger existe
	 * - on supprime la carte à bouger de sa case (depuis)
	 * - on vérifie si la case où la carte sera déplacée est vide
	 * - on pose ou non la carte dans cette nouvelle case (vers)
	 * - on renvoie un boolean indiquant si la carte a pu être bougée
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
				System.out.println("Il y a une carte sur la case sélectionnée");//il faudrait redemander si jamais réussite =false 
			}
		}else {
			peutBougerCarte=false;
			System.out.println("Il n'y a pas de carte sur la case sélectionnée");
		}
		return peutBougerCarte;
	}
	
	
	/*	Pour l'instant je ne sais pas comment afficher les axes c'est en réflexion
		
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


