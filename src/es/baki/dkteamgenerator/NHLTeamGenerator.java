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

		generateTeam();

		for (NHLPlayer p : team) 
			System.out.println(p.getName());
			
		
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
		while (C + W + D + G != 0) {
			maxRating = -100000000;
			for (NHLPlayer p : playerList) { 
				maxRating = Math.max(p.getRating(), maxRating);
				if (maxRating == p.getRating()) { 
					playerToAdd = p;
					System.out.println("Highest rating: " + p.getName());
				}
			}
			if(playerToAdd.getPos() == 'C') { 
				if (C == 0)
					continue;
				C--;
			} else if (playerToAdd.getPos() == 'W') { 
				if (W == 0)
					continue;
				W--;
			} else if (playerToAdd.getPos() == 'D') { 
				if (D == 0)
					continue;
				D--;
			} else if (playerToAdd.getPos() == 'G') { 
				if (G == 0)
					continue;
				G--;
			}
			playerList.remove(playerToAdd);
			team.add(playerToAdd);
		}
		maxRating = -1000000;
		for (NHLPlayer p : playerList) { 
			if (p.getPos() == 'G')
				continue;
			maxRating = Math.max(p.getRating(), maxRating);
			if (maxRating == p.getRating()) { 
				playerToAdd = p;
			}
		}
		team.add(playerToAdd);
		playerList.remove(playerToAdd);

	}
}
