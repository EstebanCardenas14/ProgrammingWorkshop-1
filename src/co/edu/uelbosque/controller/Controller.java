package co.edu.uelbosque.controller;

import java.io.IOException;
import java.util.Scanner;

import co.edu.uelbosque.model.persistence.ManagerDAO;

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

	public Controller() throws IOException {
		startApplication();
		assignID(key);
		options(key2);

	}

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

			}

		}

	}

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
			System.out.println(Style(22, " OKAY! see you later :)  \n   -YES   -NO"));
		}

	}

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
			System.out.println(Style(22, " OKAY! see you later :)  \n   -YES   -NO"));
		}


	}

	public void optionThird(int quantity, String top_last, String neighborhood) {
		String a = "\n -USAQUEN      -CHAPINERO      -SANTA FE      -SAN CRISTOBAL      -USME   ";
		String b = "\n -TUNJUELITO   -BOSA           -KENNEDY       -FONTIBON           -ENGATIVA ";
		String c = "\n -SUBA         -B. UNIDOS      -TEUSAQUILLO   -LOS MARTIRES       -A. NARINO ";
		String d = "\n -P. ARANDA    -LA CANDELARIA  -R. URIBE      -C. BOLIVAR         -SUMAPAZ  ";
		String e = "\n -MUNICIPIOS ALEDA�OS BOGOTA D.C.             -SIN IDENTIFICAR";

		String sopi = "";
		try {
			for (int i = 0; i < 4; i++) {

				if (i == 0) {
					System.out.println(Style(39, "First put a number of animals to see! "));
					quantity = sc.nextInt();
				}
				if (i == 1) {
				
				}
				if (i == 2) {
					System.out.println(Style(28, "  Second The order to see! \n       -TOP  -LAST "));
					top_last = sc.nextLine().toUpperCase();

				}
				if (i == 3) {
					System.out.println(Style(76,
							"                    Third put the neighborhood to review \n" + a + b + c + d + e));
					neighborhood = sc.nextLine();
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}

		String result = mg.findBypotentDangerousInNeighborhood(quantity, top_last, neighborhood);
		if (result.equals("")) {
			System.out.println("Not found in data log");
		} else {
			System.out.println(result + "\n\n");
			System.out.println(Style(22, " Back To Menu  \n   -YES   -NO"));
			String back = sc.nextLine().toUpperCase();
			if (back.equals("YES")) {
				options(true);
			}
			if (back.equals("NO")) {
				System.out.println(Style(22, " OKAY! see you later :)  \n   -YES   -NO"));
			}
		}

	}

	public void optionFour(String species, String sex, String size, boolean dangerous) {
		for (int i = 0; i < 4; i++) {

			if (i == 0) {
				System.out.println(Style(39, "First put a specie! "));
				species = sc.nextLine();
			}
			if (i == 1) {
				System.out.println(Style(39, "Second put a sex! "));
				sex = sc.nextLine();
			}
			if (i == 2) {
				System.out.println(Style(39, "Third put a size! "));
				size = sc.nextLine();
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
				System.out.println(Style(22, " OKAY! see you later :)  \n   -YES   -NO"));
			}
		}

	}

	public String stat() {
		String a = "a";
		try {
			for (int i = 0; i < mg.getPetList().size(); i++) {
				if (mg.getPetList().get(i).getNeighborhood().equals(mg.getPetList().get(i + 1).getNeighborhood())) {
					a = a + "\n" + mg.getPetList().get(i).getNeighborhood();
				} else {

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return a;
	}

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