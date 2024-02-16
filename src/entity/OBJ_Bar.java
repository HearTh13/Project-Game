package entity;

import main.GamePanel;

public class OBJ_Bar extends Entity{
    
    public OBJ_Bar(GamePanel gp) {
        super(gp);
        image = setup("/objects/lifeBar", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manaBar", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/bar", gp.tileSize, gp.tileSize);
    }
    
}
