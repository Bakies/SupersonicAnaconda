package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.baki.mitchnpals.supersonicanaconda.Interpreter;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GridBagLayout layoutManager;
	
	// Menu bar stuff
	private MenuBar menubar;
	private Menu fileMenu;
	private MenuItem open, newCanvas, save;
	
	// Panels
	private JPanel ioPanel;
	private JLabel ioTitle;
	private JTextField ioInput;
	private JTextArea ioOutput;
	
	private JPanel debugPanel;
	private JLabel debugTitle; 
	private JButton debugRunButton; 
	private JButton debugStepButton;
	private JButton debugStopButton; 
	
	private JPanel colorsPickerPanel;
	private JLabel colorPickerTitle;
	private JPanel selectedColorPanel;
	private JPanel  lred, red, dred,
					lyel, yel, dyel,
					lgrn, grn, dgrn,
					lcyn, cyn, dcyn,
					lblu, blu, dblu,
					lmag, mag, dmag,
					black, white;
	
	private JPanel toolPickerPanel; 
	private JLabel toolPickerTitle;
	
	private JPanel canvasPanel;

	private IDE ide;
	
	public Frame(IDE ide, int height, int width) {
		super("Supersonic Anaconda");
		this.ide = ide;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layoutManager = new GridBagLayout();
		this.setLayout(layoutManager);
		GridBagConstraints c = new GridBagConstraints();
		
		makeIOPanel();
		c.fill  = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0; 
		this.add(ioPanel, c);
		
		makeDebugPanel();
		c.gridy = 1;
		this.add(debugPanel, c);
		
		makeColorPickerPanel();
		c.gridx = 1;
		c.gridy = 0;
		this.add(colorsPickerPanel, c);
		
		makeToolPickerPicker();
		c.gridx = 1; 
		c.gridy = 1;
		this.add(toolPickerPanel, c);
		
		canvasPanel = new CanvasPanel(height, width);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 5;
		this.add(canvasPanel, c);
		
		makeMenuBar();
		this.setMenuBar(menubar);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void makeToolPickerPicker() {
		toolPickerPanel = new JPanel();
		toolPickerPanel.setLayout(new BoxLayout(toolPickerPanel, BoxLayout.Y_AXIS));

		toolPickerTitle = new JLabel("Tools");
		toolPickerPanel.add(toolPickerTitle);
	}

	private void makeDebugPanel() {
		debugPanel = new JPanel();
		debugPanel.setLayout(new BoxLayout(debugPanel, BoxLayout.Y_AXIS));

		debugTitle = new JLabel("Debug");
		debugPanel.add(debugTitle);

		debugRunButton = new JButton("Run");
		debugPanel.add(debugRunButton);

		debugStopButton = new JButton("Stop");
		debugPanel.add(debugStopButton);

		debugStepButton = new JButton("Step");
		debugPanel.add(debugStepButton);

	}

	private void makeColorPickerPanel() {
		colorsPickerPanel = new JPanel();
		colorsPickerPanel.setLayout(new BoxLayout(colorsPickerPanel, BoxLayout.Y_AXIS));

		colorPickerTitle = new JLabel("Colors");
		colorsPickerPanel.add(new JPanel().add(colorPickerTitle));

		selectedColorPanel = new JPanel();
		selectedColorPanel.setBackground(Color.red);
		colorsPickerPanel.add(selectedColorPanel);
		colorsPickerPanel.add(new JPanel());
		
		JPanel colors = new JPanel();
		colors.setLayout(new GridLayout(7, 3, 3, 3));
		
		lred = new JPanel();
		lred.setBackground(new Color(0xFFC0C0));
		colors.add(lred);
		red = new JPanel();
		red.setBackground(new Color(0xFF0000));
		colors.add(red);
		dred = new JPanel();
		dred.setBackground(new Color(0xC00000));
		colors.add(dred);
		lyel = new JPanel();
		lyel.setBackground(new Color(0xFFFFC0));
		colors.add(lyel);
		yel = new JPanel();
		yel.setBackground(new Color(0xFFFF00));
		colors.add(yel);
		dyel = new JPanel();
		dyel.setBackground(new Color(0xC0C000));
		colors.add(dyel);
		lgrn = new JPanel();
		lgrn.setBackground(new Color(0xC0FFC0));
		colors.add(lgrn);
		grn = new JPanel();
		grn.setBackground(new Color(0x00FF00));
		colors.add(grn);
		dgrn = new JPanel();
		dgrn.setBackground(new Color(0x00C000));
		colors.add(dgrn);
		lcyn = new JPanel();
		lcyn.setBackground(new Color(0xC0FfFF));
		colors.add(lcyn);
		cyn = new JPanel();
		cyn.setBackground(new Color(0x00FFFF));
		colors.add(cyn);
		dcyn = new JPanel();
		dcyn.setBackground(new Color(0x00C0C0));
		colors.add(dcyn);
		lblu = new JPanel();
		lblu.setBackground(new Color(0xC0C0FF));
		colors.add(lblu);
		blu = new JPanel();
		blu.setBackground(new Color(0x0000FF));
		colors.add(blu);
		dblu = new JPanel();
		dblu.setBackground(new Color(0x0000C0));
		colors.add(dblu);
		lmag = new JPanel();
		lmag.setBackground(new Color(0xFFC0FF));
		colors.add(lmag);
		mag = new JPanel();
		mag.setBackground(new Color(0xFF00FF));
		colors.add(mag);
		dmag = new JPanel();
		dmag.setBackground(new Color(0xC000C0));
		colors.add(dmag);
		white = new JPanel();
		white.setBackground(new Color(0xFFFFFF));
		colors.add(white);
		
		colors.add(new JPanel());
		
		black = new JPanel();
		black.setBackground(new Color(0x000000));
		colors.add(black);
		
		colorsPickerPanel.add(colors);
	}

	private void makeMenuBar() {
		menubar = new MenuBar();
		fileMenu = new Menu("File");
		
		newCanvas = new MenuItem("New");
		open = new MenuItem("Open");
		save = new MenuItem("Save");
		
		fileMenu.add(newCanvas);
		fileMenu.add(open);
		fileMenu.add(save);
		
		menubar.add(fileMenu);
	}
	
	private void makeIOPanel() {
		ioPanel = new JPanel();
		ioPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		ioTitle = new JLabel("Input/Output");	
		ioPanel.add(ioTitle);

		ioInput = new JTextField();
		ioPanel.add(ioInput);

		ioOutput = new JTextArea();
		ioPanel.add(ioOutput);
	}

	public class ButtonStopActionListener implements ActionListener {
		IDE ide;
		public ButtonStopActionListener(IDE ide) {
			this.ide = ide;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.stopInterpreter();
		} 
		
	}
	public class ButtonStepActionListener implements ActionListener {
		IDE ide;
		public ButtonStepActionListener(IDE ide) {
			this.ide = ide;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.stepInterpreter();
		} 
	}
	public class ButtonRunActionListener implements ActionListener {
		IDE ide;
		public ButtonRunActionListener(IDE ide) {
			this.ide = ide;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.runInterpreter();
		} 
	}
}
