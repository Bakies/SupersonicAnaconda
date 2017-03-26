package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CanvasPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel[][] panels;
	private Border[][] borders; 
	private Border border;
	public CanvasPanel(){
		super(new GridLayout(10,10));
		placePanels(10,10);
	}
	public CanvasPanel(int width, int height){
		super(new GridLayout(width, height));
		placePanels(width, height);
	}
	public CanvasPanel(GridLayout gl){
		super(gl);
		placePanels(gl.getColumns(), gl.getRows());
	}

	public void placePanels(int width, int height){
		panels = new JPanel[width][height];		
		border = BorderFactory.createLineBorder(Color.black);

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++){
				panels[i][j] = new CodelPanel(i, j);
				this.add(panels[i][j]);
			}
		}
	}
	public void changePanelColor(int posX, int posY, Color c){
		panels[posX][posY].setBackground(c);
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
