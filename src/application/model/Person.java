package application.model;

/**
 * @author Austin Moore
 * 
 * Person.java class, 
 * holds name, activity, color, latitude, longitude values
 * loadPersons method to load people from file in data folder, and constructor
 * has getters/setters for each value
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import application.Main;

public class Person {

	private String name;
	private String activity;
	private String color;
	private int latitude;
	private int longitude;
	
	// Contructor
	public Person(String name, String activity, String color, int latitude, int longitude) {
		
		this.name = name;
		this.activity = activity;
		this.color = color;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	public static ArrayList<Person> loadPersons(String f) {
		
		ArrayList<Person> people = new ArrayList<>();
		
		f = "data/" + f;
    	Scanner scan = null;
		File file = new File(f);
		String[] tokens;
		Person temp = null;
		
		try {
			
			scan = new Scanner(file);
			String line;
			
			while (scan.hasNext()) {
				temp = null;
				line = scan.nextLine();
				tokens = line.split(",");
				
				if (tokens.length == 4) {
					
					System.out.println("Adding Person: " + tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3]);
					temp = new Person(tokens[0], loadActivity("activities.txt"), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
					
				} else {
					System.out.println("Error loading persons from file");
				}
				
				// if temp was assigned, add to contacts
				if (temp != null) {
					people.add(temp);
				} else { 
					System.out.println("Error loading person.");
				}
				
				
			}
			
			
			} catch (Exception e) {

				e.printStackTrace();

			}
		
		return people;
		
	}
	
	public static String loadActivity(String f) {
		
		String activity = "";
		f = "data/" + f;
    	Scanner scan = null;
		File file = new File(f);
		
		try {
			
			scan = new Scanner(file);
			Random r = new Random();
			int q = r.nextInt(12) + 0;
			
			for (int i = 0; i < q; i++) {
				scan.nextLine();
			}
			
			activity = scan.nextLine();
			
			
		
		} catch (Exception e) {

			e.printStackTrace();

		}
		
		System.out.println("Activity: "+ activity);
		return activity;
		
	}
	
	
	// getters/setters
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getActivity() {
		return this.activity;
	}
	
	public void setActivity(String a) {
		this.activity = a;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String c) {
		this.color = c;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Integer l) {
		this.latitude = l;
	}
	
	public int getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Integer l) {
		this.longitude = l;
	}
	
}
