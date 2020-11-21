package jeu;

import java.util.HashMap;

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
	
	public boolean peutPoserCarte(Case emplacement) {
		
		boolean peutPoserCarte=false;
		int[] coord = this.getCoord(emplacement);
		if( this.estVide() || (emplacement.estVide() && this.estAdjacente(coord[0], coord[1]))) {
			peutPoserCarte=true;
		}		
		return peutPoserCarte;
	}
	
	//	Cette méthode sert à savoir si une case de coord x, y est adjacente a une case qui a une carte
	public boolean estAdjacente(int x, int y) {
		boolean estAdjacente = false;
		
		//	On récupère les cases adjacentes
		Case[] adjacentes = {null,null,null,null};
		adjacentes[0] = this.getCase(x, y+1);
		adjacentes[1] = this.getCase(x, y-1);
		adjacentes[2] = this.getCase(x-1, y);
		adjacentes[3] = this.getCase(x+1, y);
		
		for (int i = 0; i < 4; i++) {
			if (adjacentes[i] != null) {
				if (!adjacentes[i].estVide()) {
					estAdjacente = true;
				}
			}
		}
		
		return estAdjacente;
	}

	public Case getCase(int x, int y) {		
		String coord = Plateau.getKey(x, y);
		return this.cases.get(coord);
	}

	/*
	 * Dans cette méthode : 
	 * - on vérifie si la carte a bouger existe
	 * - on supprime la carte à bouger de sa case (depuis)
	 * - on vérifie si la case où la carte sera déplacée est vide
	 * - on pose ou non la carte dans cette nouvelle case (vers)
	 * - on renvoie un boolean indiquant si la carte a pu être bougée
	 * 	
	 */
	public boolean bougerCarte(int x1, int y1, int x2, int y2) {
		Case depuis = this.getCase(x1, y1);
		Case vers = this.getCase(x2, y2);
		boolean peutBougerCarte = true;
		
		if(!depuis.estVide()) {
			Carte carteABougee = depuis.getCarte();
			depuis.setCarte(null);
			if(this.peutPoserCarte(vers)) {			
				vers.setCarte(carteABougee);
			} else {
				peutBougerCarte = false;
				depuis.setCarte(carteABougee);
			}
		} else {
			peutBougerCarte=false;
		}
		return peutBougerCarte;
	}
	
	
	/*	Pour l'instant je ne sais pas comment afficher les axes c'est en réflexion
		
				
		4	|	*		TKT		MDR		*		*
		3	|	*		*		LOL		TGV		*
		2	|			*		*				*
		1	|	*		*		*				*
		Y	|_____________________________________
		   X	1		2		3		4		5
		
	 */
	public void afficher() {		
		StringBuffer sb =new StringBuffer();
		sb.append("Voici le plateau :\n");
		
		switch(this.forme) {
		
		case RECTANGLE :
			int ligne = 3;
			Case emplacement;
			for (int i = 0; i < 15; i++) {
				sb.append('\t');
				
				if(i%5==0) {
					if (i!=0) {
						ligne--;
					}
					sb.append("\n");
					sb.append(ligne + "  |\t");
				}
				
				emplacement = this.getCase((i%5)+1, ligne);
				if(!emplacement.estVide()) {
					sb.append(emplacement.getCarte().getCode() );	
				} else {
					sb.append(" *");
				}
			}
			sb.append('\n');
			sb.append("Y  |_______________________________________");
			sb.append('\n');
			sb.append(" X\t 1\t 2\t 3\t 4\t 5");
			
			break;
			
		case TRIANGLE: 
			break;
			
		case ROND:
			break;
			
		}
		System.out.println(sb);
	}
	
	public int[] getCoord(Case emplacement) {
		int[] coord = {0,0};
		for(String coordStr : this.cases.keySet()) {
			if (this.cases.get(coordStr).equals(emplacement)) {
				coord[0] = Integer.parseInt(coordStr.split(";")[0]);
				coord[1] = Integer.parseInt(coordStr.split(";")[1]);
			}
        }
		return coord;
	}
	
	public static String getKey(int x, int y) {
		return x + ";" + y;
	}

}


