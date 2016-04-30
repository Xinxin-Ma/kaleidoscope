
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The View "observes" and displays what is going on in the Model. In this
 * example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek, modified by swapneel
 * @author JuliaMa
 */
@SuppressWarnings("serial")
public class View extends JPanel implements Observer {

	/** This is what we will be observing. */
	/**
	 * instance variables
	 */
	private Model model;
	int reflectionNumber = 8;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            The Model whose working is to be displayed.
	 */
	public View(Model model) {
		this.model = model;
	}

	/**
	 * @param reflectionString
	 * @return reflection number
	 */
	public int getReflectionNumber(String reflectionString) {
		if (reflectionString == "8-Fold") {
			return 8;
		} else if (reflectionString == "4-Fold") {
			return 4;
		} else if (reflectionString == "2-Fold") {
			return 2;
		}
		return 1;
	}

	/**
	 * set reflection number according to selection
	 * 
	 * @param reflectionNumber
	 *
	 */
	public void setReflectionNumber(int reflectionNumber) {
		this.reflectionNumber = reflectionNumber;
	}

	/**
	 * Displays what is going on in the Model. Note: This method should NEVER be
	 * called directly; call repaint() instead.
	 * 
	 * @param g
	 *            The Graphics on which to paint things.
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// System.out.println(SwingUtilities.isEventDispatchThread());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (Shape s : model.getShapes()) {
			g.setColor(s.getColor());
			if (s instanceof Ball) {
				paintBallSymm(g, s);
			} else if (s instanceof Rectangle) {
				paintRectSymm(g, s);
			} else if (s instanceof RoundRect) {
				paintRoundRectSymm(g, s);
			}
		}

	}

	/**
	 * paint balls according to selected reflection number
	 * 
	 * @param g
	 *            graphics
	 * @param s
	 *            shape
	 */
	public void paintBallSymm(Graphics g, Shape s) {
		if (reflectionNumber == 8) {
			g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillOval(s.getY(), s.getX(), s.getHeight(), s.getWidth());

			g.fillOval(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
			g.fillOval(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getX(),
					s.getHeight(), s.getWidth());

			g.fillOval(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight());
			g.fillOval(s.getY(), s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.getHeight(), s.getWidth());

			g.fillOval(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight());
			g.fillOval(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getHeight(),
					s.getWidth());
		} else if (reflectionNumber == 4) {
			g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillOval(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
			g.fillOval(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight());
			g.fillOval(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight());
		} else if (reflectionNumber == 2) {
			g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillOval(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
		} else {
			g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
		}

	}

	/**
	 * paint rectangles according to selected reflection number
	 * 
	 * @param g
	 *            graphics
	 * @param s
	 *            shape
	 */
	public void paintRectSymm(Graphics g, Shape s) {
		if (reflectionNumber == 8) {
			g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillRect(s.getY(), s.getX(), s.getHeight(), s.getWidth());

			g.fillRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
			g.fillRect(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getX(),
					s.getHeight(), s.getWidth());

			g.fillRect(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight());
			g.fillRect(s.getY(), s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.getHeight(), s.getWidth());

			g.fillRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight());
			g.fillRect(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getHeight(),
					s.getWidth());
		} else if (reflectionNumber == 4) {
			g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
			g.fillRect(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight());
			g.fillRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight());
		} else if (reflectionNumber == 2) {
			g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g.fillRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight());
		} else {
			g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
		}

	}

	/**
	 * paint round rectangles according to selected reflection number
	 * 
	 * @param g
	 *            graphics
	 * @param s
	 *            shape
	 */
	public void paintRoundRectSymm(Graphics g, Shape s) {
		if (reflectionNumber == 8) {
			g.fillRoundRect(s.getX(), s.getY(), s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(),
					((RoundRect) s).getArcY());
			g.fillRoundRect(s.getY(), s.getX(), s.getHeight(), s.getWidth(), ((RoundRect) s).getArcY(),
					((RoundRect) s).getArcX());
			g.fillRoundRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
			g.fillRoundRect(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getX(),
					s.getHeight(), s.getWidth(), ((RoundRect) s).getArcY(), ((RoundRect) s).getArcX());

			g.fillRoundRect(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
			g.fillRoundRect(s.getY(), s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.getHeight(), s.getWidth(), ((RoundRect) s).getArcY(), ((RoundRect) s).getArcX());

			g.fillRoundRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
			g.fillRoundRect(s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getHeight(),
					s.getWidth(), ((RoundRect) s).getArcY(), ((RoundRect) s).getArcX());
		} else if (reflectionNumber == 4) {
			g.fillRoundRect(s.getX(), s.getY(), s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(),
					((RoundRect) s).getArcY());
			g.fillRoundRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
			g.fillRoundRect(s.getX(), s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()),
					s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
			g.fillRoundRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()),
					s.reflection(s.getY(), s.getHeight(), s.getYLimit() + s.getHeight()), s.getWidth(),
					s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
		} else if (reflectionNumber == 2) {
			g.fillRoundRect(s.getX(), s.getY(), s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(),
					((RoundRect) s).getArcY());
			g.fillRoundRect(s.reflection(s.getX(), s.getWidth(), s.getXLimit() + s.getWidth()), s.getY(),
					s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(), ((RoundRect) s).getArcY());
		} else {
			g.fillRoundRect(s.getX(), s.getY(), s.getWidth(), s.getHeight(), ((RoundRect) s).getArcX(),
					((RoundRect) s).getArcY());
		}

	}
	

	/**
	 * When an Observer notifies Observers (and this View is an Observer), this
	 * is the method that gets called.
	 * 
	 * @param obs
	 *            Holds a reference to the object being observed.
	 * @param arg
	 *            If notifyObservers is given a parameter, it is received here.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		// System.out.println(SwingUtilities.isEventDispatchThread());
		repaint();
	}
}