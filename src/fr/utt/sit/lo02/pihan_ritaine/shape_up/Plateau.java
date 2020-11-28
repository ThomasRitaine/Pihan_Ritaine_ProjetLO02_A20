package fr.utt.sit.lo02.pihan_ritaine.shape_up;

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
	
	
	public void afficher() {
		
		//	Déclaration des variables
		StringBuffer tableau = new StringBuffer();
		StringBuffer ligne = new StringBuffer();
		Case emplacement;
		int xMax = this.getExtremum("xMax");
		int xMin = this.getExtremum("xMin");
		int yMax = this.getExtremum("yMax");
		int yMin = this.getExtremum("yMin");
		
		tableau.append("Voici le plateau :\n\n");
	
		for (int y = yMax; y >= yMin; y--) {
			//	On supprime ce que le buffer contient
			ligne.delete(0, ligne.length());
			
			//	On ajoute le numéro de la ligne
			ligne.append(y + "  |\t");
			
			for (int x = xMin; x <= xMax; x++) {
				emplacement = this.getCase(x, y);
				if (emplacement != null) {
					if(!emplacement.estVide()) {
						ligne.append(emplacement.getCarte().getCode() );	
					} else {
						ligne.append(" *");
					}
				}
				ligne.append('\t');
			}
			
			//	Fin de la ligne, on retourne à la ligne
			ligne.append('\n');
			ligne.append("   |");
			ligne.append('\n');
			if (y!=yMin) {
				ligne.append("   |");
				ligne.append('\n');
			}
			
			
			//	On ajoute la ligne au tableau
			tableau.append(ligne.toString());
		}
		
		//	On affiche l'axe des x
		tableau.append("Y  |");
		for (int x = xMin; x <= xMax; x++) {
			tableau.append("________");
		}
		tableau.append('\n');
		tableau.append(" X\t");
		for (int x = xMin; x <= xMax; x++) {
			tableau.append(" " +x+"\t");
		}
			
		System.out.println(tableau);
	}
	
	
	//	Cette fonction décale chaque case du tableau par un vecteur (x;y)
	public boolean decaler(int x, int y) {
		boolean reussite = true;
		HashMap<String,Case> casesDecale = new HashMap<String,Case>();
		int[] coordInt = {0,0};
		
		this.cases.forEach((coordStr, emplacement) -> {
			
            coordInt[0] = this.getCoord(emplacement)[0];
            coordInt[1] = this.getCoord(emplacement)[1];
            
            coordInt[0] += x;
            coordInt[1] += y;
            String newCoordStr = Plateau.getKey(coordInt[0], coordInt[1]);
            
            casesDecale.put(newCoordStr, emplacement);
        });
		
		this.cases = casesDecale;
		
		return reussite;
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
	
	
	//	Cette fonction prend en paramètre "xMax", "xMin", "yMax" ou "yMin" et renvoit la valeur de cette coordonnée
	public int getExtremum(String extremumStr) {
		int extremum = 0;
		boolean estInitialise = false;
		int xMax = 0;
		int xMin = 0;
		int yMax = 0;
		int yMin = 0;
		int xSuivant;
		int ySuivant;
		
		if (extremumStr.equals("xMax") || extremumStr.equals("xMin") || extremumStr.equals("yMax") || extremumStr.equals("yMin")) {
			
			for(String coordStr : this.cases.keySet()) {
				xSuivant = Integer.parseInt(coordStr.split(";")[0]);
				ySuivant = Integer.parseInt(coordStr.split(";")[1]);
				
				if (!estInitialise) {
					xMax = xSuivant;
					xMin = xSuivant;
					yMax = ySuivant;
					yMin = ySuivant;
					estInitialise = true;
				}
				
				if (xMax < xSuivant) {
					xMax = xSuivant;
				}
				if (xMin > xSuivant) {
					xMin = xSuivant;
				}
				if (yMax < ySuivant) {
					yMax = ySuivant;
				}
				if (yMin > ySuivant) {
					yMin = ySuivant;
				}
	        }
			
			if (extremumStr == "xMax") {
				extremum = xMax;
			}
			if (extremumStr == "xMin") {
				extremum = xMin;
			}
			if (extremumStr == "yMax") {
				extremum = yMax;
			}
			if (extremumStr == "yMin") {
				extremum = yMin;
			}
			
		}
		
		return extremum;	
	}
	
	public static String getKey(int x, int y) {
		return x + ";" + y;
	}

}


