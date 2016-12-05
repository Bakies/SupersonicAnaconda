package es.baki.mitchnpals.supersonicanaconda;

import java.util.ArrayList;

/**
 * A helper class for Color enum
 */
public class Colors {
	private static ArrayList<Color> colors;
	/**
	 * @deprecated
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static int getHueDifference(Color c1, Color c2) {
		return c1.getHueDifference(c2);
	}
	/**
	 * @deprecated
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static int getDarknessDifference(Color c1, Color c2) {
		return c1.getDarknessDifference(c2);
	}
	
	private static void init() {
		if (colors == null) {
			colors = new ArrayList<>();
			colors.add(Color.LIGHT_RED);
			colors.add(Color.RED);
			colors.add(Color.DARK_RED);
			
			colors.add(Color.LIGHT_YELLOW);
			colors.add(Color.YELLOW);
			colors.add(Color.DARK_YELLOW);
			
			colors.add(Color.LIGHT_GREEN);
			colors.add(Color.GREEN);
			colors.add(Color.DARK_GREEN);
			
			
			colors.add(Color.LIGHT_CYAN);
			colors.add(Color.CYAN);
			colors.add(Color.DARK_CYAN);
			
			colors.add(Color.LIGHT_BLUE);
			colors.add(Color.BLUE);
			colors.add(Color.DARK_BLUE);
			
			colors.add(Color.LIGHT_MAGENTA);
			colors.add(Color.MAGENTA);
			colors.add(Color.DARK_MAGENTA);
		}
	}
	
	/**
	 * gets based on index
	 * does not return white or black
	 * @param i
	 * @return
	 */
	public static Color get(int i) {
		if (colors == null)
			init();
		return colors.get(i);
	}
	
	/**
	 * Get based on hue and darkness
	 * will not return black or white 
	 * @param hue
	 * @param darkness
	 * @return
	 */
	public static Color get(int hue, int darkness){
			if (colors == null)
				init();
			for (Color c : colors) {
				if (c.getHue() == hue && c.getDarkness() == darkness)
					return c;
			}
			return null;
	}
	
}
