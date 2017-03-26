package es.baki.mitchnpals.supersonicanaconda.gui;

import es.baki.mitchnpals.supersonicanaconda.Canvas;
import es.baki.mitchnpals.supersonicanaconda.Interpreter;

public class IDE {
	private Frame frame; 
	public Interpreter i;
	public boolean run = true;
	private boolean running = false;
	private boolean firstRun = true;
	
	public IDE(int height, int width ) {
		frame = new Frame(this);
		
	}
	public IDE() {
		this(10,10);
	}
	
	public static void main(String[] args) {
		new IDE(10, 10);
	}
	public void stepInterpreter() {
		try {
			//i.step();
			if(firstRun){
				Canvas canvas = frame.getCanvas();
				i = new Interpreter(canvas, System.in);
				firstRun = false;
			}
			//System.out.println("Step");
			i.step();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void stopInterpreter() {
		run = false;
		running = false;
		firstRun = true;
		//System.out.println("Stop");
		
	}
	public void runInterpreter() {
		run = true;
		if(firstRun){
			Canvas canvas = frame.getCanvas();
			i = new Interpreter(canvas, System.in);
			firstRun = false;
		}
		if(!running){
			RunnableIDE r = new RunnableIDE(this, i);
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
	Interpreter i;
	RunnableIDE(IDE ide, Interpreter i){
		this.ide = ide;
		this.i = i;
	}
	public void start() {
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		try{
			while(ide.run && i.step());
				//System.out.println("run");
			
		}catch (Exception e){
			// TODO idk man its up to you
		}
		
		
	}
	
}
