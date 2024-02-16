package entity;

import java.util.Random;
import main.GamePanel;

public class MON_RockMonster extends Entity{
    public MON_RockMonster(GamePanel gp) {
        super(gp);
        
        type = typeMonster;
        name = "Rock Monster";
        speed = 2;
        maxHealth = 120;
        health = maxHealth;
        attack = 5;
        defense = 6;
        exp = 50;
        projectile = new OBJ_Rock(gp);
        
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = gp.tileSize-(4*2);
        solidArea.height = gp.tileSize-16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/monster/rockMonster_back1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/rockMonster_back2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/rockMonster_back1", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/rockMonster_front1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/rockMonster_front2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/rockMonster_front1", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/rockMonster_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/rockMonster_left2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/rockMonster_left1", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/rockMonster_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/rockMonster_right2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/rockMonster_right1", gp.tileSize, gp.tileSize);
    }
    
    @Override
    public void setAction(){
        
        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            speed = 3;
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
    
    @Override
    public void damageReaction(){
        actionLockCounter = 0;
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
            case "down":
                direction = "up";
                break;
        }
        speed = 0;
    }
    
    public void checkDrop(){
        
        int i = new Random().nextInt(100)+1;
        
        if (i < 44) {
            dropItem(new OBJ_Gold(gp){{
                int x = new Random().nextInt(25)+7;
                setValue(x);
            }});
            
        }
        
        i = new Random().nextInt(100)+1;
        if (i < 29) {
            dropItem(new OBJ_Health(gp){{
                int x = new Random().nextInt(21)+10;
                setValue(x);
            }});
        }
        
        i = new Random().nextInt(100)+1;
        if (i < 47) {
            dropItem(new OBJ_Mana(gp){{
                int x = new Random().nextInt(15)+7;
                setValue(x);
            }});
        }
    }
    
}
