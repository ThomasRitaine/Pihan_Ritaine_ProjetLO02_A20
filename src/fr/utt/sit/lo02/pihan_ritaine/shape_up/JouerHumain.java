package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Parametre.ModeJeu;

public class JouerHumain extends Jouer{
	
	public JouerHumain(Joueur joueur) {
		super(joueur);
	}
	
    public void jouerTour() {
        
    	System.out.println("\nAu tour de " + this.joueur.getNom() + ".\n");
    	
    	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
    		System.out.println("Votre carte de victoire est " + this.joueur.getCarteVictoire().toString() + ".");
        	System.out.println("La carte à jouer est " + this.joueur.getCarteAJouer().toString() + ".");
		}
    	
    	if (this.joueur.modeJeu == ModeJeu.AVANCE) {
			System.out.println("Vous avez " + this.joueur.nbCarteDansMain() + " cartes dans votre main :");
			for (int i = 0; i < this.joueur.nbCarteDansMain(); i++) {
				if (this.joueur.getCarteDeMain(i) != null) {
					System.out.println("\t" + (i+1) + ") " + this.joueur.getCarteDeMain(i).toString());
				}
				else {
					System.out.println("\t" + (i+1) + ") Pas de carte.");
				}
			}
		}
    	
    	
    	System.out.println('\n');
    	this.plateau.afficher();
    	
    	//	Interface en ligne de commande
    	boolean finTour = false;
    	boolean aPoseCarte = false;
    	boolean aBouge = false;
    	
    	while (!finTour) {
    		
    		System.out.println('\n');
    		System.out.println("Saisissez une commande.\n\"aide\" pour avoir la liste des commandes.");
        	
        	String[] commande;		//	Sert à contenir les mots
        	commande = Input.scanner.nextLine().split(" ");
        	
        	System.out.println('\n');
        	
        	switch (commande[0]) {
        	
	            case "aide":
	            	System.out.println("Liste des commandes :");
	            	System.out.println("\n--  Affichage  --");
	        		System.out.println("\taide\t\t=>\tAffiche la liste des commandes et leur fonction.");
	        		System.out.println("\tplateau\t\t=>\tAffiche le plateau avec les coordonnées des cases et leur contenu.");
	        		if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	        			System.out.println("\tvictoire\t=>\tAffiche votre carte de victoire. (Non, cette commande ne vous fait pas gagner ;) )");
		        		System.out.println("\tcarte\t\t=>\tAffiche la carte à poser sur le plateau.");
	        		}
	        		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
	        			System.out.println("\tmain\t\t=>\tAffiche la liste des cartes de votre main.");
	        		}
	        		
	        		
	        		System.out.println("\n--  Actions  --");
	        		if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	        			System.out.println("\tposer X Y\t\t=>\tPose la carte sur la case de coordonnées X et Y du plateau.");
	        		}
	        		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
	        			System.out.println("\tposer n°Carte X Y\t=>\tPose la carte n°Carte de votre main sur la case de coordonnées X et Y du plateau.");
	        		}
	        		System.out.println("\tbouger X1 Y1 X2 Y2\t=>\tBouge la carte qui est sur la case avec les coordonnées X1 et Y1, vers la case qui a pour coordonnées X2 et Y2.");
	        		System.out.println("\tfin\t\t\t=>\tMet fin au tour si vous avez posé votre carte.");
	        		
	        		break;
	            	
	            case "fin":
	            	if (aPoseCarte) {
	            		finTour = true;
					} else {
						System.out.println("Il faut d'abord poser votre carte avant de mettre fin au tour.");
					}
	            	break;
	            	
	            case "victoire":
	            	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	            		System.out.println("Votre carte de victoire est toujours " + this.joueur.getCarteVictoire().toString());
	            	} else {
						System.out.println("Cette commande n'est pas disponible dans ce mode de jeu");
					}
	            	break;
	            
	            case "carte":
	            	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	            		if (!aPoseCarte) {
		            		System.out.println("Vous avez déjà oublié votre carte à jouer à jouer ?");
		            		System.out.println("C'est la suivante : " + this.joueur.getCarteAJouer().toString());
						} else {
							System.out.println("Vous avez déjà posé votre carte !");
						}
	            	} else {
						System.out.println("Cette commande n'est pas disponible dans ce mode de jeu");
					}
	            	break;
	            	
	            case "poser":
	            	if (!aPoseCarte) {
	            		int coordX = 0;
	            		int coordY = 0;
	            		int numCarte = 0;
	            		
	            		try {
	            			if (this.joueur.modeJeu == ModeJeu.NORMAL) {
		            			numCarte = 1;	//	Dans ce mode, la carte à jouer a l'id 1
		            			coordX = Integer.parseInt(commande[1]);
			            		coordY = Integer.parseInt(commande[2]);
		            		}
		            		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
		            			numCarte = Integer.parseInt(commande[1]) - 1;	//	On fait -1 car pour l'affichage, on montre id+1
		            			coordX = Integer.parseInt(commande[2]);
			            		coordY = Integer.parseInt(commande[3]);
		            		}
		            		aPoseCarte = this.poserCarte(coordX, coordY, numCarte);
		            		if (aPoseCarte) {
		            			System.out.println("La carte a bien été posée sur la case ( " + coordX + " ; " + coordY + " ) !" );
							} else {
								System.out.println("Erreur lors du placement de la carte.");
								System.out.println("Il y a peut-être déjà une carte sur cette case, ou bien elle n'est pas adjacente à une case contenant une carte.");
							}
						}
            			catch (NumberFormatException e) {
            				System.out.println("Les paramètres doivent être des nombres entiers.");
						}
	            		catch (ArrayIndexOutOfBoundsException e) {
	            			System.out.println("Vous n'avez pas donné assez d'argument à la fonction. Saisissez \"aide\" pour plus d'information.");
						}
	            		
					} else {
						System.out.println("Vous avez déjà posé votre carte !");
					}
	            	break;
	            	
	            case "bouger":
	            	if (!aBouge) {
		            		
            			try {
            				int coordX1 = Integer.parseInt(commande[1]);
            				int coordY1 = Integer.parseInt(commande[2]);
            				int coordX2 = Integer.parseInt(commande[3]);
            				int coordY2 = Integer.parseInt(commande[4]);
		            		aBouge = this.plateau.bougerCarte(coordX1, coordY1, coordX2, coordY2);
		            		if (aBouge) {
		            			System.out.println("La carte de la case ( " + coordX1 + " ; " + coordY1 + " ) a bien été bougée sur la case ( " + coordX2 + " ; " + coordY2 + " ) !" );
							} else {
								System.out.println("Erreur lors du déplacement de la carte.");
								System.out.println("Il y a peut-être déjà une carte sur la case ciblée, ou bien elle n'est pas adjacente à une case contenant une carte, ou alors il n'y a pas de carte sur la première case.");
							}
						}
            			catch (NumberFormatException e) {
            				System.out.println("Les coordonnées doivent être des nombres entiers.");
						}
            			catch (ArrayIndexOutOfBoundsException e) {
            				System.out.println("Il faut spécifier les coordonnées des deux cases, rien de plus.");
						}
						
					} else {
						System.out.println("Vous avez déjà bougé une carte !");
					}
	            	break;
	            	
	            case "plateau":
	            	this.plateau.afficher();
	            	break;
	            	
	            case "main":
	            	if (this.joueur.modeJeu == ModeJeu.AVANCE) {
	            		this.afficherMain();
	            	} else {
						System.out.println("Cette commande n'est pas disponible dans ce mode de jeu");
					}
	            	
	            	break;
	            	
	            default:
	            	System.out.println("La commande \"" + commande[0] + "\" n'est pas connue de nos services.");
	            	break;
        	}
    		
        	//	On met fin au tour si la carte a été posée et qu'une carte a été bougée, on met fin au tour
        	if (aBouge && aPoseCarte) {
				finTour = true;
			}
        	
		}
    	
    }
    
    

}
