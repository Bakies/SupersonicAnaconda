package es.baki.dkteamgenerator;

import java.io.File;
import java.util.Scanner;

public class Generator {
	private static Scanner input;
	public static File csv;
	
	public static void main(String... args) { 
		input = new Scanner(System.in);
		System.out.print("Input the file path");
		csv = new File(input.nextLine());
		
		
		System.out.print("Choose a league: (NHL) ");
		String n = input.nextLine();
		if (n.equalsIgnoreCase("NHL")) { 
			new NHLTeamGenerator();
		}
	}
}
