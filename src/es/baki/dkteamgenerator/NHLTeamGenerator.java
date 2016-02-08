package es.baki.dkteamgenerator;

import java.util.ArrayList;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class NHLTeamGenerator {	
	public static final int SALARY_CAP = 50_000;
	public static ArrayList<NHLPlayer> playerList; 
	public static ArrayList<NHLPlayer> team;
	private CSVConverter converter;
	
	private SimpleRegression bestFit = new SimpleRegression();

	private final int C_MAX = 2;
	private final int W_MAX = 3;
	private final int D_MAX = 2;
	private final int G_MAX = 1;
	private final int U_MAX = 1;
	
	private int C = 2;
	private int W = 3;
	private int D = 2;
	private int G = 1;
	private int U = 1;
		
	public NHLTeamGenerator() {
		converter = new CSVConverter(CSVConverter.NHL);
		playerList = converter.getNHLList();
		
	}
	
	public void generateTeam() { 
		for (Player p : playerList) { 
			bestFit.addData(p.getPpg(), p.getSalary());
		}
		
		
		
		//		double rating = -Double.MAX_VALUE;
//		NHLPlayer bestRating;
//		
//		for (NHLPlayer p : playerList) { 
//			if (p.getRating() > rating) {
//				rating = p.getRating();
//				bestRating = p;
//			}
//		}
	}
}
