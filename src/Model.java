
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 * This is the Model class for a kaleidoscope which is composed of a series of
 * shapes. It is an Observable, which means that it can notifyObservers that
 * something in the model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek, modified by swapneel
 * @author JuliaMa
 */
public class Model extends Observable {

	/**
	 * instance variables
	 */
	public Shape[] shapeArray;
	private Timer timer;
	public final int SHAPE_NUM = 10;

	/**
	 * constructor -- initialize a new shape array consist of shapes
	 */
	public Model() {
		shapeArray = new Shape[SHAPE_NUM];
	}

	/**
	 * @return shapeArray -- an array contains all the shapes
	 */
	public Shape[] getShapes() {
		return shapeArray;
	}

	/**
	 * Tells the shape to start moving. This is done by starting a Timer that
	 * periodically executes a TimerTask. The TimerTask then tells the ball to
	 * make one "step."
	 */
	public void start() {
		timer = new Timer(20, new Strobe());
		timer.start();
	}

	/**
	 * Tells the shape to stop where it is.
	 */
	public void pause() {
		timer.stop();
	}

	/**
	 * Tells the model to advance one "step."
	 */
	private class Strobe implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Shape s : shapeArray) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						s.makeOneStep();
						return null;
					}
				};
				worker.execute();
			}
			// Notify observers
			setChanged();
			notifyObservers();
		}
	}

}