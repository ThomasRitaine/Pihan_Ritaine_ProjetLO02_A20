package jeu;


public class Case {

//Attributs
   private Carte carte;//si carte value = null alors case vide 
   /*private boolean vide = true;*/
   
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
   
 /* boolean isInterdite() {//fonction utile par la suite? 
	  if (this.getInterdite()){
		  return true;
	  }else return false;
  }*/
   
   public boolean contientCarte(Plateau p, int x, int y) { //fonction dépendante de la fonction rechercheCase dans Plateau
	  Case caseTrouvee=p.rechercheCase(x,y);
	  if (caseTrouvee != null) {
    		if (caseTrouvee.getCarte() != null) {
    			return true;
    		}else {
    			System.out.println("[isWithCarte]: La case est vide");
    			return false; 
    		}
    	}else {
    		System.out.println("[isWithCarte]: Aucune case ne possède ces coordonnées.");
    		return false;
    	}
    }    
    
    public boolean isCaseAdjacente(Plateau p) {
    	if(contientCarte(p,coordX+1,coordY) | contientCarte(p,coordX,coordY+1) |contientCarte(p,coordX-1,coordY)|contientCarte(p,coordX,coordY-1)) {
    		return true;
    	}else return false;
    }

    public boolean isCaseDansPlateau() {//à développer que si l'on sait comment implémenter un plateau mobile
    	
    }
    
    
    /*
    boolean isVide() {//en fait pas besoin d'un attribut vide la valeur de la carte suffit!
         if(this.getCarte()==null) {
      	   return true;
         }else return false;
      }

     boolean getVide() {
  	   return this.vide;
     }
      void setVide() {
          if(this.getCarte()==null) {
          	this.vide=true;
          }else this.vide=false;
      }*/
}
