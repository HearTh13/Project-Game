
package entity;

import main.GamePanel;

public class OBJ_FairyBeam extends Projectiles{
    
    GamePanel gp;
    
    public OBJ_FairyBeam(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Fairy Beam";
        speed = 7;
        
        maxHealth = 60;
        health = maxHealth;
        attack = 4;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = up2 = down1 = down2 = setup("/projectiles/fairyBeam_vertical", gp.tileSize, gp.tileSize);
        left1 = left2 = right1 = right2 = setup("/projectiles/fairyBeam_horizontal", gp.tileSize, gp.tileSize);
    }
    
}
