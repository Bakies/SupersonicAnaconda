package es.baki.mitchnpals.supersonicanaconda;
import java.util.ArrayList;


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
	
	
	private static ArrayList<String> checked;
	private Stack stack;
	private Canvas canvas;
	private int x = 1, y = 0, currentHue, deltaHue, currentDarkness, deltaDarkness, posX = 0, posY = 0, op;
	
	public Interpreter(Canvas canvas) {
		stack = new Stack();
		this.canvas = canvas;
	}
	
	public void run() {
		while(step());
	}
	
	public boolean step() {
		
		/*
		 * change Color class to have a getDeltaHue and getDeltaDarkness
		 * but make jon do it
		 * because he's a piece of shit
		 */
		deltaHue = canvas.getColor(posX,posY).getHue() - canvas.getColor(posX + x, posY + y).getHue();

		deltaDarkness = canvas.getColor(posX,posY).getDarkness() - canvas.getColor(posX + x, posY + y).getDarkness();
		
		op = deltaDarkness + deltaHue *10;
		checked = new ArrayList<String>();
		if(op == 1){
			stack.push(5);
		}else if(op == 2){
			stack.pop();
		}else if(op == 10){
			stack.add();
		}else if(op == 11){
			stack.subtract();	
		}else if(op == 12){
			stack.multiply();
		}else if(op == 20){
			stack.divide();
		}else if(op == 21){
			stack.mod();
		}else if(op == 22){
			stack.not();
		}else if(op == 30){
			stack.greater();
		}else if(op == 31){
			stack.pointer();
		}else if(op == 32){
			stack.switch_();
		}else if(op == 40){
			stack.duplicate();
		}else if(op == 41){
			stack.roll();
		}else if(op == 42){
			stack.in(5);
		}else if(op == 50){
			/*
			 * make this stack.in(char);
			 */
			stack.in(3);
		}else if(op == 51){
			stack.out();
		}else if(op == 52){
			/*
			 * stack.out()return char
			 */
			stack.out();
		}
		
		
		return true;
	}
	public int checkSurrounding(int x, int y){
		int total = 1;
		if(canvas.getColor(x, y).equals(canvas.getColor(x, y+1)))
				total+= checkSurrounding(x,y+1);
		if(canvas.getColor(x, y).equals(canvas.getColor(x, y-1)))
				total+= checkSurrounding(x,y-1);
		if(canvas.getColor(x, y).equals(canvas.getColor(x+1, y)))
				total+= checkSurrounding(x+1,y);
		if(canvas.getColor(x, y).equals(canvas.getColor(x-1, y)))
				total+= checkSurrounding(x-1,y);
		
		return total;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
