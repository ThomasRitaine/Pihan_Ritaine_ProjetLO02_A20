package jeu;

import java.util.Scanner;
import jeu.Plateau.FormesPlateau;


/**
 * Parametre est la classe représentant les paramètres d'une partie.
 * On peut la modifier au début de la partie, elle sert à créer la partie.
 * Tous les algorithmes de la partie consultent les paramètres.
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
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
    
  
//	Méthodes
    public void parametrerPartie() {
    		//	Import des librairies pour lire la console
    	Scanner scanner = new Scanner(System.in);
    	String textInput;
    	
    	//	Récupération du nombre de joueur
    	System.out.println("-   1. Paramétrons la partie !   -\n");
    	System.out.println("Entrez le nombre de joueur (Humain + IA <= 3) : ");
    	this.nbJoueur = scanner.nextInt();
    	
    	//	Récupération du nombre de joueur humain
    	System.out.println("\nParmi ces " + this.nbJoueur + " joueurs, combien sont humains ?");
    	this.nbJoueurHumain = scanner.nextInt();
    	
    	//	Calcul et confirmation du nombre de joueur IA
    	this.nbJoueurIA = this.nbJoueur - this.nbJoueurHumain;
    	System.out.println("\nIl y a donc " + this.nbJoueurIA + " joueur(s) IA.");
    	
    	//	A faire : récupération des noms des joueurs
    	scanner.nextLine();
    	for (int i = 0; i < this.nbJoueur; i++) {
			if (i < this.nbJoueurHumain) {
				System.out.println("\nQuel est le nom du joueur n°" + (i+1) + " ? (C'est un humain)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n°" + i + " ? (C'est un humain)\n");
			} else {
				System.out.println("\nQuel est le nom du joueur n°" + (i+1) + " ? (C'est un ordinateur)");
				//this.nomsJoueurs[i] = console.readLine("\nQuel est le nom du joueur n°" + i + " ? (C'est un ordinateur)\n");
			}
			this.nomsJoueurs[i] = scanner.nextLine();
			
		}
    	
    	// 	Choix de la forme du plateau
    	System.out.println("\nChoisissez un plateau parmi ceux-ci : (Entrez le numéro)");
    	int i = 0;
    	for ( FormesPlateau forme : FormesPlateau.values()) {
    		i++;
    		System.out.println(i + ") " + forme);
    	}
    	System.out.println("Choisissez un numéro.");
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
