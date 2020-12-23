package fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur.Carte.CouleursCarte;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.coeur.Carte.FormesCarte;

/**
 * Pioche est la classe d�finissant la pioche du jeu.
 * Elle constitue une liste de cartes m�lang�es al�atoirement et pouvant �tre retir�es les unes � la suite des autres.
 * @author Ya�lle Pihan & Thomas Ritaine
 * @version 1.0
 *
 */
public class Pioche {
	
//	Attribut de classe
	private static boolean cree = false;
	
//	Attributs
    private Queue<Carte> cartes;

    
//	Constructeur
    /**
     * Initialise la pioche.
     */
    private Pioche() {
    	this.cartes = new LinkedList<Carte>();
		
		//	On g�n�re toutes les cartes et on les met dans la pioche
    	boolean rempli = false;
    	for (int i = 0; i < 2; i++) {	//	On fait deux fois cette boucle. La premi�re fois, rempli == false
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
    /**
     * Permet d'obtenir une instance du singleton de Pioche.
     * @return La pioche.
     */
    public static Pioche getPioche() {
    	Pioche pioche = null;
    	if (!Pioche.cree) {
			pioche = new Pioche();
			Pioche.cree = true;
		}
    	return pioche;
    }
    
    
//	M�thodes
    /**
     * M�lange les cartes enregistr�es dans la pioche.
     */
    private void melanger() {
		Collections.shuffle((List<?>) this.cartes);
		this.cartes = (Queue<Carte>)this.cartes;
	}

    /**
     * Permet de piocher en retirant la premi�re carte de la liste pioche.
     * @return
     */
    public Carte piocher() {
    	Carte cartePiochee = null;
		if (!this.cartes.isEmpty()) {
			cartePiochee = this.cartes.poll();
		}
		return cartePiochee;
    }
    
    /**
     * Annoce si la pioche est vide ou non.
     * @return Vrai ou faux.
     */
    public boolean estVide() {
    	return this.cartes.isEmpty();
	}
    
    /**
     * R�cup�re le nombre de cartes restantes dans la pioche.
     * @return
     */
    public int nbCarteRestantes() {
		return this.cartes.size();
	}
    

//	Supprimer l'instance de Pioche
    /**
     * Supprime le singleton de Pioche.
     */
    public static void supprime() {
		Pioche.cree = false;
	}

}
