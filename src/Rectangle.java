import java.awt.Color;

/**
 * This class makes a new rectangle object.
 * 
 * @author JuliaMa
 *
 */
public class Rectangle extends Shape {

	/**
	 * constructor -- initialize a new rectangle
	 * 
	 * @param width
	 * @param height
	 * @param xDelta
	 * @param yDelta
	 * @param xPosition
	 * @param yPosition
	 * @param color
	 */
	public Rectangle(int width, int height, int xDelta, int yDelta, int xPosition, int yPosition, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.xDelta = xDelta;
		this.yDelta = yDelta;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
	}

}
