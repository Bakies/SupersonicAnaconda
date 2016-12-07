package es.baki.mitchnpals.supersonicanaconda;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Interpreter {	
	private ArrayList<Codel> checked;
	private Codel codel;
	private Stack stack;
	private Canvas canvas;
	private int x = 1, y = 0, deltaHue, deltaDarkness, posX = 0, posY = 0, op, wait = 0;
	private int cc = 0, dp = 0;//dp right = 0, d= 1, l = 2, up = 3
	//cc 0 is left 1 is right
	private Scanner scan;

	public Interpreter(Canvas canvas, InputStream is) {
		scan = new Scanner(is);
		stack = new Stack();
		this.canvas = canvas;
	}

	public void run() {
		try {
			while(step()){
				System.out.println(stack);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Skipped an OP");
			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			run();
		}
	}

	public boolean step() throws Exception {
		checkSurrounding(posX, posY);
		codel = Codel.getFarthest(dp, cc, checked);
		if (codel != null) {
			posX = codel.getX();
			posY = codel.getY();
		}

		if(canvas.getColor(posX + x, posY + y) == null || canvas.getColor(posX + x, posY + y) == Color.BLACK){
			op = 0;
			wait++;
			System.out.println("wait");
			changeCC(1);
			if(wait%2 == 0)
				changeDirection(1);
			if(wait == 8)
				return false;
		} else if (canvas.getColor(posX, posY) == Color.WHITE) { 
			op = 0;
			posX += x;
			posY += y;				
		} else {
			deltaHue = canvas.getColor(posX,posY).getHueDifference(canvas.getColor(posX + x, posY + y));
			deltaDarkness = canvas.getColor(posX,posY).getDarknessDifference(canvas.getColor(posX + x, posY + y));		
			op = deltaDarkness + deltaHue *10;
			posX += x;
			posY += y;
		}
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
			System.out.print("Input: ");
			stack.in(scan.nextInt());
		}else if(op == 50){
			// TODO input char
			stack.in(3);
		}else if(op == 51){
			System.out.println(stack.out());
		}else if(op == 52){
			System.out.println((char) stack.out());
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

	public int checkSurrounding(int x, int y) {
		checked = new ArrayList<Codel>();
		return checkSurroundingHelp(x, y);
	}

	public int checkSurroundingHelp(int x, int y){
		codel = new Codel(x,y,canvas.getColor(x,y));
		checked.add(codel);
		int total = 1;
		if(canvas.getColor(x, y+1) != null && canvas.getColor(x, y).equals(canvas.getColor(x, y+1)) && !(new Codel(x, y+1, Color.BLACK).inList(checked)))
			total+= checkSurroundingHelp(x,y+1);
		if(y !=0 && canvas.getColor(x, y).equals(canvas.getColor(x, y-1))&& !(new Codel(x, y-1, Color.BLACK).inList(checked)))
			total+= checkSurroundingHelp(x,y-1);
		if(canvas.getColor(x+1, y) != null && canvas.getColor(x, y).equals(canvas.getColor(x+1, y)) && !(new Codel(x+1, y, Color.BLACK).inList(checked)))
			total+= checkSurroundingHelp(x+1,y);
		if(x !=0 && canvas.getColor(x, y).equals(canvas.getColor(x-1, y))&& !(new Codel(x-1, y, Color.BLACK).inList(checked)))
			total+= checkSurroundingHelp(x-1,y);

		return total;
	}
	public static void main(String[] args) {
		System.out.println(Color.LIGHT_CYAN.getDarknessDifference(Color.DARK_MAGENTA));
		System.out.println(0 % 4);
		String board = "2 3 8 1 13 16 17 3 1 11 10 0 3 14 1 11 12 12 1 0 3 11 0 0 0 0 0 0 0 2 2 1 0 0 -1 0 -1 0 0 14 0 1 0 -1 8 8 8 -1 0 14 0 3 11 10 -1 -1 -1 0 0 15 0 0 0 3 0 0 0 0 2 16 0 0 2 2 2 0 0 0 3 17 13 18 0 0 0 0 0 0 10 17 18 8 8 7 18 10 5 1 3 9";
		Canvas c = new Canvas(10);
		c.importFromIndexString(board);
		c.set(2, 1, Color.RED);
		c.set(2, 8, Color.WHITE);
		c.set(5, 8, Color.RED);
		System.out.println(c.exportToIndexString());

		System.out.println(c.toReadableString());
		Interpreter i = new Interpreter(c, System.in);
		i.run();
	}

}
