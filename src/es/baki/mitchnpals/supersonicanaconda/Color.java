package es.baki.mitchnpals.supersonicanaconda;


public enum Color {
	WHITE("White",0,0,0),
	BLACK("Black",-1,0,0),
	
	LIGHT_RED ("Light Red", 1,0, 0),
	RED ("Red", 2,0, 1),
	DARK_RED ("Dark Red", 3,0, 2),

	LIGHT_YELLOW ("Light Yellow", 4,1, 0),
	YELLOW ("Yellow", 5,1, 1),
	DARK_YELLOW ("Dark Yellow", 6,1, 2),

	LIGHT_GREEN ("Light Green", 7,2, 0),
	GREEN ("Green", 8,2, 1),
	DARK_GREEN ("Dark Green", 9,2, 2),

	LIGHT_CYAN ("Light Cyan", 10,3, 0),
	CYAN ("Cyan", 11,3, 1),
	DARK_CYAN ("Dark Cyan", 12,3, 2),

	LIGHT_BLUE ("Light Blue", 13,4, 0),
	BLUE ("Blue", 14,4, 1),
	DARK_BLUE ("Dark Blue", 15,4, 2),

	LIGHT_MAGENTA ("Light Magenta", 16,5, 0),
	MAGENTA ("Magenta", 17,5, 1),
	DARK_MAGENTA ("Dark Magenta", 18,5, 2);
	
	private final String name; 
	private final int index, hue, darkness;
	Color(String name, int index, int hue, int darkness) {
			this.hue = hue;
			this.darkness = darkness;
			
			this.name = name;
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
			return 0;
		}
		int ret = c2.getHue() - hue;
		if (ret < 0) {
			ret += 6;
		}
		ret %= 6;
		return ret;
	}		
	public int getDarknessDifference(Color c2) {
		if (index == -1 || index == 0 || c2.index == 0 || c2.index == -1) { 
			return 0;
		}
		int ret = c2.getDarkness() - darkness;
		if (ret < 0) {
			ret += 3;
		}
		ret %= 3;
		return ret;
	}

	public String getName() { 
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

	public int getIndex() {
		return index;
	}
	
	/**
	 * will return 
	 * @param rgb
	 * @return
	 */
	public static Color getColorFromRGBInt(int rgb) {
		rgb = 0xFFFFFF & rgb;
		switch (rgb) {
		case 0xFFC0C0:
		return Color.LIGHT_RED;
		case 0xFF0000:
		return Color.RED;
		case 0xC00000:
		return Color.DARK_RED;
		case 0xFFFFC0:
		return Color.LIGHT_YELLOW;
		case 0xFFFF00:
		return Color.YELLOW;
		case 0xC0C000:
		return Color.DARK_YELLOW;
		case 0xC0FFC0:
		return Color.LIGHT_GREEN;
		case 0x00FF00:
		return Color.GREEN;
		case 0x00C000:
		return Color.DARK_GREEN;
		case 0xC0FfFF:
		return Color.LIGHT_CYAN;
		case 0x00FFFF:
		return Color.CYAN;
		case 0x00C0C0:
		return Color.DARK_CYAN;
		case 0xC0C0FF:
		return Color.LIGHT_BLUE;
		case 0x0000FF:
		return Color.BLUE;
		case 0x0000C0:
		return Color.DARK_BLUE;
		case 0xFFC0FF:
		return Color.LIGHT_MAGENTA;
		case 0xFF00FF:
		return Color.MAGENTA;
		case 0xC000C0:
		return Color.DARK_MAGENTA;
		case 0xFFFFFF:
		return Color.WHITE;
		case 0x000000:
		return Color.BLACK;
		default:
			System.err.println("Unknown Color " + rgb);
			return Color.WHITE;
		}
	}
	public static int getRGB(Color c) {
		switch (c) {
		case LIGHT_RED:
			return 0xFFC0C0;
		case RED:
			return 0xFF0000;
		case DARK_RED:
			return 0xC00000;
		case LIGHT_YELLOW:
			return 0xFFFFC0;
		case YELLOW:
			return 0xFFFF00;
		case DARK_YELLOW:
			return 0xC0C000;
		case LIGHT_GREEN:
			return 0xC0FFC0;
		case GREEN:
			return 0x00FF00;
		case DARK_GREEN:
			return 0x00C000;
		case LIGHT_CYAN:
			return 0xC0FfFF;
		case CYAN:
			return 0x00FFFF;
		case DARK_CYAN:
			return 0x00C0C0;
		case LIGHT_BLUE:
			return 0xC0C0FF;
		case BLUE:
			return 0x0000FF;
		case DARK_BLUE:
			return 0x0000C0;
		case LIGHT_MAGENTA:
			return 0xFFC0FF;
		case MAGENTA:
			return 0xFF00FF;
		case DARK_MAGENTA:
			return 0xC000C0;
		case WHITE:
			return 0xFFFFFF;
		case BLACK:
			return 0x000000;
		default:
			return 0xFFFFFF;
		}
	}

	public static Color awtToBakiesColor(java.awt.Color c) {
		return Color.getColorFromRGBInt(c.getRGB());
	}

}
