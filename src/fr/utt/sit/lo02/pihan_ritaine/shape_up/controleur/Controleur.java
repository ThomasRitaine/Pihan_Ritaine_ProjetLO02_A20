package fr.utt.sit.lo02.pihan_ritaine.shape_up.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Ce controleur surveille les evenements sur un objet graphique de la classe Button ou TextField et en fonction de ces evenements,
 *  il declenche des methodes de l'objet associes provenant des classes du modele.
 * @author Yaëlle Pihan et Thomas Ritaine
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
				txtMessage.setText("Votre carte a bien été bougée.");
				/*appeler la méthode bouger carte.*/
				
			}
		});
		
		btnPoserCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMessage.setText("Votre carte a bien été posée.");
				/*appeler la méthode poser carte.*/
			}
		});
		
		btnFinirTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAuTourDe.setText("A votre tour"+"nom joueur");
				/*récupérer le nom du joueur.*/
				/*appeler la méthode Fin tour.*/
			}
		});
	}
}
