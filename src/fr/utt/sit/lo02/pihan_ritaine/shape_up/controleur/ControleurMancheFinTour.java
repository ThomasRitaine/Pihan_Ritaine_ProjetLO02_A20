package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.JouerHumain;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;

/**
 * Ce controleur surveille les evenements sur un objet graphique de la classe Button ou TextField et en fonction de ces evenements,
 *  il declenche des methodes de l'objet associes provenant des classes du modele.
 * @author Yaëlle Pihan et Thomas Ritaine
 *
 */
public class ControleurMancheFinTour {
	
	public ControleurMancheFinTour(JLabel txtMessage, JButton btnFinirTour) {
		
		btnFinirTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JouerHumain actionHumain = (JouerHumain)Partie.getPartie().getMancheActuelle().getJoueurQuiJoueTour().getTypeJouer();
					if(!actionHumain.getAPoseCarte()) {
						throw new Exception();
					}
					txtMessage.setText("Vous avez passé votre tour.");
				} catch (Exception e2) {
					txtMessage.setText("Vous ne pouvez pas passer votre tour.");
				}
			}
		});
		
	}
}
