package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;

public class CanvasPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private boolean mouseDown = false;
	private int mouseDownXCoord = 0, mouseDownYCoord = 0; 
	private int mouseXCoord = 0, mouseYCoord = 0;
	private int selectedX = 0, selectedY = 0;
	private int mouseButton = -1;

	private int height, width;

	private CodelPanel[][] panels, undo;
	private Frame f;

	public void setCodelGridSize(int x, int y) {
		placePanels(x, y);
		this.height = x; 
		this.width = y;
	}

	public int getCodelGridHeight() {
		return height;
	}

	public int getCodelGridWidth() {
		return width;
	}

	public CanvasPanel(Frame f){
		super(new GridLayout(10,10));
		placePanels(10,10);
		this.f = f;

	}
	public CanvasPanel(int width, int height, Frame f){
		super(new GridLayout(width, height));
		placePanels(width, height);
		this.f = f;
	}
	public CanvasPanel(GridLayout gl, Frame f){
		super(gl);
		placePanels(gl.getColumns(), gl.getRows());
		this.f = f;
	}

	public void placePanels(int width, int height){
		while (this.getComponentCount() > 0){
			this.remove(0);
		}
		this.setLayout(new GridLayout(width, height));

		panels = new CodelPanel[width][height];		


		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++){
				// J and I are backwards because we are converting to gui which origins in the top left
				panels[j][i] = new CodelPanel(j, i);
				this.add(panels[j][i]);
			}
		}
	}
	public void setPanelColor(int posX, int posY, Color c){
		panels[posX][posY].setBackground(c);
	}

	public java.awt.Color getColorAt(int x, int y) {
		return panels[x][y].getBackground();
	}

	@SuppressWarnings("serial")
	public class CodelPanel extends JPanel implements MouseListener{
		private int codelY, codelX;
		private boolean selected = false;

		public void unselect() { 
			selected = false;
			this.setBorderColor(Color.BLACK);
		}

		public void select() {
			selected = true;
			this.setBorderColor(Color.BLUE);
		}

		public CodelPanel(int x, int y) { 
			super();

			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.setBackground(java.awt.Color.white);

			this.addMouseListener(this);

			this.codelX = x;
			this.codelY = y;

		}

		public int getCodelY() { 
			return this.codelY;
		}
		public int getCodelX() {
			return this.codelX;
		}

		public void setBackground(Color bg) { 
			super.setBackground(bg);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			this.setBackground(f.getSelectedColor());
		}

		public void setBorderColor(Color borderColor) {
			super.setBorder(BorderFactory.createLineBorder(borderColor));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.yellow));

			mouseXCoord = codelX;
			mouseYCoord = codelY;
			
			System.out.println("X: " + mouseXCoord + ", Y: " + mouseYCoord);
			
			if (mouseDown)
				for (CodelPanel[] x : panels) {
					for (CodelPanel p : x) {
						p.unselect();
					}
				}
			if (mouseDown) {
				for (int x = Math.min(mouseDownXCoord, codelX); x <= Math.max(mouseDownXCoord, codelX); x++) {
					for (int y = Math.min(mouseDownYCoord, codelY); y <= Math.max(mouseDownYCoord, codelY); y ++) {
						panels[x][y].select();
					}
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (selected) {
				this.setBorderColor(Color.BLUE);
			} else {
				this.setBorderColor(Color.BLACK);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (mouseDown) {
				// second mouse button? ignore?
				return;
			}

			for (CodelPanel[] x : panels) {
				for (CodelPanel p : x) {
					p.unselect();
				}
			}

			mouseDownXCoord = codelX;
			mouseDownYCoord = codelY;
			mouseDown = true;
			mouseButton = e.getButton();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int xSize = Math.max(1, Math.max(mouseDownXCoord, mouseXCoord) - Math.min(mouseDownXCoord, mouseXCoord));
			int ySize = Math.max(1, Math.max(mouseDownYCoord, mouseYCoord) - Math.min(mouseDownYCoord, mouseYCoord));
			System.out.println("Selection size: " + xSize + " " + ySize);
			undo = new CodelPanel[ySize][xSize];
			
			selectedX = Math.min(mouseDownXCoord, mouseXCoord);
			selectedY = Math.min(mouseDownYCoord, mouseYCoord);
			
			for (int x = Math.min(mouseDownXCoord, mouseXCoord); x <= Math.max(mouseDownXCoord, mouseXCoord); x++) {
				for (int y = Math.min(mouseDownYCoord, mouseYCoord); y <= Math.max(mouseDownYCoord, mouseYCoord); y ++) {
					undo[x - Math.min(mouseDownXCoord, mouseXCoord)][y - Math.min(mouseDownYCoord, mouseYCoord)] = panels[x][y];
					panels[x][y] = new CodelPanel(x, y);
					panels[x][y].select();
					panels[x][y].setBackground(f.getSelectedColor());
				}
			}
			mouseDown = false;

		}

	}

	public void undo() {
		for (int x = selectedX; x < undo.length + selectedX; x ++) {
			for (int y = selectedY; y < undo[0].length + selectedY; y ++) {
				panels[x][y] = undo[x - selectedX][y - selectedY];
			}
		}
	}
}
