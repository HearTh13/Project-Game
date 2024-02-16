package entity;

import main.GamePanel;

public class OBJ_Table extends Entity{
    
    public OBJ_Table(GamePanel gp){
        super(gp);
        
        name = "Table";
        image = setup("/objects/table", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
    }
}
