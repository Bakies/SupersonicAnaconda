package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import es.baki.mitchnpals.supersonicanaconda.Color;

public class CanvasPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel[][] panels;
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
		panels = new JPanel[width][height];		

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++){
				panels[i][j] = new CodelPanel(i, j);
				this.add(panels[i][j]);
			}
		}
	}
	public void changePanelColor(int posX, int posY, java.awt.Color c){
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
			this.setBackground(Color.white);
			
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
		public void mouseClicked(MouseEvent e) {
			this.setBackground(f.getSelectedColor());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.yellow));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
