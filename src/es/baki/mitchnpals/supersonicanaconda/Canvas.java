package es.baki.mitchnpals.supersonicanaconda;

public class Canvas {
	private int height, width; 
	private int[][] canvas;
	public Canvas(int height, int width) {
		this.height = this.width;
		canvas = new int[width][height];
	}
}
