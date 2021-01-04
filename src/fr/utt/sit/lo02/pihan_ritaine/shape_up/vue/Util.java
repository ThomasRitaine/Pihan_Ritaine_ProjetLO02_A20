package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Util {
	
	//	Cette méthode permet de mettre une image sur un label.
	//	L'image est automatiquement mise à l'échelle.
	public static void setImageOfLabel(JLabel emplacement, String codeCarte) {
		
		//	On crée le chemin vers l'image
		String pathToImageString = "/images/cartes/" + codeCarte + ".jpg";
		
		System.out.println(Jeu.class.getResource(pathToImageString));
		//	Code pris ici : https://stackoverflow.com/a/18335435/14300530
		ImageIcon imageIcon = new ImageIcon(Util.class.getResource(pathToImageString)); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(Jeu.LARG_CASE, Jeu.LONG_CASE, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		emplacement.setIcon(imageIcon);
	}
	
}
