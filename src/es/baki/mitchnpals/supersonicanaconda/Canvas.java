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
		new Canvas(length, length);
	}
	
	public Canvas(int width, int height) {
		this.height = height;
		this.width = width;
		canvas = new Color[width][height];
		for (int x = 0; x < width && x < this.width; x++){
			for (int y = 0; y < height && y < this.height; y++){
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
}
