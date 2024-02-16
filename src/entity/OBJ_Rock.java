
package entity;

import main.GamePanel;

public class OBJ_Rock extends Projectiles{
    
    GamePanel gp;
    
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Rock";
        speed = 6;
        
        maxHealth = 30;
        health = maxHealth;
        attack = 6;
        manaCost = 1;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = up2 = down1 = down2 = left1 = left2 = right1 = right2 = setup("/projectiles/rock", gp.tileSize, gp.tileSize);
    }
    
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if (user.mana >= manaCost) {
            haveResource = true;
        }
        return haveResource;
    }
    
    public void subtractResource(Entity user){
        user.mana -= manaCost;
    }
    
}
