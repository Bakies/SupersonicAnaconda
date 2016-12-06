package es.baki.mitchnpals.supersonicanaconda;
import java.util.ArrayList;


public class Interpreter {	
	private ArrayList<Codel> checked;
	private Codel codel;
	private Stack stack;
	private Canvas canvas;
	private int x = 1, y = 0, currentHue, deltaHue, currentDarkness, deltaDarkness, posX = 0, posY = 0, op, wait = 0;
	private int cc = 0, dp = 0;//dp right = 0, d= 1, l = 2, up = 3
							   //cc 0 is left 1 is right
	
	public Interpreter(Canvas canvas) {
		stack = new Stack();
		this.canvas = canvas;
	}
	
	public void run() {
		while(step());
	}
	
	public boolean step() {
		checked = new ArrayList<Codel>();
		codel = Codel.getFarthest(dp, cc, checked);
		//posX = codel.getX();
		//posY = codel.getY();
		
		if(canvas.getColor(posX + x, posY + y) == null || canvas.getColor(posX + x, posY + y) == Color.BLACK){
			op = 0;
			wait++;
			if(wait % 2 == 1)
				changeCC(1);
			if(wait%2 == 0)
				changeDirection(1);
			if(wait == 8)
				return false;
		}
		deltaHue = Colors.getHueDifference(canvas.getColor(posX,posY), canvas.getColor(posX + x, posY + y));
		deltaDarkness = Colors.getDarknessDifference(canvas.getColor(posX,posY), canvas.getColor(posX + x, posY + y));		
		op = deltaDarkness + deltaHue *10;
		
		if(op == 1){
			stack.push(checked.size());
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
			changeCC(stack.switch_());
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
			if(dp == 0){
				directionDown();
			}else if(dp == 1){
				directionLeft();
			}else if(dp == 2){
				directionUp();
			}else if(dp == 3){
				directionRight();
			}
		}
	}
	
	public void changeCC(int x){
		x = x %2;
		if(x == 1){
			if(cc == 1){
				cc = 0;
			}else{
				cc = 1;
			}
		}
	}
	public void directionRight(){
		x = 1;
		y = 0;
		dp = 0;
	}
	public void directionDown(){
		x = 0;
		y = 1;
		dp = 1;
	}
	public void directionLeft(){
		x = -1;
		y = 0;
		dp = 2;
	}
	public void directionUp(){
		x = 0;
		y = -1;
		dp = 3;
	}
	
	public int checkSurrounding(int x, int y){
		codel = new Codel(x,y,canvas.getColor(x,y));
		checked.add(codel);
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
