package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import treasurehunt.GamePanel;
public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // Assuming we have 10 types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Initialize the map tile numbers
        getTileImage();
        loadMap();
    }
        private void getTileImage() {
            try{
                tile[0] = new Tile();
                tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/grass.png"));

                tile[1] = new Tile();
                tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/wall.png"));
                tile[1].collision = true; // Set collision for wall tile

                tile[2] = new Tile();
                tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/water.png"));
                tile[2].collision = true; // Set collision for water tile

                tile[3] = new Tile();
                tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/earth.png"));

                tile[4] = new Tile();
                tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/tree.png"));
                tile[4].collision = true; // Set collision for tree tile

                tile[5] = new Tile();
                tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles1/old version/sand.png"));
            }
                catch(IOException e) {
                    e.printStackTrace();
                }
}

public void loadMap() {
    try{
        InputStream is = getClass().getResourceAsStream("/res/maps/map02.txt");
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            String line = br.readLine();
            while(col < gp.maxWorldCol) {
                String numbers[] = line.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        br.close();

    }catch(Exception e) {}
}

    public void draw(Graphics2D g2) {
        
       int worldCol = 0;
       int worldRow = 0;

       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

              int tileNum = mapTileNum[worldCol][worldRow];

              int worldX = worldCol * gp.tileSize; // Calculate the world X position
              int worldY = worldRow * gp.tileSize; // Calculate the world Y position
              int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
              int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

              if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                 worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                 worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                 worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {


           g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Draw grass tile
                 
                  }
                   worldCol++;
                 // If the tile is not within the player's view, skip drawing it
           if(worldCol == gp.maxWorldCol) {
               worldCol = 0;
               worldRow++;
           }
       }
    }

}
