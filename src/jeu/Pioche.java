package jeu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jeu.Carte.Couleurs;
import jeu.Carte.Formes;


public class Pioche {
	
//	Attribut de classe
	private static boolean cree = false;
	
//	Attributs
    	//private Carte[] cartes = new Carte[18];
    private Queue<Carte> cartes;

    
//	Constructeur
    private Pioche() {
    	this.cartes = new LinkedList<Carte>();
		
		//	On génère toutes les cartes et on le met dans la pioche
    	boolean rempli = false;
    	for (int i = 0; i < 2; i++) {	//	On fait deux fois cette boucle. La première fois, rempli == flase
			if (i==1) rempli = true;	//	Au deuxième passage de la boucle, rempli == true
			
			for ( Formes forme : Formes.values()) {
				for ( Couleurs couleur : Couleurs.values()) {
					this.cartes.add(new Carte(rempli, forme, couleur));
				}
			}
		}
		
    	//	Les cartes sont dans l'odre après la génération, on les mélange.
		this.melanger();
    }


//	Obtenir une instance du singleton
    public Pioche getPioche() {
    	Pioche pioche = null;
    	if (!Pioche.cree) {
			pioche = new Pioche();
			Pioche.cree = true;
		}
    	return pioche;
    }
    
    
//	Méthodes
    private void melanger() {
		Collections.shuffle((List<?>) this.cartes);
		this.cartes = (Queue<Carte>)this.cartes;
	}

    public Carte piocher() {
    	Carte cartePiochee = null;
		if (!this.cartes.isEmpty()) {
			cartePiochee = this.cartes.poll();
		}
		return cartePiochee;
    }

}
