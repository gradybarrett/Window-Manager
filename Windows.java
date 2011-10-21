import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

/**
 * Class to create, manage and draw window objects 
 * @author Grady Barrett
 */
public class Windows{

	int winUpperLeftX; // window upper left x location
	int winUpperLeftY; // window upper left y location
	int winWidth; // window width
	int winHeight; // window height
	final int SQ_WIDTH = 20; // constant square width to pass to square constructor
	final int SQ_HEIGHT = 20; // constant square height to pass to square constructor
	Color squareColor; // color of squares in a specific window.
	
	// linked list of square objects
	KWLinkedList<Square> squares = new KWLinkedList<Square>(); 
	
	/**
	 * Constructor to initialize x & y location and window width & height variables
	 * @param newX 
	 * 			new x location for window
	 * @param newY
	 * 			new y location for window
	 * @param newWidth
	 * 			new width of window
	 * @param newHeight
	 * 			new height of window
	 * @param NewSquareColor
	 * 			new color for all squares in a specific window
	 */
	public Windows(int newX, int newY, int newWidth, int newHeight, 
			Color newSquareColor){
		
		winUpperLeftX = newX;
		winUpperLeftY = newY;
		winWidth = newWidth;
		winHeight = newHeight;
		squareColor = newSquareColor;
		
	}
	
	/**
	 * draw method that draws all windows with black borders except the 
	 * 		active window, which is drawn with a red border.
	 * @param g
	 * 			Graphics object for drawing
	 * @param front
	 * 			boolean that tells whether window to be drawn is the 
	 * 				front/active window
	 * @param color
	 * 			color of all squares in a specific window
	 */
	public void draw(Graphics g, boolean front, Color color) {
		
		// iterator placed at beginning of squares linked list
		ListIterator<Square> iter = squares.listIterator(); 
		
		g.setColor(Color.white);// window fill color
		
		//filled window w/o border
		g.fillRect(winUpperLeftX, winUpperLeftY, winWidth, winHeight); 
		
		// determine if the window is active and if so 
		//		sets border color red instead of black
		if(front == true)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		
		// draws border around window
		g.drawRect(winUpperLeftX, winUpperLeftY, winWidth, winHeight);
		
		// increases border around window
		g.drawRect(winUpperLeftX+1, winUpperLeftY+1, winWidth, winHeight);
		
		// increases border around window
		g.drawRect(winUpperLeftX+2, winUpperLeftY+2, winWidth, winHeight);	
		
		//shadow on right side
		g.drawLine(winUpperLeftX+winWidth+3, winUpperLeftY, winUpperLeftX+winWidth+3,
				winUpperLeftY+winHeight+2);
		g.drawLine(winUpperLeftX+winWidth+4, winUpperLeftY-1, winUpperLeftX+winWidth+4,
				winUpperLeftY+winHeight+1);
		g.drawLine(winUpperLeftX+winWidth+5, winUpperLeftY-2, winUpperLeftX+winWidth+5,
				winUpperLeftY+winHeight);
		g.drawLine(winUpperLeftX+winWidth+6, winUpperLeftY-3, winUpperLeftX+winWidth+6,
				winUpperLeftY+winHeight-1);
		g.drawLine(winUpperLeftX+winWidth+7, winUpperLeftY-3, winUpperLeftX+winWidth+7,
				winUpperLeftY+winHeight-2);
		//shadow on top
		g.drawLine(winUpperLeftX, winUpperLeftY+2, winUpperLeftX+winWidth+3, 
				winUpperLeftY+2);
		g.drawLine(winUpperLeftX, winUpperLeftY+1, winUpperLeftX+winWidth+4, 
				winUpperLeftY+1);
		g.drawLine(winUpperLeftX, winUpperLeftY, winUpperLeftX+winWidth+5, 
				winUpperLeftY);
		g.drawLine(winUpperLeftX+1, winUpperLeftY-1, winUpperLeftX+winWidth+6, 
				winUpperLeftY-1);
		g.drawLine(winUpperLeftX+2, winUpperLeftY-2, winUpperLeftX+winWidth+7, 
				winUpperLeftY-2);
		g.drawLine(winUpperLeftX+3, winUpperLeftY-3, winUpperLeftX+winWidth+7, 
				winUpperLeftY-3);
		
		
		
		
		
		//draws all squares in each window
		while(iter.hasNext()){			
			iter.next().draw(g); // call to Square draw method
		}// while end	
	}//draw end
	
	/**
	 * handles a user click inside the active window
	 * @param x
	 * 			x coordinate of user click
	 * @param y
	 * 			y coordinate of user click
	 */
	public void handleClick(int x, int y) {
		
		// boolean that indicates whether squares have been deleted from 
		//		the squares linked list.
		boolean deleted = false; 
		
		// iterator inserted at beginning of squares linked list
		ListIterator<Square> iter = squares.listIterator(); 
		
		// determines if click is inside another square and if so deletes 
		//		that square and all squares that intersect it.
		while(iter.hasNext()){
			if(iter.next().isInside(x,y)){
				iter.remove();
				deleted = true;
			}//if end
		}//while end
		
		//if nothing is deleted from the squares linked list then a new 
		//		square is created
		if(deleted == false){
			// makes sure that all parts of all boxes are within the 
			//		window boundary.
			if(x-10 >= winUpperLeftX && y-10 >= winUpperLeftY &&
					x+10 <= winUpperLeftX + winWidth && y+10 <= winUpperLeftY + 
						winHeight){
				Square s = new Square(x, y, SQ_WIDTH, SQ_HEIGHT, squareColor);
				squares.add(s);
			}//inner if end
		}//if end
	}//handleClick end
	
	/**
	 * 
	 * @param x
	 * 			x coordinate of user click
	 * @param y
	 * 			y coordinate of user click
	 * @return Returns true if the user click is inside a particular 
	 * 		window otherwise returns false
	 */
	public boolean isInside(int x, int y){
		
		if((x >= this.winUpperLeftX && x <= this.winUpperLeftX + winWidth) && 
				(y >= this.winUpperLeftY && y <= this.winUpperLeftY + winHeight)){
			return true;
		
		}else
			return false;	
	}//isInside end
}//Windows end
