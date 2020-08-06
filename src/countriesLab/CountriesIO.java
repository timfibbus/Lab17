package countriesLab;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;



 

public class CountriesIO{
	private static Path filePath = Paths.get("countries.txt");

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		List<Country> nationz = new ArrayList<>();
 		
		while (true) {
			System.out.print("Enter a command (list, add, remove, edit, sort, quit): ");
			String choose = scan.nextLine();
			
			if (choose.equals("quit")) {
				break;
				
			} else if (choose.equals("list")) {
				nationz = readFile();
				
			} else if (choose.equals("add")) {
				Country country = customCountry(scan);
				System.out.println("Adding " + country);
				addACountry(country);
				
			} else if (choose.equals("remove")) {
				System.out.println("Select a country to delete.");
				nationz = readFile();
				int r = scan.nextInt();
				nationz.remove(nationz.get(r-1));
				removeTheIsh(nationz);
				scan.nextLine();
				
			} else if (choose.equals("edit")) {
				System.out.println("Which country? ");
				nationz = readFile();
				int thatOne = scan.nextInt();
				int newPop = Validator.getInt(scan, "Enter new population: ");
				nationz.get(thatOne).setPopulation(newPop);
				removeTheIsh(nationz);
			
			} else if (choose.equals("sort")) {
				nationz = readFile();
				int thatPop = 0;
				int max = 0;
			
				for (int j = 1 ; j < nationz.size() ; j++) {
				for(int i = 1 ; i < nationz.size() ; i++) {
					Country bumpkin = nationz.get(i-1);
					if(compare(nationz.get(i-1).getPopulation(),nationz.get(i).getPopulation())==1) {
						max = nationz.get(i-1).getPopulation();
						nationz.remove(bumpkin);
						nationz.add(bumpkin);
						removeTheIsh(nationz);
					}	
				}
				} 
				nationz = readFile();
				
				
			} else {
				System.out.println("Invalid command.");
				
			}
		}
		
		System.out.println("Goodbye, old friend.");
		scan.close();		
		
		}
				

	private static int compare(int one, int two) {
		if (one > two) {
			return 1;
		} else if (two > one) {
			return -1;
		} else {
			return 0;
		}
	}


	private static Country customCountry(Scanner scan) {
		String name = Validator.getString(scan, "Enter name: ");
		int population = Validator.getInt(scan, "Enter population: ");
		String capital = Validator.getString(scan, "Enter the capital: ");
		
		return new Country(name, population, capital);
	}
	
		public static void addACountry(Country country) {
			String line= country.toString2();
			List<String> lines = Collections.singletonList(line);
			try {
				Files.write(filePath, lines, StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				System.out.println("Unable to write to file.");
			}	
	}
			
		public static List<Country> readFile() {
			try {
				List<String> inputs = Files.readAllLines(filePath );
				List<Country> nationz = new ArrayList<>();
				int i = 1;
				for (String line : inputs) {
					String[] s = line.split("~~~");
					String name = s[0];
					int population = Integer.parseInt(s[1]);
					String capital = s[2];
					Country x = new Country(name, population, capital);
					System.out.printf("%2d)" + x + "\n", i);
					nationz.add(x);
					i++;
					}	
				return nationz;
				
			} catch (IOException e) {
				System.out.println("I can't read");
				e.printStackTrace();
				return null;
				} 	
		}
		
		private static void removeTheIsh(List<Country> list) {
			try (FileWriter fr = new FileWriter("countries.txt", false);
					BufferedWriter br = new BufferedWriter(fr);
					PrintWriter pr = new PrintWriter(br)) {
				for (Country count : list) {
					pr.println(count.toString2());
				}
			} catch (IOException e) {
				System.out.println("Failed to read file.");
			}
		}



		
		

}
