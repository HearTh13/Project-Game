
package entity;

import java.util.Random;
import main.GamePanel;

public class MON_Minotaur extends Entity{
    public MON_Minotaur(GamePanel gp) {
        super(gp);
        
        type = typeMonster;
        name = "Minotaur";
        speed = 3;
        maxHealth = 500;
        health = maxHealth;
        attack = 7;
        defense = 10;
        exp = 600;
        projectile = new OBJ_Rock(gp);
        
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = gp.tileSize-(4*2);
        solidArea.height = gp.tileSize-16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    @Override
    public void update(){
        if (gp.ui.boss) {
            setAction();

            collisionOn = false;
            gp.cc.CheckTile(this);
            gp.cc.checkObject(this, false);
            gp.cc.checkEntity(this, gp.npc);
            gp.cc.checkEntity(this, gp.mon);
            boolean contactPlayer = gp.cc.checkPlayer(this);

            if (this.type == typeMonster && contactPlayer) {
                damagePlayer(attack);
            }

            //If collision is false, player can move
            if (collisionOn == false) {
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;

            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else if (spriteNum == 3) {
                    spriteNum = 4;
                }
                else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotCounter < 60) {
                shotCounter++;
            }
        }
    }
    
    public void getImage(){
        up1 = setup("/monster/minotaur_back1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/minotaur_back2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/minotaur_back3", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/minotaur_front1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/minotaur_front2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/minotaur_front3", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/minotaur_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/minotaur_left2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/minotaur_left3", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/minotaur_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/minotaur_right2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/minotaur_right3", gp.tileSize, gp.tileSize);
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
}
