package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.JouerHumain;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Manche;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Partie;

/**
 * Ce controleur gère le clique sur le bouton pour poser une carte sur InterfaceGraphiqueManche.
 * Il declenche des methodes de l'objet associé provenant des classes du modele.
 * 
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnPoserCarte
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class ControleurManchePoserCarte {
	
	/**
	 * Le constructeur de ce controleur gère le rôle du controleur.
	 * 
	 * @param JLabel
	 * @param JButton
	 * @param JTextField
	 * @param JTextField
	 * @param JTextField
	 * 
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#txtMessage
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#btnPoserCarte
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#mode
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#idCarteInput
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordXPoserInput
	 * @see fr.utt.sit.lo02.pihan_ritaine.shape_up.vue.InterfaceGraphiqueManche#coordYPoserInput
	 */
	public ControleurManchePoserCarte(JLabel txtMessage, JButton btnPoserCarte, ModeJeu mode, JTextField idCarteInput, JTextField coordXPoserInput, JTextField coordYPoserInput) {
		
		btnPoserCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int coordX = Integer.parseInt(coordXPoserInput.getText());
					int coordY = Integer.parseInt(coordYPoserInput.getText());
					int idCarte = 1;
					if(mode == ModeJeu.AVANCE) {
						idCarte = Integer.parseInt(idCarteInput.getText());
					}
					
					Partie partieEnCours = Partie.getPartie();
					Manche mancheEnCoursManche = partieEnCours.getMancheActuelle();
					
					if(!mancheEnCoursManche.getJoueurQuiJoueTour().getTypeJouer().poserCarte(mancheEnCoursManche.getPlateau(), coordX, coordY, idCarte)) {
						throw new Exception();
					}
					
					JouerHumain actionHumain = (JouerHumain)Partie.getPartie().getMancheActuelle().getJoueurQuiJoueTour().getTypeJouer();
					actionHumain.setAPoseCarte(true);
					txtMessage.setText("Votre carte a bien été posée.");
				} catch (Exception e2) {
					txtMessage.setText("Il y a eu une erreur.");
				}
			}
		});
		
	}
}
