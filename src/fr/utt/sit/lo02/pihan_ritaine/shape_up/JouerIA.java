package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;
import java.util.Collections;
import java.util.Comparator;
/**
 * <b>JouerIA est la classe h�ritant de Jouer d�finissant les comportements d'un joueur IA.</b>
 * <p>Elle est compos�e de la m�thode jouerTour et de sous-m�thodes assurant les calculs permettant de prendre les d�cisions des actions de l'IA : 
 * <ul><li>calculPoserMeilleurCoup</li>
 * <li>calculBougerMeilleurCoup</li>
 * <li>poserMeilleurCoup</li>
 * <li>bougerMeilleurCoup</li>
 * </ul>
 * </p>
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 * @see Jouer
 */

public class JouerIA extends Jouer{
	
	//	Cette variable sert � faire des calculs de points sans toucher au plateau de la partie
	private Plateau plateauCalcul;
	
	/**
	 * <b>R�cup�re le constructeur de la super classe Jouer.</b>
	 * <p>Initialise le joueurIA et le plateau qui servira aux calculs de l'IA : un plateau copie du plateau du tour actuel.</p>
	 * @see Jouer	
	 */	
	public JouerIA(Joueur joueur) {
		super(joueur);
		
		//	On initialise le plateau vide.
		//	Pas de forme car il va copier celle du plateau de jeu au moment du tour.
		this.plateauCalcul = new Plateau(null, null);
	}
	
	/**
	 * <b>G�re les tours d'une manche pour les joueurs IA.</b>
	 * <p>Annonce le nom du joueurIA qui va jouer, rappelle sa carte victoire et la carte � jouer ainsi que sa main si le mode est avanc�.</p>	
	 * @see  JouerIA#calculPoserMeilleurCoup()
	 * @see  JouerIA#poserMeilleurCoup(int[])
	 * @see  JouerIA#calculBougerMeilleurCoup()
	 * @see  JouerIA#bougerMeilleurCoup(int[])
	 */
    public void jouerTour() {
    	
    	System.out.println("\n\n");
    	AsciiArt.littleDivider();
    	System.out.println("\n\tAu tour de l'ordinateur " + this.joueur.getNom() + ".  [o_o]\n");
    	
    	//	Affichage des cartes
    	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
    		System.out.println("Sa carte � jouer est " + this.joueur.getCarteAJouer().toString() + ".");
		}
    	if (this.joueur.modeJeu == ModeJeu.AVANCE) {
    		this.afficherMain();
		}
    	
		
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
    //	Retourne 4 entiers :
    //		-	En 0 : la coordonn�e x du meilleur placement
    //		-	En 1 : la coordonn�e y du meilleur placement
    //		-	En 2 : les points gagn�s en posant la carte sur cette case
    //		-	En 3 : l'id de la carte � poser (surtout utile pour le mode avanc�)
    /**
     * <b>Calcule le meilleur coup pour poser une carte.</b>
     * @return Un tableau � 4 entiers : 
     * <ul><li>En 0 : la coordonn�e x du meilleur placement</li>
     * <li>En 1 : la coordonn�e y du meilleur placement</li>
     * <li>En 2 : les points gagn�s en posant la carte sur cette case</li>
     * <li>En 3 : l'id de la carte � poser (surtout utile pour le mode avanc�)</li></ul>
     * @see Partie#getCalculateurPts()
     * @see Joueur#getCarteVictoire()
     * @see Joueur#getCarteDeMain(int)
     * @see Joueur#nbCarteDeMain()
     * @see Plateau#getExtremum(string)
     * @see CalculPointsVisitor#calculerPoints(Carte,Plateau)
     * @see	Plateau#getKey(int, int)
     * @see Plateau#poserCarte(int, int, Carte)
     */
    public int[] calculPoserMeilleurCoup() {
    	
    	int[] meilleurCoup = {0,0,0,0};
    	
    	//	Le tableau dans lequel on stock les coords et les points du placement sur ces coords
    	HashMap<String,Integer> listePointPlacement = new HashMap<String,Integer>();
    	
    	//	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	// 	On r�cup�re la carte de victoire
    		//	Pour simplifier le mode avanc�, on consid�re que la carte de victoire est la carte 0 de la main.
    	Carte carteVictoire = null;
    	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
    		carteVictoire = this.joueur.getCarteVictoire();
		}
    	if (this.joueur.modeJeu == ModeJeu.AVANCE) {
    		carteVictoire = this.joueur.getCarteDeMain(0);
		}
    	
    	// 	Copie du plateau 
    	this.plateauCalcul.copy(this.plateau);
    	
    	//	R�cup�ration des coordonn�es extr�mes du plateau
    	int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		
		//	On parcourt le tableau pour chaque carte � poser
		for (int i = 1; i < this.joueur.nbCarteDansMain(); i++) {
			
			if (this.joueur.getCarteDeMain(i) == null) {
				i++;
			}
			
			//Parcours d'un plateau en consid�rant m�me les cases n'existant pas encore mais qui le pourrait si un d�calage du plateau avait lieu
			for(int y=yMin-1; y<=yMax+1; y++) {
				
				for(int x=xMin-1; x<=xMax+1; x++) {
					
					//this.poserCarte(x,y,1) fait appel � la fonction plateau.poserCarte(int coordX, int coordY, Carte carteAPoser)
					//cette derni�re v�rifie si une carte peut �tre pos�e en fonction de sa position et pose la carte dans ce cas(ici la carte � jouer)  en consid�rant un plateau dynamique
					if( this.plateauCalcul.poserCarte(x, y, this.joueur.getCarteDeMain(i)) ) {
						
						//	Calcul des points
						int points = calculateur.calculerPoints(carteVictoire, this.plateauCalcul);
						
						//ajout de la valeur "points" associ� aux coords de la case jou�e et � la carte � poser. "3;4/1"
						listePointPlacement.put( Plateau.getKey(x,y)+"/"+i, points);
						
						//	On remet le plateau de calcul comme avant
						this.plateauCalcul.copy(this.plateau);
					}
				}
			}
			
		}
		
		
		//	On calcul les points que rapporte le meilleur placement
		meilleurCoup[2] = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
		
		//	On calcule les coordon�es du meilleur placement
		String key = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		String coordStr = key.split("/")[0];
    	int[] coordInt = this.plateau.getCoord(coordStr);
    	int idCarte = Integer.parseInt(key.split("/")[1]);
    	
    	meilleurCoup[0] = coordInt[0];
    	meilleurCoup[1] = coordInt[1];
    	
    	meilleurCoup[3] = idCarte;
    	
    	return meilleurCoup;
	}
    
    
    
    //	Cette fonction calcule le meilleur coup pour d�placer une carte
    //	Retourne 5 entiers :
    //		-	En 0 : la coordonn�e x de la meilleur carte � d�placer
    //		-	En 1 : la coordonn�e y de la meilleur carte � d�placer
    //		-	En 2 : la coordonn�e x du meilleur emplacement o� mettre la carte
    //		-	En 3 : la coordonn�e y du meilleur emplacement o� mettre la carte
    //		-	En 4 : les points gagn�s en d�placant la carte
    /**
     * <b>Calcule le meilleur coup pour d�placer une carte</b>
     * @return Un tableau de 5 entiers : 
     * <ul><li>En 0 : la coordonn�e x de la meilleur carte � d�placer</li>
     * <li>En 1 : la coordonn�e y de la meilleur carte � d�placer</li>
     * <li>En 2 : la coordonn�e x du meilleur emplacement o� mettre la carte</li>
     * <li>En 3 : la coordonn�e y du meilleur emplacement o� mettre la carte</li>
     * <li>En 4 : les points gagn�s en d�placant la carte</li></ul>
     * @see Partie#getCalculateurPts()
     * @see Joueur#getCarteVictoire()
     * @see Joueur#getCarteDeMain(int)
     * @see Joueur#nbCarteDeMain()
     * @see Plateau#getExtremum(string)
     * @see CalculPointsVisitor#calculerPoints(Carte,Plateau)
     * @see	Plateau#getKey(int, int)
     * @see Plateau#bougerCarte(int, int, int, int)
     */
    public int[] calculBougerMeilleurCoup() {
    	
    	int[] meilleurCoup = {0,0,0,0,0};
    	
    	//	Le tableau dans lequel on stock les coords et les points du placement sur ces coords
    	HashMap<String,Integer> listePointPlacement = new HashMap<String,Integer>();
    	
    	//	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//	On r�cup�re la carte de victoire
			//	Pour simplifier le mode avanc�, on consid�re que la carte de victoire est la carte 0 de la main.
		Carte carteVictoire = null;
		if (this.joueur.modeJeu == ModeJeu.NORMAL) {
			carteVictoire = this.joueur.getCarteVictoire();
		}
		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
			carteVictoire = this.joueur.getCarteDeMain(0);
		}
    	
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
    
    /**
     * <b>Pose la carte du joueurIA au meilleur endroit</b>
     * <p></p>
     * @see Jouer#poserCarte(Plateau, int, int, int)
     * @see Plateau#getExtremum(string)	
     */
    public void poserMeilleurCoup(int[] resultatPoserMeilleurCoup) {
    	
    	int[] coordInt = {0,0};
		coordInt[0] = resultatPoserMeilleurCoup[0];
		coordInt[1] = resultatPoserMeilleurCoup[1];
		int ptPoser = resultatPoserMeilleurCoup[2];
		int idCarte = resultatPoserMeilleurCoup[3];

		//	R�cup�ration des coordonn�es extr�mes du plateau
    	int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//	Si les points sont sup�rieur � 0, on joue le placement
		//	Sinon, on choisi une case au hasard
	    if (ptPoser > 0) {
	    	//	On pose la carte sur l'emplacement
		    this.poserCarte(this.plateau, coordInt[0], coordInt[1], idCarte);
		}
	    else {
			do {
				//	On g�n�re un x et un y al�atoires entre les max et min du plateau
				Random r = new Random();
		        coordInt[0] = r.nextInt((xMax - xMin) + 1) + xMin;
		        
		        r = new Random();
		        coordInt[1] = r.nextInt((yMax - yMin) + 1) + yMin;

			} while (!poserCarte(this.plateau, coordInt[0], coordInt[1], idCarte));
		}
	    
	    //	Affichage emplacement carte pos�e
	    StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append(this.joueur.getNom());
		sb.append(" a pos� sa carte");
		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
			sb.append(" n�");
			sb.append(idCarte+1);
		}
		sb.append(" en ( ");
		sb.append(coordInt[0]);
		sb.append(" ; ");
		sb.append(coordInt[1]);
		sb.append(" ).\n");
		System.out.println(sb.toString());
	}
    
    /**
     * <b>Bouge la carte du joueurIA</b>
     * <p></p>
     * @see Plateau#bougerCarte(int, int, int, int)	
     */
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
