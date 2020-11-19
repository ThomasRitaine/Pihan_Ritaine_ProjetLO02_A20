package jeu;
import java.util.*;

public class Plateau {

//	Enumération
	public enum FormesPlateau {
		RECTANGLE, ROND, TRIANGLE;
	}

//	Attributs
	private ArrayList<Case> cases = new ArrayList<Case>();
	private Carte carteCachee;
	private FormesPlateau forme;

	


//	Constructeur
	public Plateau(FormesPlateau forme) {
		this.forme = forme;

		// Génération des cases du plateau et de 
		//leurs coordonnées
		if (forme == FormesPlateau.RECTANGLE) {
			// on doit générer un rectangle de 3*5	   
		      
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					this.cases.add(new Case(j,i));					
				}
			}
						
		} else if (forme == FormesPlateau.TRIANGLE) {
			// on doit générer un rectangle de 7*4 
			//pour y intégrer un triangle de 7 cases +
			// 5 + 3 + 0 ou 1
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 7; j++) {
					this.cases.add(new Case(j,i));
					}
			}
			// interdiction des cases autour du triangle
			// en laissant la case[4] libre pour une 
			//carte même si à la fin de la manche une
			// case sera vide

			cases.get(1).setInterdite();
			cases.get(2).setInterdite();
			cases.get(3).setInterdite();
			cases.get(5).setInterdite();
			cases.get(6).setInterdite();
			cases.get(7).setInterdite();
			cases.get(8).setInterdite();
			cases.get(9).setInterdite();
			cases.get(13).setInterdite();
			cases.get(14).setInterdite();
			cases.get(15).setInterdite();
			cases.get(21).setInterdite();
		}

	}

//	Méthodes
	
	//permet de savoir quand la premiere carte est posée
	public boolean isVide() {
		/*	Commentaires du Thomas :
				
				1.	Tu devrais plutôt utiliser une boucle for, puisque tu sais à l'avance que tu vas parcourir
					les 15 cases du tableau.
					
				2.	Je te conseille de nommer tes variables avec ce qu'elles font. Par exemple, remplacer le
					booléen "résultat" par "vide" rendrait le code plus compréhensible.
					
				3.	A chaque itération, tu peux checker si la case est vide. Pas besoin de sortir de la boucle.
					Tu peux faire un truc comme :
					
						for (i = 0;  i< this.cases.size(); i++) {
							if( ! this.cases.get(i).isVide()) {
								vide = false;
							}
						}
		*/
		boolean resultat =false;
		int i = 0;
		while (i<this.cases.size() && this.cases.get(i).isVide()) {
			if(i==this.cases.size()-1) {
				resultat = true;
			}
			i++;
		}
		return resultat;
	}
	
	public boolean peutPoserCarte(Case emplacement) {
		boolean resultat=false;
		if(this.isVide()) {
			resultat=true;
		}
		if (emplacement.isVide() && emplacement.isCaseAdjacente(this) /* & emplacement.isCaseDansFormePlateau() */) {
			resultat = true;
		} 
		return resultat;
	}
	
	public void setCarteCachee(Carte value) {
		this.carteCachee = value;
	}

	public Carte getCarteCachee() {
		return this.carteCachee;
	}

	public Case rechercheCase(int x, int y) {		
		Case caseCherchee = null;
		int i = 0;
		while ((this.cases.get(i).getCoordX() != x || this.cases.get(i).getCoordY() != y) && i<this.cases.size()) {//i<15
			if (i == this.cases.size()-1) {//14 = dernière case
				System.out.println("[RechercheCase]:Aucune case ne possède ces coordonnées");					
			}
			i++;
		}
		caseCherchee = this.cases.get(i);
		return caseCherchee;
	}
	/*		
	
	2)	Si on cherche la première case : this.cases[0], on ne va pas rentrer dans le while
		et ca va retourner la valeur null.
		
			=> Yaya : maintenant si ? 
			=> Thomas : A toi de trouver un moyen de le tester ;)
						(mais indique bien avec des commentaires tes blocs de test)
			
		
	3) 	Je te conseille de faire un booléen "caseTrouvee" et une case "caseCherchee"
		Comme ca, tu ne controlles que la valeur de caseTrouvee dans ton while, et tu
		fais un if à la fin de ton while où tu checkes si la case coincide 
		
			=> Yaya : Je pense que j'ai trouvé une solution alternative en rajoutant la condition sur le i, non?
			=> Thomas :	Tu vas avoir un problème si tu ne trouves pas la case. Tu vas faire get(16) et ca va faire
						une erreur.
						Test ton code et tu verras ce qui fonctionne et ne fonctionne pas.

*/
	
	
	
	
	/*	Commentaires du Thomas :
	
	1.	Il faut tester que tu puisses mettre la carte sur la case "vers". Tu peux utiliser ta fonction peutPoserCarte
		
	2.	Quand tu bouges une carte, elle ne se trouve plus sur la case d'avant. Donc avant de regarder si tu peux poser
		ta carte sur la case "vers", il faut enlever la carte de la case "depuis".
		Mais attention, si une erreur se produit, il faut remettre la carte sur la case "depuis" et mettre reussite
		sur false.
		
	3.	Tu peux aussi faire des vérifications comme "Y a-t-il une carte sur la case "depuis" ? "
	 */
	public boolean bougerCarte(Case depuis, Case vers) {
		boolean reussite = true;
		Carte carteBougee = depuis.getCarte();
		depuis.setCarte(null);
		vers.setCarte(carteBougee);
		return reussite;
	}
	
	
	/*	Commentaires du Thomas :
	
	1.	Wouaaah ! C'est vraiment bien fait, bravo !
		En plus l'algorithme est bien organisé !
		
	2.	Est-ce que tu pourrais rajouter une rangée avec X, et une avec les Y ?
		Je vois bien un truc comme ca, tu en penses quoi ?
		
		\ X		1		2		3		4		5
		Y		
		1		*		TKT		MDR		*		*
		2		*		*		LOL		TGV		*
		3				*		*				*
		4		*		*		*				*
	
	3.	Il faudra aussi faire l'affichage pour les autres plateaux.
		
	 */
	public void afficher() {		
		StringBuffer sb =new StringBuffer();
		int i=0;
		sb.append("Voici le plateau :\n");
		while(i<this.cases.size()) {
			if(i%5==0) {
				sb.append("\n");
			}
			sb.append('\t');
			if(!this.cases.get(i).isVide()) {
				sb.append(this.cases.get(i).getCarte().getCode() );	
			}else {
				sb.append(" *");
			}
			
			i++;
		}
		System.out.println(sb);
	}

}


