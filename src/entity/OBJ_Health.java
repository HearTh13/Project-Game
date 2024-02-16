package entity;

import main.GamePanel;

public class OBJ_Health extends Entity{
    
    public OBJ_Health(GamePanel gp){
        super(gp);
        name = "Health";
        type = type_pickup;
        image = setup("/objects/health", gp.tileSize, gp.tileSize);
        down1 = image;
    }
    
    public void setValue(int val){
        value = val;
    }
    
    public void use (Entity entity){
        gp.playSE(20);
        gp.ui.addMessage("Health +"+value);
        entity.health += value;
    }
}

