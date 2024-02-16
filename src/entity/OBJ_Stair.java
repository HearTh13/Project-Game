package entity;

import main.GamePanel;

public class OBJ_Stair extends Entity{
    public OBJ_Stair(GamePanel gp){
        super(gp);
        
        name = "Stair";
        image = setup("/objects/stair", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = false;
    }
}
