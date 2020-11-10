package jeu;


public class Case {

    private boolean vide;

    private Carte carte;

    private int coordX;

    private int coordY;

    public Carte Contient;// a quoi ça sert ? il me faut un boolean qui test si une case contient une carte en prenant en paramètre les coord de la case 
    
   int getCoordX() {
    	return this.coordX;
    }
   
   int getCoordY() {
   	return this.coordY;
   }
    
   void setCoordX(int x) {
   	 this.coordX=x;
   }
  
 void setCoordY(int y) {
  	 this.coordY=y;
  }
   
    
    boolean isAvecCarte(int x, int y) {
    	if (x==this.coordX & y==this.coordY) {
    		return true;
    	}else {
    		this.vide = false;
    		return false;
    	}
    }

    boolean isVide() {
        return this.vide;
    }

    void setVide(boolean value) {
        this.vide = value;
    }

    void setCarte(Carte value) {
        this.carte = value;
    }

    Carte getCarte() {
        return this.carte;
    }
    
    boolean isCaseAdjacente() {
    	if(isAvecCarte(coordX+1,coordY) | isAvecCarte(coordX,coordY+1) |isAvecCarte(coordX-1,coordY) |isAvecCarte(coordX,coordY-1)) {
    		
    	}
    }

    boolean isCaseDansPlateau() {
    	
    }
}
