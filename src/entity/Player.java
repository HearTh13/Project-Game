package entity;

import main.*;
import inputs.*;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity{
    
    private KeyboardInputs key;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCancel;
    
    public Player(GamePanel gp, KeyboardInputs key){
        
        super(gp);
        
        this.key = key;
        
        name = "Hero";
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle(8, 16, 32, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
        //Set Scene back to 0
        gp.ui.scene = 0;
        
        //Set Player Position
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        
        //Player Speed
        speed = 4;
        
        //Player Direction
        direction = "down";
        
        //Player Status
        level = 1;
        maxHealth = 15;
        health = maxHealth;
        maxMana = 10;
        mana = maxMana;
        strength = 2;
        dexterity = 2;
        exp = 0;
        toNextLevel = 5;
        money = 0;
        currentWeapon = new OBJ_Weapon_Cranium_Sword(gp);
        currentProtectives = new OBJ_Protectives_Cranium_Gauntlet(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
        invincible = false;
    }
    public void setDefaultPosition(){
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        direction = "down";
    }
    public void setDefaultPosition(int x, int y, String direction){
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        this.direction = direction;
    }
    public void restoreStatus(){
        health = maxHealth;
        mana = maxMana;
        invincible = false;
    }
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentProtectives);
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return strength*currentWeapon.attackValues;
    }
    public int getDefense(){
        return dexterity*currentProtectives.defenseValues;
    }
    public void getPlayerImage(){
        //Player image into variable
        up1 = setup("/player/playerBack1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/playerBack2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/playerBack3", gp.tileSize, gp.tileSize);
        down1 = setup("/player/playerFront1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/playerFront2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/playerFront3", gp.tileSize, gp.tileSize);
        left1 = setup("/player/playerLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/playerLeft2", gp.tileSize, gp.tileSize);
        left3 = setup("/player/playerLeft3", gp.tileSize, gp.tileSize);
        right1 = setup("/player/playerRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/playerRight2", gp.tileSize, gp.tileSize);
        right3 = setup("/player/playerRight3", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        if (currentWeapon.type == typeSword) {
            attackUp1 = setup("/player/player_back_battler1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/player_back_battler2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/player_front_battler1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/player_front_battler2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/player_left_battler1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/player_left_battler2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/player_right_battler1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/player_right_battler2", gp.tileSize*2, gp.tileSize);
        }
        else if (currentWeapon.type == typeAxe) {
            attackUp1 = setup("/player/player_back_axe1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/player_back_axe2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/player_front_axe1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/player_front_axe2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/player_left_axe1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/player_left_axe2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/player_right_axe1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/player_right_axe2", gp.tileSize*2, gp.tileSize);
        }
        else if (currentWeapon.type == typeStaff) {
            attackUp1 = setup("/player/player_back_staff1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/player_back_staff2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/player_front_staff1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/player_front_staff2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/player_left_staff1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/player_left_staff2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/player_right_staff1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/player_right_staff2", gp.tileSize*2, gp.tileSize);
        }
    }
    public void update(){
        
        if (attacking) {
            attack();
        }
        else if (key.upPressed||key.downPressed||key.leftPressed||
                key.rightPressed || key.enter) {
            if (key.upPressed) {
                direction = "up";
            }
            else if (key.downPressed) {
                direction = "down";
            }
            else if (key.leftPressed) {
                direction = "left";
            }
            else if (key.rightPressed) {
                direction = "right";
            }
            
            //Check Tile Collision
            collisionOn = false;
            gp.cc.CheckTile(this);
            
            //Check object Collision
            int objIndex = gp.cc.checkObject(this, true);
            contactObject(objIndex);
            
            //Check NPC Collision
            int npcIndex = gp.cc.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            //Check Monster Collision
            int monIndex = gp.cc.checkEntity(this, gp.mon);
            contactMon(monIndex);
            
            int iObjectIndex = gp.cc.checkEntity(this, gp.iObject);
            
            //Check Event
            gp.event.checkEvent();
                        
            //If collision is false, player can move
            if (!collisionOn && !key.enter) {
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
            
            if (key.enter && !attackCancel) {
                gp.playSE(24);
                attacking = true;
                spriteCounter = 0;
            }
            
            attackCancel = false;
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
        }
        else{
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        
        if (gp.key.shoot && !projectile.alive && shotCounter == 60 && projectile.haveResource(this)) {
            
            projectile.set(worldX, worldY, direction, true, this);
            
            projectile.subtractResource(this);
            
            gp.projectileList.add(projectile);
            gp.playSE(22);
            
            shotCounter = 0;
        }
        
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if (shotCounter < 60) {
            shotCounter++;
        }
        
        if (actionLockCounter <= 30) {
            actionLockCounter++;
        }
        
        if (health <= 0) {
            gp.stopMusic();
            gp.playMusic(1);
            gp.gameState = gp.gameOverState;
            gp.ui.command = -1;
        }
        
        if (health > maxHealth) {
            health = maxHealth;
        }
        
        if (mana > maxMana) {
            mana = maxMana;
        }
        
    }
    public void attack(){
        spriteCounter++;
        
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            
            //Current worldX, worldY, width, and height
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //Adjust player's worldY and worldX for attacking
            switch(direction){
                case "up"   : worldY -= attackArea.height; break;
                case "down" : worldY += attackArea.height; break;
                case "left" : worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            int monsterIndex = gp.cc.checkEntity(this, gp.mon);
            damageMonster(monsterIndex, attack);
            
            int iObjectIndex = gp.cc.checkEntity(this, gp.iObject);
            damageIObject(iObjectIndex);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    
    public void damageIObject(int i){
        if (i != 999 && gp.iObject[gp.currentMap][i].destructible
                && gp.iObject[gp.currentMap][i].isCorrectItem(this)) {
            gp.iObject[gp.currentMap][i] = null;
        }
    }
    
    public void contactObject(int i){
        if (i != 999) {
            Entity object;
            String text;
            String name = gp.obj[gp.currentMap][i].name;
            switch(name){
                case "Arrow":
                    break;
                case "Harta Karun":
                    attackCancel = true;
                    if (gp.key.enter) {
                        gp.playSE(27);
                        if (gp.currentMap == 0) {
                            if (i == 1) {
                                Entity object1 = new OBJ_Key(gp);
                                inventory.add(object1);
                                String name1 = object1.name;

                                Entity object2 = new OBJ_Weapon_Chopping_Axe(gp);
                                inventory.add(object2);
                                String name2 = object2.name;
                                
                                gp.gameState = gp.dialogueState;
                                gp.ui.currentDialogue = "Mendapat "+name1+" dan "+name2+"!";
                                gp.obj[gp.currentMap][i] = null;
                            }
                        }
                        if (gp.currentMap == 1) {
                            if (i == 3) {
                                object = new OBJ_Cloack(gp);
                                inventory.add(object);
                                name = object.name;
                                
                                gp.gameState = gp.dialogueState;
                                gp.ui.currentDialogue = "Mendapat "+name+"!";
                                gp.obj[gp.currentMap][i] = null;
                            }
                        }
                        if (gp.currentMap == 2) {
                            if (i == 0) {
                                Entity object1 = new OBJ_Key(gp);
                                inventory.add(object1);
                                String name1 = object1.name;

                                Entity object2 = new OBJ_Weapon_Cranium_Staff(gp);
                                inventory.add(object2);
                                String name2 = object2.name;
                                
                                gp.gameState = gp.dialogueState;
                                gp.ui.currentDialogue = "Mendapat "+name1+" dan "+name2+"!";
                                gp.obj[gp.currentMap][i] = null;
                            }
                        }
                        actionLockCounter = 0;
                    }
                    break;
                case "Table":
                    break;
                case "Stair":
                    break;
                case "Door":
                    if (gp.key.enter) {
                        attackCancel = true;
                        for (int j = 0; j < inventory.size(); j++) {
                            switch(inventory.get(j).name){
                                case "Kunci":
                                    gp.obj[gp.currentMap][i] = null;
                                    gp.gameState = gp.dialogueState;
                                    gp.ui.currentDialogue = "Terbuka";
                                    inventory.remove(j);
                                    break;
                                default:
                                    gp.gameState = gp.dialogueState;
                                    gp.ui.currentDialogue = "Kamu memerlukan sebuah kunci.";
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    
                    if (gp.obj[gp.currentMap][i].type == type_pickup) {
                        gp.obj[gp.currentMap][i].use(this);
                        gp.obj[gp.currentMap][i] = null;
                    }
                    else{
                        if (inventory.size() != maxInventorySize) {
                            gp.playSE(27);
                            inventory.add(gp.obj[gp.currentMap][i]);
                            text = "Mendapat "+name+"!";
                        }
                        else{
                            text = "Inventory penuh!";
                        }
                        gp.ui.addMessage(text);
                        gp.obj[gp.currentMap][i] = null;
                    }
                    break;
            }
            
        }
    }
    public void interactNPC(int i){
        
        if (gp.key.enter) {
            if (i != 999) {
                attackCancel = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMon(int i){
        if (i != 999) {
            if (!invincible && !gp.mon[gp.currentMap][i].dead) {
                gp.playSE(25);
                
                int damage = gp.mon[gp.currentMap][i].attack*gp.mon[gp.currentMap][i].attack/defense;
                if (damage < 0) {
                    damage = 0;
                }
                
                health -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack){
        if (i != 999) {
            if (!gp.mon[gp.currentMap][i].invincible) {
                gp.playSE(25);
                
                int damage = attack*attack/gp.mon[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.mon[gp.currentMap][i].health -= damage;
                
                gp.ui.addMessage("Memberikan "+damage + " damage!");
                
                gp.mon[gp.currentMap][i].invincible = true;
                gp.mon[gp.currentMap][i].damageReaction();
                
                if (gp.mon[gp.currentMap][i].health <= 0) {
                    gp.mon[gp.currentMap][i].dead = true;
                    gp.ui.addMessage(gp.mon[gp.currentMap][i].name+" kalah!");
                    gp.ui.addMessage("Mendapat "+gp.mon[gp.currentMap][i].exp+" Exp!");
                    exp += gp.mon[gp.currentMap][i].exp;
                    levelUp();
                }
            }
        }
    }
    public void levelUp(){
        if (exp >= toNextLevel) {
            level++;
            toNextLevel += toNextLevel*2;
            maxHealth += 2;
            maxMana += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            projectile.attack += 2;
            
            gp.playSE(23);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Level up!\nLevelmu sekarang adalah "+level+"!";
            
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemSlotIndex(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            
            if (selectedItem.type == typeSword
                    || selectedItem.type == typeAxe
                    || selectedItem.type == typeStaff) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == typeProtectives) {
                currentProtectives = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == typeConsumables) {
                if (health < maxHealth) {
                    selectedItem.use(this);
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public void draw(Graphics2D g2){
    //g2.setColor(Color.white);
    //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        BufferedImage image = null;
        //Animation
        switch(direction){
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = up3;}
                    if (spriteNum == 4) {image = up2;}
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = down3;}
                    if (spriteNum == 4) {image = down2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = left3;}
                    if (spriteNum == 4) {image = left2;}
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = right3;}
                    if (spriteNum == 4) {image = right2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        //Opacity
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        //Reset Opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
