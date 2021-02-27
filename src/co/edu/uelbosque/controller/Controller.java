package co.edu.uelbosque.controller;

import java.io.IOException;
import java.util.Scanner;

import co.edu.uelbosque.model.persistence.ManagerDAO;

/**
 * 
 * @author luisestebancardenascortes 
 * in this class you will join all the logic of the program
 *
 */

public class Controller {

	ManagerDAO mg = new ManagerDAO("pets-citizens.csv");
	Scanner sc = new Scanner(System.in);
	boolean key = false;
	boolean key2 = false;
	boolean key3 = false;
	boolean key4 = false;
	boolean key5 = false;
	int quantity = 0;
	String neighborhood = "";
	String top_last = "";
	String species = "";
	String sex = "";
	String size = "";
	boolean dangerous = false;

	/**
	 * Simple Constructor Method 
	 */
	
	public Controller() throws IOException {
		startApplication();
		assignID(key);
		options(key2);

	}

	/**
	 * method to start the application<b>pre</b>CSV file path must be specified<br>
	 * <b>post</b>reading CSV file<br>
	 * 
	 * @return success or error message
	 */
	
	public void startApplication() throws IOException {

		String startData = "";

		System.out.println(Style(33, " DO YOU WANT TO UPLOAD THE DATA?  \n     - YES            -NO "));
		startData = sc.nextLine().toUpperCase();

		switch (startData) {
		case "YES": {
			mg.uploadData();
			System.out.println(Style(33, "    UPLOAD DATA SUCCESSFULY :D "));

			key = true;
			break;

		}
		case "NO": {
			System.out.println(Style(33,
					"  IF YOU NOT UPLOAD THE DATA \\n  YOU CANNOT RUN THE PROGRAM :( \\n\\n        plis try again!"));

			break;
		}
		default: {
			System.out.println(Style(33, " Put only yes or no, plis!  :D "));

		}

		}
	}

	/**
	 * method used to generate the IDs <b>pre</b>permission key to generate the IDs<br>
	 * <b>post</b>IDs are generated with the requested characteristics<br>
	 * 
	 * @return success or error message
	 */
	
	public void assignID(boolean key) {
		if (key == true) {
			System.out.println(Style(33, "   Do you want to assign an ID? \n     - YES            -NO "));

			String option = sc.nextLine().toUpperCase();

			switch (option) {
			case "YES": {
				System.out.println("           assigning...");
				System.out.println(mg.assignID());
				break;
			}
			case "NO": {
				System.out.println(Style(47,
						"                  OKAY \n remember that if you don't assign an ID now, \n            you can't go back :D \n         do you want to add an id? \n               -YES    -NOT "));

				option = sc.nextLine().toUpperCase();

				switch (option) {
				case "YES": {
					System.out.println("           assigning...");
					System.out.println(mg.assignID());
					break;
				}
				case "NO": {
					System.out.println(Style(33, "            OKAY :D \n          BYE BYE "));
					break;
				}
				default:
					System.out.println(Style(33, " Put only yes or no, plis!  :D "));

				}

				break;
			}
			default:
				System.out.println(Style(33, " Put only yes or no, plis!  :D "));
			}

			key2 = true;

		}

	}

	/**
	 * application options menu <b>pre</b>permission key to generate start the menu<br>
	 * <b>post</b>selected menu options<br>
	 * 
	 * @return activation of the selected option
	 */
	
	public void options(boolean key2) {

		if (key2 == true) {
			System.out.println(Style(60,
					"                           OPTIONS                           \n                (PUT JUST THE NUMBER OPTION) \n\n 1) Find By Microchip            2) Count By Species \n 3) Find For Potential Damage    4) Find By Multiple Fields "));
			String option = sc.nextLine().toUpperCase();

			switch (option) {
			case "1": {
				optionOne(option);
				break;
			}
			case "2": {
				optionTwo(option);
				break;
			}
			case "3": {
				optionThird(quantity, top_last, neighborhood);
				break;
			}
			case "4": {
				optionFour(species, sex, size, dangerous);

				break;
			}
			
			default:
				System.out.println("JUST PUT THE MENU OPTION");
				
			}
			


		}

	}

	/**
	 * logic of the first menu option<b>pre</b>data required to execute the option<br>
	 * <b>post</b>generated solution of problem one<br>
	 * 
	 * @return  animal data
	 */

	
	public void optionOne(String option) {

		System.out.println(Style(33, "   Put a numer Microchip plis! "));
		option = sc.nextLine();
		System.out.println(mg.findByMicrochip(option)+ "\n\n");

		System.out.println(Style(22, " Back To Menu  \n   -YES   -NO"));
		String back = sc.nextLine().toUpperCase();
		if (back.equals("YES")) {
			options(true);
		}
		if (back.equals("NO")) {
			System.out.println(Style(22, " OKAY! see you later :)"));
		}
		

	}

	/**
	 * logic of the second menu option<b>pre</b>data required to execute the option<br>
	 * <b>post</b>generated solution of problem two<br>
	 * 
	 * @return  quantity of specie
	 */
	
	public void optionTwo(String option) {
		System.out.println(Style(33, "Put ONE of the following species! \n       -CANINO   -FELINO"));
		option = sc.nextLine().toUpperCase();
		System.out.println(mg.countBySpecies(option)+ "\n\n");
		
		System.out.println(Style(22, " Back To Menu  \n   -YES   -NO"));
		String back = sc.nextLine().toUpperCase();
		if (back.equals("YES")) {
			options(true);
		}
		if (back.equals("NO")) {
			System.out.println(Style(22, " OKAY! see you later :)"));
		}
		

	}

	/**
	 * logic of the third menu option<b>pre</b>data required to execute the option<br>
	 * <b>post</b>generated solution of problem three<br>
	 * 
	 * @return  data on possible dangerous animals
	 */
	
	public void optionThird(int quantity, String top_last, String neighborhood) {
		String a = "\n -USAQUEN      -CHAPINERO      -SANTA FE      -SAN CRISTOBAL      -USME   ";
		String b = "\n -TUNJUELITO   -BOSA           -KENNEDY       -FONTIBON           -ENGATIVA ";
		String c = "\n -SUBA         -B. UNIDOS      -TEUSAQUILLO   -LOS MARTIRES       -A. NARINO ";
		String d = "\n -P. ARANDA    -LA CANDELARIA  -R. URIBE      -C. BOLIVAR         -SUMAPAZ  ";
		String e = "\n -MUNICIPIOS ALEDA�OS BOGOTA D.C.             -SIN IDENTIFICAR";

		try {
			for (int i = 0; i < 4; i++) {

				if (i == 0) {
					System.out.println(Style(39, "First put a number of animals to see! "));
					quantity = sc.nextInt();
				}
				if (i == 1) {
					System.out.println("");
					top_last = sc.nextLine().toUpperCase();
				}
				if (i == 2) {
					System.out.println(Style(28, "  Second The order to see! \n       -TOP  -LAST "));
					top_last = sc.nextLine().toUpperCase();

				}
				if (i == 3) {
					System.out.println(Style(76,
							"                    Third put the neighborhood to review \n" + a + b + c + d + e));
					neighborhood = sc.nextLine().toUpperCase();
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}

		String result = mg.findBypotentDangerousInNeighborhood(quantity, top_last, neighborhood);
		
			System.out.println(result + "\n\n");
			System.out.println(Style(22, " Back To Menu  \n   -YES   -NO"));
			String back = sc.nextLine().toUpperCase();
			if (back.equals("YES")) {
				options(true);
			}
			if (back.equals("NO")) {
				System.out.println(Style(22, " OKAY! see you later :)"));
			}
			
		

	}

	/**
	 * logic of the four menu option<b>pre</b>data required to execute the option<br>
	 * <b>post</b>generated solution of problem four<br>
	 * 
	 * @return  animals ID
	 */
	
	public void optionFour(String species, String sex, String size, boolean dangerous) {
		for (int i = 0; i < 4; i++) {

			if (i == 0) {
				System.out.println(Style(39, "First put a specie! \n  -CANINO  -FELINO"));
				species = sc.nextLine().toUpperCase();
			}
			if (i == 1) {
				System.out.println(Style(39, "Second put a sex!  \n  -MACHO  -HEMBRA"));
				sex = sc.nextLine().toUpperCase();
			}
			if (i == 2) {
				System.out.println(Style(39, "Third put a size! \n   -MINIATURA    -PEQUE�O \n   -MEDIANO      -GRANDE"));
				size = sc.nextLine().toUpperCase();
			}
			if (i == 3) {
				System.out.println(Style(39, "Four, you animal is dangerous?\n     -YES     -NO! "));
				String var = sc.nextLine().toUpperCase();
				if (var.equals("YES")) {
					dangerous = true;
				}

			}
		}
		String check = mg.findByMultipleFields(species, sex, size, dangerous);
		if (check.equals("")) {
			System.out.println("Not found in data log");
		} else {
			System.out.println(check + "\n\n");
			System.out.println(Style(22, " Back To Menu  \n   -YES   -NO"));
			String back = sc.nextLine().toUpperCase();
			if (back.equals("YES")) {
				options(true);
			}
			if (back.equals("NO")) {
				System.out.println(Style(22, " OKAY! see you later :)"));
			}
			
		}

	}

	/**
	 * method used for program decoration<b>pre</b>number of characters and text to display<br>
	 * <b>post</b>stylized text<br>
	 * 
	 * @return  stylized text
	 */
	
	public String Style(int numer, String Text) {
		String a = "-";
		String solution = "";
		for (int i = 0; i < numer; i++) {

			solution = solution + a;

		}

		solution = solution + "\n" + Text + "\n";

		for (int i = 0; i < numer; i++) {

			solution = solution + a;

		}

		return solution;
	}
}