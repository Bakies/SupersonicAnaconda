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
}
