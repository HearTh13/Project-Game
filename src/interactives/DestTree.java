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
        health = 3;
    }
    
    public void playSE(){
        gp.playSE(28);
    }
    
    public InteractiveObject getDestroyedForm(){
        InteractiveObject object = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return object;
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        
        if (entity.currentWeapon.type == typeAxe || entity.currentWeapon.type == typeSword) {
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    
}
