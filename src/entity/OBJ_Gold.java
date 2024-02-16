package entity;

import main.GamePanel;

public class OBJ_Gold extends Entity{
    
    GamePanel gp;
    
    public OBJ_Gold(GamePanel gp){
        super(gp);
        
        this.gp = gp;
        
        type = type_pickup;
        name = "Gold";
        image = setup("/objects/coin", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
    }
    
    public void setValue(int val){
        value = val;
    }
    
    public void use (Entity entity){
        gp.playSE(21);
        gp.ui.addMessage("Gold +"+value);
        entity.money += value;
    }
    
}
