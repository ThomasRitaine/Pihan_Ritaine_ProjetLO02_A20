package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.JouerHumain;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;

/**
 * Ce controleur surveille les evenements sur un objet graphique de la classe Button ou TextField et en fonction de ces evenements,
 *  il declenche des methodes de l'objet associes provenant des classes du modele.
 * @author Yaëlle Pihan et Thomas Ritaine
 *
 */
public class ControleurMancheBougerCarte {
	
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
