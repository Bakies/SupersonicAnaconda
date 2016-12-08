package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CanvasPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel[][] panels;
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
				panels[i][j] = new JPanel();
				panels[i][j].setBorder(border);
				panels[i][j].setBackground(Color.WHITE);
				this.add(panels[i][j]);
			}
		}
	}
	public void changePanelColor(int posX, int posY, Color c){
		panels[posX][posY].setBackground(c);
	}


}
