package es.baki.mitchnpals.supersonicanaconda;

public class Colors {
	public enum Color {
		WHITE(0,0,0),
		BLACK(-1,0,0),
		
		LIGHT_RED (1,0, 0),
		RED (2,0, 1),
		DARK_RED (3,0, 2),
		LIGHT_YELLOW (4,1, 0),
		YELLOW (5,1, 1),
		DARK_YELLOW (6,1, 2),
		LIGHT_GREEN (7,2, 0),
		GREEN (8,2, 1),
		DARK_GREEN (9,2, 2),
		LIGHT_CYAN (10,3, 0),
		CYAN (11,3, 1),
		DARK_CYAN (12,3, 2),
		LIGHT_BLUE (13,4, 0),
		BLUE (14,4, 1),
		DARK_BLUE (15,4, 2),
		LIGHT_MAGENTA (16,5, 0),
		MAGENTA (17,5, 1),
		DARK_MAGENTA (18,5, 1);
		
		
		private final int index, hue, darkness;
		Color(int index, int hue, int darkness) {
				this.hue = hue;
				this.darkness = darkness;
				
				this.index = index;
		}
		
		public int getHue() {
			return hue;
		}
		public int getDarkness() {
			return darkness;
		}
		
		public int getHueDifference(Color c2) {
			if (index == -1 || index == 0 || c2.index == 0 || c2.index == -1) { 
				return -1;
			}
			int ret = c2.getHue() - hue;
			if (ret < 0) {
				ret += 6;
			}
			ret %= 5;
			return ret;
		}		
		public int getDarknessDifference(Color c2) {
			if (index == -1 || index == 0 || c2.index == 0 || c2.index == -1) { 
				return -1;
			}
			int ret = c2.getDarkness() - darkness;
			if (ret < 0) {
				ret += 4;
			}
			ret %= 3;
			return ret;
		}
	}
	
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
	

}
