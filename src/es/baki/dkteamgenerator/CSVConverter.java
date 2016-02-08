package es.baki.dkteamgenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVConverter {
	public static final int NHL = 0;
	private Scanner scan;

	public CSVConverter(int league) { 
		if (league == 0){ 
			try {
				scan = new Scanner(Generator.csv);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
				return;
			}
			scan.nextLine(); // Trash the header of the CSV
		}
	}
	
	public ArrayList<NHLPlayer> getNHLList() {
		ArrayList<NHLPlayer> ret = new ArrayList<>();
		while (scan.hasNext()) { 
			String line = scan.nextLine();
			String[] lines = line.split(",");
			
			char pos = lines[0].replace("\"", "").charAt(lines[0].length() == 4 ? 1 : 0);
			
			String name = lines[1].replace("\"", "");
			int salary = Integer.parseInt(lines[2]);
			float ppg = Float.parseFloat(lines[4]);
			ret.add(new NHLPlayer(pos, name, salary, ppg));
		}
		return ret;
	}
}
