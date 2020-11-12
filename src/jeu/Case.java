package jeu;


public class Case {

//Attributs
   private Carte carte;//si carte value = null alors case vide
   private int coordX;
   private int coordY;
   private boolean interdite = false;   
   
 //Methodes get et set   
   public int getCoordX() {
     return this.coordX;
    }
   
   public void setCoordX(int x) {
   	 this.coordX=x;
   }    
  
   public int getCoordY() {
   	 return this.coordY;
   }
   public void setCoordY(int y) {
  	 this.coordY=y;
  }
   
   public Carte getCarte() {
       return this.carte;
   }
   
   public void setCarte(Carte value) {
       this.carte = value;
   }   
  
   public boolean getInterdite() {
	   return this.interdite;
   }
   
   public void setInterdite() {
	   this.interdite=true;
   }

   boolean isVide() {
	   /*	Commentaires du petit geek qui fait le projet avec toi :
		
		1)	1 seul return par fonction
	    */
        if(this.getCarte()==null) {
     	   return true;
        }else return false;
   }
   
    // fonction dépendante de la fonction rechercheCase dans Plateau
   public boolean contientCarte(Plateau p, int x, int y) {
	   
	   /*	Commentaires du petit geek qui fait le projet avec toi :
		
		1)	A quoi sert cette fonction ? On en a déjà une dans la classe plateau qui fait la même chose
	    */
	   
		boolean resultat = false;
		Case caseTrouvee = p.rechercheCase(x, y);
		if (caseTrouvee != null) {
			if (caseTrouvee.getCarte() != null) {
				resultat = true;
			} else {
				System.out.println("[ContientCarte]: La case est vide");
			}
		} else {
			System.out.println("[ContientCarte]: Aucune case ne possède ces coordonnées.");
		}
		return resultat;
	}
   															
	// fonction dépendante de la fonction rechercheCase2 dans Plateau
	public boolean contientCarte2(Plateau p, int x, int y) {
		boolean resultat = false;
		Case caseTrouvee = p.rechercheCase2(x, y);
		if (caseTrouvee != null) {
			if (caseTrouvee.getCarte() != null) {
				resultat = true;
			} else {
				System.out.println("[ContientCarte]: La case est vide");
			}
		} else {
			System.out.println("[ContientCarte]: Aucune case ne possède ces coordonnées.");
		}
		return resultat;
	}
    
    public boolean isCaseAdjacente(Plateau p) {
    	
    	/*	Commentaires du petit geek qui fait le projet avec toi :
		
		1)	Les coordX et coordY ne sont pas initialisées.
			Il faut que tu utilises this.coordX pour accéder aux coord de la case sur
			laquelle tu applique la méthode.
	    */
    	
    	boolean resultat = false;
    	if(contientCarte(p,coordX+1,coordY) || contientCarte(p,coordX,coordY+1) ||contientCarte(p,coordX-1,coordY)||contientCarte(p,coordX,coordY-1)) {
    		resultat = true;
    	}
    	return resultat;
    }
    
    //à développer que si l'on sait comment implémenter un plateau mobile
    /*public boolean isCaseDansPlateau() {
    	
    }*/
    
    
  

}
