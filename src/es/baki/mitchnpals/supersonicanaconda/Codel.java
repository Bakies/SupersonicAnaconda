package es.baki.mitchnpals.supersonicanaconda;

import java.util.ArrayList;

public class Codel {
	private int posX, posY;
	private Color color;

	public Codel(int x, int y, Color c){
		posX = x;
		posY = y;
		color = c;
	}

	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public static Codel getFarthest(int dp, int cc, ArrayList<Codel> codels){
		if (dp < 0 || dp > 3 || cc < 0 || cc > 1)
			return null;
		Codel cl = null;
		Codel cr = null;
		if (codels.size() == 0) {
			return null;
		}
		if (codels.get(0).color == Color.WHITE)
			return null;
		if (codels.size() == 1) {
			return codels.get(0);
		}
		int maxX = 0, maxY = 0;
		int minX = codels.get(0).posX;
		int minY = codels.get(0).posY;
		for (Codel c : codels) {
			maxX = Math.max(c.posX, maxX);
			maxY = Math.max(c.posY, maxY);

			minX = Math.min(c.posX, minX);
			minY = Math.min(c.posY, minY);
		}
		if (dp == 0) {
			for (Codel c : codels) {
				if(c.posX == maxX){
					if (c.posY == maxY) {
						cr = c;
					}
					if (c.posY == minY){
						cl = c;
					}
				}
			}
		} else if (dp == 1) {
			for (Codel c : codels) 
				if (c.posY == maxY) {
					if (c.posX == maxX) {
						cl = c;
					}
					if (c.posX == minX) {
						cr = c;
					}
				}

		} else if (dp == 2) {
			for (Codel c : codels)
				if(c.posX == minX) {
					if (c.posY == maxY) {
						cl = c;
					}
					if (c.posY == minY){
						cr = c;
					}
				}
		} else if (dp == 3) {
			for (Codel c : codels) 
				if (c.posY == minY)  {
					if (c.posX == maxX) {
						cr = c;
					} 
					if (c.posX == minX) {
						cl = c;
					}
				}
		} else { 
			return null;
		}

		return cc == 0 ? cl : cr;
	}

	public boolean inList(ArrayList<Codel> list) {
		for (Codel c : list)
			if (c.equals(this))
				return true;
		return false;
	}
	public boolean equals(Codel c) {
		return c.posX == posX && c.posY == posY;
	}

	public Color getColor() {
		return this.color;
	}

}
