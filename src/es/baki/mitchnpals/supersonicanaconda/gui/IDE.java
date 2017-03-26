package es.baki.mitchnpals.supersonicanaconda.gui;

public class IDE {
	private Frame frame; 
	
	public IDE(int height, int width ) {
		frame = new Frame(height, width);
	}
	public IDE() {
		frame = new Frame(10, 10);
	}

	public static void main(String[] args) {
		IDE ide = new IDE(10, 10);
	}

}
