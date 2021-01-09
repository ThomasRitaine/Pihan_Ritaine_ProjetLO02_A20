package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Case;

public class Plateau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int TAILLE_SEPARATEUR = 2; 	//	Largeur du trait de s�paration en pixel.
	public static int DECALAGE_X = 50; 			//	D�calage entre la gauche du plateau et son container
	
	//	LARG_CASE / LONG_CASE doit �tre 0.7
	public static int LARG_CASE = 105; 	//	Largeur des cases en pixel
	public static int LONG_CASE = 150; 	//	Longueur des cases en pixel
	
	private fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau;

	public Plateau(fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau) {
		this.plateau = plateau;
	}

	//	Est appel� apr�s la cr�ation d'un objet de cette classe
	//	Permet de dessiner le plateau
	public void paint(Graphics g) {
		
		//	On transforme le graphique en graphique 2D pour dessiner
		Graphics2D g2d = (Graphics2D) g;
		
		//	On met une police d'�criture pour afficher du texte
		Font font = g2d.getFont().deriveFont( 25.0f );
	    g2d.setFont( font );
		
		int xMax = this.plateau.getExtremum("xMax");
		int yMax = this.plateau.getExtremum("yMax");
		int xMin = this.plateau.getExtremum("xMin");
		int yMin = this.plateau.getExtremum("yMin");
		
		//	Nombre de case sur l'axe des X
		int nbCasesX = xMax - xMin +1;
		//	Nombre de case sur l'axe des X
		int nbCasesY = yMax - yMin +1;
		
		//	On fait un rectangle
		int largeurRect = (nbCasesX * Plateau.LARG_CASE) + (nbCasesX+1)*Plateau.TAILLE_SEPARATEUR;
		int longeurRect = (nbCasesY * Plateau.LONG_CASE) + (nbCasesY+1)*Plateau.TAILLE_SEPARATEUR;
		g2d.fillRect(Plateau.DECALAGE_X, 0, largeurRect, longeurRect);
		
		int x = xMin;
		int y = yMin;
		
		//	On dessine les cases dans ce rectangle et on met les cartes dans les cases
		for (int i = Plateau.TAILLE_SEPARATEUR+Plateau.DECALAGE_X; i < largeurRect; i+=(Plateau.LARG_CASE+Plateau.TAILLE_SEPARATEUR)) {
			
			//	On ajoute les coordonn�es x en dessous du graphique
			g.drawString(String.valueOf(x), i+Plateau.LARG_CASE/2, longeurRect+30);
			
			for (int j = longeurRect-(Plateau.LONG_CASE+Plateau.TAILLE_SEPARATEUR); j > 0; j-=(Plateau.LONG_CASE+Plateau.TAILLE_SEPARATEUR)) {
				
				//	On ajoute les coordonn�es y � gauche du graphique
				if (x == xMin) {
					g.drawString(String.valueOf(y), Plateau.DECALAGE_X/3, j+Plateau.LONG_CASE/2);
				}
				
				//	Si la case existe, on met un carr� blanc et la carte par dessus
				Case emplacement = this.plateau.getCase(x, y);
				if (emplacement != null) {
					
					//	On dessine un espace blanc
					g2d.clearRect(i, j, Plateau.LARG_CASE, Plateau.LONG_CASE);
					
					//	Si il y a une carte sur cette case, on affiche l'image sur la case
					if (emplacement.getCarte() != null) {
						
						String codeCarte = emplacement.getCarte().getCode();
						String pathToImageString = "/cartes/" + codeCarte + ".jpg";
						
						ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(pathToImageString)); // load the image to a imageIcon
						Image image = imageIcon.getImage(); // transform it
						//g2d.drawImage(newimg, i, j, null);
						g2d.drawImage(image, i, j, Plateau.LARG_CASE, Plateau.LONG_CASE, this);
					}
				}
				y++;
			}
			x++;
			y = yMin;
		}
		
		
		
    }
	
}
