package jeu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jeu.Carte.CouleursCarte;
import jeu.Carte.FormesCarte;


public class Pioche {
	
//	Attribut de classe
	private static boolean cree = false;
	
//	Attributs
    private Queue<Carte> cartes;

    
//	Constructeur
    private Pioche() {
    	this.cartes = new LinkedList<Carte>();
		
		//	On g�n�re toutes les cartes et on le met dans la pioche
    	boolean rempli = false;
    	for (int i = 0; i < 2; i++) {	//	On fait deux fois cette boucle. La premi�re fois, rempli == flase
			if (i==1) rempli = true;	//	Au deuxi�me passage de la boucle, rempli == true
			
			for ( FormesCarte forme : FormesCarte.values()) {
				for ( CouleursCarte couleur : CouleursCarte.values()) {
					this.cartes.add(new Carte(rempli, forme, couleur));
				}
			}
		}
		
    	//	Les cartes sont dans l'odre apr�s la g�n�ration, on les m�lange.
		this.melanger();
    }


//	Obtenir une instance du singleton
    public static Pioche getPioche() {
    	Pioche pioche = null;
    	if (!Pioche.cree) {
			pioche = new Pioche();
			Pioche.cree = true;
		}
    	return pioche;
    }
    
    
//	M�thodes
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
    

//	Supprimer l'instance de Pioche
    public static void supprime() {
		Pioche.cree = false;
	}

}
