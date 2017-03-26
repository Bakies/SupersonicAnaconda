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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.FileChooserUI;

import es.baki.mitchnpals.supersonicanaconda.Canvas;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GridBagLayout layoutManager;
	
	// Menu bar stuff
	private MenuBar menubar;
	private Menu fileMenu;
	private FileOpenButton open;
	private FileNewButton newCanvas;
	private FileSaveButton save;
	
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
	
	private CanvasPanel canvasPanel;

	private IDE ide;
	
	private int width, height;
	
	public Color getSelectedColor() { 
		return selectedColorPanel.getBackground();
	}
	
	public Frame(IDE ide, int height, int width) {
		super("Supersonic Anaconda");
		this.width = width; 
		this.height = height;
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
		
		canvasPanel = new CanvasPanel(height, width, this);
		canvasPanel.setAutoscrolls(true);
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
	
	private void makeNewCanvas(Canvas canvas) {
		canvasPanel.setSize(canvas.getWidth(), canvas.getHeight());
		this.pack();
		for (int x = 0; x < canvas.getWidth(); x ++) {
			for (int y = 0; y < canvas.getHeight(); y++) {
				canvasPanel.setPanelColor(x, y, canvas.getAwtColor(x, y));
			}
		}
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
		debugRunButton.addActionListener(new ButtonRunActionListener(this, ide));
		

		debugStopButton = new JButton("Stop");
		debugPanel.add(debugStopButton);
		debugStopButton.addActionListener(new ButtonStopActionListener(this, ide));

		debugStepButton = new JButton("Step");
		debugPanel.add(debugStepButton);
		debugStepButton.addActionListener(new ButtonStepActionListener(this, ide));

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
		
		lred = new ColorSelectorPanel(this);
		lred.setBackground(new Color(0xFFC0C0));
		colors.add(lred);
		
		red = new ColorSelectorPanel(this);
		red.setBackground(new Color(0xFF0000));
		colors.add(red);
		
		dred = new ColorSelectorPanel(this);
		dred.setBackground(new Color(0xC00000));
		colors.add(dred);
		
		lyel = new ColorSelectorPanel(this);
		lyel.setBackground(new Color(0xFFFFC0));
		colors.add(lyel);
		
		yel = new ColorSelectorPanel(this);
		yel.setBackground(new Color(0xFFFF00));
		colors.add(yel);
		
		dyel = new ColorSelectorPanel(this);
		dyel.setBackground(new Color(0xC0C000));
		colors.add(dyel);
		
		lgrn = new ColorSelectorPanel(this);
		lgrn.setBackground(new Color(0xC0FFC0));
		colors.add(lgrn);
		
		grn = new ColorSelectorPanel(this);
		grn.setBackground(new Color(0x00FF00));
		colors.add(grn);
		
		dgrn = new ColorSelectorPanel(this);
		dgrn.setBackground(new Color(0x00C000));
		colors.add(dgrn);
		
		lcyn = new ColorSelectorPanel(this);
		lcyn.setBackground(new Color(0xC0FfFF));
		colors.add(lcyn);
		
		cyn = new ColorSelectorPanel(this);
		cyn.setBackground(new Color(0x00FFFF));
		colors.add(cyn);
		
		dcyn = new ColorSelectorPanel(this);
		dcyn.setBackground(new Color(0x00C0C0));
		colors.add(dcyn);
		
		lblu = new ColorSelectorPanel(this);
		lblu.setBackground(new Color(0xC0C0FF));
		colors.add(lblu);
		
		blu = new ColorSelectorPanel(this);
		blu.setBackground(new Color(0x0000FF));
		colors.add(blu);
		
		dblu = new ColorSelectorPanel(this);
		dblu.setBackground(new Color(0x0000C0));
		colors.add(dblu);
		
		lmag = new ColorSelectorPanel(this);
		lmag.setBackground(new Color(0xFFC0FF));
		colors.add(lmag);
		
		mag = new ColorSelectorPanel(this);
		mag.setBackground(new Color(0xFF00FF));
		colors.add(mag);
		
		dmag = new ColorSelectorPanel(this);
		dmag.setBackground(new Color(0xC000C0));
		colors.add(dmag);
		
		white = new ColorSelectorPanel(this);
		white.setBackground(new Color(0xFFFFFF));
		colors.add(white);
		
		colors.add(new JPanel());
		
		black = new ColorSelectorPanel(this);
		black.setBackground(new Color(0x000000));
		colors.add(black);
		
		colorsPickerPanel.add(colors);
	}

	private void makeMenuBar() {
		menubar = new MenuBar();
		fileMenu = new Menu("File");
		
		newCanvas = new FileNewButton("New");
		open = new FileOpenButton(this, "Open");
		save = new FileSaveButton("Save");
		
		fileMenu.add(newCanvas);
		fileMenu.add(open);
		fileMenu.add(save);
		
		menubar.add(fileMenu);
	}
	
	private class FileOpenButton extends MenuItem implements ActionListener {
		private static final long serialVersionUID = 7904843145726959810L;
		private Frame parent; 
		public FileOpenButton(Frame parent, String title) {
			super(title);
			
			this.addActionListener(this);
		} 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			final JFileChooser fc = new JFileChooser(); 
			fc.showDialog(parent, "Open");
			
			File file = fc.getSelectedFile();
			makeNewCanvas(Canvas.readFromFile(file));
		} 
		
	}

	private class FileSaveButton extends MenuItem implements ActionListener {
		private static final long serialVersionUID = 7904843145726959811L;
		public FileSaveButton(String title) {
			super(title);
			this.addActionListener(this);
		} 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Action performed stub
		} 
		
	}
	private class FileNewButton extends MenuItem implements ActionListener {
		private static final long serialVersionUID = 7904843145726959812L;
		public FileNewButton(String title) {
			super(title);
			this.addActionListener(this);
		} 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Action performed stub
		} 
		
	}

	
	
	private void makeIOPanel() {
		ioPanel = new JPanel();
		ioPanel.setLayout(new GridBagLayout());
		
		ioTitle = new JLabel("Input/Output");	
		ioPanel.add(ioTitle);

		ioInput = new JTextField();
		ioPanel.add(ioInput);

		ioOutput = new JTextArea();
		ioPanel.add(ioOutput);
	}

	@SuppressWarnings("serial")
	public class ColorSelectorPanel extends JPanel implements MouseListener {
		private Frame f; 
		public ColorSelectorPanel(Frame f) {
			this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			this.addMouseListener(this);
			this.f = f;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			f.selectedColorPanel.setBackground(this.getBackground());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.white));
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// ignored
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// ignored
		} 
		
		
	}
	
	public class ButtonStopActionListener implements ActionListener {
		IDE ide;
		Frame f; 
		public ButtonStopActionListener(Frame f, IDE ide) {
			this.ide = ide;
			this.f = f; 
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.stopInterpreter();
		} 
		
	}
	public class ButtonStepActionListener implements ActionListener {
		IDE ide;
		Frame f;
		public ButtonStepActionListener(Frame f, IDE ide) {
			this.f = f;
			this.ide = ide;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.stepInterpreter();
		} 
	}
	public class ButtonRunActionListener implements ActionListener {
		IDE ide;
		Frame f;
		public ButtonRunActionListener(Frame f, IDE ide) {
			this.ide = ide;
			this.f = f;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ide.runInterpreter();
		} 
	}
	
	public Canvas getCanvas() {
		Canvas canvas = new Canvas(width, height);
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++){
				canvas.set(y, x, es.baki.mitchnpals.supersonicanaconda.Color.awtToBakiesColor(canvasPanel.getColorAt(x, y)));
			}
		}
		return canvas;
	}
}
