package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;

/**
 *  AsciiArt est la classe permettant d'ajouter d'imprimer des dessins.
 *  Chaque fonction contient un "dessin" soit une chaîne de caractère qui est imprimé à l'écran.
 * 
 * @author Yaëlle Pihan & Thomas Ritaine
 * @version 1.0
 */
public class AsciiArt {

	public static void welcome() {
		System.out.println(" ___                                                     _                    _                               ___   _                         _   _         _ \r\n"
				+ "(  _ \\ _                                                ( )                  (_ )             _              (  _ \\( )                       ( ) ( )       ( )\r\n"
				+ "| (_) )_)  __   ___  _   _   __   ___  _   _   __      _| |  _ _  ___   ___   | |   __       (_)  __  _   _  | (_(_) |__    _ _ _ _     __   | | | |_ _    | |\r\n"
				+ "|  _ (| |/ __ \\  _  \\ ) ( )/ __ \\  _  \\ ) ( )/ __ \\  / _  |/ _  )  _  \\  __)  | | / __ \\     | |/ __ \\ ) ( )  \\__ \\|  _  \\/ _  )  _ \\ / __ \\ | | | |  _ \\  | |\r\n"
				+ "| (_) ) |  ___/ ( ) | \\_/ |  ___/ ( ) | (_) |  ___/ ( (_| | (_| | ( ) |__  \\  | |(  ___/     | |  ___/ (_) | ( )_) | | | | (_| | (_) )  ___/ | (_) | (_) ) (_)\r\n"
				+ "(____/(_)\\____)_) (_)\\___/ \\____)_) (_)\\___/ \\____)  \\__ _)\\__ _)_) (_)____/ (___)\\____)  _  | |\\____)\\___/   \\____)_) (_)\\__ _)  __/ \\____) (_____)  __/     \r\n"
				+ "                                                                                         ( )_| |                               | |                 | |     (_)\r\n"
				+ "                                                                                          \\___/                                (_)                 (_)        \r\n"
				+ "");
	}
	
	public static void thanks() {
		System.out.println("                         ______                     \r\n"
				+ " _________        .---\"\"\"      \"\"\"---.              \r\n"
				+ ":______.-':      :  .--------------.  :             \r\n"
				+ "| ______  |      | :                : |             \r\n"
				+ "|:______B:|      | |  Merci d'avoir | |             \r\n"
				+ "|:______B:|      | |     joué !     | |             \r\n"
				+ "|:______B:|      | |                | |             \r\n"
				+ "|         |      | | A bientôt pour | |             \r\n"
				+ "|:_____:  |      | |    de vrais    | |             \r\n"
				+ "|    ==   |      | :   graphismes   : |             \r\n"
				+ "|       O |      :  '--------------'  :             \r\n"
				+ "|       o |      :'---...______...---'              \r\n"
				+ "|       o |-._.-i___/'             \\._              \r\n"
				+ "|'-.____o_|   '-.   '-...______...-'  `-._          \r\n"
				+ ":_________:      `.____________________   `-.___.-. \r\n"
				+ "                 .'.eeeeeeeeeeeeeeeeee.'.      :___:\r\n"
				+ "               .'.eeeeeeeeeeeeeeeeeeeeee.'.         \r\n"
				+ "              :____________________________:");
	}
	
	public static void bigDivider() {
		System.out.println("     .-.     .-.     .-.     .-.     .-.     .-.     .-.\r\n"
				+ "`._.'   `._.'   `._.'   `._.'   `._.'   `._.'   `._.'   `._.'");
	}
	
	public static void littleDivider() {
		System.out.println("<+><+><+><+><+><+><+><+><+><+><+><+><+><+><+><+><+><+><+>");
	}
	
	public static void score() {
		System.out.println(
				  "\t\t(¯`·¯`·.¸¸.·´¯`·.¸¸.·´¯`·´¯)\r\n"
				+ "\t\t( \\                      / )\r\n"
				+ "\t\t ( ) Tableau des scores ( ) \r\n"
				+ "\t\t  (/                    \\)  \r\n"
				+ "\t\t   (.·´¯`·.¸¸.·´¯`·.¸¸.·)   ");
	}
	
	public static void medal() {
		System.out.println("\t     _______________\r\n"
				+ "\t    |@@@@|     |####|\r\n"
				+ "\t    |@@@@|     |####|\r\n"
				+ "\t    |@@@@|     |####|\r\n"
				+ "\t    \\@@@@|     |####/\r\n"
				+ "\t     \\@@@|     |###/\r\n"
				+ "\t      `@@|_____|##'\r\n"
				+ "\t           (O)\r\n"
				+ "\t        .-'''''-.\r\n"
				+ "\t      .'  * * *  `.\r\n"
				+ "\t     :  *       *  :\r\n"
				+ "\t    : ~ Shape Up! ~ :\r\n"
				+ "\t    : ~ A W A R D ~ :\r\n"
				+ "\t     :  *       *  :\r\n"
				+ "\t      `.  * * *  .'\r\n"
				+ "\t        `-.....-'");
	}
}
