package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import treasurehunt.GamePanel;
import treasurehunt.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX; 
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize/2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize/2); // Center the player on the screen

        solidArea = new Rectangle(8, 16, 32, 32); 
        setDefaultValues();
        getPlayerImage();
}

    public void setDefaultValues() {
        worldX=gp.tileSize * 23; 
        worldY=gp.tileSize * 21;
        speed=4;
        direction = "down"; 
    }
    public void getPlayerImage() {
        try{
             up1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
             up2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
             down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
             down2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
             left1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
             left2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
             right1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
             right2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
            } 
            catch (IOException e) {
            e.printStackTrace();
            }
        }

     public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true 
            || keyH.leftPressed == true || keyH.rightPressed == true) 
        { 
         if(keyH.upPressed == true) {
            direction = "up";
        }
        else if(keyH.downPressed == true) {
            direction = "down";
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
        }

        // Check for collision with tiles
        collisionOn = false; // Reset collision status
        gp.cChecker.checkTile(this); // Check for tile collisions

        // If collision is detected, player can move
        if(collisionOn == false) {
            // Update the player's position
            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0; // Reset the counter
        }
    }}

    public void draw(java.awt.Graphics2D g2){
         
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                else if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                else if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                else if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                else if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}