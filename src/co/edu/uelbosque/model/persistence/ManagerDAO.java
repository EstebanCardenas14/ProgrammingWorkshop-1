package co.edu.uelbosque.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uelbosque.model.PetDTO;

public class ManagerDAO {

	private PetDTO petObj;
	private ArrayList<PetDTO> petList;
	private String v;
	public final String SEPARATOR = ";";
	private int number;
	private Boolean key = false;

	public ManagerDAO(String v) {
		this.v = v;
		petList = new ArrayList<PetDTO>();
		number = 3;

	}

	public String uploadData() throws IOException  {

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
					
					PetDTO p = new PetDTO("NO-ID", Long.parseLong(microchip), species, sex, size, potentDangerous, neighborhood);
					petList.add(p);
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				line = br.readLine();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if (null!=br) {
	            br.close();
	         }
		}
		
		
key = true;
		return "ok";

	}

	public String assignID() {
		
	if(key == true) {
	int quantity = petList.size();
		
		for (int i = 0; i < quantity; i++) {
			int var = ( String.valueOf(petList.get(i).getMicrochip()).length() - 3);
			String part1 = String.valueOf(petList.get(i).getMicrochip()).substring(var);
			String part2 = String.valueOf(petList.get(i).getSpecies().charAt(0));
			String part3 = String.valueOf(petList.get(i).getSex().charAt(0));
			String part4 = String.valueOf(petList.get(i).getSize().charAt(0));
			String part5 = "0";
			if(petList.get(i).getPotentDangerous().equals(true)) {
				 part5 = "T";
			}else {
				 part5 = "F";
			}
			String part6 = petList.get(i).getNeighborhood();
			
			String new_Id = part1+"-"+part2+part3+part4+part5+"-"+part6;
			petList.get(i).setId(new_Id);
			
			try {
				for (int j = 0; j < quantity; j++) {
					if(petList.get(i).getId() == petList.get(j).getId()&& i != j) {
						var--;
						part1 = String.valueOf(petList.get(i).getMicrochip()).substring(var);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			new_Id = part1+"-"+part2+part3+part4+part5+"-"+part6;
			petList.get(i).setId(new_Id);
			
		}
		
		return "all IDs were assigned successfully";
	}else {
		return "we need to load the data to assign an ID";
	}
		
	}
	
	public String findByMicrochip(String microchip) {
	
		int pet = 0;
		
		for (int i = 0; i < petList.size(); i++) {
			if(String.valueOf(petList.get(i).getMicrochip()) == microchip) {
				pet=i;
			}else {
				return "Sorry :( - Pet not found!";
			}
		}
		
		return "DATA PET: \n" + petList.get(pet).toString();
	}

	public String countBySpecies(String species) {
		
	int pets = 0;
		
		for (int i = 0; i < petList.size(); i++) {
			if(String.valueOf(petList.get(i).getSpecies()) == species) {
				pets++;
			}
		}
		
		return "The number of the species " + species +" is : #" + pets;

		
	}
	
	public String findByMultipleFields(String species, String sex , String size, String potentDangerous) {
		
		
		
		return null;
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

	
	

}
