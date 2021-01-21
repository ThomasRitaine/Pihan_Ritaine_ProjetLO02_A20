package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.JouerHumain;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;

/**
 * Ce controleur gère le clique sur le bouton pour bouger une carte sur InterfaceGraphiqueManche.
 * Il declenche des methodes de l'objet associé provenant des classes du modele.
 * 
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnBougerCarte
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class ControleurMancheBougerCarte {
	
	/**
	 * Le constructeur de ce controleur gère le rôle du controleur.
	 * 
	 * @param JLabel
	 * @param JButton
	 * @param JTextField
	 * @param JTextField
	 * @param JTextField
	 * @param JTextField
	 * 
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#txtMessage
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnBougerCarte
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordXDepuisBougerInput
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordYDepuisBougerInput
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordXVersBougerInput
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordYVersBougerInput
	 */
	public ControleurMancheBougerCarte(JLabel txtMessage, JButton btnBougerCarte, JTextField coordXDepuisBougerInput, JTextField coordYDepuisBougerInput, JTextField coordXVersBougerInput, JTextField coordYVersBougerInput) {
		
		btnBougerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int xDepuis = Integer.parseInt(coordXDepuisBougerInput.getText());
					int yDepuis = Integer.parseInt(coordYDepuisBougerInput.getText());
					int xVers = Integer.parseInt(coordXVersBougerInput.getText());
					int yVers = Integer.parseInt(coordYVersBougerInput.getText());
					
					if(!Partie.getPartie().getMancheActuelle().getPlateau().bougerCarte(xDepuis, yDepuis, xVers, yVers)) {
						throw new Exception();
					}
					
					JouerHumain actionHumain = (JouerHumain)Partie.getPartie().getMancheActuelle().getJoueurQuiJoueTour().getTypeJouer();
					actionHumain.setABouge(true);
					txtMessage.setText("La carte a bien été bougée.");
				} catch (Exception e2) {
					txtMessage.setText("Il y a eu une erreur.");
				}
				
			}
		});
		
	}
}
