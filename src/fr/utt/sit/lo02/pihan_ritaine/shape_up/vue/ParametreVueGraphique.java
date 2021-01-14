package fr.utt.sit.lo02.pihan_ritaine.shape_up.vue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Plateau.FormesPlateau;
import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre;

import fr.utt.sit.lo02.pihan_ritaine.shape_up.modele.Parametre.ModeJeu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Color;

public class ParametreVueGraphique {
// noms des composants
	private JFrame frame;
	private JSpinner spinnerNombreJoueursIA;
	private JSpinner spinnerNombreJoueurHumain;
	private JSpinner spinnerNombreManche;
	private JComboBox<FormesPlateau> formesPlateauComboBox;
	private JComboBox<ModeJeu> modeJeuComboBox;
	private JButton btnJouer;

// instance pour parametrer la partie par le biais de cette interface
	private Parametre parametre;

//pour générer des boutons dynamiquement
	private ArrayList<JTextField> tabTextFieldHumain = new ArrayList<JTextField>();
	private ArrayList<JTextField> tabTextFieldIA = new ArrayList<JTextField>();

	/**
	 * Create the application.
	 */
	public ParametreVueGraphique(Parametre parametre) {
		this.parametre = parametre;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 880, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 864, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBienvenu = new JLabel("Bienvenu dans ShapeUp! ");
		lblBienvenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblBienvenu);

		JLabel lblNbJoueurs = new JLabel("Nombre de joueurs humains ?");
		lblNbJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNbJoueurs.setBounds(117, 78, 271, 30);
		frame.getContentPane().add(lblNbJoueurs);

		spinnerNombreJoueursIA = new JSpinner();
		spinnerNombreJoueursIA.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		spinnerNombreJoueursIA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinnerNombreJoueursIA.setBounds(398, 119, 43, 30);
		frame.getContentPane().add(spinnerNombreJoueursIA);

		JLabel lblNombreDeJoueurs = new JLabel("Nombre de joueurs IA ?");
		lblNombreDeJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreDeJoueurs.setBounds(117, 119, 231, 30);
		frame.getContentPane().add(lblNombreDeJoueurs);

		spinnerNombreJoueurHumain = new JSpinner();
		spinnerNombreJoueurHumain.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		spinnerNombreJoueurHumain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinnerNombreJoueurHumain.setBounds(398, 78, 43, 30);
		frame.getContentPane().add(spinnerNombreJoueurHumain);

		JLabel lblNombreDeManches = new JLabel("Nombre de manches ?");
		lblNombreDeManches.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreDeManches.setBounds(117, 257, 231, 30);
		frame.getContentPane().add(lblNombreDeManches);

		spinnerNombreManche = new JSpinner();
		spinnerNombreManche.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		spinnerNombreManche.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinnerNombreManche.setBounds(398, 252, 43, 30);
		frame.getContentPane().add(spinnerNombreManche);

		JLabel lblMo = new JLabel("Mode de jeu ?");
		lblMo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMo.setBounds(117, 298, 231, 30);
		frame.getContentPane().add(lblMo);

		JLabel lblFormeDuPlateau = new JLabel("Forme du plateau ?");
		lblFormeDuPlateau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFormeDuPlateau.setBounds(117, 339, 231, 30);
		frame.getContentPane().add(lblFormeDuPlateau);

		formesPlateauComboBox = new JComboBox<FormesPlateau>();
		formesPlateauComboBox.setModel(new DefaultComboBoxModel(FormesPlateau.values()));
		formesPlateauComboBox.setBounds(398, 339, 192, 30);
		frame.getContentPane().add(formesPlateauComboBox);

		modeJeuComboBox = new JComboBox<ModeJeu>();
		modeJeuComboBox.setModel(new DefaultComboBoxModel(ModeJeu.values()));
		modeJeuComboBox.setBounds(398, 293, 192, 30);
		frame.getContentPane().add(modeJeuComboBox);

		//premier bouton pour indiquer les noms des joueurs
		JButton btnRenseignerNoms = new JButton("Renseigner les noms");
		btnRenseignerNoms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verifie qu'il y a bien au moins 1 joueur
						if ((int) spinnerNombreJoueurHumain.getValue() + (int) spinnerNombreJoueursIA.getValue() > 0) {
							JPanel panelNoms = new JPanel();
							panelNoms.setBounds(691, 52, 138, 424);
							frame.getContentPane().add(panelNoms);
							panelNoms.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
							//affichage pour humains
							if ((int) spinnerNombreJoueurHumain.getValue() > 0) {
								JLabel lblNomsHumains = new JLabel("Noms joueurs humains");
								panelNoms.add(lblNomsHumains);
								for (int i = 0; i < (int) spinnerNombreJoueurHumain.getValue(); i++) {
									JTextField jtf = new JTextField();
									jtf.setText("joueur " + (i + 1));
									panelNoms.add(jtf);
									tabTextFieldHumain.add(jtf);
								}
							}
							//affichage pour ia
							if ((int) spinnerNombreJoueursIA.getValue() > 0) {
								JLabel lblNomsIA = new JLabel("Noms joueurs IA");
								panelNoms.add(lblNomsIA);
								for (int i = 0; i < (int) spinnerNombreJoueursIA.getValue(); i++) {
									JTextField jtf = new JTextField();
									jtf.setText("joueur " + (i + 1));
									panelNoms.add(jtf);
									tabTextFieldIA.add(jtf);
								}
							}
						}
					//rend visible le nouveau panelNoms
				frame.setVisible(true);
			}
		});
		btnRenseignerNoms.setBounds(306, 185, 172, 23);
		frame.getContentPane().add(btnRenseignerNoms);

//2nd bouton pour initialiser les parametres
		btnJouer = new JButton("Jouer");
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Initialisation des membres de parametre */
				// Nb joueurs
				parametre.setNbJoueur(
						(int) spinnerNombreJoueursIA.getValue() + (int) spinnerNombreJoueurHumain.getValue());
				parametre.setNbJoueurHumain((int) spinnerNombreJoueurHumain.getValue());
				parametre.setNbJoueurIA((int) spinnerNombreJoueursIA.getValue());
				//noms joueurs
				for (int i = 0; i < parametre.getNbJoueurHumain(); i++) {
					String texte = tabTextFieldHumain.get(i).getText();
					parametre.setNomJoueurs(i,texte);
				}
				for (int i = 0; i < parametre.getNbJoueurIA(); i++) {
					String texte = tabTextFieldIA.get(i).getText();
					parametre.setNomJoueurs(i + parametre.getNbJoueurHumain(),texte);
				}
				//nb manches
				parametre.setNbManche((int) spinnerNombreManche.getValue());
				//forme plateau
				parametre.setFormePlateau((FormesPlateau) formesPlateauComboBox.getSelectedItem());
				//mode de jeu
				parametre.setModeJeu((ModeJeu) modeJeuComboBox.getSelectedItem());

				/* Pour court circuiter la fonction parametrerPartie() de parametre. */
				parametre.setPretAJouer(true); 
			}
		});
		btnJouer.setBounds(352, 398, 89, 23);
		frame.getContentPane().add(btnJouer);

	}

	public JFrame getFrame() {
		return frame;
	}
}
