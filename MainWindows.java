/**
 * Name: Grady Barrett
 * Assignment: Lab #5
 * Title: Linked Lists and Window Managers
 * Course: CSCE 270
 * Lab Section: 2
 * Semester: Spring 2010
 * Instructor: David Wolff
 * Date: 3/25/10
 * Sources consulted: Dr. Wolff
 * Program description: Creates 6 windows inside a GUI that can 
 * 						be rearranged by clicking on them.  
 * 						Small boxes can be added to the active window.
 * Known Bugs: none
 * Creativity: Each window has its own unique color of squares,  
 * 			   the border thicknesses of the windows has been increased,
 * 			   and there is a shadow on each window as if light is 
 * 			   shining from the bottom left.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ListIterator;
import java.util.Random;

/**
 * Manages creation of window and box objects within the GUI. 
 * @author Grady Barrett
 * @implements SimpleWindowManager
 */
public class MainWindows implements SimpleWindowManager {
		
	final int WIN_WIDTH = 400; // constant window width
	final int WIN_HEIGHT = 300; // constant window height
	Color squaresColor;
	
	//linked list of window objects
	KWLinkedList<Windows> windows = new KWLinkedList<Windows>(); 
	
	/**
	 * Constructor that loads the windows linked list with 
	 * 		6 window objects placed at random locations 
	 * 		within the GUI.
	 */
	public MainWindows(){
		for(int i = 0; i < 6; i++){
			
			// for generating random window x,y coordinates and 
			//		RGB values.
			Random rand = new Random(); 
			
			// random RBG values for constructing a random color.
			int randR = rand.nextInt(255);
			int randG = rand.nextInt(255);
			int randB = rand.nextInt(255);
			
			//new color constructed from random RGB values
			Color color = new Color(randR, randG, randB);
			
			squaresColor = color;
			int randX = rand.nextInt(400); // random window x coordinate
			int randY = rand.nextInt(300); // random window y coordinate
			
			// new window object
			Windows w = new Windows(randX, randY, WIN_WIDTH, WIN_HEIGHT, color);
			
			windows.add(w); //window object loaded into windows linked list
		}//for loop end
	}//MainWindows end
	
	/**
	 * draw method that calls Windows draw method, determines which window 
	 * 		is the front window and provides that information to the Windows 
	 * 		draw method.
	 * @param g
	 * 			Graphics object for drawing
	 */
	public void draw(Graphics g) {
		
		// iterator for windows list
		ListIterator<Windows> iter = windows.listIterator(); 
		
		// indicates whether window object should be active window
		boolean frontWindow = false;  
		
		//determines which window object should be the active window then 
		//		calls draw method
		while(iter.hasNext()){
			
			// windows object to reference iter.next() object reference
			Windows w = iter.next(); 
			
			// if iterator passes last item in the list it is the front window
			if (!iter.hasNext()){
				frontWindow = true;
				w.draw(g, frontWindow, squaresColor); // call to Windows class draw 
			}else // all other windows are not the front window
				w.draw(g, frontWindow, squaresColor); // call to Windows class draw
		}// while loop end
	}// draw end

	/**
	 * Handles user clicks. Calls Windows isInside to determine which window 
	 * 		click falls in and reorders linked list so clicked window is at 
	 * 		the end of the list( makes it the active window)
	 * @param x 
	 * 			x coordinate of click
	 * @param y 
	 * 			y coordinate of click
	 */
	public void handleClick(int x, int y) {
		
		//iterator inserted at end of linked list
		ListIterator<Windows> iter = windows.listIterator(6); 
		
		while(iter.hasPrevious()){
			
			// variable to store iter.previous() returned window
			Windows windowToMove = iter.previous(); 
			
			//checks if click fell inside windowToMove window
			if(windowToMove.isInside(x,y) == true){
				//checks to see if click falls within active window.  
				//		If so calls Windows class handleClick
				if(iter.nextIndex() == 5){
					windowToMove.handleClick(x, y);
				}else{
					// remove clicked on window from current location in windows 
					//		linked list
					iter.remove();  
					// put clicked on window object at end of windows linked list
					windows.add(windowToMove); 	
				}
				return; // return to exit from while loop.
			}// if end
		}// while end
	}// handleClick end
}
