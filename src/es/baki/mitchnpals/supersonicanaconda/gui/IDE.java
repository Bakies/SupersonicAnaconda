package es.baki.mitchnpals.supersonicanaconda.gui;

import java.util.Stack;

import javax.swing.JOptionPane;

import es.baki.mitchnpals.supersonicanaconda.Canvas;
import es.baki.mitchnpals.supersonicanaconda.Interpreter;

public class IDE {
	private static IDE ide;

	private Frame frame;
	public Interpreter i;
	public boolean run = true;
	private boolean running = false;
	private boolean firstRun = true;
	private Tool currTool = Tool.fill;
	private Stack<Canvas> history, unhistory;

	public enum Tool {
		fill, pencil;
	}

	private IDE(int height, int width) {
		ide = this;
		frame = new Frame();
		history = new Stack<Canvas>();
		unhistory = new Stack<Canvas>();
	}

	public static IDE getIDE() {
		return ide;
	}

	public static void main(String[] args) {
		new IDE(10, 10);
	}

	public void stepInterpreter() {
		try {
			// i.step();
			if (firstRun) {
				Canvas canvas = frame.getCanvas();
				i = new Interpreter(canvas, System.in);
				firstRun = false;
			}
			// System.out.println("Step");
			i.step();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.frame, "The interpreter encountered an error");
		}
	}

	public void stopInterpreter() {
		run = false;
		running = false;
		firstRun = true;
		// System.out.println("Stop");

	}

	public void runInterpreter() {
		run = true;
		if (firstRun) {
			Canvas canvas = frame.getCanvas();
			i = new Interpreter(canvas, System.in);
			firstRun = false;
		}
		if (!running) {
			RunnableIDE r = new RunnableIDE(this, i);
			r.start();
		}
	}

	public Frame getFrame() {
		return this.frame;
	}

	/**
	 * Returns this currently selected tool
	 * 
	 * @return the current tool
	 */
	public Tool getTool() {
		return currTool;
	}

	public void setTool(Tool currToo) {
		this.currTool = currToo;
		this.frame.toolChange();
	}

	/**
	 * Clears both the redo and undo stacks
	 */
	public void clearHistory() {
		history.clear();
		unhistory.clear();
	}

	/**
	 * Adds the current frame's canvas to the history
	 */
	public void snapHistory() {
		history.add(this.frame.getCanvas());
		unhistory.clear();
	}

	/**
	 * Adds the current frames canvas to unhistory and sets the frames canvas to
	 * the top of the history stack
	 */
	public void undo() { 
		if (history.size() == 0) {
			System.out.println("Cannot undo 0 length history");
			return;
		}
		unhistory.push(frame.getCanvas());
		Canvas c = history.pop();
		frame.makeNewCanvas(c);
	}

	/**
	 * Adds the current frames canvas to history and sets the frames canvas to
	 * the top of the unhistory stack
	 */
	public void redo() {
		System.out.println("Redoing");
		if (unhistory.size() == 0) {
			System.out.println("Cannot undo 0 length unhistory");
			return;
		}
		history.push(frame.getCanvas());
		Canvas c = unhistory.pop();
		frame.makeNewCanvas(c);
	}
}

class RunnableIDE implements Runnable {
	private Thread t;
	IDE ide;
	Interpreter i;

	RunnableIDE(IDE ide, Interpreter i) {
		this.ide = ide;
		this.i = i;
	}

	public void start() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			while (ide.run && i.step())
				;
			// System.out.println("run");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(ide.getFrame(), "The interpreter ran into an error and had to stop");
		}

	}

}
