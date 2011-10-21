
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements a simple GUI with a blank canvas.
 * An instance of a class that implements SimpleWindowManager 
 * is responsible for all drawing within the canvas, and is 
 * responsible for updating the window information based on 
 * the location of the mouse clicks.
 */
public class SimpleWindowsGUI extends JFrame implements MouseListener {

	/** The canvas where the windows are drawn. */
	private RectanglesCanvas canvas;
	
	/** The object that manages the window data. */
	private SimpleWindowManager model;
	
	/** The default width of the canvas */
	public static final int CANVAS_WIDTH = 800;
	/** The default height of the canvas */
	public static final int CANVAS_HEIGHT = 600;
	
	/**
	 * Constructs the GUI and makes the window visible.
	 * 
	 * @param mod the window manager to be used by this GUI.
	 */
	public SimpleWindowsGUI(SimpleWindowManager mod) {
		this.model = mod;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Rectangles!");
		
		this.add(canvas = new RectanglesCanvas(), BorderLayout.CENTER);
		canvas.addMouseListener(this);

		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * This class implements the canvas where the windows 
	 * are drawn.
	 */
	private class RectanglesCanvas extends JPanel {
		
		/**
		 * Constructs the canvas with a white background.
		 */
		public RectanglesCanvas() {
			this.setPreferredSize( new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			this.setBackground(Color.white);
		}
		
		/**
		 * Draws this component by calling the draw method
		 * of the window manager.
		 */
		public void paintComponent( Graphics g ) {
			super.paintComponent(g);
			model.draw(g);
		}
	}

	/**
	 * When mouse is clicked, the handleClick method in the
	 * window manager is called.
	 * @param e the mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		model.handleClick(e.getX(), e.getY());
		canvas.repaint();
	}

	// These methods are unused.
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}
