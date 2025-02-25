package edu.appstate.cs.projectname;

import java.awt.image.BufferedImage;

// Entity class
public class Entity {
	
	// Declare variables for x and y coordinates
	public int x, y;
	
	// Declare variable for speed
	public int speed;
	
	// Declare BufferedImages for different directions and sprite numbers
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	// Declare variable for direction
	public String direction;
	
	// Declare variable for sprite counter
	public int spriteCounter = 0;
	
	// Declare variable for sprite number
	public int spriteNum = 1;
}
