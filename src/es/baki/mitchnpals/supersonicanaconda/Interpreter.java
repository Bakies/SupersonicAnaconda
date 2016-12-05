package es.baki.mitchnpals.supersonicanaconda;

public class Interpreter {
	public static Colors LIGHT_RED = new Colors(0,0);
	public static Colors RED= new Colors(0,1);
	public static Colors DARK_RED = new Colors(0,2);
	public static Colors LIGHT_YELLOW = new Colors(1,0);
	public static Colors YELLOW = new Colors(1,1);
	public static Colors DARK_YELLOW = new Colors(1,2);
	public static Colors LIGHT_GREEN = new Colors(2,0);
	public static Colors GREEN= new Colors(2,1);
	public static Colors DARK_GREEN= new Colors(2,2);
	public static Colors LIGHT_CYAN= new Colors(3,0);
	public static Colors CYAN= new Colors(3,1);
	public static Colors DARK_CYAN= new Colors(3,2);
	public static Colors LIGHT_BLUE= new Colors(4,0);
	public static Colors BLUE= new Colors(4,1);
	public static Colors DARK_BLUE= new Colors(4,2);
	public static Colors LIGHT_MAGENTA= new Colors(5,0);
	public static Colors MAGENTA= new Colors(5,1);
	public static Colors DARK_MAGENTA= new Colors(5,2);
	
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
