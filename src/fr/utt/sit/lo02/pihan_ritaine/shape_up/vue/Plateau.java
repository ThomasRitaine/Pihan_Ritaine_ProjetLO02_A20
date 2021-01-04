package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Case;

public class Plateau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int TAILLE_SEPARATEUR = 2; 	//	Largeur du trait de séparation en pixel.
	public static int DECALAGE_X = 50; 			//	Décalage entre la gauche du plateau et son container
	
	private fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau;

	public Plateau(fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau) {
		this.plateau = plateau;
	}

	//	Est appelé après la création d'un objet de cette classe
	//	Permet de 
	public void paint(Graphics g) {
		
		//	On transforme le graphique en graphique 2D pour dessiner
		Graphics2D g2d = (Graphics2D) g;
		
		int xMax = this.plateau.getExtremum("xMax");
		int yMax = this.plateau.getExtremum("yMax");
		int xMin = this.plateau.getExtremum("xMin");
		int yMin = this.plateau.getExtremum("yMin");
		//	Nombre de case sur l'axe des X
		int nbCasesX = xMax - xMin +1;
		//	Nombre de case sur l'axe des X
		int nbCasesY = yMax - yMin +1;
		
		//	On fait un rectangle
		int largeurRect = (nbCasesX * Jeu.LARG_CASE) + (nbCasesX+1)*Plateau.TAILLE_SEPARATEUR;
		int longeurRect = (nbCasesY * Jeu.LONG_CASE) + (nbCasesY+1)*Plateau.TAILLE_SEPARATEUR;
		g2d.fillRect(Plateau.DECALAGE_X, 0, largeurRect, longeurRect);
		
		int x = xMin;
		int y = yMin;
		
		//	On dessine les cases dans ce rectangle et on met les cartes dans les cases
		for (int i = Plateau.TAILLE_SEPARATEUR+Plateau.DECALAGE_X; i < largeurRect; i+=(Jeu.LARG_CASE+Plateau.TAILLE_SEPARATEUR)) {
			for (int j = Plateau.TAILLE_SEPARATEUR; j < longeurRect; j+=(Jeu.LONG_CASE+Plateau.TAILLE_SEPARATEUR)) {
				
				//	On dessine un espace blanc
				g2d.clearRect(i, j, Jeu.LARG_CASE, Jeu.LONG_CASE);
				
				//	On met la carte si la case existe
				Case emplacement = this.plateau.getCase(x, y);
				if (emplacement != null) {
					if (emplacement.getCarte() != null) {
						JLabel labelCarte = new JLabel();
						String code = emplacement.getCarte().getCode();
						Util.setImageOfLabel(labelCarte, code);
						labelCarte.setLocation(i, y);
						this.add(labelCarte);
					}
				}
				
				y++;
			}
			x++;
		}
		
		//	On ajoute les coordonnées sur le côté
		
    }
	
}
