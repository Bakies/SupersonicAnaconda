package es.baki.mitchnpals.supersonicanaconda;

import java.util.ArrayList;
import java.util.Collections;

public class Stack {
	private ArrayList<Integer> stack = new ArrayList<Integer>();
	
	/**
	 * push: Pushes the value of the colour block just exited on to the stack. Note that values of colour blocks are not automatically pushed on to the stack - this push operation must be explicitly carried out
	 * @param x
	 */
	public void push(int x) {
		stack.add(new Integer(x));
	}
	
	/**
	 * Pops the top value off the stack and discards it.
	 * @return
	 */
	public int pop() {
		return stack.remove(0);
		
	}
	
	/**
	 * Pops the top two values off the stack, calculates the integer division of the
	 * second top value by the top value, and pushes the result back on the stack. If 
	 * a divide by zero occurs, it is handled as an implementation-dependent error,
	 *  though simply ignoring the command is recommended.
	 *  
	 *  returns false if a div by 0 occurs
	 */
	public boolean divide(){
		int x = pop();
		int y = pop();
		if (x == 0) {
			return false;
		}
		push(y / x);
		return true;
	}
	
	/** 
	 *  Pops the top two values off the stack,
	 *   adds them, and pushes the result back on the stack.
	 */
	public void add() {
		int x = pop();
		int y = pop();
		push(x + y);
	}
	
	/**
	 *  Pops the top two values off the stack,
	 *  calculates the second top value minus the top value, 
	 *  and pushes the result back on the stack.
	 */
	public void subtract() {
		int x = pop();
		int y = pop();
		push(y - x);
	}
	
	/**
	 * Pops the top two values off the stack, multiplies them, and pushes the result back on the stack.
	 */
	public void multiply() {
		int x = pop();
		int y = pop();
		push (x * y);		
	}

	/**
	 * Pops the top two values off the stack, calculates the second top value modulo the top value, and pushes the result back on the stack. The result has the same sign as the divisor (the top value). If the top value is zero, this is a divide by zero error, which is handled as an implementation-dependent error, though simply ignoring the command is recommended.
	 */
	public void mod() {
		int x = pop();
		int y = pop();
		push(y / x);
		
	}
	
	/**
	 * Replaces the top value of the stack with 0 if it is non-zero, and 1 if it is zero.
	 */
	public void not() {
		int x = pop();
		if (x != 0) { 
			push(0);
		} else { 
			push(1);
		}
	}
	/**
	 * Pops the top two values off the stack,
	 *  and pushes 1 on to the stack if the second 
	 *  top value is greater than the top value,
	 *   and pushes 0 if it is not greater.
	 */
	public void greater() {
		int x = pop();
		int y = pop();
		if (y > x) {
			push(1);
		} else {
			push(0);
		}
	}
	/**
	 * Returns pop()
	 * @return
	 */
	public int pointer() {
		return pop();
		
	}
	
	/**
	 * returns pop()
	 * @return
	 */
	public int switch_() {
		return pop();
	}
	
	/**
	 * Pushes a copy of the top value on the stack on to the stack.
	 */
	public void duplicate() {
		int x = pop();
		push(x);
		push(x);
	}
	
	/**
	 * Pops the top two values off the stack and "rolls" the remaining stack
	 * entries to a depth equal to the second value popped, by a number of
	 * rolls equal to the first value popped. A single roll to depth n is
	 * defined as burying the top value on the stack n deep and bringing all
	 * values above it up by 1 place. A negative number of rolls rolls in
	 * the opposite direction. A negative depth is an error and the command
	 * is ignored. If a roll is greater than an implementation-dependent maximum
	 * stack depth, it is handled as an implementation-dependent error, though
	 * simply ignoring the command is recommended.
	 */
	public void roll() {
		int depth = pop();
		int num = pop();
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for (int x = 0; x < num; x ++){
			Integer rm = stack.remove(0);
			temp.add(rm);
		}
		
		Collections.rotate(temp, -depth);
		stack.addAll(0, temp);
	}
	/**
	 * Adds x to the stack
	 * @param x
	 */
	public void in(int x) {
		stack.add(0, new Integer(x));
	}
	
	/**
	 * returns pop();
	 * @return
	 */
	public int out() {
		return pop();
	}
	
	public String toString() {
		String ret = "";
		
		ArrayList<Integer> temp = new ArrayList<>(stack);
		Collections.reverse(temp);
		
		for (int x : temp) {
			ret += x + ", ";
		}
		
		if (ret.equals("")) {
			return ret;
		} else {
			return ret.substring(0, ret.length() - 2);
		}
	}
	
	public static void main(String...strings) {
		Stack stack = new Stack();
		stack.in(5);
		stack.in(10);
		stack.in(15);
		System.out.println(stack);
		stack.in(3);
		stack.in(1);
		System.out.println(stack);
		stack.roll();
		System.out.println(stack);
		
		
	}
	
}
