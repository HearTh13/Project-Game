package entity;

import main.GamePanel;

public class OBJ_Key extends Entity{
    
    public OBJ_Key(GamePanel gp){
        super(gp);
        
        name = "Kunci";
        image = setup("/objects/key", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        description = "["+name+"]\nBerguna untuk membuka pintu.";
        price = 10;
    }
}
