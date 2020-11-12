package jeu;

import java.util.Scanner;
import jeu.Plateau.FormesPlateau;


/**
 * Parametre est la classe repr�sentant les param�tres d'une partie.
 * On peut la modifier au d�but de la partie, elle sert � cr�er la partie.
 * Tous les algorithmes de la partie consultent les param�tres.
 * 
 * @author Ya�lle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class Parametre {
	
//	ATTRIBUTS
    private int nbJoueur;
    private int nbJoueurHumain;
    private int nbJoueurIA;
    private String[] nomsJoueurs = new String[3];
    private FormesPlateau formePlateau;
    private int nbManche;

    
//	Constructeurs
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
    
  
//	M�thodes
    public void parametrerPartie() {
    		//	Import des librairies pour lire la console
    	Scanner scanner = new Scanner(System.in);
    	String textInput;
    	
    	//	R�cup�ration du nombre de joueur
    	System.out.println("-   1. Param�trons la partie !   -\n");
    	System.out.println("Entrez le nombre de joueur (Humain + IA <= 3) : ");
    	this.nbJoueur = scanner.nextInt();
    	
    	//	R�cup�ration du nombre de joueur humain
    	System.out.println("\nParmi ces " + this.nbJoueur + " joueurs, combien sont humains ?");
    	this.nbJoueurHumain = scanner.nextInt();
    	
    	//	Calcul et confirmation du nombre de joueur IA
    	this.nbJoueurIA = this.nbJoueur - this.nbJoueurHumain;
    	System.out.println("\nIl y a donc " + this.nbJoueurIA + " joueur(s) IA.");
    	
    	//	A faire : r�cup�ration des noms des joueurs
    	scanner.nextLine();
    	for (int i = 0; i < this.nbJoueur; i++) {
			if (i < this.nbJoueurHumain) {
				System.out.println("\nQuel est le nom du joueur n�" + (i+1) + " ? (C'est un humain)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n�" + i + " ? (C'est un humain)\n");
			} else {
				System.out.println("\nQuel est le nom du joueur n�" + (i+1) + " ? (C'est un ordinateur)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n�" + i + " ? (C'est un ordinateur)\n");
			}
			this.nomsJoueurs[i] = scanner.nextLine();
			
		}
    	
    	// 	Choix de la forme du plateau
    	System.out.println("\nChoisissez un plateau parmi ceux-ci : (Entrez le num�ro)");
    	int i = 0;
    	for ( FormesPlateau forme : FormesPlateau.values()) {
    		i++;
    		System.out.println(i + ") " + forme);
    	}
    	System.out.println("Choisissez un num�ro.");
    	int plateauChoisi = scanner.nextInt();
    	i = 0;
    	for ( FormesPlateau forme : FormesPlateau.values()) {
    		i++;
    		if (i == plateauChoisi) {
				this.formePlateau = forme;
			}
    	}
    	
    	//	Choix du nombre de manche
    	System.out.println("\nEt pour finir, combien de manche voulez-vous jouer ?");
    	this.nbManche = scanner.nextInt();
    	
    	//	On ferme la lecture
    	scanner.close();
    	
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
