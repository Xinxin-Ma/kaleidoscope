import java.awt.Color;

/**
 * This class makes a new round rectangle.
 * 
 * @author JuliaMa
 *
 */
public class RoundRect extends Shape {

	/**
	 * instance variables
	 */
	private int arcX;
	private int arcY;

	/**
	 * constructor -- initialize a new round rectangle
	 * 
	 * @param width
	 * @param height
	 * @param xDelta
	 * @param yDelta
	 * @param xPosition
	 * @param yPosition
	 * @param arcX
	 * @param arcY
	 * @param color
	 */
	public RoundRect(int width, int height, int xDelta, int yDelta, int xPosition, int yPosition, int arcX, int arcY,
			Color color) {
		super();
		this.width = width;
		this.height = height;
		this.xDelta = xDelta;
		this.yDelta = yDelta;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
		this.arcX = arcX;
		this.arcY = arcY;

	}

	/**
	 * @return arc along x-axis
	 */
	public int getArcX() {
		return arcX;
	}

	/**
	 * @return arc along y-axis
	 */
	public int getArcY() {
		return arcY;
	}

}
