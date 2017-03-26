package es.baki.mitchnpals.supersonicanaconda.gui;

import es.baki.mitchnpals.supersonicanaconda.*;

public class IDE {
	private Frame frame; 
	public Interpreter i;
	public boolean run = true;
	private boolean running = false;
	
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
		ide.i.toggleDebug();
	}
	public void stepInterpreter() {
		try {
			//i.step();
			System.out.println("Step");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void stopInterpreter() {
		run = false;
		running = false;
		System.out.println("Stop");
		
	}
	public void runInterpreter() {
		run = true;
		if(!running){
			RunnableIDE r = new RunnableIDE(this);
			r.start();		
		}
	}
	public Frame getFrame() {
		return this.frame;
	}

}

class RunnableIDE implements Runnable{
	private Thread t;
	IDE ide;
	RunnableIDE(IDE ide){
		this.ide = ide;
	}
	public void start() {
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		try{
			while(ide.run)	
				System.out.println("run");
			
		}catch (Exception e){
			// TODO idk man its up to you
		}
		
	}
	
}
