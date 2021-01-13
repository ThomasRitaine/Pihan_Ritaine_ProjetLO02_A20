package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
/**
 * JouerHumain est la classe h�ritant de Jouer d�finissant les comportements d'un joueur humain.
 * Elle n'est compos�e que de la m�thode jouerTour permettant � un joueur humain d'interragir avec le programme afin de r�aliser ses actions et donc de jouer son tour. 
 * @author Ya�lle Pihan et Thomas Ritaine
 * @version 1.0
 * @see Jouer
 */
public class JouerHumain extends Jouer {
	/**
	 * Recup�re le constructeur de la super classe Jouer.
	 * @param joueur
	 */
	public JouerHumain(Joueur joueur) {
		super(joueur);
	}
	
	/**
	 * G�re les tours d'une manche pour les joueurs Humains.
	 * Permet aux joueurs de jouer les uns apr�s les autres : indique qui doit jouer, rappelle sa carte Victoire, annonce la carte pioch�e et pour le mode avanc�, rappelle sa main.
	 * Propose au joueur une commande "aide" r�sumant toutes les commandes auxquelles il a acc�s lors de son tour de jeu ainsi que les actions qu'il peut faire :
	 * 
	 *  aide,
	 *  plateau,
	 *  victoire,
	 *  carte,
	 *  main (mode avanc�),
	 *  poser X Y,
	 *  poser n�Carte X Y(mode avanc�),
	 *  bouger X1 Y1 X2 Y2,
	 *  fin. 
	 * Propose des rectifications aux erreurs d'entr�es du joueur lorsqu'elles arrivent pour l'aider � comprendre ce qui n'a pas march�. 
	 * 
	 */ 
    public void jouerTour() {
        
    	System.out.println("\n\n");
    	AsciiArt.littleDivider();
    	System.out.println("\n\tAu tour de " + this.joueur.getNom() + ".  (^_^)\n");
    	
    	if (this.joueur.modeJeu == ModeJeu.NORMAL) {
    		System.out.println("Votre carte de victoire est " + this.joueur.getCarteVictoire().toString() + ".");
        	System.out.println("La carte � jouer est " + this.joueur.getCarteAJouer().toString() + ".");
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
        	
        	String[] commande;		//	Sert � contenir les mots
        	commande = Input.scanner.nextLine().split(" ");
        	
        	System.out.println('\n');
        	
        	switch (commande[0]) {
        	
	            case "aide":
	            	System.out.println("Liste des commandes :");
	            	System.out.println("\n--  Affichage  --");
	        		System.out.println("\taide\t\t=>\tAffiche la liste des commandes et leur fonction.");
	        		System.out.println("\tplateau\t\t=>\tAffiche le plateau avec les coordonn�es des cases et leur contenu.");
	        		if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	        			System.out.println("\tvictoire\t=>\tAffiche votre carte de victoire. (Non, cette commande ne vous fait pas gagner ;) )");
		        		System.out.println("\tcarte\t\t=>\tAffiche la carte � poser sur le plateau.");
	        		}
	        		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
	        			System.out.println("\tmain\t\t=>\tAffiche la liste des cartes de votre main.");
	        		}
	        		
	        		
	        		System.out.println("\n--  Actions  --");
	        		if (this.joueur.modeJeu == ModeJeu.NORMAL) {
	        			System.out.println("\tposer X Y\t\t=>\tPose la carte sur la case de coordonn�es X et Y du plateau.");
	        		}
	        		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
	        			System.out.println("\tposer n�Carte X Y\t=>\tPose la carte n�Carte de votre main sur la case de coordonn�es X et Y du plateau.");
	        		}
	        		System.out.println("\tbouger X1 Y1 X2 Y2\t=>\tBouge la carte qui est sur la case avec les coordonn�es X1 et Y1, vers la case qui a pour coordonn�es X2 et Y2.");
	        		System.out.println("\tfin\t\t\t=>\tMet fin au tour si vous avez pos� votre carte.");
	        		
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
		            		System.out.println("Vous avez d�j� oubli� votre carte � jouer � jouer ?");
		            		System.out.println("C'est la suivante : " + this.joueur.getCarteAJouer().toString());
						} else {
							System.out.println("Vous avez d�j� pos� votre carte !");
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
		            			numCarte = 1;	//	Dans ce mode, la carte � jouer a l'id 1
		            			coordX = Integer.parseInt(commande[1]);
			            		coordY = Integer.parseInt(commande[2]);
		            		}
		            		if (this.joueur.modeJeu == ModeJeu.AVANCE) {
		            			numCarte = Integer.parseInt(commande[1]) - 1;	//	On fait -1 car pour l'affichage, on montre id+1
		            			coordX = Integer.parseInt(commande[2]);
			            		coordY = Integer.parseInt(commande[3]);
		            		}
		            		aPoseCarte = this.poserCarte(this.plateau, coordX, coordY, numCarte);
		            		if (aPoseCarte) {
		            			System.out.println("La carte a bien �t� pos�e sur la case ( " + coordX + " ; " + coordY + " ) !" );
		            			this.plateau.afficher();
		            		} else {
								System.out.println("Erreur lors du placement de la carte.");
								System.out.println("Il y a peut-�tre d�j� une carte sur cette case, ou bien elle n'est pas adjacente � une case contenant une carte.");
							}
						}
            			catch (NumberFormatException e) {
            				System.out.println("Les param�tres doivent �tre des nombres entiers.");
						}
	            		catch (ArrayIndexOutOfBoundsException e) {
	            			System.out.println("Vous n'avez pas donn� assez d'argument � la fonction. Saisissez \"aide\" pour plus d'information.");
						}
	            		
					} else {
						System.out.println("Vous avez d�j� pos� votre carte !");
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
		            			System.out.println("La carte de la case ( " + coordX1 + " ; " + coordY1 + " ) a bien �t� boug�e sur la case ( " + coordX2 + " ; " + coordY2 + " ) !" );
		            			this.plateau.afficher();
		            		} else {
								System.out.println("Erreur lors du d�placement de la carte.");
								System.out.println("Il y a peut-�tre d�j� une carte sur la case cibl�e, ou bien elle n'est pas adjacente � une case contenant une carte, ou alors il n'y a pas de carte sur la premi�re case.");
							}
						}
            			catch (NumberFormatException e) {
            				System.out.println("Les coordonn�es doivent �tre des nombres entiers.");
						}
            			catch (ArrayIndexOutOfBoundsException e) {
            				System.out.println("Il faut sp�cifier les coordonn�es des deux cases, rien de plus.");
						}
						
					} else {
						System.out.println("Vous avez d�j� boug� une carte !");
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
    		
        	//	On met fin au tour si la carte a �t� pos�e et qu'une carte a �t� boug�e, on met fin au tour
        	if (aBouge && aPoseCarte) {
				finTour = true;
			}
        	
		}
    	
    }
    
    

}
