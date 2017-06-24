package es.baki.mitchnpals.supersonicanaconda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Canvas {
	private int height, width; 
	private Color[][] canvas;

	public Canvas(int width, int height, Canvas canvas) {
		this(width, height);
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
	public void set(int x, int y, Color c){
		canvas[x][y] = c;
	}

	public void set(int x, int y, Enum<Color> color) {
		if (color == null)
			color = Color.WHITE;
		canvas[x][y] = (Color) color;
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

	public static Canvas readFromFile(String filename) {
		File file = new File(filename);
		return readFromFile(file);
	}
	public static Canvas readFromFile(File file) {
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
			for (int x = 0; x < w; x ++)
				c.set(x, y, Color.getColorFromRGBInt(image.getRGB(x, y)));

		return c;
	}


	public void saveToPietFile(String filename) {
		// TODO Save canvas to file
	}

	
	public void exportToPNG(File file) {
		if (!file.exists()) {
			System.out.println("Making new file");
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to make image: " + e.getMessage());
				return; 
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < height; y ++)
			for (int x = 0; x < width; x ++)
				image.setRGB(x, y, Color.getRGB(canvas[x][y]));
		
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			System.err.println("Failed to write image: " + e.getMessage());
		}
	}
	public void exportToPNG(String filename) {
		File file = new File(filename);
		exportToPNG(file);
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

	@Override
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

	public java.awt.Color getAwtColor(int x, int y) {
		return new java.awt.Color(Color.getRGB(getColor(x, y)));
	}
	public static void main(String...strings) {
		Canvas c = readFromFile("hello2.png");
		System.out.println(c);
		System.out.println(c.toReadableString());
	}

	@Override
	public Canvas clone() {
		Canvas c = new Canvas(width, height);
		for (int x = 0; x < width; x ++)  {
			for (int y = 0; y < height; y ++) {
				c.set(x, y, this.getColor(x, y));
			}
		}
		return c;
	}


}
