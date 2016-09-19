package es.baki.dkteamgenerator;



public class NHLPlayer extends Player {
	private char pos;
	private String name;
	private int salary;
	private float ppg;
	private double rating;
	
	public NHLPlayer(char pos, String name, int salary, float ppg) { 
		this.pos = pos;
		this.name = name;
		this.salary = salary;
		this.ppg = ppg;
		this.setRating((ppg / ((double) salary)));
	}

	public char getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public float getPpg() {
		return ppg;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
