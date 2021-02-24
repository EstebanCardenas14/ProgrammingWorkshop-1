package co.edu.uelbosque.controller;

import java.io.IOException;

import co.edu.uelbosque.model.persistence.ManagerDAO;

public class Controller {

	ManagerDAO mg = new ManagerDAO("pets-citizens.csv");
	
	public Controller() throws IOException {
		
		System.out.println(mg.uploadData());
		
		
		
		for (int i = 0; i < 5; i++) {
			System.out.println(mg.getPetList().get(i).toString());
		}
	   
		
		
		
	}
	
	
}
