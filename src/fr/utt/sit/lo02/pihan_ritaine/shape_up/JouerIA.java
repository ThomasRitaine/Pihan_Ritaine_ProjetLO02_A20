package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Comparator;


public class JouerIA extends Jouer{
	
	//	Cette variable sert � faire des calculs de points sans toucher au plateau de la partie
	private Plateau plateauCalcul;
	
	public JouerIA(Joueur joueur) {
		super(joueur);
		
		//	On initialise le plateau vide.
		//	Pas de forme car il va copier celle du plateau de jeu au moment du tour.
		this.plateauCalcul = new Plateau(null, null);
	}
	
    public void jouerTour() {
    	
    	System.out.println("\n\n");
    	AsciiArt.littleDivider();
    	System.out.println("\n\tAu tour de l'ordinateur " + this.joueur.getNom() + ".  [o_o]\n");
    	System.out.println("Sa carte � jouer est " + this.joueur.getCarteAJouer().toString() + ".");
    	
    	
    	
    	// 	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//  On donne la carte de victoire du joueur au calculateur de points
    	Carte carteVictoire = this.joueur.getCarteVictoire();
		calculateur.setCarteVictoire(carteVictoire);
		
		
		//	Calcul du meilleur placement de carte
		int[] resultatPoserMeilleurCoup = this.calculPoserMeilleurCoup();
		
		//	Placement de la carte sur le meilleur emplacement
		this.poserMeilleurCoup(resultatPoserMeilleurCoup);
		
	    
	    //	Calcul du meilleur d�placement de carte
		int[] resultatBougerMeilleurCoup = this.calculBougerMeilleurCoup();
		
		//	Placement de la carte sur le meilleur emplacement
		this.bougerMeilleurCoup(resultatBougerMeilleurCoup);
		
		
		//	Affichage du plateau une fois le tour pass�
		this.plateau.afficher();
	    
    }
    
    
    
    //	Cette fonction calcule le meilleur coup pour poser sa carte
    //	Retourne 3 entiers :
    //		-	En 0 : la coordonn�e x du meilleur placement
    //		-	En 1 : la coordonn�e y du meilleur placement
    //		-	En 2 : les points gagn�s en posant la carte sur cette case
    public int[] calculPoserMeilleurCoup() {
    	
    	int[] meilleurCoup = {0,0,0};
    	
    	//	Le tableau dans lequel on stock les coords et les points du placement sur ces coords
    	HashMap<String,Integer> listePointPlacement = new HashMap<String,Integer>();
    	
    	//	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//  On r�cup�re la carte de victoire
    	Carte carteVictoire = this.joueur.getCarteVictoire();
    	
    	// 	Copie du plateau 
    	this.plateauCalcul.copy(this.plateau);
    	
    	//	R�cup�ration des coordonn�es extr�mes du plateau
    	int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//Parcours d'un plateau en consid�rant m�me les cases n'existant pas encore mais qui le pourrait si un d�calage du plateau avait lieu
		for(int y=yMin-1; y<=yMax+1; y++) {
			
			for(int x=xMin-1; x<=xMax+1; x++) {
				
				//this.poserCarte(x,y,1) fait appel � la fonction plateau.poserCarte(int coordX, int coordY, Carte carteAPoser)
				//cette derni�re v�rifie si une carte peut �tre pos�e en fonction de sa position et pose la carte dans ce cas(ici la carte � jouer)  en consid�rant un plateau dynamique
				if( this.plateauCalcul.poserCarte(x, y, this.joueur.getCarteDeMain(1)) ) {
					
					//	Calcul des points
					int points = calculateur.calculerPoints(carteVictoire, this.plateauCalcul);
					
					//ajout de la valeur "points" associ� aux coords de la case jou�e 
					listePointPlacement.put( Plateau.getKey(x,y), points);
					
					//	On remet le plateau de calcul comme avant
					this.plateauCalcul.copy(this.plateau);
				}
				
			}
			
		}
		
		//	On calcul les points que rapporte le meilleur placement
		meilleurCoup[2] = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
		
		//	On calcule les coordon�es du meilleur placement
		String coordStr = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    	int[] coordInt = this.plateau.getCoord(coordStr);
    	
    	meilleurCoup[0] = coordInt[0];
    	meilleurCoup[1] = coordInt[1];
    	
    	return meilleurCoup;
	}
    
    
    
    //	Cette fonction calcule le meilleur coup pour d�placer une carte
    //	Retourne 5 entiers :
    //		-	En 0 : la coordonn�e x de la meilleur carte � d�placer
    //		-	En 1 : la coordonn�e y de la meilleur carte � d�placer
    //		-	En 2 : la coordonn�e x du meilleur emplacement o� mettre la carte
    //		-	En 3 : la coordonn�e y du meilleur emplacement o� mettre la carte
    //		-	En 4 : les points gagn�s en d�placant la carte
    public int[] calculBougerMeilleurCoup() {
    	
    	int[] meilleurCoup = {0,0,0,0,0};
    	
    	//	Le tableau dans lequel on stock les coords et les points du placement sur ces coords
    	HashMap<String,Integer> listePointPlacement = new HashMap<String,Integer>();
    	
    	//	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//  On r�cup�re la carte de victoire
    	Carte carteVictoire = this.joueur.getCarteVictoire();
    	
    	//	On copie le plateau
    	this.plateauCalcul.copy(this.plateau);
    	
    	//	R�cup�ration des coordonn�es extr�mes du plateau
    	int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//	Pour chaque case du plateau qui contient une carte, on essaye de la d�placer
		for (Entry<String, Case> entry : this.plateau.getCases().entrySet()) {
			
			//	Si il y a une carte sur cette case, on essaye de la d�placer
			if (!entry.getValue().estVide()) {
				
				//	On r�cup�re les coords de la case avec une carte
				int[] coordDepuis = this.plateauCalcul.getCoord(entry.getKey());
				
				//Parcours d'un plateau en consid�rant m�me les cases n'existant pas encore mais qui le pourrait si un d�calage du plateau avait lieu
				for(int y=yMin-1; y<=yMax+1; y++) {
					
					for(int x=xMin-1; x<=xMax+1; x++) {
						
						if( this.plateauCalcul.bougerCarte(coordDepuis[0], coordDepuis[1], x, y) ) {
							
							//	Calcul des points
							int points = calculateur.calculerPoints(carteVictoire, this.plateauCalcul);
							
							//ajout de la valeur "points" associ� aux coords des cases jou�es 
							String bougeKey = entry.getKey() + ";" + Plateau.getKey(x,y);
							listePointPlacement.put( bougeKey, points);
							
							//	On remet le plateau de calcul comme avant
							this.plateauCalcul.copy(this.plateau);
						}
						
					}
					
				}
				
			}
			
		}
		
		
		//	On calcule les coordon�es du meilleur placement
		String coordStr = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    	
		//	On mets les coords du meilleur coup dans la variable de retour
		for (int i = 0; i < 4; i++) {
			meilleurCoup[i] = Integer.parseInt(coordStr.split(";")[i]);
		}
		
		//	On calcul les points que rapporte le meilleur placement
		meilleurCoup[4] = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
		
    	return meilleurCoup;
	}
    
    
    public void poserMeilleurCoup(int[] resultatPoserMeilleurCoup) {
    	
    	int[] coordInt = {0,0};
		coordInt[0] = resultatPoserMeilleurCoup[0];
		coordInt[1] = resultatPoserMeilleurCoup[1];
		int ptPoser = resultatPoserMeilleurCoup[2];

		//	R�cup�ration des coordonn�es extr�mes du plateau
    	int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//	Si les points sont sup�rieur � 0, on joue le placement
		//	Sinon, on choisi une case au hasard
	    if (ptPoser > 0) {
	    	//	On pose la carte sur l'emplacement
		    this.poserCarte(this.plateau, coordInt[0], coordInt[1], 1);
		}
	    else {
			do {
				//	On g�n�re un x et un y al�atoires entre les max et min du plateau
				Random r = new Random();
		        coordInt[0] = r.nextInt((xMax - xMin) + 1) + xMin;
		        
		        r = new Random();
		        coordInt[1] = r.nextInt((yMax - yMin) + 1) + yMin;

			} while (!poserCarte(this.plateau, coordInt[0], coordInt[1], 1));
		}
	    
	    //	Affichage emplacement carte pos�e
	    System.out.println(this.joueur.getNom()+" a pos� sa carte en ( "+ coordInt[0] +" ; "+ coordInt[1] + " ).\n");
	}
    
    
    public void bougerMeilleurCoup(int[] resultatBougerMeilleurCoup) {
    	int[] coordDepuis = {0,0};
		int[] coordVers = {0,0};
		coordDepuis[0] = resultatBougerMeilleurCoup[0];
		coordDepuis[1] = resultatBougerMeilleurCoup[1];
		coordVers[0] = resultatBougerMeilleurCoup[2];
		coordVers[1] = resultatBougerMeilleurCoup[3];
		int ptBouger = resultatBougerMeilleurCoup[4];
		
		//	On bouge si le mouvement apporte des points et si la case d'arriv�e n'est pas celle de d�part
		if (ptBouger > 0 && ( coordDepuis[0] != coordVers[0] || coordDepuis[0] != coordVers[0])) {
			
			this.plateau.bougerCarte(coordDepuis[0], coordDepuis[1], coordVers[0], coordVers[1]);
			
			//	Affichage emplacement carte pos�e
		    System.out.println(this.joueur.getNom()+" a boug� la carte de la case ( "+ coordDepuis[0] +" ; "+ coordDepuis[1] + " ) vers la case ( "+ coordVers[0] +" ; "+ coordVers[1] + " ).\n");
		}
	}
}	
