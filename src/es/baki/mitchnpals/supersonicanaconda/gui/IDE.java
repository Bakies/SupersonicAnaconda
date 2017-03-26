package es.baki.mitchnpals.supersonicanaconda.gui;

import es.baki.mitchnpals.supersonicanaconda.*;

public class IDE {
	private Frame frame; 
	private Interpreter i;
	
	public IDE(int height, int width ) {
		frame = new Frame(this, height, width);
		Canvas canvas = frame.getCanvas();
		i = new Interpreter(canvas, System.in);
		
	}
	public IDE() {
		this(10,10);
	}
	
	public static void main(String[] args) {
		IDE ide = new IDE(10, 10);
	}
	public void stepInterpreter() {
		try {
			i.step();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stopInterpreter() {
		// TODO Auto-generated method stub
		
	}
	public void runInterpreter() {
		// TODO Auto-generated method stub
		
	}

}
