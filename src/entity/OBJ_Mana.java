package entity;

import main.GamePanel;

public class OBJ_Mana extends Entity{
    
    public OBJ_Mana(GamePanel gp){
        super(gp);
        name = "Mana";
        type = type_pickup;
        image = setup("/objects/energy", gp.tileSize, gp.tileSize);
        down1 = image;
    }
    
    public void setValue(int val){
        value = val;
    }
    
    public void use (Entity entity){
        gp.playSE(19);
        gp.ui.addMessage("Mana +"+value);
        entity.mana += value;
    }
}

