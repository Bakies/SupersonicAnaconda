package es.baki.mitchnpals.supersonicanaconda;

public class Colors {
	public enum Color {
		LIGHT_RED (0, 0),
		RED (0, 1),
		DARK_RED (0, 2),
		LIGHT_YELLOW (1, 0),
		YELLOW (1, 1),
		DARK_YELLOW (1, 2),
		LIGHT_GREEN (2, 0),
		GREEN (2, 1),
		DARK_GREEN (2, 2),
		LIGHT_CYAN (3, 0),
		CYAN (3, 1),
		DARK_CYAN (3, 2),
		LIGHT_BLUE (4, 0),
		BLUE (4, 1),
		DARK_BLUE (4, 2),
		LIGHT_MAGENTA (5, 0),
		MAGENTA (5, 1),
		DARK_MAGENTA (5, 1);
		
		
		private int hue, darkness;
		Color(int hue, int darkness) {
				this.hue = hue;
				this.darkness = darkness;
		}
		
		public int getHue() {
			return hue;
		}
		public int getDarkness() {
			return darkness;
		}
		
		public int getHueDifference(Color c2) {
			int ret = c2.getHue() - hue;
			if (ret < 0) {
				ret += 6;
			}
			ret %= 5;
			return ret;
		}		
		public int getDarknessDifference(Color c2) {
			int ret = c2.getDarkness() - darkness;
			if (ret < 0) {
				ret += 4;
			}
			ret %= 3;
			return ret;
		}
	}
	
	public static int getHueDifference(Color c1, Color c2) {
		return c1.getHueDifference(c2);
	}
	public static int getDarknessDifference(Color c1, Color c2) {
		return c1.getDarknessDifference(c2);
	}
	

}
