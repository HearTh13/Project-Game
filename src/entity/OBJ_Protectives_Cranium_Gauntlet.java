package entity;

import main.GamePanel;

public class OBJ_Protectives_Cranium_Gauntlet extends Entity{
    
    public OBJ_Protectives_Cranium_Gauntlet(GamePanel gp) {
        super(gp);
        
        type = typeProtectives;
        name = "Gauntlet Cranium";
        image = setup("/equipments/craniumGauntlet", gp.tileSize, gp.tileSize);
        down1 = image;
        defenseValues = 1;
        description = "["+name+"]\nGauntlet ini sudah berlumut.";
        price = 24;
    }
    
}
