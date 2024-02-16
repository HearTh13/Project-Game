package entity;

import main.GamePanel;

public class OBJ_Weapon_Cranium_Sword extends Entity{
    
    public OBJ_Weapon_Cranium_Sword(GamePanel gp) {
        super(gp);
        
        type = typeSword;
        name = "Pedang Cranium";
        image = setup("/equipments/craniumSword", gp.tileSize, gp.tileSize);
        down1 = image;
        attackValues = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "["+name+"]\nPedang rongsokan...";
        price = 39;
    }
    
}
