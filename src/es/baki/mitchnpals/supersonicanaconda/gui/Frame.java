package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.MenuBar;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private MenuBar menubar;

	public Frame() {
		super("Supersonic Anaconda");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		this.setMenuBar(menubar);
	}

}
