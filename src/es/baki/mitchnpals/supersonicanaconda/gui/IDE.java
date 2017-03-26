package es.baki.mitchnpals.supersonicanaconda.gui;

public class IDE {
	private Frame frame; 
	
	public IDE(int height, int width ) {
		frame = new Frame(this, height, width);
	}
	public IDE() {
		frame = new Frame(this, 10, 10);
	}
	
	public static void main(String[] args) {
		IDE ide = new IDE(10, 10);
	}
	public void stepInterpreter() {
		// TODO Auto-generated method stub
		
	}
	public void stopInterpreter() {
		// TODO Auto-generated method stub
		
	}
	public void runInterpreter() {
		// TODO Auto-generated method stub
		
	}

}
