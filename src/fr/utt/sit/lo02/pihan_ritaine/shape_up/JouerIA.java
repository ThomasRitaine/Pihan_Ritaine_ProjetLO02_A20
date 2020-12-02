package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

//import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;

public class JouerIA extends Jouer{
	
	public JouerIA(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom());
    	System.out.println("Sa carte de victoire est " + this.joueur.getCarteVictoire().toString() + ".");
    	System.out.println("Sa carte � jouer est " + this.joueur.getCarteAJouer().toString() + ".");
    	

    	
    	
    //	Mise en place du calculateur de points
    	
    	// 	On r�cup�re le calculateur
    	CalculePointsVisitor calculateur = Partie.getPartie().getCalculateurPts();
    	
    	//  On donne la carte de victoire du joueur au calculateur de points
    	Carte carteVictoire = this.joueur.getCarteVictoire();
		calculateur.setCarteVictoire(carteVictoire);
		
		//	On r�cup�re la manche
		//Manche mancheEnCours = Partie.getPartie().getMancheActuelle();
		
		//	On calcule les points
		//int points = mancheEnCours.accept(calculateur);
		HashMap<Case,Integer>listMeilleurPlacement=new HashMap<Case,Integer>();
		
		int xMax = plateau.getExtremum("xMax");
		int xMin = plateau.getExtremum("xMin");
		int yMax = plateau.getExtremum("yMax");
		int yMin = plateau.getExtremum("yMin");
		
		//Parcours d'un plateau en consid�rant m�me les cases n'existant pas encore mais qui le pourrait si un d�calage du plateau avait lieu
		for(int i=yMin-1;i<=yMax+1;i++) {//faut-il mettre < ou <= ?
			for(int j=xMin-1;j<=xMax+1;j++) {//faut-il mettre < ou <= ?				
				//this.poserCarte(j,i,1) fait appel � la fonction plateau.poserCarte(int coordX, int coordY, Carte carteAPoser)
				//cette derni�re v�rifie si une carte peut �tre pos�e en fonction de sa position et pose la carte dans ce cas(ici la carte � jouer)  en consid�rant un plateau dynamique
				if(this.poserCarte(j,i,1)) {
					int points = calculateur.calculerPoints(carteVictoire, this.plateau);
					//ajout de la valeur "points" associ� � sa cl�, la case consid�r�e actuellement par l'algorithme, "plateau.getCase(j,i)" dans la HashMap 
					listMeilleurPlacement.put(plateau.getCase(j,i),points);
					//suppression de la carte � jouer sur la case consid�r�e
					plateau.getCase(j,i).setCarte(null);
				}
			}		
		}
		
		//on utilise une fonction de la classe Collections qui trouve la cl� ici la case meilleurPlacement associ� � la valeur maximum du calcul de points
	    Case meilleurPlacement=Collections.max(listMeilleurPlacement.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();	
    	//ajout de la carte sur le meilleurPlacement 
	    this.poserCarte(plateau.getCoord(meilleurPlacement)[0],plateau.getCoord(meilleurPlacement)[1],1);
    }
}	
