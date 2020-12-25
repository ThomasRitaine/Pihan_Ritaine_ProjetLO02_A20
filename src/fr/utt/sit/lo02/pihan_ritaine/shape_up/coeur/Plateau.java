package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur.exceptions.CaseDejaRemplieException;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur.exceptions.CaseInexistanteException;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur.exceptions.NonAdjacenteException;
/**
 * Plateau définit les caractéristique d'un plateau de jeu utilisé lors d'une manche.
 * Elle définit donc la forme du plateau.
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 *
 */
public class Plateau {

//	Enumération
	public enum FormesPlateau {
		RECTANGLE,
		ROND,
		TRIANGLE,
		CERCEAU,
		ECHELLE,
		COEUR;
	}

//	Attributs
	private HashMap<String,Case> cases = new HashMap<String,Case>();
	@SuppressWarnings("unused")
	private Carte carteCachee;
	private FormesPlateau forme;
	
//	Constructeur
	/**
	 * Initialise le plateau d'une manche.
	 * @param forme - La forme du plateau.
	 * @param carteCachee - La carte cachée du plateau, tirée en début de partie.
	 */
	public Plateau(FormesPlateau forme, Carte carteCachee) {
		this.forme = forme;
		this.setCarteCachee(carteCachee);
		// Génération des cases du plateau et de leurs coordonnées
		String coord;
		
		//	Les formes de plateau doivent contenir au minimum 15 cases.
		
		if (this.forme == FormesPlateau.RECTANGLE) {
			// on doit générer un rectangle de 3*5 => 15 cases
			for (int y = 1; y <= 3; y++) {
				for (int x = 1; x <= 5; x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());					
				}
			}		
		}
		
		else if (forme == FormesPlateau.TRIANGLE) {
			//	Un triangle de 7 + 5 + 3 + 1 => 16 cases
			for (int y = 1; y <= 4; y++) {
				for (int x = y; x <= (8-y); x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
			}
		}
		
		else if (forme == FormesPlateau.ROND) {
			//	Un rond, dans un rectangle de 4*5 sans les coins => 16 cases
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
		
		else if (forme == FormesPlateau.CERCEAU) {
			//	Un cerceau rond avec un trou au milieu => 20 cases
			for (int y = 1; y <= 3; y++) {
				for (int x = (4-y); x <= (3+y); x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
					coord = Plateau.getKey(x, 7-y);
					this.cases.put(coord,new Case());
				}
			}
			//	On enlève les cases du milieu
			this.cases.remove("3;3");
			this.cases.remove("3;4");
			this.cases.remove("4;3");
			this.cases.remove("4;4");
		}
		
		else if (forme == FormesPlateau.ECHELLE) {
			//	Une  échelle de 7 cases de hauteur avec des barreaux de 2 cases => 20 cases
			for (int y = 1; y <= 7; y++) {
				for (int x = 1; x <= 4; x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
				if (y%2 == 1) {
					for (int x = 2; x <= 3; x++) {
						coord = Plateau.getKey(x, y);
						this.cases.remove(coord);
					}
				}
			}
		}
		
		else if (forme == FormesPlateau.COEUR) {
			//	Un  coeur plutôt grand => 27 cases
			for (int y = 1; y <= 4; y++) {
				for (int x = (5-y); x <= (3+y); x++) {
					coord = Plateau.getKey(x, y);
					this.cases.put(coord,new Case());
				}
			}
			for (int x = 1; x <= 7; x++) {
				coord = Plateau.getKey(x, 5);
				this.cases.put(coord,new Case());
			}
			this.cases.put("2;6",new Case());
			this.cases.put("3;6",new Case());
			this.cases.put("5;6",new Case());
			this.cases.put("6;6",new Case());
			
		}

	}

//getter et setter
	/**
	 * Initialise la carte cachée du plateau.
	 * @param value - La carte cachée.
	 */
	private void setCarteCachee(Carte value) {
		this.carteCachee = value;
	}
	
	/**
	 * Récupère la forme du plateau.
	 * @return La forme du plateau.
	 */
	public FormesPlateau getFormesPlateau() {
		return this.forme;
	}
	
	/**
	 * Récupère les cases du plateau.
	 * @return
	 */
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
	
	
	private boolean peutPoserCarte(int x, int y) throws Exception {
		
		boolean peutPoserCarte=false;
		Case emplacement = this.getCase(x, y);
		
		//	Si le plateau est vide, on ne regarde pas les règles d'adjacence.
		//	Sinon, on fait les test
		if( this.estVide() && emplacement != null) {
			peutPoserCarte = true;
		}
		else {
			//	Gestion des erreurs
			if (emplacement == null) {
				throw new CaseInexistanteException();
			}
			if (!this.estAdjacente(x, y)) {
				throw new NonAdjacenteException();
			}
			if (!emplacement.estVide()) {
				throw new CaseDejaRemplieException();
			}
			
			//	Si aucune erreur n'a été levée, on met peutPoserCarte à vrai
			peutPoserCarte = true;
		}
				
		return peutPoserCarte;
	}
	
	
	public boolean poserCarte(int coordX, int coordY, Carte carteAPoser) {
		boolean reussite = false;
		boolean aDecale;
		boolean peutPoserCarte;
		int decalageX = 0;
		int decalageY = 0;
		Case emplacement = this.getCase(coordX, coordY);
		
		//	Conserve l'état des cases du plateau avant de le modifier en le décalant
		HashMap<String,Case> copieCases = new HashMap<String,Case>();
		copieCases.putAll(this.cases);  
		
		//	On sort de la boucle si on reussi à poser la carte ou si on a regarder dans toutes les directions
		//	On regarde si, en décalant le plateau en haut, à droite, en bas, à gauche, puis dans en diagonale, on peut poser la carte.
		int i = 0;
		while (!reussite && i<9) {
			
			aDecale = false;
			switch (i) {
				case 1:
					decalageX = 0;
					decalageY = 1;
					break;
				
				case 2:
					decalageX = 1;
					decalageY = 0;
					break;
					
				case 3:
					decalageX = 0;
					decalageY = -1;
					break;
					
				case 4:
					decalageX = -1;
					decalageY = 0;
					break;
					
				case 5:
					decalageX = 1;
					decalageY = 1;
					break;
				
				case 6:
					decalageX = 1;
					decalageY = -1;
					break;
					
				case 7:
					decalageX = -1;
					decalageY = -1;
					break;
					
				case 8:
					decalageX = -1;
					decalageY = 1;
					break;
			}
			
			//System.out.println("decalageX = " + decalageX);
			//System.out.println("decalageY = " + decalageY);
			aDecale = this.decaler(decalageX, decalageY);
			//this.afficher();
			
			if(aDecale) {
				try {
					peutPoserCarte = this.peutPoserCarte(coordX, coordY);
				} catch (Exception e) {
					//	On attrape une exception, on ne peut pas poser la carte
					peutPoserCarte = false;
				}
				
				if (peutPoserCarte) {
					//	On recalcule l'emplacement car plateau.peutPoserCarte modifie les emplacements
					emplacement = this.getCase(coordX, coordY);
					
					//	On met la carte sur la case
					emplacement.setCarte(carteAPoser);
					
					//	On indique que l'opération a réussi
					reussite = true;
					
				} else {
					//	On remet le plateau comme avant
					this.cases.clear();
					this.cases.putAll(copieCases);
				}
				//System.out.println("Après remise comme avant");
				//this.afficher();
			}
			
			//	Fin de la boucle, on passe au prochain décalage
			i++;
		}
		
		return reussite;
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
		boolean aBougeCarte = true;
		
		if (depuis != null) {
			if(!depuis.estVide()) {
				Carte carteABougee = depuis.getCarte();
				depuis.setCarte(null);
				
				//	On essaye de poser la carte
				if( ! this.poserCarte(x2, y2, carteABougee)) {
					
					//	Si on n'a pas pu poser la carte, on recalcule la case depuis et on y remet la carte
					depuis = this.getCase(x1, y1);
					depuis.setCarte(carteABougee);
					aBougeCarte = false;
				}
			} else {
				aBougeCarte=false;
			}
		} else {
			aBougeCarte=false;
		}
		
		return aBougeCarte;
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
			if (y<0) {
				ligne.append(y + " |\t");
			}
			else {
				ligne.append(y + "  |\t");
			}
			
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
	
	
	//	Cette fonction décale chaque case du tableau par un vecteur (x;y) en laissant les cases
	//	avec un carte là où elles sont. Si la forme du plateau ne peut pas être respectée, renvoit false
	public boolean decaler(int x, int y) {
		boolean reussite = true;
		HashMap<String,Case> casesDecale = new HashMap<String,Case>();
		Set<String> coordAutorise = new HashSet<String>();
		int[] coordInt = {0,0};
		
		//	Obtenir les coordonnées des points après le décalage dans le set coordAutorise
		for (Entry<String, Case> entry : this.cases.entrySet()) {
			coordInt[0] = this.getCoord(entry.getValue())[0];
            coordInt[1] = this.getCoord(entry.getValue())[1];
            
            coordInt[0] += x;
            coordInt[1] += y;
            
            String newCoordStr = Plateau.getKey(coordInt[0], coordInt[1]);
            
            coordAutorise.add(newCoordStr);
        }
		
		//	Vérifier si les cases contenant des cartes peuvent rester aux mêmes coord tout en restant
		//	dans les cases autorisées
		for (Entry<String, Case> entry : this.cases.entrySet()) {
			
			//	Si la case a une carte
			if(! entry.getValue().estVide()) {
				//	Si les coords ne sont pas autorisées, on annule le décalage
				if ( !coordAutorise.contains(entry.getKey()) ) {
					reussite = false;
				}
			}
        }
		
		//	Si pas de problème, on lance le décalage
		for (Entry<String, Case> entry : this.cases.entrySet()) {
			
			Case emplacement = entry.getValue();
				
			coordInt[0] = this.getCoord(emplacement)[0];
            coordInt[1] = this.getCoord(emplacement)[1];
            
            coordInt[0] += x;
            coordInt[1] += y;
            
            String newCoordStr = Plateau.getKey(coordInt[0], coordInt[1]);
            
            // 	Si la case ne contient pas de carte, on la décale
        	if (emplacement.estVide()) {
        		if (! casesDecale.containsKey(newCoordStr)) {
        			casesDecale.put(newCoordStr, emplacement);
				}
			}
			else {	//	Si la case contient une carte, on la laisse et on ajoute une case avec les nouvelles coords
				casesDecale.put(entry.getKey(), emplacement);
				if (! casesDecale.containsKey(newCoordStr)) {
					casesDecale.put(newCoordStr, new Case());
				}
			}
			
        }
		
		//	Si les coord sont 0 0, on ne décale pas
		if (reussite && (x!=0 || y!=0)) {
			this.cases = casesDecale;
		}
		
		//	Si les coords sont 0 0, on ne fait rien et on le considère comme une réussite
		if (x==0 && y==0) {
			reussite = true;
		}
		
		return reussite;
	}
	
	
	public void copy(Plateau plateauACopier) {
		this.cases.clear();
		//this.cases.putAll(plateauACopier.getCases());
		for (Entry<String, Case> entry : plateauACopier.getCases().entrySet()) {
			Case caseACopier = new Case();
			Carte carteACopier = entry.getValue().getCarte();
			caseACopier.setCarte(carteACopier);
			this.cases.put(entry.getKey(), caseACopier);
		}
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
	
	public int[] getCoord(String coordStr) {
		int[] coord = {0,0};
		coord[0] = Integer.parseInt(coordStr.split(";")[0]);
		coord[1] = Integer.parseInt(coordStr.split(";")[1]);
		return coord;
	}
	
	
	public Case getCase(int x, int y) {
		String coord = Plateau.getKey(x, y);
		return this.cases.get(coord);
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
			
			if (extremumStr.equals("xMax")) {
				extremum = xMax;
			}
			if (extremumStr.equals("xMin")) {
				extremum = xMin;
			}
			if (extremumStr.equals("yMax")) {
				extremum = yMax;
			}
			if (extremumStr.equals("yMin")) {
				extremum = yMin;
			}
			
		}
		
		return extremum;	
	}
	
	public static String getKey(int x, int y) {
		return x + ";" + y;
	}

}


