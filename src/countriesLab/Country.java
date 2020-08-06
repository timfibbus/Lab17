package countriesLab;

import java.util.Collections;
import java.util.Comparator;

public class Country {

	private String name;
	private String capital;
	private int population;
	
	public Country() {
		
	}
	
	public Country(String name, int population, String capital) {
		this.name=name;
		this.population=population;
		this.capital=capital;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		String format = String.format( " %14s  Capital: %15s  Population: %10d ",name,capital,population);
		return format;
	}
	
	public String toString2() {
		return name+"~~~"+population+"~~~"+capital;
	}
	

	
	//public int compare (int popOne , int popTwo) {
	//	return 0;
	//}

	
	
	//public int populationSorter(Country o1, Country o2) {
		//int i = o2.getPopulation().compareTo(o1.getPopulation());
		//return i;
//}
	
}
