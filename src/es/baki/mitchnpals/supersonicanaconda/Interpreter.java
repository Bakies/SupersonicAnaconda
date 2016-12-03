package es.baki.mitchnpals.supersonicanaconda;

public class Interpreter {
	public static Color LIGHT_RED = new Color(0,0);
	public static Color RED= new Color(0,1);
	public static Color DARK_RED = new Color(0,2);
	public static Color LIGHT_YELLOW = new Color(1,0);
	public static Color YELLOW = new Color(1,1);
	public static Color DARK_YELLOW = new Color(1,2);
	public static Color LIGHT_GREEN = new Color(2,0);
	public static Color GREEN= new Color(2,1);
	public static Color DARK_GREEN= new Color(2,2);
	public static Color LIGHT_CYAN= new Color(3,0);
	public static Color CYAN= new Color(3,1);
	public static Color DARK_CYAN= new Color(3,2);
	public static Color LIGHT_BLUE= new Color(4,0);
	public static Color BLUE= new Color(4,1);
	public static Color DARK_BLUE= new Color(4,2);
	public static Color LIGHT_MAGENTA= new Color(5,0);
	public static Color MAGENTA= new Color(5,1);
	public static Color DARK_MAGENTA= new Color(5,2);
	
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
