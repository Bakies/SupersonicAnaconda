package es.baki.mitchnpals.supersonicanaconda.gui;

import es.baki.mitchnpals.supersonicanaconda.Interpreter;

public class IDE {
	private Frame frame; 
	private Interpreter i;
	
	public IDE(int height, int width ) {
		frame = new Frame(this, height, width);
		Canvas canvas = frame.getCanvas();
		i = new Interpreter(frame, System.in);
		
	}
	public IDE() {
		this(10,10);
	}
	
	public static void main(String[] args) {
		IDE ide = new IDE(10, 10);
	}
	public void stepInterpreter() {
		
		
	}
	public void stopInterpreter() {
		// TODO Auto-generated method stub
		
	}
	public void runInterpreter() {
		// TODO Auto-generated method stub
		
	}

}
