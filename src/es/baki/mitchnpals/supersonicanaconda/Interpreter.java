package es.baki.mitchnpals.supersonicanaconda;

public class Interpreter {
	
	private Stack stack;
	private Canvas canvas;
	private int x = 1, y = 0;
	private int[][] position;
	
	public Interpreter(Canvas canvas) {
		stack = new Stack();
		this.canvas = canvas;
		position = new int[canvas.getWidth()][canvas.getHeight()];
	}
	
	public void run() {
		while(step());
	}
	
	public boolean step() {
		
		
		
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
