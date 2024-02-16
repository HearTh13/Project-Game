package entity;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
        
    public OBJ_Door(GamePanel gp){
        super(gp);
        
        name = "Door";
        image = setup("/objects/door", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize-16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
