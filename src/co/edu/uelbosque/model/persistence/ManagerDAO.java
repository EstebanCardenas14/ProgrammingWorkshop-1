package co.edu.uelbosque.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

import co.edu.uelbosque.model.PetDTO;

/**
 * 
 * @author luisestebancardenascortes 
 * in this class you find the methods and validations
 *
 */
public class ManagerDAO {

	private PetDTO petObj;
	private ArrayList<PetDTO> petList;
	private ArrayList<PetDTO> invertedList;
	private String v;
	public final String SEPARATOR = ";";
	private int number;
	private Boolean key = false;

	/**
	 * Simple Constructor Method <b>pre</b>Ask for the path of the csv file<br>
	 * <b>post</b>Assign the csv file path<br>
	 * * @param v
	 */

	public ManagerDAO(String v) {
		this.v = v;
		petList = new ArrayList<PetDTO>();
		number = 3;
		invertedList = new ArrayList<PetDTO>();

	}

	/**
	 * Constructor method that initializes variables <b>pre</b>Variables must be
	 * created.<br>
	 * <b>post</b>Variables are initialized<br>
	 * 
	 * @param modelo
	 * @param petObj
	 * @param petList
	 * @param invertedList
	 * @param v
	 * @param number
	 * @param key
	 */
	public ManagerDAO(PetDTO petObj, ArrayList<PetDTO> petList, ArrayList<PetDTO> invertedList, String v, int number,
			Boolean key) {
		super();
		this.petObj = petObj;
		this.petList = petList;
		this.invertedList = invertedList;
		this.v = v;
		this.number = number;
		this.key = key;
	}

	/**
	 * method used to read the csv file <b>pre</b>the path must be specified.<br>
	 * <b>post</b>csv file is read and stored in an ArrayList<br>
	 * 
	 * @return success or error message
	 */

	public String uploadData() throws IOException {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(v));
			String line = br.readLine();

			while (null != line) {
				File f2 = new File(v);
				String[] fields = line.split(SEPARATOR);

				try {
					String microchip = fields[0];
					var species = fields[1];
					var sex = fields[2];
					var size = fields[3];
					String potentDangerous2 = fields[4];
					var neighborhood = fields[5];
					Boolean potentDangerous = false;
					if (potentDangerous2.equals("SI")) {
						potentDangerous = true;
					}

					PetDTO p = new PetDTO("NO-ID", Long.parseLong(microchip), species, sex, size, potentDangerous,
							neighborhood);
					petList.add(p);

				} catch (Exception e) {
					// TODO: handle exception
				}

				line = br.readLine();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != br) {
				br.close();
			}
		}

		key = true;
		return "ok";

	}

	/**
	 * method used to generate the IDs <b>pre</b>csv file must be read<br>
	 * <b>post</b>IDs are generated with the requested characteristics<br>
	 * 
	 * @return success or error message
	 */

	public String assignID() {

		if (key == true) {
			int quantity = petList.size();

			for (int i = 0; i < quantity; i++) {
				int var = (String.valueOf(petList.get(i).getMicrochip()).length() - 3);
				String part1 = String.valueOf(petList.get(i).getMicrochip()).substring(var);
				String part2 = String.valueOf(petList.get(i).getSpecies().charAt(0));
				String part3 = String.valueOf(petList.get(i).getSex().charAt(0));
				String part4 = String.valueOf(petList.get(i).getSize().charAt(0));
				String part5 = "0";
				if (petList.get(i).getPotentDangerous().equals(true)) {
					part5 = "T";
				} else {
					part5 = "F";
				}
				String part6 = petList.get(i).getNeighborhood();

				String new_Id = part1 + "-" + part2 + part3 + part4 + part5 + "-" + part6;
				petList.get(i).setId(new_Id);

				try {
					for (int j = 0; j < quantity; j++) {
						if (petList.get(i).getId() == petList.get(j).getId() && i != j) {
							var--;
							part1 = String.valueOf(petList.get(i).getMicrochip()).substring(var);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "oka se repite puto 2");
				}

				new_Id = part1 + "-" + part2 + part3 + part4 + part5 + "-" + part6;
				petList.get(i).setId(new_Id);

			}

			return "-------------------------------------\nall IDs were assigned successfully :D \n-------------------------------------";
		} else {
			return "------------------------------------\nwe need to load the data to assign an ID \n-------------------------------------";
		}

	}

	/**
	 * method to find by microchip <b>pre</b>a microchip must be entered<br>
	 * <b>post</b>search for the animal corresponding to the microchip<br>
	 * 
	 * @return animal data
	 */

	public String findByMicrochip(String microchip) {

		int pet = 0;

		for (int i = 0; i < petList.size(); i++) {

			if (petList.get(i).getMicrochip() == Long.parseLong(microchip)) {
				pet = i;
				return "--------------------------------- \n DATA PET: \n" + petList.get(pet).toString();
			}
		}

		return "Sorry :( - Pet not found!";
	}

	/**
	 * method to count the number of species
	 * <b>pre</b>a species must be entered<br>
	 * <b>post</b>count the amount of data for that species<br>
	 * 
	 * @return quantity of specie
	 */
	
	public String countBySpecies(String species) {

		int pets = 0;

		if (species.equals("CANINO") || species.equals("FELINO")) {
			for (int i = 0; i < petList.size(); i++) {
				if (petList.get(i).getSpecies().equals(species)) {
					pets++;
				}
			}

			return "The number of animals of the species " + species + " is : #" + pets;
		}

		return "Sorry, not register of species " + species;

	}

	/**
	 * method of counting potentially dangerous animals in the neighborhood
	 * <b>pre</b>number of animals, order and neighborhood must be entered<br>
	 * <b>post</b>verifications of dangerous animals in the database<br>
	 * 
	 * @return data on possible dangerous animals
	 */
	
	public String findBypotentDangerousInNeighborhood(int quantity, String position, String neighborhood) {

		PetDTO[] Solution = new PetDTO[quantity];
		int iterator = 0;
		String visualS = "";

		Collections.reverse(petList);

		for (int i = 0; i < petList.size(); i++) {
			invertedList.add(petList.get(i));
		}

		Collections.reverse(petList);

		for (int i = 0; i < petList.size(); i++) {

			if (iterator < quantity) {

				if (position.equals("TOP")) {

					if (petList.get(i).getPotentDangerous() == true) {

						if (petList.get(i).getNeighborhood().equals(neighborhood)) {

							visualS = visualS + "\n" + petList.get(i) + "\n";
							iterator++;

						}
					}
				}

				if (position.equals("LAST")) {

					if (invertedList.get(i).getPotentDangerous() == true) {
						if (invertedList.get(i).getNeighborhood().equals(neighborhood)) {

							visualS = visualS + "\n" + invertedList.get(i) + "\n";
							iterator++;

						}

					}
				}
			}

			if (visualS.equals("")) {
				visualS = "----------------------\nNot found in data log\n----------------------";
			}

		}

		return visualS;
	}

	/**
	 * method used to search for animals with specific data
	 * <b>pre</b>enter data such as species, sex, size and possibility of danger<br>
	 * <b>post</b>search for animals with entered parameters <br>
	 * 
	 * @return animals ID
	 */
	
	public String findByMultipleFields(String species, String sex, String size, Boolean potentDangerous) {

		String solution = "";

		for (int i = 0; i < petList.size(); i++) {
			if (petList.get(i).getSpecies().equals(species)) {
				if (petList.get(i).getSex().equals(sex)) {
					if (petList.get(i).getSize().equals(size)) {
						if (petList.get(i).getPotentDangerous().equals(potentDangerous)) {
							solution = solution + "\n" + petList.get(i).getId() + "\n";
						}
					}
				}
			}
		}

		if (solution.equals("")) {
			solution = "--------------------\n DATA NOT FOUND \n--------------------";
		}

		return solution;
	}

	/**
	 * @return the petObj
	 */
	public PetDTO getPetObj() {
		return petObj;
	}

	/**
	 * @param petObj the petObj to set
	 */
	public void setPetObj(PetDTO petObj) {
		this.petObj = petObj;
	}

	/**
	 * @return the petList
	 */
	public ArrayList<PetDTO> getPetList() {
		return petList;
	}

	/**
	 * @param petList the petList to set
	 */
	public void setPetList(ArrayList<PetDTO> petList) {
		this.petList = petList;
	}

	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}

	/**
	 * @param v the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the invertedList
	 */
	public ArrayList<PetDTO> getInvertedList() {
		return invertedList;
	}

	/**
	 * @param invertedList the invertedList to set
	 */
	public void setInvertedList(ArrayList<PetDTO> invertedList) {
		this.invertedList = invertedList;
	}

	/**
	 * @return the key
	 */
	public Boolean getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Boolean key) {
		this.key = key;
	}

}
