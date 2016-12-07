package es.baki.mitchnpals.supersonicanaconda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public static Canvas readFromPNGFile(String filename) {
		File file = new File(filename);
		BufferedImage image ;
		if (!file.exists()){
			System.err.println("File does not exist " + file.getAbsolutePath());
			return null;
		}
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		int w, h;
		w = image.getData().getWidth(); 
		h = image.getData().getHeight();
		Canvas c = new Canvas(w, h);
		
		for (int y = 0; y < h; y ++) 
			for (int x = 0; x < w; x ++){
				int rgb = image.getRGB(x, y);
				rgb = rgb & 0xFFFFFF;
				if (rgb == 0xFFC0C0) 
					c.set(x, y, Color.LIGHT_RED);
				else if (rgb == 0xFF0000)
					c.set(x, y, Color.RED);
				else if (rgb == 0xC00000) 
					c.set(x, y, Color.DARK_RED);
				else if (rgb == 0xFFFFC0)
					c.set(x, y, Color.LIGHT_YELLOW);
				else if (rgb == 0xFFFF00)
					c.set(x, y, Color.YELLOW);
				else if (rgb == 0xC0C000)
					c.set(x, y, Color.DARK_YELLOW);
				else if (rgb == 0xC0FFC0)
					c.set(x, y, Color.LIGHT_GREEN);
				else if (rgb == 0x00FF00)
					c.set(x, y, Color.GREEN);
				else if (rgb == 0x00C000)
					c.set(x, y, Color.DARK_GREEN);
				else if (rgb == 0xC0FfFF)
					c.set(x, y, Color.LIGHT_CYAN);
				else if (rgb == 0x00FFFF) 
					c.set(x, y, Color.CYAN);
				else if (rgb == 0x00C0C0)
					c.set(x, y, Color.DARK_CYAN);
				else if (rgb == 0xC0C0FF)
					c.set(x, y, Color.LIGHT_BLUE);
				else if (rgb == 0x0000FF)
					c.set(x, y, Color.BLUE);
				else if (rgb == 0x0000C0)
					c.set(x, y, Color.DARK_BLUE);
				else if (rgb == 0xFFC0FF)
					c.set(x, y, Color.LIGHT_MAGENTA);
				else if (rgb == 0xFF00FF)
					c.set(x, y, Color.MAGENTA);
				else if (rgb == 0xC000C0)
					c.set(x, y, Color.DARK_MAGENTA);
				else if (rgb == 0xFFFFFF)
					c.set(x, y, Color.WHITE);
				else if (rgb == 0x000000)
					c.set(x, y, Color.BLACK);
				else {
					System.err.printf("Unrecognized Color %x%n", rgb);
					c.set(x, y, Color.WHITE);
				}
				
			}
		return c;
	}
	
	public void saveToFile(String filename) {
		// TODO Save canvas to file
	}
	
	public void exportToPNG(String filename) {
		// TODO exportToPNG
		
	}
	
	/**
	 * import from a string where color indexes are separated by spaces
	 * @param s
	 */
	public void importFromIndexString(String s) {
		String[] splits = s.split(" ");
		int index = 0;
		for (int y = 0; y < width; y ++) {
			for (int x = 0; x < height; x ++) {
				if (index == splits.length)
					return;
				canvas[x][y] = Colors.get(Integer.parseInt(splits[index]));
				index ++;
			}
		}
	}
	
	public String exportToIndexString() {
		String ret = "";
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x ++)
				ret += canvas[x][y].getIndex() + " ";
		return ret.trim();
	}
	
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int y = 0; y < width; y ++) {
			for (int x = 0; x < height; x ++) {
				ret.append(String.format("%2d ", canvas[x][y].getIndex()));
			}
			ret.append(String.format("%n"));
		}
		return ret.toString();
	}
	
	public String toReadableString() {
		StringBuilder ret = new StringBuilder();
		for (int y = 0; y < width; y ++) {
			for (int x = 0; x < height; x ++) {
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
