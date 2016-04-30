import java.awt.Color;

/**
 * This class makes a new ball.
 * 
 * @author JuliaMa
 *
 */
public class Ball extends Shape {

	/**
	 * constructor -- initialize a new ball
	 * 
	 * @param width
	 * @param height
	 * @param xDelta
	 * @param yDelta
	 * @param xPosition
	 * @param yPosition
	 * @param color
	 */
	public Ball(int width, int height, int xDelta, int yDelta, int xPosition, int yPosition, Color color) {
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
