package event;

import entity.*;
import main.GamePanel;

public class Event {
    GamePanel gp;
    EventRect[][][] eventRect;
    public int tempMap, tempCol, tempRow;
    public int previousX, previousY;
    boolean eventTouch;
        
    public Event(GamePanel gp){
        this.gp = gp;
        
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        
        int map = 0;
        int col = 0;
        int row = 0;
        
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
        
    }
    public void checkEvent(){
        
        //Check previous x and y event
        int xDistance = Math.abs(gp.player.worldX - previousX);
        int yDistance = Math.abs(gp.player.worldY - previousY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            eventTouch = true;
        }
        
        if (eventTouch) {
            //Map 1
            if (hit(0, 40, 2, "right"))
                healingPond(gp.dialogueState);
            else if (hit(0, 40, 2, "right"))
                healingPond(gp.dialogueState);
            else if (hit(0, 40, 3, "right"))
                healingPond(gp.dialogueState);
            else if (hit(0, 40, 4, "right"))
                healingPond(gp.dialogueState);
            else if (hit(0, 40, 5, "right")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 40, 6, "right")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 41, 7, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 42, 7, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 43, 7, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 44, 7, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(0, 12, 3, "any")){
                if (gp.ui.scene == 1) {
                    scene();
                }
            }
            else if (hit(0, 17, 48, "any")) {
                teleport(1, 1, 1);
                if (gp.ui.scene == 2) {
                    scene();
                }
            }
            
            //Map 2
            else if (hit(1, 1, 1, "any")) 
                teleport(0, 17, 48);
            else if (hit(1, 45, 34, "any")) 
                teleport(2, 29, 29);
            else if (hit(1, 43, 43, "any")) {
                teleport(3, 2, 47);
                if (gp.ui.scene == 3) {
                    scene();
                }
            }
            else if (hit(1, 29, 42, "up"))
                healingPond(gp.dialogueState);
            else if (hit(1, 30, 42, "up"))
                healingPond(gp.dialogueState);
            else if (hit(1, 31, 42, "up"))
                healingPond(gp.dialogueState);
            else if (hit(1, 32, 42, "up"))
                healingPond(gp.dialogueState);
            else if (hit(1, 33, 42, "up"))
                healingPond(gp.dialogueState);
            
            //Map 3
            else if (hit(2, 29, 29, "any")) 
                teleport(1, 45, 34);
            
            //Map 4
            else if (hit(3, 2, 47, "up")) 
                teleport(1, 42, 43);
            else if (hit(3, 24, 29, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 25, 29, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 26, 29, "up")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 27, 24, "left")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 27, 25, "left")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 27, 26, "left")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 27, 27, "left")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 27, 28, "left")) 
                healingPond(gp.dialogueState);
            else if (hit(3, 39, 45, "up")) {
                if (gp.ui.scene == 4) {
                    scene();
                }
                if (gp.ui.boss == false) {
                    gp.ui.boss = true;
                }
            }
            else if (hit(3, 44, 41, "any")) {
                if (gp.mon[3][14] == null) {
                    teleport(4, 2, 4);
                    gp.player.direction = "right";
                    if (gp.ui.scene == 5) {
                        scene();
                    }
                }
            }
            
            //Map 5
            else if (hit(4, 5, 4, "right")) {
                if (gp.ui.scene == 6) {
                    scene();
                }
                
            }
        }
    }
    public boolean hit(int map, int col, int row, String reqDir){
        boolean hit = false;
        if (map == gp.currentMap) {
            gp.player.solidArea.x += gp.player.worldX;
            gp.player.solidArea.y += gp.player.worldY;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDir) || reqDir.contentEquals("any")) {
                    hit = true;

                    previousX = gp.player.worldX;
                    previousY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        
        
        return hit;
    }
    
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.playSE(25);
        gp.ui.currentDialogue = "Kamu kena jebakan!\nKurang 1 damage.";
        gp.player.health--;
        eventTouch = false;
    }
    
    public void healingPond(int gameState){
        if (gp.key.enter) {
            gp.gameState = gameState;
            gp.player.attackCancel = true;
            gp.playSE(26);
            gp.ui.currentDialogue = "Minum air.\nKau menjadi sehat lagi!";
            gp.player.health = gp.player.maxHealth;
            gp.player.mana = gp.player.maxMana;
        }
    }
    
    public void teleport(int map, int toX, int toY){
        
        gp.playSE(18);
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = toX;
        tempRow = toY;
        
        eventTouch = false;
        
        gp.asset.setMonster();
    }
    
    public void scene(){
        gp.gameState = gp.sceneState;
    }
    
}
