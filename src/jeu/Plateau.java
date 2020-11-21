package jeu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plateau {

//	Enumération
	public enum FormesPlateau {
		RECTANGLE,
		ROND,
		TRIANGLE;
	}

//	Attributs
	private HashMap<String,Case> cases = new HashMap<String,Case>();
	private Carte carteCachee;
	private FormesPlateau forme;
	
//	Constructeur
	public Plateau(FormesPlateau forme) {
		this.forme = forme;
		// Génération des cases du plateau et de leurs coordonnées
		String coord;
		if (this.forme == FormesPlateau.RECTANGLE) {
			// on doit générer un rectangle de 3*5
			for (int y = 1; y <= 3; y++) {
				for (int x = 1; x <= 5; x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());					
				}
			}
						
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit générer un rectangle de 7*4 
			//pour y intégrer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			for (int y = 0; y <= 4; y++) {
				for (int x = 0; x <= 7; x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
			}
			// interdiction des cases autour du triangle
			// en laissant la case[4] libre pour une 
			//carte même si à la fin de la manche une
			// case sera vide

			this.cases.get("0;0").setInterdite();
			/* Je ferai le triangle après pour l'instant priorité rectangle =)
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
			cases.get(21).setInterdite();*/
		}

	}

//get et set
	
	public void setCarteCachee(Carte value) {
		this.carteCachee = value;
	}

	public Carte getCarteCachee() {
		return this.carteCachee;
	}
	public HashMap<String,Case> getCases(){
		return this.cases;
	}

//	Méthodes
	
	//	Renvoit vrai si le plateau est vide => aucune carte n'est posée
	public boolean estVide() {
		boolean vide = true;
		for(String coord : this.cases.keySet()){
			if (!this.cases.get(coord).estVide()) {
				vide = false;
			}
        }
		return vide;
	}
	
	public boolean peutPoserCarte(int x, int y) {
		String coord = Plateau.getKey(x, y);
		Case emplacement = this.cases.get(coord);
		
		boolean peutPoserCarte=false;
		if( this.estVide() || (emplacement.estVide() && this.estAdjacente(x, y))) {
			peutPoserCarte=true;			
		}		
		return peutPoserCarte;
	}
	

	public boolean estAdjacente(int x, int y) {
		boolean estAdjacente = false;
		
		//	On récupère les cases. H = Haut, B = Bas, G = Gauche, D = Droit
		Case emplacementH = this.cases.get(Plateau.getKey(x, y+1));
		Case emplacementB = this.cases.get(Plateau.getKey(x, y-1));
		Case emplacementG = this.cases.get(Plateau.getKey(x-1, y));
		Case emplacementD = this.cases.get(Plateau.getKey(x+1, y));
		
		estAdjacente = (emplacementH.getCarte() != null) || (emplacementB.getCarte() != null) || (emplacementG.getCarte() != null) || (emplacementD.getCarte() != null);
		
		return estAdjacente;
	}

	public Case getCase(int x, int y) {		
		String coord = Plateau.getKey(x, y);
		return this.cases.get(coord);
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
		if(!depuis.estVide()) {
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
		
				
		4	|	*		TKT		MDR		*		*
		3	|	*		*		LOL		TGV		*
		2	|			*		*				*
		1	|	*		*		*				*
		Y	|_____________________________________
		   X	1		2		3		4		5
	
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
				if(!this.cases.get(i).estVide()) {
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
	
	public static String getKey(int x, int y) {
		return x + ";" + y;
	}

}


