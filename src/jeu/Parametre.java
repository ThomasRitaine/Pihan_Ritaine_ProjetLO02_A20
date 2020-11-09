package jeu;

import java.io.Console;
import java.io.Reader;
import java.util.Scanner;

import jeu.Plateau.FormesPlateau;

public class Parametre {
	
//	Attributs
    private int nombreJoueurs;
    private int nombreJoueurHumain;
    private int nombreJoueurIA;
    private String[] nomsJoueurs = new String[3];
    private FormesPlateau formePlateau;
    private int nbManche;

    
//	Constructeurs
    public Parametre(int nbJoueur, int nbJoueurHumain, FormesPlateau formePlateau, int nbManche) {
    	this.nombreJoueurs = nbJoueur;
    	this.nombreJoueurHumain = nbJoueurHumain;
    	this.nombreJoueurIA = nbJoueur - nbJoueurHumain;
    	this.formePlateau = formePlateau;
    	this.nbManche = nbManche;
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
    	this.nombreJoueurs = scanner.nextInt();
    	
    	//	Récupération du nombre de joueur humain
    	System.out.println("\nParmi ces " + this.nombreJoueurs + " joueurs, combien sont humains ?");
    	this.nombreJoueurHumain = scanner.nextInt();
    	
    	//	Calcul et confirmation du nombre de joueur IA
    	this.nombreJoueurIA = this.nombreJoueurs - this.nombreJoueurHumain;
    	System.out.println("\nIl y a donc " + this.nombreJoueurIA + " joueur(s) IA.");
    	
    	//	A faire : récupération des noms des joueurs
    	scanner.nextLine();
    	for (int i = 0; i < this.nombreJoueurs; i++) {
			if (i < this.nombreJoueurHumain) {
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
    	
    	//	On résume les paramètres
    	System.out.println("\nVoici un résumé des paramètres choisis :");
    	System.out.println("Nombre de joueurs humains : " + this.nombreJoueurHumain);
    	System.out.println("Nombre de joueurs IA : " + this.nombreJoueurIA);
    	System.out.println("Nombre de joueurs totaux :" + this.nombreJoueurs);
    	for (i = 0; i < this.nombreJoueurs; i++) {
    		System.out.println("Le joueur n°" + (i+1) + " se nomme : " + this.nomsJoueurs[i]);
		}
    	System.out.println("Plateau choisi : " + this.formePlateau);
    	System.out.println("Nombre de manches : " + this.nbManche);
	}
    
//	Getter et Setter
    public int getNombreJoueurs() {
        return this.nombreJoueurs;
    }

    public void setNombreJoueurs(int value) {
        this.nombreJoueurs = value;
    }

    public int getNombreJoueurHumain() {
        return this.nombreJoueurHumain;
    }

    public void setNombreJoueurHumain(int value) {
        this.nombreJoueurHumain = value;
    }

    public void setNombreJoueurIA(int value) {
        this.nombreJoueurIA = value;
    }

    public int getNombreJoueurIA() {
        return this.nombreJoueurIA;
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
