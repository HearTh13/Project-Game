package tile;

import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    
    public TileManager (GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.mp", 0);
        loadMap("/maps/map02.mp", 1);
        loadMap("/maps/map03.mp", 2);
        loadMap("/maps/map04.mp", 3);
        loadMap("/maps/map05.mp", 4);
    }
    
    public void getTileImage(){
        setupTile(0, "grass", false);
        setupTile(1, "wall", true);
        setupTile(2, "water", true);
        setupTile(3, "waterTop", true);
        setupTile(4, "waterBottom", true);
        setupTile(5, "waterLeft", true);
        setupTile(6, "waterRight", true);
        setupTile(7, "waterTopLeft", true);
        setupTile(8, "waterTopRight", true);
        setupTile(9, "waterBottomLeft", true);
        setupTile(10, "waterBottomRight", true);
        setupTile(11, "dirt", false);
        setupTile(12, "tree", true);
        setupTile(13, "sand", false);
        setupTile(14, "floor", false);
        setupTile(15, "darkFloor", false);
        setupTile(16, "darkDirt", false);
    }
    
    public void setupTile(int i, String imagePath, boolean collision){
        UtilityTool tool = new UtilityTool();
        
        try{
            tile[i] = new Tile();
            tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[i].image = tool.scaledImage(tile[i].image, gp.tileSize, gp.tileSize);
            tile[i].collision = collision;
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath, int map){
        try{
            
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        }catch(Exception e){
            
        }
        
    }
    
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            
            }
            
            worldCol++;
            
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
    }
    
}
