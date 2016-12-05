package es.baki.mitchnpals.supersonicanaconda;

import es.baki.mitchnpals.supersonicanaconda.Colors.Color;

public class Canvas {
	private int height, width; 
	private Color[][] canvas;
	
	public Canvas(int height, int width, Canvas canvas) {
		this(height, width);
		for (int x = 0; x < canvas.width && x < this.width; x++){
			for (int y = 0; y < canvas.height && y < this.height; y++){
				this.canvas[x][y] = canvas.canvas[x][y];
			}
		}
	}
	
	public Canvas(int height, int width) {
		this.height = height;
		this.width = width;
		canvas = new Color[width][height];
	}
	
	public void set(int x, int y, Color color) {
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
}
