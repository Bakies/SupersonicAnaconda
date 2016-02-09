package es.baki.dkteamgenerator;

import java.util.ArrayList;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class NHLTeamGenerator {	
	public static final int SALARY_CAP = 50_000;
	public static ArrayList<NHLPlayer> playerList; 
	public static ArrayList<NHLPlayer> team;
	private CSVConverter converter;

	private SimpleRegression bestFit = new SimpleRegression();

	//	private final int C_MAX = 2;
	//	private final int W_MAX = 3;
	//	private final int D_MAX = 2;
	//	private final int G_MAX = 1;
	//	private final int U_MAX = 1;

	private int C = 2;
	private int W = 3;
	private int D = 2;
	private int G = 1;

	public NHLTeamGenerator() {
		converter = new CSVConverter(CSVConverter.NHL);
		playerList = converter.getNHLList();
		
		team = new ArrayList<>();

		generateTeam();
		int salaryTotal = 0;
		System.out.println("Done generating");
		System.out.printf("%-20s|%4s|%5s|%6s%n", "Name", "Pos", "$$$$", "ppg");
		for (NHLPlayer p : team) { 
			System.out.printf("%-20s|%-4s|%-5d|%-6f%n", p.getName(), " " + p.getPos() + " ", p.getSalary(), p.getPpg());
			salaryTotal += p.getSalary();
		}
		System.out.println("Total team cost: " + salaryTotal);
			
		
	}

	public void generateTeam() { 
		for (Player p : playerList) { 
			bestFit.addData(p.getSalary(), p.getPpg());
		}
		System.out.println("y = " + bestFit.getSlope() + "x + "  + bestFit.getIntercept());
		for (NHLPlayer p : playerList) { 
			p.setRating(p.getPpg() - (p.getPpg() * bestFit.getSlope() + bestFit.getIntercept()));
//			System.out.println(p.getName() + ": " + p.getRating());
		}
		double maxRating = -100000;
		NHLPlayer playerToAdd = null;
		ArrayList<NHLPlayer> loopList = new ArrayList<>();
		loopList.addAll(playerList);
		while (C + W + D + G != 0) {
			maxRating = -100000000;
			
			for (NHLPlayer p : loopList) { 
				maxRating = Math.max(p.getRating(), maxRating);
				if (maxRating == p.getRating()) { 
					playerToAdd = p;
//					System.out.println("Highest rating: " + p.getName());
				}
			}
			if(playerToAdd.getPos() == 'C') { 
				if (C == 0) {
					loopList.remove(playerToAdd);
					continue;
				}
				C--;
			} else if (playerToAdd.getPos() == 'W') { 
				if (W == 0) {
					loopList.remove(playerToAdd);
					continue;
				}
				W--;
			} else if (playerToAdd.getPos() == 'D') { 
				if (D == 0){
					loopList.remove(playerToAdd);
					continue;
				}
				D--;
			} else if (playerToAdd.getPos() == 'G') { 
				if (G == 0) { 
					loopList.remove(playerToAdd);
					continue;
				}
				G--;
			}
			System.out.printf("Added [%s]%-20s C:%d W:%d D:%d G:%d%n", playerToAdd.getPos() + "", playerToAdd.getName(), C, W, D, G);
			team.add(playerToAdd);
			loopList.remove(playerToAdd);
		}
		loopList.removeAll(playerList);
		loopList.addAll(playerList);
		maxRating = -1000000;
		for (NHLPlayer p : loopList) { 
			if (p.getPos() == 'G')
				continue;
			maxRating = Math.max(p.getRating(), maxRating);
			if (maxRating == p.getRating()) { 
				playerToAdd = p;
				System.out.println("UTIL: " + p.getName());
			}
		}
		team.add(playerToAdd);
		loopList.remove(playerToAdd);

	}
}
