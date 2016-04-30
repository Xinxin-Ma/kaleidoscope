
/**
 * This is a kaleidoscope created by modifying the given "bouncing ball" project. It is an example of MVC pattern and implemented by graphics.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Controller sets up the GUI and handles all the controls (buttons, menu
 * items, etc.)
 * 
 * @author David Matuszek, modified by swapneel
 * @author JuliaMa
 */
public class Controller {
	/**
	 * instance variables
	 */
	private JFrame frame;
	private JPanel buttonPanel;
	private JButton runButton;
	private JButton stopButton;
	private JButton restartButton;
	private JButton clearButton;
	private JButton colorButton;

	private JComboBox<String> reflectionNumberBox;
	private JSlider speedSlider;
	private JLabel speedLabel;
	private JLabel reflectionNumberLabel;

	private JPanel functionPanel; 
	private JPanel controlPanel;

	int defaultValue = 0;

	/**
	 * The Model is the object that does all the computations. It is completely
	 * independent of the Controller and View objects.
	 */
	private Model model;
	private Shape[] shapes;

	/** The View object displays what is happening in the Model. */
	private View view;

	/**
	 * Runs the bouncing ball program.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller c = new Controller();
				c.init();
				c.display();
			}
		});

	}

	/**
	 * Sets up communication between the components.
	 */
	private void init() {
		model = new Model(); // The model is independent from the other classes
		view = new View(model); // The view needs to know what model to look at
		model.addObserver(view); // The model needs to give permission to be
									// observed
		shapes = model.getShapes();
		shapes[0] = new Rectangle(50, 60, 10, 10, 80, 80, Color.RED);
		shapes[1] = new Rectangle(45, 50, 6, 8, 40, 30, Color.MAGENTA);
		shapes[2] = new RoundRect(60, 45, 6, 9, 130, 130, 25, 15, Color.YELLOW);
		shapes[3] = new Rectangle(80, 80, 12, 9, 0, 0, Color.BLUE);
		shapes[4] = new Ball(20, 20, 10, 8, 75, 75, Color.PINK);
		shapes[5] = new Ball(30, 5, 6, 10, 5, 50, Color.ORANGE);
		shapes[6] = new Ball(5, 30, 10, 6, 17, 38, Color.ORANGE);
		shapes[7] = new RoundRect(15, 20, 15, 15, 120, 80, 5, 5, Color.GREEN);
		shapes[8] = new Ball(50, 50, 12, 8, 225, 50, Color.CYAN);
//		int[] coords1 = {230, 220, 230, 240};
//		int[] coords2 = {210, 220, 230, 220};
//        shapes[8] = new Polygon(coords1, coords2, 10, 10, Color.CYAN);
		shapes[9] = new RoundRect(40, 40, 10, 10, 130, 40, 8, 10, Color.ORANGE);
	}

	/**
	 * Displays the GUI.
	 */
	private void display() {
		layOutComponents();
		attachListenersToComponents();
		frame.setSize(500, 625);
		frame.setVisible(true);
	}

	/**
	 * Arranges the various components in the GUI.
	 */
	private void layOutComponents() {
		frame = new JFrame("Kaleidoscope");
		buttonPanel = new JPanel();
		controlPanel = new JPanel();
		functionPanel = new JPanel();

		runButton = new JButton("Run");
		stopButton = new JButton("Stop");
		clearButton = new JButton("Clear");
		restartButton = new JButton("Restart");
		colorButton = new JButton("Change Color");
		speedLabel = new JLabel("       Speed: ");
		speedSlider = new JSlider(JSlider.HORIZONTAL, -5, 5, 0);
		reflectionNumberLabel = new JLabel("     Reflection: ");

		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel.add(runButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(restartButton);
		buttonPanel.add(colorButton);
		stopButton.setEnabled(false);

		controlPanel.setLayout(new GridLayout(1, 4));
		controlPanel.add(speedLabel);
		controlPanel.add(speedSlider);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);

		reflectionNumberBox = new JComboBox<String>(new String[] { "1-Fold", "2-Fold", "4-Fold", "8-Fold" });
		reflectionNumberBox.setSelectedItem("8-Fold");
		controlPanel.add(reflectionNumberLabel);
		controlPanel.add(reflectionNumberBox);

		functionPanel.setLayout(new GridLayout(2, 1));
		functionPanel.add(buttonPanel);
		functionPanel.add(controlPanel);
		frame.add(BorderLayout.SOUTH, functionPanel);
		frame.add(BorderLayout.CENTER, view);
	}

	/**
	 * Attaches listeners to the components, and schedules a Timer.
	 */
	private void attachListenersToComponents() {
		// The Run button tells the Model to start
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				runButton.setEnabled(false);
				stopButton.setEnabled(true);
				model.start();

			}
		});

		// The Stop button tells the Model to pause
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				runButton.setEnabled(true);
				stopButton.setEnabled(false);
				model.pause();

			}
		});

		// The Clear button clears all the shapes in Model
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				for (Shape s : shapes) {
					s.setWidth(0);
					s.setHeight(0);
				}
				runButton.setEnabled(false);
				stopButton.setEnabled(false);
				restartButton.setEnabled(true);
				colorButton.setEnabled(false);
				view.repaint();
			}
		});

		// The Restart button restarts a new Model.
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				Controller c = new Controller();
				c.init();
				c.display();
			}
		});

		// The Change Color button changes colors of the shapes.
		colorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				for (Shape s : shapes) {
					s.setColor();
				}
				view.repaint();
			}
		});

		// The speed slider changes the speed.
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int change = (int) source.getValue() - defaultValue;
					defaultValue = source.getValue();
					for (Shape s : shapes) {
						if (s.getxDelta() >= 0) {
							s.setxDelta(s.getxDelta() + change);
						} else {
							s.setxDelta(s.getxDelta() - change);
						}
						if (s.getyDelta() >= 0) {
							s.setyDelta(s.getyDelta() + change);
						} else {
							s.setyDelta(s.getyDelta() - change);
						}
					}

				}
			}
		});

		// Select a reflection number.
		reflectionNumberBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setReflectionNumber(view.getReflectionNumber((String) reflectionNumberBox.getSelectedItem()));
				view.repaint();
			}
		});

		// When the window is resized, the Model is given the new limits
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				for (Shape s : shapes) {
					int square = Math.max(view.getWidth(), view.getHeight());
					s.setLimits(square, square);
					
				}
			}
		});
	}

}