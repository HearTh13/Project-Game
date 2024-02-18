package interactives;

import entity.Entity;
import main.GamePanel;

public class DestTree extends interactives.InteractiveObject{
    GamePanel gp;

    public DestTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        
        down1 = setup("/interactives/Destructable_Tree", gp.tileSize, gp.tileSize);
        destructible = true;
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        
        if (entity.currentWeapon.type == typeAxe || entity.currentWeapon.type == typeSword) {
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    
}
