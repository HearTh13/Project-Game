
package entity;

import main.GamePanel;

public class OBJ_SlimeBall extends Projectiles{
    
    GamePanel gp;
    
    public OBJ_SlimeBall(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Slime Ball";
        speed = 6;
        
        maxHealth = 80;
        health = maxHealth;
        attack = 2;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = up2 = down1 = down2 = left1 = left2 = right1 = right2 = setup("/projectiles/slimeBall", gp.tileSize, gp.tileSize);
    }
    
}
