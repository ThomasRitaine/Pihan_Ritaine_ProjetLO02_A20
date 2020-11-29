package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import java.util.HashMap;

public class JouerIA extends Jouer{
	
	public JouerIA(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de l'ordinateur " + this.joueur.getNom());
    	
    	
    	
    	//	Test, on simule que le joueur pose sa carte
    	this.afficherMain();
    	
    	if (this.joueur.getCarteDeMain(0) == null) {
    		this.joueur.setCarteDeMain(1, null);
		} else {
			this.joueur.setCarteDeMain(0, null);
		}
    	
    	//this.poserCarteIA(emplacement);	
    	
    }
    
    	//algo pour poser automatiquement une carte.
    //Pour l'instant pose la carte sur la première case adjacente
    
    public boolean poserCarteIA(int idCarte) {
		boolean reussite = false;
		HashMap<String,Case> cases=this.plateau.getCases();
		for(String key_i: cases.keySet()) {
		 while(!reussite) {
			if(this.plateau.peutPoserCarte(cases.get(key_i))) {
				//	On met la carte du joueur sur la case
				cases.get(key_i).setCarte(this.joueur.getCarteDeMain(idCarte));
				//	Puis on supprime la carte à jouer du joueur
				this.joueur.setCarteDeMain(idCarte, null);
				//on sort de la boucle for
				reussite=true;
			}
		 }
		}
		return reussite;
	}
    
  //algo pour poser automatiquement une carte.
    //Version en construction ou l'ia choisit la meilleure solution pour elle 
    //mais ne bloque pas les autres joueurs en bougeant aléatoirement des cartesà certains tours
    public boolean poserCarteIA2(int idCarte) {
		boolean reussite = false;
		Case meilleurPlacement=this.trouverMeilleurPlacement(idCarte);
		if(meilleurPlacement!=null) {
			//	On met la carte du joueur sur la case
			meilleurPlacement.setCarte(this.joueur.getCarteDeMain(idCarte));
			//	Puis on supprime la carte à jouer du joueur
			this.joueur.setCarteDeMain(idCarte, null);
			reussite=true;
		}		
		return reussite;
		}	
			

    
    public Case trouverMeilleurPlacement(int idCarte) {
    	Case meilleurPlacement=null;
    	HashMap<String,Case>cases=this.plateau.getCases();
    	
		for(String key_i: cases.keySet()) {		 
			if(this.plateau.peutPoserCarte(cases.get(key_i))) {
				cases.get(key_i).setCarte(this.joueur.getCarteDeMain(idCarte));
				//calculerPoints( this.joueur.getCarteVictoire(), this.plateau);
				//il faut trouver un moyen pour déclancher le calcul des points
				//il faut enregistrer les resultats dans une arrayliste pour avoir un tableau de points dynamique
				//il faut retirer la carte de la case
			}	
		}			
		//il faut utiliser une fonction qui trouve le maximum dans le tableau
		//il faut set la carte à cet endroit et définir l'endroit comme le meilleur placement  
		return meilleurPlacement;
   }

}
