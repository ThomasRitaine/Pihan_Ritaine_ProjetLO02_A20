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
    	this.setCarteVictoire(carteVictoire);
    	System.out.println("\n\n****Calcul du score couleur du joueur****");
    	int scoreCouleur=CalculerPointsLignesCouleur(plateau);
    	System.out.println("\n\n****Calcul du score forme du joueur****");
    	int scoreForme=CalculerPointsLignesForme(plateau);
    	System.out.println("\n\n****Calcul du score remplissage du joueur****");
    	int scoreRemplissage= CalculerPointsLignesRemplissage(plateau);
    	int scoreTotalManche = scoreCouleur
				    		  +scoreForme
				    		  +scoreRemplissage;
    	return scoreTotalManche;

    }

    public void setCarteVictoire(Carte carteVictoire) {
        this.carteVictoire = carteVictoire;
    }
  
	public int CalculerPointsLignesCouleur(Plateau plateau) {
		boolean estContinu;
		int scoreLigne = 0;
		int score = 0;
		int nbCaseLigneCouleur = 1;
		for (int k = 1; k <= 2; k++) {
			for (int y = 1; y <= this.const1; y++) {
				estContinu = false;
				nbCaseLigneCouleur = 1;
				for (int x = 1; x <= this.const2; x++) {
					if(k==1) {
						System.out.println(x+";"+y);
					}else {							
						System.out.println(y+";"+x);	
					}
					if ( k == 1 
							&& (plateau.getCase(x, y) != null) 
							&& !plateau.getCase(x, y).estVide()
							&& plateau.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())
					   ||k == 2 
					        && (plateau.getCase(y, x) != null) 
					        && !plateau.getCase(y, x).estVide()
							&& plateau.getCase(y, x).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
						if (estContinu == false) {
							estContinu = true;
							System.out.println("[score couleur] avant ajout de scoreLigne : " + score +"[scoreLigne] couleur:"+scoreLigne);
							score+=scoreLigne;
							System.out.println("[score couleur] après ajout : " + score);
						} else {
							nbCaseLigneCouleur++;
							System.out.println("[nbCaseLigneCouleur] : " + nbCaseLigneCouleur);
							if(nbCaseLigneCouleur>=3) {
								scoreLigne = nbCaseLigneCouleur +1;
								System.out.println("[scoreLigne couleur] : " + scoreLigne);
							}									
						}
					} else {
						estContinu = false;
						nbCaseLigneCouleur = 1;
					}
				}
				score+=scoreLigne;
				scoreLigne=0;
			}
			int pourInverser = const1;
			const1 = const2;
			const2 = pourInverser;
			System.out.println("\n");
		}
		System.out.println("\n[score total couleur] : "+score);
		return score;
	}
	
	public int CalculerPointsLignesForme(Plateau plateau) {
		boolean estContinu;
		int scoreLigne = 0;
		int score=0;
		int nbCaseLigneForme = 1;
		for(int k=1; k<=2;k++) {
			for (int y = 1; y <= this.const1; y++) {				
					estContinu = false;
					nbCaseLigneForme = 1;					
					for (int x = 1; x <= this.const2; x++) {
						if(k==1) {
							System.out.println(x+";"+y);
						}else {							
							System.out.println(y+";"+x);	
						}
						
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
								System.out.println("[score forme] avant ajout de scoreLigne : " + score +"[scoreLigne forme]:"+scoreLigne);
								score+=scoreLigne;
								System.out.println("[score forme] après ajout : " + score);
								
							} else {
								nbCaseLigneForme++;
								System.out.println("[nbCaseLigneForme] : " + nbCaseLigneForme);
								scoreLigne = nbCaseLigneForme-1;
								System.out.println("[scoreLigne forme] : " + scoreLigne);								
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
				System.out.println("\n");
			}
		System.out.println("\n[score Total forme] : "+score);
		return score;			
	}
		
	

	public int CalculerPointsLignesRemplissage(Plateau plateau) {
		boolean estContinu;
		int scoreLigne = 0;
		int score = 0;
		int nbCaseLigneRemplissage = 1;
		for (int k = 1; k <= 2; k++) {
			for (int y = 1; y <= this.const1; y++) {
				estContinu = false;
				nbCaseLigneRemplissage = 1;
				for (int x = 1; x <= this.const2; x++) {
					if(k==1) {
						System.out.println(x+";"+y);
					}else {							
						System.out.println(y+";"+x);	
					}
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
							System.out.println("[score remplissage] avant ajout de scoreLigne : " + score +"[scoreLigne]:"+scoreLigne);
							score+=scoreLigne;
							System.out.println("[score remplissage] après ajout : " + score);
						} else {
							nbCaseLigneRemplissage++;
							System.out.println("[nbCaseLigneRemplissage] : " + nbCaseLigneRemplissage);
							if(nbCaseLigneRemplissage>=3) {										
								scoreLigne = nbCaseLigneRemplissage;
								System.out.println("[scoreLigne remplissage] : " + scoreLigne);
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
			System.out.println("\n");
		}
		System.out.println("\n[score Remplissage Total] : "+score);
		return score;
	}
	
	
	public static void main(String[] args) {
		 Plateau p = new Plateau(FormesPlateau.RECTANGLE);
		 Carte carteVictoire=new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU);
		 p.afficher();
		 p.getCase(1,1).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU));
		 p.getCase(2,1).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU));
		 p.getCase(3,1).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU));
		 p.getCase(4,1).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU));
		 p.getCase(5,1).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.BLEU));
		 
		 p.getCase(1,2).setCarte(new Carte(true,FormesCarte.TRIANGLE,CouleursCarte.VERT));
		 p.getCase(2,2).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.VERT));
		 p.getCase(3,2).setCarte(new Carte(true,FormesCarte.CARRE,CouleursCarte.VERT));
		 p.getCase(4,2).setCarte(new Carte(false,FormesCarte.CARRE,CouleursCarte.VERT));
		 p.getCase(5,2).setCarte(new Carte(false,FormesCarte.TRIANGLE,CouleursCarte.VERT));
		 
		 p.getCase(1,3).setCarte(new Carte(false,FormesCarte.CARRE,CouleursCarte.ROUGE));
		 p.getCase(2,3).setCarte(new Carte(false,FormesCarte.CARRE,CouleursCarte.VERT));
		 p.getCase(3,3).setCarte(new Carte(false,FormesCarte.TRIANGLE,CouleursCarte.VERT));
		 p.getCase(4,3).setCarte(new Carte(false,FormesCarte.CARRE,CouleursCarte.ROUGE));
		 p.getCase(5,3).setCarte(new Carte(false,FormesCarte.CARRE,CouleursCarte.ROUGE));
		 p.afficher();
		 boolean estContinu;
			int scoreLigne = 0;
			int score=0;
			int nbCaseLigneForme = 1;
			int const1 = 3;
			int const2 = 5;
			for(int k=1; k<=2;k++) {
			for (int y = 1; y <= const1; y++) {				
					estContinu = false;
					nbCaseLigneForme = 1;					
					for (int x = 1; x <= const2; x++) {
						if(k==1) {
							System.out.println(x+";"+y);
						}else {							
							System.out.println(y+";"+x);	
						}
						
						if ( k==1
								&&(p.getCase(x, y) != null)
								&&(!p.getCase(x, y).estVide())
								&& p.getCase(x, y).getCarte().getForme().equals(carteVictoire.getForme())
						   ||k==2
							    &&(p.getCase(y, x) != null)
							    &&(!p.getCase(y, x).estVide())
								&& p.getCase(y, x).getCarte().getForme().equals(carteVictoire.getForme())){
								
						  	if (estContinu == false) {
								estContinu = true;
								System.out.println("[score] avant ajout de scoreLigne : " + score +"[scoreLigne]:"+scoreLigne);
								score+=scoreLigne;
								System.out.println("[score] après ajout : " + score);
								
							} else {
								nbCaseLigneForme++;
								System.out.println("[nbCaseLigneForme] : " + nbCaseLigneForme);
								scoreLigne = nbCaseLigneForme-1;
								System.out.println("[scoreLigne] : " + scoreLigne);
								
							}
						} else {
							
							estContinu = false;
							nbCaseLigneForme = 1;
						}						
					}
					System.out.println("[score] avant ajout de scoreLigne : " + score +"[scoreLigne]:"+scoreLigne);
					score+=scoreLigne;
					System.out.println("[score] après ajout : " + score);
					scoreLigne=0;
				}			    		    
				int pourInverser = const1;
				const1 = const2;
				const2 = pourInverser;
				System.out.println("\nCalcul des colonnes");
			}
				System.out.println(score);
				
				
				System.out.println("\nCalcul des lignes couleurs\n");
				int nbCaseLigneCouleur = 1;
				 
				for (int k = 1; k <= 2; k++) {
					for (int y = 1; y <= const1; y++) {
						estContinu = false;
						nbCaseLigneCouleur = 1;
						for (int x = 1; x <= const2; x++) {
							if(k==1) {
								System.out.println(x+";"+y);
							}else {							
								System.out.println(y+";"+x);	
							}
							if ( k == 1 
									&& (p.getCase(x, y) != null) 
									&& !p.getCase(x, y).estVide()
									&& p.getCase(x, y).getCarte().getCouleur().equals(carteVictoire.getCouleur())
							   ||k == 2 
							        && (p.getCase(y, x) != null) 
							        && !p.getCase(y, x).estVide()
									&& p.getCase(y, x).getCarte().getCouleur().equals(carteVictoire.getCouleur())) {
								if (estContinu == false) {
									estContinu = true;
									System.out.println("[score] avant ajout de scoreLigne : " + score +"[scoreLigne]:"+scoreLigne);
									score+=scoreLigne;
									System.out.println("[score] après ajout : " + score);
								} else {
									nbCaseLigneCouleur++;
									System.out.println("[nbCaseLigneCouleur] : " + nbCaseLigneCouleur);
									if(nbCaseLigneCouleur>=3) {
										scoreLigne = nbCaseLigneCouleur +1;
										System.out.println("[scoreLigne] : " + scoreLigne);
									}									
								}
							} else {
								estContinu = false;
								nbCaseLigneCouleur = 1;
							}
						}
						score+=scoreLigne;
						scoreLigne=0;
					}
					int pourInverser = const1;
					const1 = const2;
					const2 = pourInverser;
					System.out.println("\nCalcul des colonnes");
				}
				System.out.println(score);
				System.out.println("\nCalcul des lignes remplissage\n");
				
				int nbCaseLigneRemplissage = 1;
				
				for (int k = 0; k < 2; k++) {
					for (int y = 0; y < const1; y++) {
						estContinu = false;
						nbCaseLigneRemplissage = 1;
						for (int x = 0; x < const2; x++) {
							if(k==1) {
								System.out.println(x+";"+y);
							}else {							
								System.out.println(y+";"+x);	
							}
							if ((  k == 0 
										&& (p.getCase(x, y) != null) 
										&& !p.getCase(x, y).estVide()							
										&& (p.getCase(x, y).getCarte().estRemplie() && carteVictoire.estRemplie()
													|| !p.getCase(x, y).getCarte().estRemplie() && !carteVictoire.estRemplie()))
								||(k == 1 
									    && (p.getCase(y, x) != null) 
									    && !p.getCase(y, x).estVide()
									    && (p.getCase(y, x).getCarte().estRemplie() && carteVictoire.estRemplie()
													|| !p.getCase(y, x).getCarte().estRemplie() && !carteVictoire.estRemplie()))) {
								if (estContinu == false) {
									estContinu = true;
									System.out.println("[score] avant ajout de scoreLigne : " + score +"[scoreLigne]:"+scoreLigne);
									score+=scoreLigne;
									System.out.println("[score] après ajout : " + score);
								} else {
									nbCaseLigneRemplissage++;
									System.out.println("[nbCaseLigneRemplissage] : " + nbCaseLigneRemplissage);
									if(nbCaseLigneRemplissage>=3) {										
										scoreLigne = nbCaseLigneRemplissage;
										System.out.println("[scoreLigne] : " + scoreLigne);
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
					int pourInverser = const1;
					const1 = const2;
					const2 = pourInverser;
					System.out.println("\nCalcul des colonnes");
				}
			System.out.println(score);	
	 }

		
		 
		 
}
