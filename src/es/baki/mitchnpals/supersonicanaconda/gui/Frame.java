package es.baki.mitchnpals.supersonicanaconda.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import es.baki.mitchnpals.supersonicanaconda.Canvas;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GridBagLayout layoutManager;

	// Menu bar stuff
	private MenuBar menubar;
	private Menu fileMenu;
	private Menu editMenu;
	private MenuItem open;
	private MenuItem newCanvas;
	private MenuItem save;
	private MenuItem undo;
	private MenuItem redo;

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
	private JPanel lred, red, dred, lyel, yel, dyel, lgrn, grn, dgrn, lcyn, cyn, dcyn, lblu, blu, dblu, lmag, mag, dmag,
			black, white;

	private JPanel toolPickerPanel;
	private JLabel toolPickerTitle;
	private JButton toolFillButton;
	private JButton toolPencilButton;

	private CanvasPanel canvasPanel;

	public Color getSelectedColor() {
		return selectedColorPanel.getBackground();
	}


	public Frame() {
		super("Supersonic Anaconda");

		// Hotkeys
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("control Y"), "redo");
		this.getRootPane().getActionMap().put("redo", new RedoAction());

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("control Z"), "undo");
		this.getRootPane().getActionMap().put("undo", new UndoAction());

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("control S"), "save");
		this.getRootPane().getActionMap().put("save", new SaveAction());

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("control O"), "open");
		this.getRootPane().getActionMap().put("open", new OpenAction());

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("control N"), "new");
		this.getRootPane().getActionMap().put("new", new NewAction());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layoutManager = new GridBagLayout();
		this.setLayout(layoutManager);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;

		makeIOPanel();
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

		canvasPanel = new CanvasPanel(10, 10, this);
		// canvasPanel.setAutoscrolls(true);
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
		canvasPanel.setMinimumSize(canvasPanel.getSize());
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));

	}

	public void makeNewCanvas(Canvas canvas) {
		canvasPanel.setCodelGridSize(canvas.getWidth(), canvas.getHeight());
		this.pack();
		for (int x = 0; x < canvas.getWidth(); x++) {
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

		toolFillButton = new JButton("Fill");
		toolFillButton.setEnabled(false);
		toolFillButton.addActionListener(new FillToolAction());
		toolPickerPanel.add(toolFillButton);

		toolPencilButton = new JButton("Pencil");
		toolPencilButton.addActionListener(new PencilToolAction());
		toolPickerPanel.add(toolPencilButton);
	}

	public void toolChange() { // TODO Make this more extensible
		JButton[] buttons = { toolFillButton, toolPencilButton };

		for (JButton b : buttons)
			b.setEnabled(true);

		if (IDE.getIDE().getTool() == IDE.Tool.fill)
			toolFillButton.setEnabled(false);
		if (IDE.getIDE().getTool() == IDE.Tool.pencil)
			toolPencilButton.setEnabled(false);
	}

	private void makeDebugPanel() {
		debugPanel = new JPanel();
		debugPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		debugTitle = new JLabel("Debug");
		c.gridx = 0;
		c.gridy = 0;
		debugPanel.add(debugTitle, c);

		debugRunButton = new JButton("Run");
		c.gridy = 1;
		debugPanel.add(debugRunButton, c);
		debugRunButton.addActionListener(this.getRootPane().getActionMap().get("new"));

		debugStopButton = new JButton("Stop");
		c.gridy = 2;
		debugPanel.add(debugStopButton, c);
		debugStopButton.addActionListener(new StopAction());

		debugStepButton = new JButton("Step");
		c.gridy = 3;
		debugPanel.add(debugStepButton, c);
		debugStepButton.addActionListener(new StepAction());
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
		int[] rgb = { 0xFFC0C0, 0xFF0000, 0xC00000, 0xFFFFC0, 0xFFFF00, 0xC0C000, 0xC0FFC0, 0x00FF00, 0x00C000,
				0xC0FfFF, 0x00FFFF, 0x00C0C0, 0xC0C0FF, 0x0000FF, 0x0000C0, 0xFFC0FF, 0xFF00FF, 0xC000C0, 0xFFFFFF,
				0x000000 };
		JPanel[] panels = { lred, red, dred, lyel, yel, dyel, lgrn, grn, dgrn, lcyn, cyn, dcyn, lblu, blu, dblu, lmag,
				mag, dmag, black, white };

		for (int x = 0; x < rgb.length; x++) {
			panels[x] = new ColorSelectorPanel();
			panels[x].setBackground(new Color(rgb[x]));
			colors.add(panels[x]);
		}

		colorsPickerPanel.add(colors);
	}

	private void makeMenuBar() {
		menubar = new MenuBar();
		fileMenu = new Menu("File");

		// File menu
		newCanvas = new MenuItem("New");
		open = new MenuItem("Open");
		save = new MenuItem("Save");

		// newCanvas.addActionListener(new NewAction());
		newCanvas.addActionListener(this.getRootPane().getActionMap().get("new"));
		open.addActionListener(new OpenAction());
		save.addActionListener(new SaveAction());

		fileMenu.add(newCanvas);
		fileMenu.add(open);
		fileMenu.add(save);

		menubar.add(fileMenu);

		// Edit Menu
		editMenu = new Menu("Edit");
		undo = new MenuItem("Undo");
		redo = new MenuItem("Redo");

		undo.addActionListener(new UndoAction());
		redo.addActionListener(new RedoAction());

		editMenu.add(redo);
		editMenu.add(undo);

		menubar.add(editMenu);
	}

	private void makeIOPanel() {
		ioPanel = new JPanel();
		ioPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHEAST;
		c.fill = GridBagConstraints.HORIZONTAL;

		ioTitle = new JLabel("Input/Output");
		c.gridx = 0;
		c.gridy = 0;
		ioPanel.add(ioTitle, c);

		ioInput = new JTextField();
		c.gridy++;
		ioPanel.add(ioInput, c);

		ioOutput = new JTextArea();
		c.gridy++;
		c.fill = GridBagConstraints.BOTH;
		ioPanel.add(ioOutput, c);
	}

	public Canvas getCanvas() {
		Canvas canvas = new Canvas(canvasPanel.getCanvasWidth(), canvasPanel.getCanvasHeight());
		for (int x = 0; x < canvasPanel.getCanvasWidth(); x++) {
			for (int y = 0; y < canvasPanel.getCanvasHeight(); y++) {
				canvas.set(x, y,
						es.baki.mitchnpals.supersonicanaconda.Color.awtToBakiesColor(canvasPanel.getColorAt(x, y)));
			}
		}
		return canvas;
	}

	public class ColorSelectorPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;

		public ColorSelectorPanel() {
			this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			this.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Frame.this.selectedColorPanel.setBackground(this.getBackground());
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

	private class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(Frame.this, "Opening a new file will discard any unsaved work");
			final JFileChooser fc = new JFileChooser();
			fc.showDialog(Frame.this, "Open");

			File file = fc.getSelectedFile();
			if (file == null)
				return; // User canceled action
			IDE.getIDE().clearHistory();
			makeNewCanvas(Canvas.readFromFile(file));
			IDE.getIDE().snapHistory();
		}

	}

	private class RedoAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			IDE.getIDE().redo();
		}

	}

	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			final JFileChooser fc = new JFileChooser();
			fc.showDialog(Frame.this, "Save");

			File file = fc.getSelectedFile();
			if (file == null) // Dialog gets cancelled, do not continue
				return;
			System.out.println(getCanvas());
			getCanvas().exportToPNG(file);
		}

	}

	private class NewAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int button = JOptionPane.showConfirmDialog(Frame.this, "Unsaved progress will be lost. Are you sure?");
			// button: 0 = yes, 1 = no, 2 = cancel
			if (button == 0) {
				boolean valid = false;
				while (!valid) {
					String input = JOptionPane.showInputDialog(Frame.this,
							"Input height and width (seperated by a space)");
					if (input == null)
						return;
					try {
						int height = Integer.parseInt(input.split(" ")[0]);
						int width = Integer.parseInt(input.split(" ")[1]);

						makeNewCanvas(new Canvas(height, width));
						IDE.getIDE().clearHistory();
						valid = true;
					} catch (Exception e) {
						// Show another input dialog
						// This can be indexOutOfBounds
						// or this can be numberFormat
						JOptionPane.showMessageDialog(Frame.this, "Bad Input!");
					}
				}
			}
		}

	}

	public class UndoAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			IDE.getIDE().undo();
		}
	}


	public class StopAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			IDE.getIDE().stopInterpreter();
		}

	}

	public class StepAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			IDE.getIDE().stepInterpreter();
		}
	}

	public class RunAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			IDE.getIDE().runInterpreter();
		}
	}

	private class FillToolAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			IDE.getIDE().setTool(IDE.Tool.fill);
		}
	}

	private class PencilToolAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			IDE.getIDE().setTool(IDE.Tool.pencil);
		}
	}
}
