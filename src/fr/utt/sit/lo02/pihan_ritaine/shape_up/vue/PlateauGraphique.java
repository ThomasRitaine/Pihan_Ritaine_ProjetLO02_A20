package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Case;

/**
 * PlateauGraphique est la classe qui va générer une image du plateau pour l'afficher sur l'interface graphique de la manche.
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class PlateauGraphique extends JPanel {

	/**
     * Un attribut conseillé par Eclipse pour résudre des erreurs.
     */
	private static final long serialVersionUID = 1L;
	
	/**
     * La taille des séparateurs entre les cases du tableau.
     * C'est la largeur du trait de s�paration en pixel.
     */
	public static int TAILLE_SEPARATEUR = 2;
	
	/**
     * La taille en pixel de la marge qu'on laisse à gauche du tableau pour afficher les coordonnées Y.
     */
	public static int DECALAGE_X = 50;
	
	/**
     * La largeur des cases en pixel.
     * LARG_CASE / LONG_CASE doit valoir 0.7
     */
	public static int LARG_CASE = 105;
	
	/**
     * La longeur des cases en pixel.
     * LARG_CASE / LONG_CASE doit valoir 0.7
     */
	public static int LONG_CASE = 150;
	
	/**
     * Le plateau du modèle que cette interface doit recopier.
     * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau
     */
	private fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau;

	
	/**
     * Le constructeur de la classe.
     * 
     * @param fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau
     */
	public PlateauGraphique(fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau plateau) {
		this.plateau = plateau;
	}

	/**
     * Est appelé après la création d'un objet de cette classe.
     * Permet de dessiner le plateau.
     * 
     * @param Graphics
     */
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
		
		int largeurRect = (nbCasesX * PlateauGraphique.LARG_CASE) + (nbCasesX+1)*PlateauGraphique.TAILLE_SEPARATEUR;
		int longeurRect = (nbCasesY * PlateauGraphique.LONG_CASE) + (nbCasesY+1)*PlateauGraphique.TAILLE_SEPARATEUR;
		g2d.fillRect(PlateauGraphique.DECALAGE_X, 0, largeurRect, longeurRect);
		
		int x = xMin;
		int y = yMin;
		
		//	On dessine les cases dans ce rectangle et on met les cartes dans les cases
		for (int i = PlateauGraphique.TAILLE_SEPARATEUR+PlateauGraphique.DECALAGE_X; i < largeurRect; i+=(PlateauGraphique.LARG_CASE+PlateauGraphique.TAILLE_SEPARATEUR)) {
			
			//	On ajoute les coordonn�es x en dessous du graphique
			g.drawString(String.valueOf(x), i+PlateauGraphique.LARG_CASE/2, longeurRect+30);
			
			for (int j = longeurRect-(PlateauGraphique.LONG_CASE+PlateauGraphique.TAILLE_SEPARATEUR); j > 0; j-=(PlateauGraphique.LONG_CASE+PlateauGraphique.TAILLE_SEPARATEUR)) {
				
				//	On ajoute les coordonn�es y � gauche du graphique
				if (x == xMin) {
					g.drawString(String.valueOf(y), PlateauGraphique.DECALAGE_X/3, j+PlateauGraphique.LONG_CASE/2);
				}
				
				//	Si la case existe, on met un carr� blanc et la carte par dessus
				Case emplacement = this.plateau.getCase(x, y);
				if (emplacement != null) {
					
					//	On dessine un espace blanc
					g2d.clearRect(i, j, PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE);
					
					//	Si il y a une carte sur cette case, on affiche l'image sur la case
					if (emplacement.getCarte() != null) {
						
						String codeCarte = emplacement.getCarte().getCode();
						String pathToImageString = "/cartes/" + codeCarte + ".jpg";
						
						ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(pathToImageString)); // load the image to a imageIcon
						Image image = imageIcon.getImage(); // transform it
						g2d.drawImage(image, i, j, PlateauGraphique.LARG_CASE, PlateauGraphique.LONG_CASE, this);
						//on peut définir ici sans mvc un actionListener ou le faire avec la fonction rendreCaseCliquable mais deuxième option complexe pour récupérer X et Y.
						/*currentBt.addActionListener((ActionListener) new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//JOUEURHUMAINAQUICLETOUR.jouerAvecCommande(commande + " " + x + " " + y);
								plateau.afficher();
								
							}
						});*/
					}
				}
				y++;
			}
			x++;
			y = yMin;
		}
		
		
		
    }
	
}
