package entity;

import main.GamePanel;

public class OBJ_Cloack extends Entity{
    
    public OBJ_Cloack(GamePanel gp){
        super(gp);
        
        type = typeProtectives;
        name = "Jubah";
        image = setup("/equipments/cloack", gp.tileSize, gp.tileSize);
        down1 = image;
        defenseValues = 2;
        description = "["+name+"]\nApakah aku bisa tidak terlihat\njika memakai ini?.";
        price = 75;
    }
}
