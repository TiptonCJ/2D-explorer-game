package edu.appstate.cs.projectname;

//package main;

// Importing classes
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

// GamePanel class that extends JPanel and implements Runnable for threading
public class GamePanel extends JPanel implements Runnable{

	// Screen settings
	final int originalTileSize = 16; // Original tile size (16x16)
	final int scale = 3; // Scale factor
	final int tileSize = originalTileSize * scale; // Scaled tile size (48x48)
	final int maxScreenCol = 16; // Maximum number of columns on the screen
	final int maxScreenRow = 12; // Maximum number of rows on the screen
	final int screenWidth = tileSize * maxScreenCol; // Screen width (768 pixels)
	final int screenHeight = tileSize * maxScreenRow; // Screen height (576 pixels)

	// Frame rate
	int FPS = 60;

	// KeyHandler object to handle key events
	KeyHandler keyH = new KeyHandler();
	// Thread for the game
	Thread gameThread;

	// Player's default position and speed
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	// Constructor for the GamePanel class
	public GamePanel () {
		// Setting the preferred size of the panel
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		// Setting the background color of the panel
		this.setBackground(Color.black);
		// Enabling double buffering to avoid flickering
		this.setDoubleBuffered(true);
		// Adding the key listener to the panel
		this.addKeyListener(keyH);
		// Making the panel focusable to receive key events
		this.setFocusable(true);
	}

	// Method to start the game thread
	public void startGameThread() {
		// Creating a new thread and starting it
		gameThread = new Thread(this);
		gameThread.start();
	}

	// The run method which will be executed in the new thread
	public void run() {	
		// Variables for controlling the frame rate
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount =0;

		// Game loop
		while(gameThread != null) {
			// Getting the current time
			currentTime = System.nanoTime();
			// Calculating the time difference and updating the timer
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			// If enough time has passed, update the game and redraw the screen
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			// If one second has passed, print the frame rate and reset the counter
			if(timer >= 1000000000) {
				System.out.println("FPS:"+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	// Method to update the game state
	public void update() {
		// Moving the player based on the keys pressed
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true) {	
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true) {	
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true) {	
			playerX += playerSpeed;
		}
	}

	// Method to draw the game state
	public void paintComponent(Graphics g) {
		// Calling the superclass's paintComponent method
		super.paintComponent(g);

		// Casting the Graphics object to Graphics2D for more features
		Graphics2D g2 = (Graphics2D)g;

		// Setting the color to white
		g2.setColor(Color.white);

		// Drawing the player as a white rectangle
		g2.fillRect(playerX ,playerY ,tileSize ,tileSize);

		// Disposing the Graphics2D object to free up system resources
		g2.dispose();
	}
}
