import java.awt.Color;
import java.awt.Graphics;

/**
 * Class to create, manage and draw window objects
 * @author Grady Barrett
 */
public class Square{
	
	 int squareX; // x location of the square
	 int squareY; // y location of the square
	 int sqWidth; // width of the square
	 int sqHeight; // height of the square
	 Color squareColor; // square color
	
	/**
	 * Constructor to initialize x & y location and square width & height variables 
	 * 		as well as the square color 
	 * @param newX 
	 * 			new x location for square
	 * @param newY
	 * 			new y location for square
	 * @param newWidth
	 * 			new width of square
	 * @param newHeight
	 * 			new height of square
	 * @param newColor
	 *			new square color
	 */
	public Square(int newX, int newY, int newWidth, int newHeight, Color newColor) {
		
		squareX = newX;
		squareY = newY;
		sqWidth = newWidth;
		sqHeight = newHeight;
		squareColor = newColor;
	}

	/**
	 * draws squares w/in active window
	 * @param g
	 * 			Graphics object for drawing
	 */
	public void draw(Graphics g){
		
		g.setColor(squareColor);// square color assigned to each window
		
		// new square drawn inside active window
		g.fillRect((squareX - 10), (squareY - 10), sqWidth, sqHeight);
	}
	
	/**
	 * 
	 * @param x
	 * 			x coordinate of user click
	 * @param y
	 * 			y coordinate of user click
	 * @return Returns true if the user click is within the square object provided
	 */
	public boolean isInside(int x, int y){
		if((x >= this.squareX-10 && x <= this.squareX + sqWidth/2) && 
				(y >= this.squareY-10 && y <= this.squareY + sqHeight/2)){
			return true;
		
		}else
			return false;
	}
}
