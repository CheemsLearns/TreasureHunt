package treasurehunt;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //3x scale

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16; //16 columns
    public final int maxScreenRow = 12; //12 rows
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    //WORLD SETTINGS
    public final int maxWorldCol = 50; //50 columns in the world
    public final int maxWorldRow = 50; //50 rows in the world
    public final int worldWidth = tileSize * maxWorldCol; //World width
    public final int worldHeight = tileSize * maxWorldRow; //World height

    int FPS = 60; //Frames per second

    TileManager tileM = new TileManager(this); 
    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; //for game loop
    public CollisionChecker cChecker = new CollisionChecker(this); // Create a collision checker instance
    public Player player = new Player(this, keyH); // Create a player instance

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; 

    public GamePanel() {
        // Set the preferred size of the game panel
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black); // Set the background color
        this.setDoubleBuffered(true); //for smoother rendering
        this.addKeyListener(keyH); // Add the key listener for input handling
        this.setFocusable(true); 
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Start the game loop
    }

    @Override
       public void run() {
        double drawInterval = 1000000000 / FPS; 
        double delta = 0;
        long lastTime = System.nanoTime(); 
        long currentTime;
        long timer = 0;
        long drawCount = 0;  

        while (gameThread != null) {
            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Calculate the time difference
            timer += (currentTime - lastTime); // Update the timer
            lastTime = currentTime; // Update lastTime to current time

            if (delta >= 1) { // If enough time has passed
                update(); // Update game logic
                repaint(); // Repaint the panel
                delta--; // Decrease delta by 1
                drawCount++; // Increment the draw count
            }
            if (timer >= 1000000000) { // If 1 second has passed
                System.out.println("FPS: " + drawCount); // Print the frames per second
                drawCount = 0; // Reset the draw count
                timer = 0; // Reset the timer
            }
        }}
       
    public void update() {

       player.update(); 
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); // Call the superclass method to clear the panel
        Graphics2D g2 = (Graphics2D) g; 
        tileM.draw(g2); 
        player.draw(g2); 
        g2.dispose();

    }
}