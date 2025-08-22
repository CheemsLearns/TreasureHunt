package treasurehunt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
      GamePanel gp;
      Font arial_40, arial_80B;
      BufferedImage keyImage;
      public boolean messageOn = false; // Flag to control message display
      public String message = ""; // Message to display
      int messageCounter = 0; // Counter for message display duration
      public boolean gameFinished = false; // Flag to indicate if the game is finished

      double playTime ; // Placeholder for platform time, not used in this context
      DecimalFormat df = new DecimalFormat("#0.00"); // Format for displaying time

      public UI(GamePanel gp) {
      this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40); // Initialize the font
        arial_80B = new Font("Arial", Font.BOLD, 80); // Initialize the font
        treasurehunt.object.OBJ_Key key = new treasurehunt.object.OBJ_Key(); // Create an instance of the key object
        keyImage = key.image; // Get the image from the key object
    }

    public void showMessage(String text) {
        message = text; // Set the message to display
        messageOn = true; // Enable message display 
        }

      public void draw(Graphics2D g2) {

        if(gameFinished == true){

          g2.setFont(arial_40); // Set the font for the graphics context
          g2.setColor(Color.WHITE); // Set the color to yellow

          String text;
          int textLength;
          int x, y;

          text = "You Found The Treasure!";
          textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
          x = gp.screenWidth / 2 - textLength / 2; // Center the text horizontally
          y = gp.screenHeight / 2 - (gp.tileSize * 3); // Center the text vertically
          g2.drawString(text, x, y); // Set the font for the graphics context

          text = "Your Time Is : " + df.format(playTime) + " secs";
          textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
          x = gp.screenWidth / 2 - textLength / 2; // Center the text horizontally
          y = gp.screenHeight / 2 + (gp.tileSize * 4); // Center the text vertically
          g2.drawString(text, x, y); // Set the font for the graphics context

          g2.setFont(arial_80B); // Set the font for the graphics contextw
          g2.setColor(Color.YELLOW); // Set the color to yellow
          text = "Congratulations!";
          textLength = (int)g2.getFontMetrics(arial_80B).getStringBounds(text, g2).getWidth();
          x = gp.screenWidth / 2  - textLength / 2; // Center the text horizontally
          y = gp.screenHeight / 2 + (gp.tileSize*2); // Move down by two tile sizes
          g2.drawString(text, x, y); // Draw the congratulations text

          gp.gameThread = null; // Stop the game thread
        }
        
        else {
        g2.setFont(arial_40); // Set the font for the graphics context
        g2.setColor(java.awt.Color.WHITE); // Set the color to white
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null); // Draw the key image at the top left corner
        g2.drawString("x" + gp.player.hasKey, 74, 65); // Draw the score at the top left corner

        //TIME
        playTime += (double) 1/60; // Calculate the playtime in minutesTime
        g2.drawString("Time: " + df.format(playTime) , gp.tileSize*11, 65); // Draw the playtime at the top right corner

        // MESSAGE
        if(messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F)); // Set the color to yellow for the message
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5); // Draw the message at specified coordinates

            messageCounter++; // Increment the message counter
            if(messageCounter > 120) { // If the message has been displayed for more than
            messageOn = false; // Disable message display
            messageCounter = 0; // Reset the message counter
          }
        }
      }
    }
  }