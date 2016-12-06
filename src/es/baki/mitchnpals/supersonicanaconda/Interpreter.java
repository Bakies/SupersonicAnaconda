package es.baki.mitchnpals.supersonicanaconda;
import java.util.ArrayList;


public class Interpreter {	
	private static ArrayList<String> checked;
	private static Stack stack;
	private static Canvas canvas;
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
		deltaHue = Colors.getHueDifference(canvas.getColor(posX,posY), canvas.getColor(posX + x, posY + y));
		deltaDarkness = Colors.getDarknessDifference(canvas.getColor(posX,posY), canvas.getColor(posX + x, posY + y));		
		op = deltaDarkness + deltaHue *10;
		checked = new ArrayList<String>();
		if(op == 1){
			stack.push(checkSurrounding(posX, posY));
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
			changeDirection(stack.pointer());
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
	public void changeDirection(int x){
		x = x % 4;
		for(int i = 0; i < x; i++){
			if(this.x == 1 && this.y == 0){
				directionDown();
			}else if(this.x == 0 && this.y == 1){
				directionLeft();
			}else if(this.x == -1 && this.y == 0){
				directionUp();
			}else if(this.x == 0 && this.y == -1){
				directionRight();
			}
		}
	}
	public void directionRight(){
		x = 1;
		y = 0;
	}
	public void directionDown(){
		x = 0;
		y = 1;
	}
	public void directionLeft(){
		x = -1;
		y = 0;
	}
	public void directionUp(){
		x = 0;
		y = -1;
	}
	public int checkSurrounding(int x, int y){
		checked.add(Integer.toString(x) + "," + Integer.toString(y));
		int total = 1;
		if(canvas.getColor(x, y+1) != null && canvas.getColor(x, y).equals(canvas.getColor(x, y+1)) && !checked.contains(Integer.toString(x) + "," + Integer.toString(y+1)))
				total+= checkSurrounding(x,y+1);
		if(y !=0 && canvas.getColor(x, y).equals(canvas.getColor(x, y-1))&& !checked.contains(Integer.toString(x) + "," + Integer.toString(y-1)))
				total+= checkSurrounding(x,y-1);
		if(canvas.getColor(x+1, y) != null && canvas.getColor(x, y).equals(canvas.getColor(x+1, y))&& !checked.contains(Integer.toString(x+1) + "," + Integer.toString(y)))
				total+= checkSurrounding(x+1,y);
		if(x !=0 && canvas.getColor(x, y).equals(canvas.getColor(x-1, y))&& !checked.contains(Integer.toString(x-1) + "," + Integer.toString(y)))
				total+= checkSurrounding(x-1,y);
		
		return total;
	}
	public static void main(String[] args) {
		
	}

}
