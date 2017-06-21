package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private boolean mouseDown = false;
	private int mouseDownXCoord = 0, mouseDownYCoord = 0; 
	private int mouseXCoord = 0, mouseYCoord = 0;
	private int selectedX = 0, selectedY = 0;
	private int mouseButton = -1;

	private int height, width;

	private CodelPanel[][] panels;
	private Frame f;

	public void setCodelGridSize(int width, int height) {
		this.height = height;
		this.width = width;
		placePanels(height, width);
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
		this.width = this.height = 10;
		this.f = f;

	}
	public CanvasPanel(int width, int height, Frame f){
		super(new GridLayout(width, height));
		placePanels(width, height);
		this.height = height;
		this.width = width;
		this.f = f;
	}
	public CanvasPanel(GridLayout gl, Frame f){
		super(gl);
		placePanels(gl.getColumns(), gl.getRows());
		this.width = gl.getColumns();
		this.height = gl.getRows();
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

	public int getCanvasHeight() {
		return height;
	}

	public int getCanvasWidth() {
		return width;
	}

	public void setPanelColor(int posX, int posY, Color c){
		panels[posX][posY].setBackground(c);
	}

	public java.awt.Color getColorAt(int x, int y) {
		return panels[x][y].getBackground();
	}

	@Override
	@Deprecated // This is to make sure we use getCanvasHeight
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	@Deprecated // This is to make sure we use getCanvasWidth
	public int getWidth() {
		return super.getWidth();
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

		@Override
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
				this.setBorderColor(Color.BLUE); // TODO Change the color
				// as one of the codel
				// colors and doesnt look
				// good
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
			switch (IDE.getIDE().getTool()) {
			case fill:
				IDE.getIDE().snapHistory();
				int maxX, maxY, minX, minY;
				maxX = Math.max(mouseDownXCoord, mouseXCoord);
				maxY = Math.max(mouseDownYCoord, mouseYCoord);

				minX = Math.min(mouseDownXCoord, mouseXCoord);
				minY = Math.min(mouseDownYCoord, mouseYCoord);

				int xSize = maxX - minX + 1;
				int ySize = minX - minY + 1;
				System.out.println("Selection size: " + xSize + " " + ySize);

				for (int x = minX; x <= maxX; x++) {
					for (int y = minY; y <= maxY; y++) {
						panels[x][y].unselect();
						panels[x][y].setBackground(f.getSelectedColor());
					}
				}
				mouseDown = false;
				break;
			}
		}
	}
}
