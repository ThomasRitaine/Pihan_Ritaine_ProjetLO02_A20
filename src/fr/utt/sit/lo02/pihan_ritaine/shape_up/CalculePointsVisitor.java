package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Carte.CouleursCarte;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.Carte.FormesCarte;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.Plateau.FormesPlateau;

public class CalculePointsVisitor implements Visitor {
	
    private Carte carteVictoire;
    private int const1=0;
    private int const2=0;
    

    public int visit(Manche m) {
		return this.calculerPoints(this.carteVictoire, m.getPlateau());
    }

    public int calculerPoints(Carte carteVictoire, Plateau plateau) {
    	//prise en considération des dimensions maximales de x (const2)et y(const1) en fonction de la nature du plateau
    	switch(plateau.getFormesPlateau()) {
    	case RECTANGLE:
    		this.const1=3;
    		this.const2=5;
    		break;
    	case TRIANGLE:
    		this.const1=4;
    		this.const2=7;
    		break;
    	case ROND:
    		this.const1=5;
    		this.const2=4;
    		break;		
    	}
    	//récupération de la carte victoire du joueur pour lequel il faut calculer les points
    	this.setCarteVictoire(carteVictoire);
    	
    	//calcul du nombre de points accumulés en plusieurs étapes. 
    	//enregistrement en mémoire de chaque résultat dans des variables int
    	//puis somme enregistrée dans scoreTotalManche
    	System.out.println("****Calcul du score couleur du joueur****");
    	int scoreCouleur=calculerPointsLignesCouleur(plateau);
    	System.out.println("****score couleur : "+scoreCouleur+"****");
    	
    	System.out.println("****Calcul du score forme du joueur****");
    	int scoreForme=calculerPointsLignesForme(plateau);
    	System.out.println("****score couleur : "+scoreForme+"****");
    	
    	System.out.println("****Calcul du score remplissage du joueur****");
    	int scoreRemplissage= calculerPointsLignesRemplissage(plateau);
    	System.out.println("****score couleur : "+scoreRemplissage+"****");    	
    	
    	int scoreTotalManche = scoreCouleur + scoreForme + scoreRemplissage;
    	
    	return scoreTotalManche;
    }

    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
  
    //3 algorithmes identiques à quelques conditions et constantes près. 
    //ils calculent les points accumulés pour chaque lignes et chaque colonne pour ensuite en faire la somme
    //la constante k permet dans un premier temps (k=1) de parcourir toutes les cases des plateaux par ligne 
    //dans un second temps (k=2) de parcourir toutes les cases des plateaux par colonne.
	public int calculerPointsLignesCouleur(Plateau plateau) {
		//estContinu permet de découvrir si une ligne ou une colonne existe		
		boolean estContinu;
		int scoreLigne = 0;
		int score = 0;
		//nbCaseLigneCouleur permet de compter combien de cases forment la ligne ou la colonne
		//comme une ligne ou une colonne comprend au moins 2 cartes, par défaut = 1
		int nbCaseLigneCouleur = 1;
		for (int k = 1; k <= 2; k++) {
			for (int y = 1; y <= this.const1; y++) {
				//A chaque nouvelle ligne ou chaque nouvelle colonne il faut réinitialiser estContinu et nbCaseLigneCouleur
				//cela évite de compter par ex la première case d'une nouvelle ligne (ex : k=1 y=2 (2eme ligne) et x=1 (1ere case))  comme appartenant au dernier alignement de la ligne précédente (ex :k=1 y=1)  
				estContinu = false;
				nbCaseLigneCouleur = 1;
				for (int x = 1; x <= this.const2; x++) {
					//conditions pour vérifier que :
					//la case considérée existe, qu'elle n'est pas vide et que la carte qui s'y trouve est de la même couleur que la carte victoire
					//les conditions sont écrites 2 fois, une pour k =1 une pour k=2 car on doit inverser x et y dans getCase pour k=2
					if ( k == 1 
							&& (plateau.getCase(x, y) != null) 
							&& !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())
					   ||k == 2 
					        && (plateau.getCase(y, x) != null) 
					        && !plateau.getCase(y, x).estVide()
							&& plateau.getCase(y, x).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
				    //si les conditions précédentes sont vérifiées 
							 //si est continu était faux 
							    //alors on a affaire à la première case d'un nouvel alignement de couleur
								//on ajoute alors les points de la dernière ligne au score final
							 //sinon si continu était vrai
							   //alors la case sur laquelle l'algo s'est arrêté parcours un alignement
							   //on peut donc incrémenter le compteur de cases alignées "nbCaseLigneCouleur"
							   //et il faut recalculer le score de la ligne scoreLigne 
						       //sachant que pour les alignements de couleurs, on compatabilise les points que si plus de 2 cases sont alignés
							
						if (estContinu == false) {
							estContinu = true;							
							score+=scoreLigne;							
						} else {
							nbCaseLigneCouleur++;							
							if(nbCaseLigneCouleur>=3) {
								scoreLigne = nbCaseLigneCouleur +1;								
							}									
						}
					//sinon, les conditions précédentes ne sont pas vérifiés
					//comme cela peut marquer la fin d'un alignement, on réinitialise estContinu et nbCaseLigneCouleur
					} else {
						estContinu = false;
						nbCaseLigneCouleur = 1;
					}
				}//fin du parcours d'une ligne ou d'une colonne
				//on ajoute le score comptabilisé sur cette ligne ou colonne
				//on réinitialise le score de la ligne à 0
				//je pense qu'on peut passer ces deux lignes en dessous de l'ouverture de la boucle des y
				score+=scoreLigne;
				scoreLigne=0;
			}//fin de la boucle des y le plateau a donc été parcouru entièrement au moins 1 fois 
			//son deuxième parcours doit considérer des colonnes on doit donc inverser les const1 et const2
			int pourInverser = const1;
			const1 = const2;
			const2 = pourInverser;			
		}		
		return score;
	}
	
	public int calculerPointsLignesForme(Plateau plateau) {
		boolean estContinu;
		int scoreLigne = 0;
		int score=0;
		int nbCaseLigneForme = 1;
		for(int k=1; k<=2;k++) {
			for (int y = 1; y <= this.const1; y++) {				
					estContinu = false;
					nbCaseLigneForme = 1;					
					for (int x = 1; x <= this.const2; x++) {				
						
						if ( k==1
								&&(plateau.getCase(x, y) != null)
								&&(!plateau.getCase(x, y).estVide())
								&& plateau.getCase(x, y).getCarte().getForme().equals(carteVictoire.getForme())
						   ||k==2
							    &&(plateau.getCase(y, x) != null)
							    &&(!plateau.getCase(y, x).estVide())
								&& plateau.getCase(y, x).getCarte().getForme().equals(carteVictoire.getForme())){
								
						  	if (estContinu == false) {
								estContinu = true;								
								score+=scoreLigne;								
								
							} else {
								nbCaseLigneForme++;								
								scoreLigne = nbCaseLigneForme-1;																
							}
						} else {							
							estContinu = false;
							nbCaseLigneForme = 1;
						}						
					}
					score+=scoreLigne;					
					scoreLigne=0;
				}			    		    
				int pourInverser = const1;
				const1 = const2;
				const2 = pourInverser;				
			}		
		return score;			
	}
		
	

	public int calculerPointsLignesRemplissage(Plateau plateau) {
		boolean estContinu;
		int scoreLigne = 0;
		int score = 0;
		int nbCaseLigneRemplissage = 1;
		for (int k = 1; k <= 2; k++) {
			for (int y = 1; y <= this.const1; y++) {
				estContinu = false;
				nbCaseLigneRemplissage = 1;
				for (int x = 1; x <= this.const2; x++) {
					if ((  k == 1 
								&& (plateau.getCase(x, y) != null) 
								&& !plateau.getCase(x, y).estVide()							
								&& (plateau.getCase(x, y).getCarte().estRemplie() && carteVictoire.estRemplie()
											|| !plateau.getCase(x, y).getCarte().estRemplie() && !carteVictoire.estRemplie()))
						||k == 2 
							    && (plateau.getCase(y, x) != null) 
							    && !plateau.getCase(y, x).estVide()
							    && (plateau.getCase(y, x).getCarte().estRemplie() && carteVictoire.estRemplie()
											|| !plateau.getCase(y, x).getCarte().estRemplie() && !carteVictoire.estRemplie())) {
						if (estContinu == false) {
							estContinu = true;							
							score+=scoreLigne;
							
						} else {
							nbCaseLigneRemplissage++;							
							if(nbCaseLigneRemplissage>=3) {										
								scoreLigne = nbCaseLigneRemplissage;								
							}									
						}
				      } else {
						estContinu = false;
						nbCaseLigneRemplissage = 1;
					}
				}
				score+=scoreLigne;
				scoreLigne=0;
			}
			int pourInverser = this.const1;
			this.const1 = this.const2;
			this.const2 = pourInverser;			
		}		
		return score;
	}
	
	
	

		
		 
		 
}
