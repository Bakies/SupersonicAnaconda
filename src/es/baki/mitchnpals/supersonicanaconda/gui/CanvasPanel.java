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
	
	private CodelPanel[][] panels;
	private Frame f;
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
		panels = new CodelPanel[width][height];		

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++){
				panels[i][j] = new CodelPanel(i, j);
				this.add(panels[i][j]);
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
			
			for (CodelPanel[] x : panels)
				for (CodelPanel y : x)
					y.setBorderColor(Color.BLACK);
			if (mouseDown) {
				for (int x = Math.min(mouseDownXCoord, codelX); x <= Math.max(mouseDownXCoord, codelX); x++) {
					for (int y = Math.min(mouseDownYCoord, codelY); y <= Math.max(mouseDownYCoord, codelY); y ++) {
						panels[x][y].setBorderColor(Color.blue);
					}
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mouseDownXCoord = codelX;
			mouseDownYCoord = codelY;
			mouseDown = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (CodelPanel[] x : panels)
				for (CodelPanel y : x)
					y.setBorderColor(Color.BLACK);
			for (int x = Math.min(mouseDownXCoord, mouseXCoord); x <= Math.max(mouseDownXCoord, mouseXCoord); x++) {
				for (int y = Math.min(mouseDownYCoord, mouseXCoord); y <= Math.max(mouseDownYCoord, mouseYCoord); y ++) {
					panels[x][y].setBackground(f.getSelectedColor());
				}
			}
			mouseDown = false;
			
		}
		
	}
}
