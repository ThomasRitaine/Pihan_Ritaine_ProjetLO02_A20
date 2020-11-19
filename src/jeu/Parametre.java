package jeu;

import jeu.Plateau.FormesPlateau;


/**
 * Parametre est la classe représentant les paramètres d'une partie.
 * On peut la modifier au début de la partie, elle sert à créer la partie.
 * Un grand nombre des algorithmes de la partie consultent les paramètres.
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class Parametre {
	
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
     * C'est un tableau de 2 à 3 String.
     * 
     * @see Parametre#getNomJoueurs(int)
     */
    private String[] nomsJoueurs = new String[3];
    
    /**
     * La forme du plateau qui sera généré pour chaque manche de la partie.
     * Sa valeur est un élément de l'énumération FormesPlateau.
     * 
     * @see Plateau#FormesPlateau
     * @see Parametre#setFormePlateau(FormesPlateau)
     * @see Parametre#getFormePlateau()
     */
    private FormesPlateau formePlateau;
    
    /**
     * Le nombre de manche qu'il faut jouer avant de calculer les points totaux et mettre fin à la partie.
     * 
     * @see Parametre#setNbManche(int)
     * @see Parametre#getNbManche()
     */
    private int nbManche;

    
//	CONSTRUCTEURS
    
    /**
     * Constructeur Parametre.
     * A la construction d'un objet Parametre, il est possible de passer tous les paramètres qui vont le composer.
     * 
     * @param nbJoueur
     *            Le nombre de joueurs en tout. Ce nombre est entre 2 et 3 inclus.
     * @param nbJoueurHumain
     *            Parmi les 2 ou 3 joueurs, nbJoueurHumain représente le nombre d'humains.
     * @param noms
     *            Un tableau de String qui contient le noms des 2 ou 3 joueurs.
     * @param formePlateau
     *            Un élément de l'énumération FormesPlateau qui correspond à la forme qu'aurons les
     *            plateaux générés à chaque manche de la partie.
     * @param nbManche
     *            Le nombre de manches qui composent la partie. C'est un entier supérieur ou égal à 1.
     * 
     * @see Plateau#FormesPlateau
     * @see Parametre#nbJoueur
     * @see Parametre#nbJoueurHumain
     * @see Parametre#formePlateau
     * @see Parametre#nbManche
     */
    public Parametre(int nbJoueur, int nbJoueurHumain, String[] noms, FormesPlateau formePlateau, int nbManche) {
    	this.nbJoueur = nbJoueur;
    	this.nbJoueurHumain = nbJoueurHumain;
    	this.nbJoueurIA = nbJoueur - nbJoueurHumain;
    	for (int i = 0; i < this.nbJoueur; i++) {
			this.nomsJoueurs[i] = noms[i];
		}
    	this.formePlateau = formePlateau;
    	this.nbManche = nbManche;
    	
    	this.resumerParametre();
    }
    
    public Parametre() {
    }
    
  
//	Méthodes
    public void parametrerPartie() {
    		//	Import des librairies pour lire la console
    	//Scanner scanner = new Scanner(System.in);
    	
    	//	Récupération du nombre de joueur
    	System.out.println("-   1. Paramétrons la partie !   -\n");
    	System.out.println("Entrez le nombre de joueur, Humain et IA compris. Entre 2 et 3.");
    	this.nbJoueur = Input.scanner.nextInt();
    	
    	//	Récupération du nombre de joueur humain
    	System.out.println("\nParmi ces " + this.nbJoueur + " joueurs, combien sont humains ?");
    	this.nbJoueurHumain = Input.scanner.nextInt();
    	
    	//	Calcul et confirmation du nombre de joueur IA
    	this.nbJoueurIA = this.nbJoueur - this.nbJoueurHumain;
    	System.out.println("\nIl y a donc " + this.nbJoueurIA + " joueur(s) IA.");
    	
    	//	Récupération des noms des joueurs
    	Input.scanner.nextLine();
    	for (int i = 0; i < this.nbJoueur; i++) {
			if (i < this.nbJoueurHumain) {
				System.out.println("\nQuel est le nom du joueur n°" + (i+1) + " ? (C'est un humain)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n°" + i + " ? (C'est un humain)\n");
			} else {
				System.out.println("\nQuel est le nom du joueur n°" + (i+1) + " ? (C'est un ordinateur)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n°" + i + " ? (C'est un ordinateur)\n");
			}
			this.nomsJoueurs[i] = Input.scanner.nextLine();
			
		}
    	
    	// 	Choix de la forme du plateau
    	System.out.println("\nChoisissez un plateau parmi ceux-ci : (Entrez le numéro)");
    	int i = 0;
    	for ( FormesPlateau forme : FormesPlateau.values()) {
    		i++;
    		System.out.println("\t" + i + ") " + forme);
    	}
    	System.out.println("Choisissez un numéro.");
    	int plateauChoisi = Input.scanner.nextInt();
    	i = 0;
    	for ( FormesPlateau forme : FormesPlateau.values()) {
    		i++;
    		if (i == plateauChoisi) {
				this.formePlateau = forme;
			}
    	}
    	
    	//	Choix du nombre de manche
    	System.out.println("\nEt pour finir, combien de manche voulez-vous jouer ?");
    	this.nbManche = Input.scanner.nextInt();
    	
    	Input.scanner.nextLine();
    	//this.resumerParametre();
	}
    
    public void resumerParametre() {
    	// 	On résume les paramètres
    	System.out.println("\nVoici un résumé des paramètres choisis :");
    	System.out.println("Nombre de joueurs humains : " + this.nbJoueurHumain);
    	System.out.println("Nombre de joueurs IA : " + this.nbJoueurIA);
    	System.out.println("Nombre de joueurs totaux : " + this.nbJoueur);
    	for (int i = 0; i < this.nbJoueur; i++) {
    		System.out.println("Le joueur n°" + (i+1) + " se nomme : " + this.nomsJoueurs[i]);
		}
    	System.out.println("Plateau choisi : " + this.formePlateau);
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
