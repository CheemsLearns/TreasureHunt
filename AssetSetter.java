package treasurehunt;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        // Example of setting an object in the game
        gp.obj[0] = new treasurehunt.object.OBJ_Key(); // Create a key object
        gp.obj[0].worldX = 23 * gp.tileSize; // Set the X position in the world
        gp.obj[0].worldY = 7 * gp.tileSize; // Set the Y position in the world

        gp.obj[1] = new treasurehunt.object.OBJ_Key(); 
        gp.obj[1].worldX = 23 * gp.tileSize; 
        gp.obj[1].worldY = 40 * gp.tileSize; 

        gp.obj[2] = new treasurehunt.object.OBJ_Key();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize; 

        gp.obj[3] = new treasurehunt.object.OBJ_Door(); 
        gp.obj[3].worldX = 10 * gp.tileSize; 
        gp.obj[3].worldY = 11 * gp.tileSize; 

        gp.obj[4] = new treasurehunt.object.OBJ_Door(); 
        gp.obj[4].worldX = 8 * gp.tileSize; 
        gp.obj[4].worldY = 28 * gp.tileSize; 

        gp.obj[5] = new treasurehunt.object.OBJ_Door(); 
        gp.obj[5].worldX = 12 * gp.tileSize; 
        gp.obj[5].worldY = 22 * gp.tileSize; 

        gp.obj[6] = new treasurehunt.object.OBJ_Chest(); 
        gp.obj[6].worldX = 10 * gp.tileSize; 
        gp.obj[6].worldY = 7 * gp.tileSize;   
        
        gp.obj[7] = new treasurehunt.object.OBJ_Boots(); 
        gp.obj[7].worldX = 37 * gp.tileSize; 
        gp.obj[7].worldY = 42 * gp.tileSize;   
    }

}
