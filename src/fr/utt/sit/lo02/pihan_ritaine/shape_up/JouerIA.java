package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;


public class JouerIA extends Jouer{
	
	//	Cette variable sert à faire des calculs de points sans toucher au plateau de la partie
	private Plateau plateauCalcul;
	
	public JouerIA(Joueur joueur) {
		super(joueur);
		
		//	On initialise le plateau vide.
		//	Pas de forme car il va copier celle du plateau de jeu au moment du tour.
		this.plateauCalcul = new Plateau(null, null);
	}
	
    public void jouerTour() {
    	
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom() + ".\n");
    	System.out.println("Sa carte à jouer est " + this.joueur.getCarteAJouer().toString() + ".");
    	
    	this.plateau.afficher();
    	
    	
    //	Mise en place du calculateur de points
    	
    	// 	On récupère le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//  On donne la carte de victoire du joueur au calculateur de points
    	Carte carteVictoire = this.joueur.getCarteVictoire();
		calculateur.setCarteVictoire(carteVictoire);
		
		
		//	Copie du plateau 
		this.plateauCalcul.copy(this.plateau);
		
		HashMap<String,Integer> listePointPlacement = new HashMap<String,Integer>();
		
		int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//Parcours d'un plateau en considérant même les cases n'existant pas encore mais qui le pourrait si un décalage du plateau avait lieu
		for(int y=yMin-1; y<=yMax+1; y++) {
			
			for(int x=xMin-1; x<=xMax+1; x++) {
				
				//this.poserCarte(x,y,1) fait appel à la fonction plateau.poserCarte(int coordX, int coordY, Carte carteAPoser)
				//cette dernière vérifie si une carte peut être posée en fonction de sa position et pose la carte dans ce cas(ici la carte à jouer)  en considérant un plateau dynamique
				if( this.plateauCalcul.poserCarte(x, y, this.joueur.getCarteDeMain(1)) ) {
					
					//	Calcul des points
					int points = calculateur.calculerPoints(carteVictoire, this.plateauCalcul);
					
					//ajout de la valeur "points" associé aux coords de la case jouée 
					listePointPlacement.put( Plateau.getKey(x,y), points);
					
					//	On remet le plateau de calcul comme avant
					this.plateauCalcul.copy(this.plateau);
				}
				
			}
			
		}
		
		//	On calcul les points que rapporte le meilleur placement
		int ptPlacement = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
		int[] coordInt = {0,0};
		
		//	Si les points sont supérieur à 0, on joue le placement
		//	Sinon, on choisi une case au hasard
	    if (ptPlacement > 0) {
	    	String coordStr = Collections.max(listePointPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
	    	coordInt = this.plateau.getCoord(coordStr);
	    	
	    	//	On pose la carte sur l'emplacement
		    this.poserCarte(this.plateau, coordInt[0], coordInt[1], 1);
		    
		}
	    else {
			do {
				//	On génère un x et un y aléatoires entre les max et min du plateau
				Random r = new Random();
		        coordInt[0] = r.nextInt((xMax - xMin) + 1) + xMin;
		        
		        r = new Random();
		        coordInt[1] = r.nextInt((yMax - yMin) + 1) + yMin;

			} while (!poserCarte(this.plateau, coordInt[0], coordInt[1], 1));
		}
	    
    }
}	
