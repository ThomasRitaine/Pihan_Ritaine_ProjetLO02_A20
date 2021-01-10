package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Ce controleur surveille les evenements sur un objet graphique de la classe Button ou TextField et en fonction de ces evenements,
 *  il declenche des methodes de l'objet associes provenant des classes du modele.
 * @author Ya�lle Pihan et Thomas Ritaine
 *
 */
public class Controleur {

	private JButton btnFinirTour;
	private JButton btnPoserCarte;
	private JButton btnBougerCarte;
	private JTextField txtMessage;
	private JTextField txtAuTourDe;
	
	public Controleur(JButton btnFinirTour,JButton btnPoserCarte,JButton btnBougerCarte,JTextField txtMessage,JTextField txtAuTourDe) {
		this.btnFinirTour=btnFinirTour;
		this.btnBougerCarte=btnBougerCarte;
		this.btnPoserCarte=btnPoserCarte;
		this.txtMessage=txtMessage;
		this.txtAuTourDe=txtAuTourDe;
		
		btnBougerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMessage.setText("Votre carte a bien �t� boug�e.");
				/*appeler la m�thode bouger carte.*/
				
			}
		});
		
		btnPoserCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMessage.setText("Votre carte a bien �t� pos�e.");
				/*appeler la m�thode poser carte.*/
			}
		});
		
		btnFinirTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAuTourDe.setText("A votre tour"+"nom joueur");
				/*r�cup�rer le nom du joueur.*/
				/*appeler la m�thode Fin tour.*/
			}
		});
	}
}
