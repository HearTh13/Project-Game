
package entity;

import main.GamePanel;

public class Projectiles extends Entity{
    
    Entity user;
    
    public Projectiles(GamePanel gp) {
        super(gp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.health = this.maxHealth;
    }
    public void update(){
        
        if (user == gp.player) {
            int monsterIndex = gp.cc.checkEntity(this, gp.mon);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        if (user != gp.player) {
            boolean contactPlayer = gp.cc.checkPlayer(this);
            if (!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
                alive = false;
            }
        }
        
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
        
        health--;
        if (health <= 0) {
            alive = false;
        }
        
        spriteCounter++;
        if (spriteCounter > 12) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0;
        }
    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user){
        
    }
    
}
