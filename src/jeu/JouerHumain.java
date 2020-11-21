package jeu;

public class JouerHumain extends Jouer{
	
	public JouerHumain(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
    	System.out.println("\nAu tour de " + this.joueur.getNom() + ".\n");
    	System.out.println("Votre carte de victoire est " + this.joueur.getCarteVictoire().toString() + ".");
    	System.out.println("La carte � jouer est " + this.joueur.getCarteAJouer().toString() + ".");
    	System.out.println('\n');
    	this.plateau.afficher();
    	
    	//	Interface en ligne de commande
    	boolean finTour = false;
    	boolean aPoseCarte = false;
    	boolean aBouge = false;
    	
    	while (!finTour) {
    		
    		System.out.println('\n');
    		System.out.println("Saisissez une commande.\n\"aide\" pour avoir la liste des commandes.");
        	
        	String commande;	//	Sert � r�cup�rer la commande
        	String[] mots;		//	Sert � contenir les mots
        	commande = Input.scanner.nextLine();
        	System.out.println('\n');
        	mots = commande.split(" ");
        	
        	switch (mots[0]) {
        	
	            case "aide":
	            	System.out.println("Liste des commandes :");
	        		System.out.println("\taide\t=>\tAffiche la liste des commandes et leur fonction.");
	        		System.out.println("\tfin\t=>\tMet fin au tour si vous avez pos� votre carte.");
	        		System.out.println("\tvictoire\t=>\tAffiche votre carte de victoire. (Non, cette commande ne vous fait pas gagner ;) )");
	        		System.out.println("\tcarte\t=>\tAffiche la carte � poser sur le plateau.");
	        		System.out.println("\tposer X Y\t=>\tPose la carte sur la case de coordonn�es X et Y du plateau.");
	        		System.out.println("\tbouger X1 Y1 X2 Y2\t=>\tBouge la carte qui est sur la case avec les coordonn�es X1 et Y1, vers la case qui a pour coordonn�es X2 et Y2.");
	        		System.out.println("\tplateau\t=>\tAffiche le plateau avec les coordonn�es des cases et leur contenu.");
	        		break;
	            	
	            case "fin":
	            	if (aPoseCarte) {
	            		finTour = true;
					} else {
						System.out.println("Il faut d'abord poser votre carte avant de mettre fin au tour.");
					}
	            	break;
	            	
	            case "victoire":
	            	System.out.println("Votre carte de victoire est toujours " + this.joueur.getCarteVictoire().toString());
	            	break;
	            
	            case "carte":
	            	if (!aPoseCarte) {
	            		System.out.println("Vous avez d�j� oubli� votre carte � jouer � jouer ?");
	            		System.out.println("C'est la suivante : " + this.joueur.getCarteAJouer().toString());
					} else {
						System.out.println("Vous avez d�j� pos� votre carte !");
					}
	            	break;
	            	
	            case "poser":
	            	if (this.joueur.getCarteAJouer() != null) {
	            		int coordX = Integer.parseInt(mots[1]);
	            		int coordY = Integer.parseInt(mots[2]);
	            		
	            		aPoseCarte = this.poserCarte(coordX, coordY);
	            		if (aPoseCarte) {
	            			System.out.println("La carte a bien �t� pos�e sur la case ( " + coordX + " ; " + coordY + " ) !" );
						} else {
							System.out.println("Erreur lors du placement de la carte.");
							System.out.println("Il y a peut-�tre d�j� une carte sur cette case, ou bien elle n'est pas adjacente � une case contenant une carte.");
						}
					} else {
						System.out.println("Vous avez d�j� pos� votre carte !");
					}
	            	break;
	            	
	            case "bouger":
	            	if (!aBouge) {
	            		if (mots.length == 5) {
		            		
	            			try {
	            				int coordX1 = Integer.parseInt(mots[1]);
	            				int coordY1 = Integer.parseInt(mots[2]);
	            				int coordX2 = Integer.parseInt(mots[3]);
	            				int coordY2 = Integer.parseInt(mots[4]);
			            		aBouge = this.plateau.bougerCarte(coordX1, coordY1, coordX2, coordY2);
			            		if (aBouge) {
			            			System.out.println("La carte de la case ( " + coordX1 + " ; " + coordY1 + " ) a bien �t� boug�e sur la case ( " + coordX2 + " ; " + coordY2 + " ) !" );
								} else {
									System.out.println("Erreur lors du d�placement de la carte.");
									System.out.println("Il y a peut-�tre d�j� une carte sur la case cibl�e, ou bien elle n'est pas adjacente � une case contenant une carte, ou alors il n'y a pas de carte sur la premi�re case.");
								}
							}
	            			catch (NumberFormatException e) {
	            				System.out.println("Les coordonn�es doivent �tre des nombres entiers.");
							}
	            		} else {
							System.out.println("Il faut sp�cifier les coordonn�es des deux cases, rien de plus.");
						}
					} else {
						System.out.println("Vous avez d�j� boug� une carte !");
					}
	            	break;
	            	
	            case "plateau":
	            	this.plateau.afficher();
	            	break;
	            	
	            default:
	            	System.out.println("La commande \"" + mots[0] + "\" n'est pas connue de nos services.");
	            	break;
        	}
    		
        	//	On met fin au tour si la carte a �t� pos�e et qu'une carte a �t� boug�e, on met fin au tour
        	if (aBouge && aPoseCarte) {
				finTour = true;
			}
        	
		}
    	
    }
    
    

}
