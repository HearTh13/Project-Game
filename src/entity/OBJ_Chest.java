package entity;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
    
    public OBJ_Chest(GamePanel gp){
        super(gp);
        
        name = "Harta Karun";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
    }
}
