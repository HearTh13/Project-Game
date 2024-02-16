package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, 
            attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogue = new String[20];
    
    //State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    protected int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    protected boolean attacking = false;
    public boolean alive = true;
    public boolean dead = false;
    public boolean HPBarOn = false;
    
    //Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    protected int deadCounter = 0;
    protected int HPBarCounter = 0;
    public int shotCounter = 0;
    
    //Character Attributes
    public String name;
    public int speed;
    public int maxHealth;
    public int health;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int toNextLevel;
    public int money;
    public Entity currentWeapon;
    public Entity currentProtectives;
    public Projectiles projectile;
    public Projectiles projectile2;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    
    //Item Attributes
    int value;
    public int attackValues;
    public int defenseValues;
    public String description = "";
    public int manaCost;
    public int price;
    
    //Type
    public int type;
    public final int typePlayer = 0;
    public final int typeNPC = 1;
    public final int typeMonster = 2;
    public final int typeSword = 3;
    public final int typeAxe = 4;
    public final int typeStaff = 5;
    public final int typeProtectives = 6;
    public final int typeConsumables = 7;
    public final int type_pickup = 8;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkDrop(){};
    public void dropItem(Entity dropped){
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = dropped;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    };
    public void setValue(int val){};
    public void setAction(){}
    public void damageReaction(){}
    public void speak(){
        if (dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;
        
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
    }
    public void use (Entity entity){}
    public void update(){
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
    public void damagePlayer(int attack){
        if (!gp.player.invincible) {
            gp.playSE(25);

            int damage = attack*attack/gp.player.defense;
            if (damage < 0) {
                damage = 0;
            }

            gp.player.health -= damage;

            gp.player.health--;
            gp.player.invincible = true;
        }
    }
    public void draw (Graphics2D g2){
        
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
            switch(direction){
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    else if (spriteNum == 2) {
                        image = up2;
                    }
                    else if (spriteNum == 3) {
                        image = up3;
                    }
                    else if (spriteNum == 4) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    else if (spriteNum == 2) {
                        image = down2;
                    }
                    else if (spriteNum == 3) {
                        image = down3;
                    }
                    else if (spriteNum == 4) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    else if (spriteNum == 2) {
                        image = left2;
                    }
                    else if (spriteNum == 3) {
                        image = left3;
                    }
                    else if (spriteNum == 4) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    else if (spriteNum == 2) {
                        image = right2;
                    }
                    else if (spriteNum == 3) {
                        image = right3;
                    }
                    else if (spriteNum == 4) {
                        image = right2;
                    }
                    break;
            }
            
            //Monster Health Bar
            if (type == typeMonster && HPBarOn) {
                
                double healthScale = (double)gp.tileSize/maxHealth;
                double HPBar = healthScale * health;
                
                g2.setColor(Color.darkGray);
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(Color.red);
                g2.fillRect(screenX, screenY - 15, (int)HPBar, 10);
                
                HPBarCounter++;
                
                if (HPBarCounter > 300) {
                    HPBarCounter = 0;
                    HPBarOn = false;
                }
                
            }
            
            if (invincible) {
                HPBarOn = true;
                HPBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dead) {
                deadAnimation(g2);
            }
            
            g2.drawImage(image, screenX, screenY, null);
            
            changeAlpha(g2, 1f);
            
        }
    }
    public void deadAnimation(Graphics2D g2){
        deadCounter++;
        
        int i = 5;
        
        if (deadCounter <= i
                || deadCounter > i*2 && deadCounter <= i*3 
                || deadCounter > i*4 && deadCounter <= i*5
                || deadCounter > i*6 && deadCounter <= i*7) {
            changeAlpha(g2, 0f);
        }
        if (deadCounter > i && deadCounter <= i*2 
                || deadCounter > i*3 && deadCounter <= i*4
                || deadCounter > i*5 && deadCounter <= i*6
                || deadCounter > i*7 && deadCounter <= i*8) {
            changeAlpha(g2, 1f);
        }
        if (deadCounter > i*8) {
            alive = false;
        }
    }    
    public void changeAlpha(Graphics2D g2, float value){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
    }    
    public BufferedImage setup(String filePath, int width, int height){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(filePath + ".png"));
            image = tool.scaledImage(image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
