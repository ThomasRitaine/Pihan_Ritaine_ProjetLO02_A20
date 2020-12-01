package fr.utt.sit.lo02.pihan_ritaine.shape_up;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.Plateau.FormesPlateau;


/**
 * Parametre est la classe repr�sentant les param�tres d'une partie.
 * On peut la modifier au d�but de la partie, elle sert � cr�er la partie.
 * Un grand nombre des algorithmes de la partie consultent les param�tres.
 * 
 * @author Ya�lle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class Parametre {
	
//	ENUMERATION
	public enum ModeJeu {
		NORMAL,
		AVANCE;
	}
	
//	ATTRIBUTS
	
	/**
     * Le nombre de joueurs dans la partie, humains et IA confondus.
     * 
     * @see Parametre#setNbJoueur(int)
     * @see Parametre#getNbJoueur()
     */
    private int nbJoueur;
    
    /**
     * Le nombre de joueurs humains dans la partie.
     * 
     * @see Parametre#setNbJoueurHumain(int)
     * @see Parametre#getNbJoueurHumain()
     */
    private int nbJoueurHumain;
    
    /**
     * Le nombre de joueurs IA dans la partie.
     * 
     * @see Parametre#setNbJoueurIA(int)
     * @see Parametre#getNbJoueurIA()
     */
    private int nbJoueurIA;
    
    /**
     * Les noms des joueurs de la partie.
     * C'est un tableau de 2 � 3 String.
     * 
     * @see Parametre#getNomJoueurs(int)
     */
    private String[] nomsJoueurs = new String[3];
    
    /**
     * La forme du plateau qui sera g�n�r� pour chaque manche de la partie.
     * Sa valeur est un �l�ment de l'�num�ration FormesPlateau.
     * 
     * @see Plateau#FormesPlateau
     * @see Parametre#setFormePlateau(FormesPlateau)
     * @see Parametre#getFormePlateau()
     */
    private FormesPlateau formePlateau;
    
    /**
     * Le nombre de manche qu'il faut jouer avant de calculer les points totaux et mettre fin � la partie.
     * 
     * @see Parametre#setNbManche(int)
     * @see Parametre#getNbManche()
     */
    private int nbManche;
    
    
    private ModeJeu modeJeu;

    
//	CONSTRUCTEURS
    
    /**
     * Constructeur Parametre.
     * A la construction d'un objet Parametre, il est possible de passer tous les param�tres qui vont le composer.
     * 
     * @param nbJoueur
     *            Le nombre de joueurs en tout. Ce nombre est entre 2 et 3 inclus.
     * @param nbJoueurHumain
     *            Parmi les 2 ou 3 joueurs, nbJoueurHumain repr�sente le nombre d'humains.
     * @param noms
     *            Un tableau de String qui contient le noms des 2 ou 3 joueurs.
     * @param formePlateau
     *            Un �l�ment de l'�num�ration FormesPlateau qui correspond � la forme qu'aurons les
     *            plateaux g�n�r�s � chaque manche de la partie.
     * @param modeJeu
     *            Le mode de jeu choisi.
     * @param nbManche
     *            Le nombre de manches qui composent la partie. C'est un entier sup�rieur ou �gal � 1.
     * 
     * @see Plateau#FormesPlateau
     * @see Parametre#ModeJeu
     * @see Parametre#nbJoueur
     * @see Parametre#nbJoueurHumain
     * @see Parametre#formePlateau
     * @see Parametre#nbManche
     */
    public Parametre(int nbJoueur, int nbJoueurHumain, String[] noms, FormesPlateau formePlateau, ModeJeu modeJeu, int nbManche) {
    	this.nbJoueur = nbJoueur;
    	this.nbJoueurHumain = nbJoueurHumain;
    	this.nbJoueurIA = nbJoueur - nbJoueurHumain;
    	for (int i = 0; i < this.nbJoueur; i++) {
			this.nomsJoueurs[i] = noms[i];
		}
    	this.formePlateau = formePlateau;
    	this.modeJeu = modeJeu;
    	this.nbManche = nbManche;
    	
    	this.resumerParametre();
    }
    
    public Parametre() {
    }
    
  
//	M�thodes
    public void parametrerPartie() {
    	boolean validInput;
    	boolean choisi;
    	
    	System.out.println("-   1. Param�trons la partie !   -\n");
    	
    	//	R�cup�ration du nombre de joueur
    	do {
    		validInput = true;
			try {
				System.out.println("Entrez le nombre de joueur, Humain et IA compris entre 2 et 3.");
		    	this.nbJoueur = Input.scanner.nextInt();
		    	
		    	if (this.nbJoueur < 2 || this.nbJoueur > 3 ) {
					throw new Exception();
				}
		    	
			} catch (Exception e) {
				System.out.println("Saisie incorrecte : Le nombre de joueur doit �tre un entier compris entre 2 et 3.\n");
				validInput = false;
				Input.scanner.nextLine();		//	On vide le buffer
			}
		} while (!validInput);
    	
    	
    	//	R�cup�ration du nombre de joueur humain
    	do {
    		validInput = true;
			try {
				System.out.println("\nParmi ces " + this.nbJoueur + " joueurs, combien sont humains ?");
		    	this.nbJoueurHumain = Input.scanner.nextInt();
		    	
		    	if (this.nbJoueurHumain < 0 || this.nbJoueurHumain > this.nbJoueur ) {
					throw new Exception();
				}
		    	
			} catch (Exception e) {
				System.out.println("Saisie incorrecte : Le nombre de joueur humain �tre un entier compris entre 0 et "+ this.nbJoueur +", le nombre de joueur total.\n");
				validInput = false;
				Input.scanner.nextLine();		//	On vide le buffer
			}
		} while (!validInput);
    	
    	
    	//	Calcul et confirmation du nombre de joueur IA
    	this.nbJoueurIA = this.nbJoueur - this.nbJoueurHumain;
    	System.out.println("\nIl y a donc " + this.nbJoueurIA + " joueur(s) IA.");
    	
    	
    	//	R�cup�ration des noms des joueurs
    	Input.scanner.nextLine();		//	On vide le buffer
    	for (int i = 0; i < this.nbJoueur; i++) {
			
			//	R�cup�ration et validation
			do {
	    		validInput = true;
				try {
					if (i < this.nbJoueurHumain) {
						System.out.println("\nQuel est le nom du joueur n�" + (i+1) + " ? (C'est un humain)");
					} else {
						System.out.println("\nQuel est le nom du joueur n�" + (i+1) + " ? (C'est un ordinateur)");
					}
					this.nomsJoueurs[i] = Input.scanner.nextLine();
			    	if (this.nomsJoueurs[i].equals("Ya�lle") || this.nomsJoueurs[i].equals("Thomas") ) {
						throw new Exception();
					}
			    	
				} catch (Exception e) {
					System.out.println("Saisie incorrecte : Vous ne pouvez pas utiliser les pr�noms des grands cr�ateurs : Ya�lle et Thomas.\n");
					validInput = false;
				}
			} while (!validInput);
		}
    	
    	
    	// 	Choix de la forme du plateau
    	do {
    		choisi = true;
			
			//	Affichage
			System.out.println("\nChoisissez un plateau parmi ceux-ci : (Entrez le num�ro)");
	    	int nbPlateau = 0;
	    	for ( FormesPlateau forme : FormesPlateau.values()) {
	    		nbPlateau++;
	    		System.out.println("\t" + nbPlateau + ") " + forme);
	    	}
	    	System.out.println("Choisissez un num�ro, ou bien saisissez \"afficher n\" pour afficher le plateau num�ro n. ");
	    	
			//	R�cup�ration de l'input
	    	String[] commande = Input.scanner.nextLine().split(" ");
	    	
	    	if (commande[0].equals("afficher")) {
	    		choisi = false;
				try {
					int choix = Integer.parseInt(commande[1]);
					//	Validation de l'input
			    	if (choix < 1 || choix > nbPlateau) {
						throw new Exception();
					}
					nbPlateau = 0;
			    	for ( FormesPlateau forme : FormesPlateau.values()) {
			    		nbPlateau++;
			    		if (nbPlateau == choix) {
							Plateau plateau = new Plateau(forme, null);
							plateau.afficher();
						}
			    	}
				} catch (Exception e) {
					System.out.println("Saisie incorrecte : Le plateau � afficher doit �tre un entier donn� au dessus.\n");
				}
			}
	    	else {
				try {
					int choix = Integer.parseInt(commande[0]);
					//	Validation de l'input
			    	if (choix < 1 || choix > nbPlateau) {
						throw new Exception();
					}
			    	
			    	//	Ajout de l'input
			    	nbPlateau = 0;
			    	for ( FormesPlateau forme : FormesPlateau.values()) {
			    		nbPlateau++;
			    		if (nbPlateau == choix) {
							this.formePlateau = forme;
						}
			    	}
				} catch (Exception e) {
					Input.scanner.nextLine();		//	On vide le buffer
					System.out.println("Saisie incorrecte : Votre choix doit �tre un entier donn� au dessus.\n");
					choisi = false;
				}
			}
			
		} while (!choisi);
    	
    	
    	//	Choix du mode de jeu
    	do {
    		choisi = true;
			
			//	Affichage
    		System.out.println("\nChoisissez un mode de jeu parmi les suivants : (Entrez le num�ro)");
    		int nbMode = 0;
	    	for ( ModeJeu mode : ModeJeu.values()) {
	    		nbMode++;
	    		System.out.println("\t" + nbMode + ") " + mode);
	    	}
	    	System.out.println("Choisissez un num�ro, ou bien saisissez \"info n\" pour afficher les r�gles du mode de jeu num�ro n. ");
	    	
			//	R�cup�ration de l'input
	    	String[] commande = Input.scanner.nextLine().split(" ");
	    	
	    	if (commande[0].equals("info")) {
	    		choisi = false;
				try {
					int choix = Integer.parseInt(commande[1]);
					//	Validation de l'input
			    	if (choix < 1 || choix > nbMode) {
						throw new Exception();
					}
					nbMode = 0;
					ModeJeu modeJeuInfo = null;
					for ( ModeJeu mode : ModeJeu.values()) {
			    		nbMode++;
			    		if (nbMode == choix) {
							modeJeuInfo = mode;
						}
			    	}
					switch (modeJeuInfo) {
					case NORMAL: 
						System.out.println("\nInformations sur le mode normal :\nDans ce mode, une carte de victoire vous est attibu�e au d�but de la manche.\nVous piochez une carte � jouer au d�but de chaque tour.\nLa manche se termine quand la pioche est vide.");
						break;
					case AVANCE: 
						System.out.println("\nInformations sur le mode avanc� :\nDans ce mode, vous avez 3 cartes en main. A vous de choisir quelle carte vous voulez poser ! A chaque d�but de tour, vous piochez une carte.\nLa manche se termine quand chaque joueur ne poss�de plus qu'une seule carte dans sa main. Cette carte sera sa carte de victoire !");
						break;
					default:
						break;
					}
				} catch (Exception e) {
					System.out.println("Saisie incorrecte : Le mode dont vous voulez voir les informations doit �tre un entier donn� au dessus.\n");
				}
			}
	    	else {
				try {
					int choix = Integer.parseInt(commande[0]);
					//	Validation de l'input
			    	if (choix < 1 || choix > nbMode) {
						throw new Exception();
					}
			    	
			    	// 	Ajout de l'input
			    	nbMode = 0;
			    	for ( ModeJeu mode : ModeJeu.values()) {
			    		nbMode++;
			    		if (nbMode == choix) {
							this.modeJeu = mode;
						}
			    	}
				} catch (Exception e) {
					Input.scanner.nextLine();		//	On vide le buffer
					System.out.println("Saisie incorrecte : Votre choix doit �tre un entier donn� au dessus.\n");
					choisi = false;
				}
			}
			
		} while (!choisi);
    	
    	
    	//	Choix du nombre de manche
    	do {
    		validInput = true;
			try {
				System.out.println("\nEt pour finir, combien de manche voulez-vous jouer ?");
				this.nbManche = Input.scanner.nextInt();
		    	
		    	if (this.nbManche <= 0) {
					throw new Exception();
				}
		    	
			} catch (Exception e) {
				System.out.println("Saisie incorrecte : Le nombre de manche doit �tre un entier sup�rieur � 0.\n");
				validInput = false;
				Input.scanner.nextLine();		//	On vide le buffer
			}
		} while (!validInput);
    	
    	
    	Input.scanner.nextLine();
    	this.resumerParametre();
	}
    
    public void resumerParametre() {
    	// 	On r�sume les param�tres
    	System.out.println("\nVoici un r�sum� des param�tres choisis :");
    	System.out.println("Nombre de joueurs humains : " + this.nbJoueurHumain);
    	System.out.println("Nombre de joueurs IA : " + this.nbJoueurIA);
    	System.out.println("Nombre de joueurs totaux : " + this.nbJoueur);
    	for (int i = 0; i < this.nbJoueur; i++) {
    		System.out.println("Le joueur n�" + (i+1) + " se nomme : " + this.nomsJoueurs[i]);
		}
    	System.out.println("Plateau choisi : " + this.formePlateau);
    	System.out.println("Mode de jeu choisi : " + this.modeJeu);
    	System.out.println("Nombre de manches : " + this.nbManche);
	}
    
//	Getter et Setter
    public int getNbJoueur() {
        return this.nbJoueur;
    }

    public void setNbJoueur(int value) {
        this.nbJoueur = value;
    }

    public int getNbJoueurHumain() {
        return this.nbJoueurHumain;
    }

    public void setNbJoueurHumain(int value) {
        this.nbJoueurHumain = value;
    }

    public void setNbJoueurIA(int value) {
        this.nbJoueurIA = value;
    }

    public int getNbJoueurIA() {
        return this.nbJoueurIA;
    }

    public void setFormePlateau(FormesPlateau value) {
        this.formePlateau = value;
    }

    public FormesPlateau getFormePlateau() {
        return this.formePlateau;
    }
    
    public void setModeJeu(ModeJeu value) {
        this.modeJeu = value;
    }

    public ModeJeu getModeJeu() {
        return this.modeJeu;
    }

	public int getNbManche() {
		return nbManche;
	}

	public void setNbManche(int nbManche) {
		this.nbManche = nbManche;
	}
	
	public String getNomJoueurs(int i) {
		return this.nomsJoueurs[i];
	}

}
