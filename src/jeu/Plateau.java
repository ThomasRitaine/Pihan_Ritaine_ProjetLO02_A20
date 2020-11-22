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
		}
		else if (forme == FormesPlateau.TRIANGLE) {
			//	Un triangle de 7 + 5 + 3 + 1
			for (int y = 1; y <= 4; y++) {
				for (int x = y; x <= (8-y); x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
			}
		}
		else if (forme == FormesPlateau.ROND) {
			//	Un rond, dans un rectangle de 4*5 sans les coins
			for (int y = 1; y <= 5; y++) {
				for (int x = 1; x <= 4; x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
			}
			//	On enlève les cases des coins pour faire la forme du rond
			this.cases.remove("1;1");
			this.cases.remove("1;5");
			this.cases.remove("4;1");
			this.cases.remove("4;5");
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
		
		if (depuis != null && vers != null) {
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
		} else {
			peutBougerCarte=false;
		}
		
		return peutBougerCarte;
	}
	
	
	/*	Affichage pour le rectangle
		
				
		4	|	*		TKT		MDR		*		*
		3	|	*		*		LOL		TGV		*
		2	|			*		*				*
		1	|	*		*		*				*
		Y	|_____________________________________
		   X	1		2		3		4		5
		
	 */
	public void afficher() {
		
		//	Déclaration des variables
		StringBuffer tableau = new StringBuffer();
		StringBuffer ligne = new StringBuffer();
		Case emplacement;
		boolean ligneVide;
		int xMax = 0;
		
		tableau.append("Voici le plateau :\n\n");
	
		for (int y = 10; y > 0; y--) {
			ligneVide = true;
			//	On supprime ce que le buffer contient
			ligne.delete(0, ligne.length());
			
			//	On ajoute le numéro de la ligne
			ligne.append(y + "  |\t");
			
			for (int x = 1; x < 10; x++) {
				emplacement = this.getCase(x, y);
				if (emplacement != null) {
					//	On indique que la ligne n'est pas vide puisqu'elle comporte au moins une case
					ligneVide = false;
					if(!emplacement.estVide()) {
						ligne.append(emplacement.getCarte().getCode() );	
					} else {
						ligne.append(" *");
					}
					
					//	On met à jour le xMax
					if (x>xMax) {
						xMax = x;
					}
				}
				ligne.append('\t');
			}
			
			//	Fin de la ligne, on retourne à la ligne
			ligne.append('\n');
			
			//	Si la ligne n'est pas vide, on l'affiche dans le tableau
			if (!ligneVide) {
				tableau.append(ligne.toString());
			}
		}
		
		//	On affiche l'axe des x
		tableau.append("Y  |");
		for (int x = 0; x < xMax; x++) {
			tableau.append("________");
		}
		tableau.append('\n');
		tableau.append(" X\t");
		for (int x = 1; x <= xMax; x++) {
			tableau.append(" " +x+"\t");
		}
			
		System.out.println(tableau);
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


