package entity;

import java.util.Random;
import main.GamePanel;

public class MON_Slime extends Entity{
    
    public MON_Slime(GamePanel gp) {
        super(gp);
        
        type = typeMonster;
        name = "Slime";
        speed = 1;
        maxHealth = 15;
        health = maxHealth;
        attack = 2;
        defense = 3;
        exp = 2;
        projectile = new OBJ_SlimeBall(gp);
        
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = gp.tileSize-(4*2);
        solidArea.height = gp.tileSize-16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/monster/Slime_back1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/Slime_back2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/Slime_back3", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/Slime_front1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/Slime_front2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/Slime_front3", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/Slime_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/Slime_left2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/Slime_left3", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/Slime_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/Slime_right2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/Slime_right3", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25) {
                direction = "up";
            }
            else if (i > 25 && i <= 50) {
                direction = "down";
            }
            else if (i > 50 && i <= 75) {
                direction = "left";
            }
            else if (i > 75 && i <= 100) {
                direction = "right";
            }
            
            actionLockCounter = 0;
        }
        
        //Shoot randomly
        int x = new Random().nextInt(100)+1;
        if (x > 99 && !projectile.alive && shotCounter == 60) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotCounter = 0;
        }
        
    }
    
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    
    public void checkDrop(){
        
        int i = new Random().nextInt(100)+1;
        if (i < 50) {
            dropItem(new OBJ_Gold(gp){{
                int x = new Random().nextInt(5)+1;
                setValue(x);
            }});
            
        }
        
        i = new Random().nextInt(100)+1;
        if (i < 30) {
            dropItem(new OBJ_Health(gp){{
                int x = new Random().nextInt(9)+4;
                setValue(x);
            }});
        }
        
        i = new Random().nextInt(100)+1;
        if (i < 20) {
            dropItem(new OBJ_Mana(gp){{
                int x = new Random().nextInt(9)+2;
                setValue(x);
            }});
        }
        
    }
    
}
