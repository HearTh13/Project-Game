package entity;

import main.GamePanel;

public class OBJ_Arrow extends Entity{
    
    public OBJ_Arrow(GamePanel gp){
        super(gp);
        
        name = "Arrow";
        getImage();
        collision = true;
    }
    
    public void getImage(){
        //Player image into variable
        up1 = setup("/objects/arrow_up1", gp.tileSize, gp.tileSize);
        down1 = setup("/objects/arrow_down1", gp.tileSize, gp.tileSize);
        left1 = setup("/objects/arrow_left1", gp.tileSize, gp.tileSize);
        right1 = setup("/objects/arrow_right1", gp.tileSize, gp.tileSize);
    }
    
}
