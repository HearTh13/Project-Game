package main;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void CheckTile(Entity entity){
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tile.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tile.tile[tileNum1].collision == true|| gp.tile.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tile.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tile.tile[tileNum1].collision == true|| gp.tile.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tile.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tile.tile[tileNum1].collision == true|| gp.tile.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tile.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tile.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tile.tile[tileNum1].collision == true|| gp.tile.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject (Entity entity, boolean player){
        int index = 999;
        
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                //Get Entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //get Object's solid area position
                gp.obj[gp.currentMap][i].solidArea.x += gp.obj[gp.currentMap][i].worldX;
                gp.obj[gp.currentMap][i].solidArea.y += gp.obj[gp.currentMap][i].worldY;
                
                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                
                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                    if (gp.obj[gp.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                
                //Reset solid area
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        
        return index;
    }
    //NPC or Monster Collision
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;
        
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                //Get Entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //get Object's solid area position
                target[gp.currentMap][i].solidArea.x += target[gp.currentMap][i].worldX;
                target[gp.currentMap][i].solidArea.y += target[gp.currentMap][i].worldY;
                
                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                
                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if (target[gp.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                
                //Reset solid area
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        
        return index;
    }
    public boolean checkPlayer(Entity entity){
        
        boolean contactPlayer = false;
        
        //Get Entity's solid area position
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;

        //get Object's solid area position
        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;

        switch(entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        
        //Reset solid area
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
    }
    
}
