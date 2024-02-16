package entity;

import main.GamePanel;

public class OBJ_Weapon_Cranium_Staff extends Entity{
    
    public OBJ_Weapon_Cranium_Staff(GamePanel gp) {
        super(gp);
        
        type = typeStaff;
        name = "Tongkat Cranium";
        image = setup("/equipments/craniumStaff", gp.tileSize, gp.tileSize);
        down1 = image;
        attackValues = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+name+"]\nA stick that can projectile magic.\nPretty cool huh.";
        price = 73;
    }
    
}
