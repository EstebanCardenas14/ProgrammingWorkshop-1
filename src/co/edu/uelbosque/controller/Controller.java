package co.edu.uelbosque.controller;

import java.io.IOException;
import java.util.Scanner;

import co.edu.uelbosque.model.persistence.ManagerDAO;

public class Controller {

	ManagerDAO mg = new ManagerDAO("pets-citizens.csv");
	Scanner sc = new Scanner(System.in);
	boolean key = false;

	public Controller() throws IOException {
		startApplication();
		assignID(key);
		 optons();

	}

	public void startApplication() throws IOException {

		String startData = "";

		System.out.println(
				"---------------------------------\n DO YOU WANT TO UPLOAD THE DATA?  \n     - YES            -NO \n---------------------------------");
		startData = sc.nextLine().toUpperCase();

		switch (startData) {
		case "YES": {
			mg.uploadData();
			System.out.println(
					"--------------------------------- \n    UPLOAD DATA SUCCESSFULY :D \n---------------------------------");
			key = true;
			break;

		}
		case "NO": {
			System.out.println(
					"--------------------------------- \n  IF YOU NOT UPLOAD THE DATA \n  YOU CANNOT RUN THE PROGRAM :( \n\n        plis try again!\n---------------------------------");
			break;
		}
		default: {
			System.out.println(
					"--------------------------------- \n Put only yes or no, plis!  :D \n---------------------------------");
		}

		}
	}

	public void assignID(boolean key) {
		if (key == true) {
			System.out.println(
					"   Do you want to assign an ID? \n     - YES            -NO \n---------------------------------");
			String option = sc.nextLine().toUpperCase();

			switch (option) {
			case "YES": {
				System.out.println("           assigning...");
				System.out.println(mg.assignID());
				break;
			}
			case "NO": {
				System.out.println("-----------------------------------------------"
						+ "\n                  OKAY \n remember that if you don't assign an ID now, "
						+ "\n            you can't go back :D \n         do you want to add an id? "
						+ "\n               -YES    -NOT \n-----------------------------------------------");
				option = sc.nextLine().toUpperCase();

				switch (option) {
				case "YES": {
					System.out.println("           assigning...");
					System.out.println(mg.assignID());
					break;
				}
				case "NO": {
					System.out.println(
							"---------------------------------\n            OKAY :D \n          BYE BYE \n---------------------------------");
					break;
				}
				default:
					System.out.println(
							"--------------------------------- \n Put only yes or no, plis!  :D \n---------------------------------");
				}

				break;
			}
			default:
				System.out.println(
						"--------------------------------- \n Put only yes or no, plis!  :D \n---------------------------------");
			}

		}

	}

	public void optons() {

		System.out.println(
				"-------------------------  OPTIONS  ------------------------ \n                (PUT JUST THE NUMBER OPTION) \n\n 1) Find By Microchip            2) Count By Species \n 3) Find For Potential Damage    4) Find By Multiple Fields \n------------------------------------------------------------ ");
		String option = sc.nextLine().toUpperCase();

	}

}