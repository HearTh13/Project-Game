
package entity;

import main.GamePanel;

public class OBJ_Fireball extends Projectiles{
    
    GamePanel gp;
    
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Fire Ball";
        speed = 6;
        
        maxHealth = 80;
        health = maxHealth;
        attack = 4;
        manaCost = 1;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = setup("/projectiles/fireBall_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/fireBall_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/fireBall_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/fireBall_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/fireBall_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/fireBall_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/fireBall_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/fireBall_right1", gp.tileSize, gp.tileSize);
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
