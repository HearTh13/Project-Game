package interactives;

import entity.Entity;
import main.GamePanel;

public class Trunk extends interactives.InteractiveObject{
    GamePanel gp;

    public Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        
        down1 = setup("/interactives/Tree_Destroyed", gp.tileSize, gp.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    
}
