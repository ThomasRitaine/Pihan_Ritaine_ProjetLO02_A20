package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.JouerHumain;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;

/**
 * Ce controleur gère le clique sur le bouton de fin de tour de InterfaceGraphiqueManche.
 * Il declenche des methodes de l'objet associé provenant des classes du modele.
 * 
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnFinirTour
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class ControleurMancheFinTour {
	
	/**
	 * Le constructeur de ce controleur gère le rôle du controleur.
	 * 
	 * @param JLabel
	 * @param JButton
	 * 
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#txtMessage
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnFinirTour
	 */
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
