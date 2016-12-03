package es.baki.mitchnpals.supersonicanaconda;

import java.util.ArrayList;

public class Stack {
	private ArrayList<Integer> stack = new ArrayList<Integer>();
	
	public Stack () {
		
	}
	
	/**
	 * push: Pushes the value of the colour block just exited on to the stack. Note that values of colour blocks are not automatically pushed on to the stack - this push operation must be explicitly carried out
	 * @param x
	 */
	public void push(int x) {
		stack.add(new Integer(x));
	}
	
	public int pop() {
		int x = stack.get(0);
		stack.remove(0);
		return x;
		
	}
	
	public void add() {
		
	}
	
	public void subtract() {
		
	}
	
	public void multiply() {
		
	}

	public void mod() {
		
	}
	
	public void not() {
		
	}
	public void greater() {
		
	}
	public int pointer() {
		return pop();
		
	}
	public int switch_() {
		return pop();
	}
	
	public void duplicate() {
		
	}
	
	public void roll() {
		
	}
	public void in(int x) {
		
	}
	public int out() {
		return pop();
	}
	
	
}
