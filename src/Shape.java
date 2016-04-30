
import java.awt.Color;
import java.util.Observable;
import java.util.Random;

/**
 * This abstract class make a new shape with the shape's all statuses including
 * width, height, x position, y position, x limit, y limit, x delta, y delta
 * (these are all attributes about movement), and the color of the shape.
 * 
 * @author JuliaMa
 *
 */
public abstract class Shape extends Observable {

	/**
	 * instance variables
	 */
	public final int BALL_SIZE = 20;
	protected int width;
	protected int height;
	protected int xPosition;
	protected int yPosition;
	protected int xLimit, yLimit;
	protected int xDelta;
	protected int yDelta;
	protected Color color;
	Random rand = new Random();

	/**
	 * set width
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * set height
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * get the position in the rightmost wall
	 * 
	 * @return xLimit
	 */
	public int getxLimit() {
		return xLimit;
	}

	/**
	 * get the position in the floor
	 * 
	 * @return yLimit
	 */
	public int getyLimit() {
		return yLimit;
	}

	/**
	 * @return The shapes X position.
	 */
	public int getX() {
		return xPosition;
	}

	/**
	 * @return The shapes Y position.
	 */
	public int getY() {
		return yPosition;
	}

	/**
	 * Sets the "walls" that the shape should bounce off from.
	 * 
	 * @param xLimit
	 *            The position (in pixels) of the wall on the right.
	 * @param yLimit
	 *            The position (in pixels) of the floor.
	 */
	public void setLimits(int xLimit, int yLimit) {
		this.xLimit = xLimit - width;
		this.yLimit = yLimit - height;
		xPosition = Math.min(xPosition, this.xLimit);
		yPosition = Math.min(yPosition, this.yLimit);
	}

	/**
	 * Tells the shape to advance one step in the direction that it is moving.
	 * If it hits a wall, its direction of movement changes.
	 */
	public void makeOneStep() {
		// Do the work
		xPosition += xDelta;
		if (xPosition < 0 || xPosition >= xLimit) {
			xDelta = -xDelta;
			xPosition += xDelta;
		}
		yPosition += yDelta;
		if (yPosition < 0 || yPosition >= yLimit) {
			yDelta = -yDelta;
			yPosition += yDelta;
		}

	}

	/**
	 * @param position
	 * @param side length
	 * @param limit
	 * @return the position of an original shape after reflection along axis
	 */
	public int reflection(int pos, int side, int limit) {
		return limit - pos - side;

	}

	/**
	 * @return the color of the shape
	 */
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	/**
	 * @return width of the shape
	 */
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	/**
	 * @return height of the shape
	 */
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	/**
	 * @return position of the rightmost wall
	 */
	public int getXLimit() {
		// TODO Auto-generated method stub
		return xLimit;
	}

	/**
	 * @return position of the floor
	 */
	public int getYLimit() {
		// TODO Auto-generated method stub
		return yLimit;
	}

	/**
	 * set a random color to a shape
	 */
	public void setColor() {
		// TODO Auto-generated method stub
		float hue = rand.nextFloat();
		float saturation = (rand.nextInt(4000) + 6000) / 10000f;
		float luminance = 1.0f;
		color = Color.getHSBColor(hue, saturation, luminance);

	}

	/**
	 * @return one movement length along x-axis
	 */
	public int getxDelta() {
		return xDelta;
	}

	/**
	 * set the movement length along x-axis
	 * 
	 * @param xDelta
	 */
	public void setxDelta(int xDelta) {
		this.xDelta = xDelta;
	}

	/**
	 * @return one movement length along y-axis
	 */
	public int getyDelta() {
		return yDelta;
	}

	/**
	 * set the movement length along y-axis
	 * 
	 * @param yDelta
	 */
	public void setyDelta(int yDelta) {
		this.yDelta = yDelta;
	}

}
