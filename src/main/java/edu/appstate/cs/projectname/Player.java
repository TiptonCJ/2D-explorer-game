package edu.appstate.cs.projectname;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// Player class extends the Entity class
public class Player extends Entity{
	
	// Declare GamePanel and KeyHandler objects
	GamePanel gp;
	KeyHandler keyH;
	
	// Player constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		
	// Initialize GamePanel and KeyHandler objects
	this.gp = gp;
	this.keyH = keyH;
	
	// Call methods to set default values and get player image
	setDefaultValues();
	getPlayerImage();
	}
	
	// Method to set default values for player
	public void setDefaultValues() {
		
		x = 100; // Set x-coordinate
		y = 100; // Set y-coordinate
		speed = 4; // Set speed
		direction = "down"; // Set initial direction
	}
	
	// Method to get player image
	public void getPlayerImage() {
		
		try {
			
			// Read player images from resources
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/YOSEF_right_2.png"));
		
		}catch(IOException e) {
			e.printStackTrace(); // Print stack trace for IOException
		}
	}
	
	// Method to update player state
	public void update() {
		
		// Check if any key is pressed
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || 
				keyH.rightPressed == true) {
			// Moving the player based on the keys pressed
			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if(keyH.downPressed == true) {	
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true) {	
				direction = "left";
				x -= speed;
			}
			else if(keyH.rightPressed == true) {	
				direction = "right";
				x += speed;
			}
			
			// Change animation of YOSEF so it walks
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	// Method to draw player
	public void draw(Graphics2D g2) {
	
		BufferedImage image = null;
	
		// Select image based on direction and sprite number
		if (direction == "up") {
		    if (spriteNum == 1) {
		        image = up1;
		    }
		    if (spriteNum == 2) {
		        image = up2;
		    }
		} else if (direction == "down") {
		    if (spriteNum == 1) {
		        image = down1;
		    }
		    if (spriteNum == 2) {
		        image = down2;
		    }
		} else if (direction == "left") {
		    if (spriteNum == 1) {
		        image = left1;
		    }
		    if (spriteNum == 2) {
		        image = left2;
		    }
		} else if (direction == "right") {
		    if (spriteNum == 1) {
		        image = right1;
		    }
		    if (spriteNum == 2) {
		        image = right2;
		    }
		}

		// Draw the selected image
		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
	}
}
