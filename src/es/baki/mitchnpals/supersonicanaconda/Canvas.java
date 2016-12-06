package es.baki.mitchnpals.supersonicanaconda;

public class Canvas {
	private int height, width; 
	private Color[][] canvas;
	
	public Canvas(int width, int height, Canvas canvas) {
		this(height, width);
		for (int x = 0; x < canvas.width && x < this.width; x++){
			for (int y = 0; y < canvas.height && y < this.height; y++){
				this.canvas[x][y] = canvas.canvas[x][y];
			}
		}
	}
	
	public Canvas(int length) {
		this(length, length);
	}
	
	public Canvas(int width, int height) {
		this.height = height;
		this.width = width;
		canvas = new Color[width][height];
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				canvas[x][y] = Color.WHITE;
			}
		}
	}
	
	public void set(int x, int y, Color color) {
		if (color == null)
			color = Color.WHITE;
		canvas[x][y] = color;
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width; 
	}

	public Color getColor(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		return canvas[x][y];
	}
	
	public void readFromPietFile() {
		// TODO Read from piet file 
	}
	
	public void readFromPNGFile() {
		// TODO Read from png file
	}
	
	public void saveToFile(String filename) {
		// TODO Save canvas to file
	}
	
	public void exportToPNG(String filename) {
		// TODO exportToPNG
		
	}
	
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				ret.append(String.format("%2d ", canvas[x][y].getIndex()));
			}
			ret.append(String.format("%n"));
		}
		return ret.toString();
	}
	
	public String toReadableString() {
		StringBuilder ret = new StringBuilder();
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				ret.append(String.format("%-13s ", canvas[x][y].getName()));
			}
			ret.substring(0, ret.length() - 1);
			ret.append(String.format("%n"));
		}
		return ret.substring(0, ret.length() - 1);
	}
	
	
	public static void main(String...strings) {
		Canvas c = new Canvas(10);
		c.set(0, 0, Color.BLACK);
		c.set(0, 1, Color.LIGHT_MAGENTA);
		System.out.println(c);
		System.out.println(c.toReadableString());
		
	}
}
