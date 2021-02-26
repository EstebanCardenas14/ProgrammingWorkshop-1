package co.edu.uelbosque.controller;

import java.io.IOException;
import java.util.Scanner;

import co.edu.uelbosque.model.persistence.ManagerDAO;

public class Controller {

	ManagerDAO mg = new ManagerDAO("pets-citizens.csv");
	Scanner sc = new Scanner(System.in);
	boolean key = false;
	boolean key2 = true;

	public Controller() throws IOException {
	//	startApplication();
	//	assignID(key);
	//	optons(key2);
       optons(key2);
	
		
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
			System.out.println(Style(33, "  IF YOU NOT UPLOAD THE DATA \\n  YOU CANNOT RUN THE PROGRAM :( \\n\\n        plis try again!"));
				
			break;
		}
		default: {
			System.out.println(Style(33, " Put only yes or no, plis!  :D "));
				
		}

		}
	}

	public void assignID(boolean key) {
		if (key == true) {
			System.out.println(Style(33, "   Do you want to assign an ID? \\n     - YES            -NO "));
				
			String option = sc.nextLine().toUpperCase();

			switch (option) {
			case "YES": {
				System.out.println("           assigning...");
				System.out.println(mg.assignID());
				break;
			}
			case "NO": {
				System.out.println(Style(47, "                  OKAY \n remember that if you don't assign an ID now, \n            you can't go back :D \n         do you want to add an id? \n               -YES    -NOT "));
			
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

	public void optons(boolean key2) {

		int quantity = 0;
		String top_last = "";
		String neighborhood = "";
		
		if(key2 == true) {
			System.out.println(Style(60, "                           OPTIONS                           \n                (PUT JUST THE NUMBER OPTION) \n\n 1) Find By Microchip            2) Count By Species \n 3) Find For Potential Damage    4) Find By Multiple Fields "));
			String option = sc.nextLine().toUpperCase();

			switch (option) {
			case "1": {
				System.out.println(Style(33, "   Put a numer Microchip plis! "));
             option = sc.nextLine();
             System.out.println(mg.findByMicrochip(option));
           
				break;
			}
			case "2": {
				System.out.println(Style(33, "Put ONE of the following species! \n       -CANINO   -FELINO"));
		             option = sc.nextLine().toUpperCase();
		             System.out.println(mg.countBySpecies(option));

				
				break;
			}
			case "3": {


				System.out.println(Style(43, "First put a number of animals to see! "));
					quantity = sc.nextInt();
					System.out.println("---------------------------- \n  Second The order to see! \n       -TOP  -LAST \n----------------------------");
					top_last = sc.nextLine().toUpperCase();
				
					switch (top_last) {
					case "TOP": {
                     System.out.println(Style(33, "Third put the neighborhood to review \\n  -USAQUEN   -CHAPINERO   -SANTA FE\n  -SAN CRISTOBAL   -USME   -")); 
                     
						break;
					}
					case "LAST": {

						break;
					}
					default:
						System.out.println(Style(33, "You can only put TOP or LAST"));
					}
				
				
				break;
			}
			case "4": {

				System.out.println("--------------------------------- \nPut ONE of the following species! \n       -CANINO   -FELINO");
	             option = sc.nextLine();
	             System.out.println(mg.countBySpecies(option));
				
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
			
			
		}
		

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