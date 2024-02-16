
package entity;

import main.GamePanel;

public class OBJ_Weapon_Chopping_Axe extends Entity{
    
    public OBJ_Weapon_Chopping_Axe(GamePanel gp) {
        super(gp);
        
        type = typeAxe;
        name = "Kapak";
        image = setup("/equipments/choppingAxe", gp.tileSize, gp.tileSize);
        down1 = image;
        attackValues = 3;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+name+"]\nSedikit lebih pendek dari pedang\nTetapi lebih sakit dari pedang.";
        price = 90;
    }
    
}
