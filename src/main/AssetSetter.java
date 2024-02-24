package main;

import entity.*;
import interactives.DestTree;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        int map;
        int i;
        
        //Map 1
        map = 0;
        i = 0;
        gp.obj[map][i] = new OBJ_Door(gp);
        gp.obj[map][i].worldX = gp.tileSize*28;
        gp.obj[map][i].worldY = gp.tileSize*6;
        i++;
        
        gp.obj[map][i] = new OBJ_Chest(gp);
        gp.obj[map][i].worldX = gp.tileSize*25;
        gp.obj[map][i].worldY = gp.tileSize*2;
        i++;
        
        gp.obj[map][i] = new OBJ_Gold(gp);
        gp.obj[map][i].setValue(500);
        gp.obj[map][i].worldX = gp.tileSize*11;
        gp.obj[map][i].worldY = gp.tileSize*4;
        i++;
        
        gp.obj[map][i] = new OBJ_Arrow(gp);
        gp.obj[map][i].worldX = gp.tileSize*17;
        gp.obj[map][i].worldY = gp.tileSize*49;
        gp.obj[map][i].direction = "down";
        i++;
        
        //Map 2
        map = 1;
        i = 0;
        
        gp.obj[map][i] = new OBJ_Arrow(gp);
        gp.obj[map][i].worldX = gp.tileSize*1;
        gp.obj[map][i].worldY = gp.tileSize*0;
        gp.obj[map][i].direction = "up";
        i++;
        
        gp.obj[map][i] = new OBJ_Arrow(gp);
        gp.obj[map][i].worldX = gp.tileSize*44;
        gp.obj[map][i].worldY = gp.tileSize*34;
        gp.obj[map][i].direction = "left";
        i++;
        
        gp.obj[map][i] = new OBJ_Stair(gp);
        gp.obj[map][i].worldX = gp.tileSize*43;
        gp.obj[map][i].worldY = gp.tileSize*43;
        i++;
        
        gp.obj[map][i] = new OBJ_Chest(gp);
        gp.obj[map][i].worldX = gp.tileSize*47;
        gp.obj[map][i].worldY = gp.tileSize*3;
        i++;
        
        //Map 3
        map = 2;
        i = 0;
        
        gp.obj[map][i] = new OBJ_Chest(gp);
        gp.obj[map][i].worldX = gp.tileSize*26;
        gp.obj[map][i].worldY = gp.tileSize*19;
        i++;
        
        gp.obj[map][i] = new OBJ_Arrow(gp);
        gp.obj[map][i].worldX = gp.tileSize*29;
        gp.obj[map][i].worldY = gp.tileSize*30;
        gp.obj[map][i].direction = "down";
        i++;
        
        gp.obj[map][i] = new OBJ_Table(gp);
        gp.obj[map][i].worldX = gp.tileSize*25;
        gp.obj[map][i].worldY = gp.tileSize*23;
        i++;
        
        //Map 4
        map = 3;
        i = 0;
        
        gp.obj[map][i] = new OBJ_Stair(gp);
        gp.obj[map][i].worldX = gp.tileSize*2;
        gp.obj[map][i].worldY = gp.tileSize*47;
        i++;
        
        //Map 5
        map = 4;
        i = 0;
        
        gp.obj[map][i] = new OBJ_Stair(gp);
        gp.obj[map][i].worldX = gp.tileSize*1;
        gp.obj[map][i].worldY = gp.tileSize*4;
        i++;
        
        
    }
    
    public void setNPC(){
        int map;
        int i;
        
        //Map 1
        map = 0;
        i = 0;
        
        gp.npc[map][i] = new NPC_Merchant(gp);
        gp.npc[map][i].worldX = gp.tileSize*19;
        gp.npc[map][i].worldY = gp.tileSize*2;
        i++;
        
        //Map 3
        map = 2;
        i = 0;
        
        gp.npc[map][i] = new NPC_Lyla(gp);
        gp.npc[map][i].worldX = gp.tileSize*29;
        gp.npc[map][i].worldY = gp.tileSize*27;
        i++;
    }
    
    public void setMonster(){
        int map;
        int i;
        
        //Map 1
        map = 0;
        i = 0;
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*28;
        gp.mon[map][i].worldY = gp.tileSize*7;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*24;
        gp.mon[map][i].worldY = gp.tileSize*6;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*26;
        gp.mon[map][i].worldY = gp.tileSize*4;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*19;
        gp.mon[map][i].worldY = gp.tileSize*23;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*48;
        gp.mon[map][i].worldY = gp.tileSize*27;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*6;
        gp.mon[map][i].worldY = gp.tileSize*22;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*2;
        gp.mon[map][i].worldY = gp.tileSize*31;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*20;
        gp.mon[map][i].worldY = gp.tileSize*32;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*46;
        gp.mon[map][i].worldY = gp.tileSize*33;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*47;
        gp.mon[map][i].worldY = gp.tileSize*41;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*14;
        gp.mon[map][i].worldY = gp.tileSize*40;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*19;
        gp.mon[map][i].worldY = gp.tileSize*44;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*21;
        gp.mon[map][i].worldY = gp.tileSize*12;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*35;
        gp.mon[map][i].worldY = gp.tileSize*11;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*21;
        gp.mon[map][i].worldY = gp.tileSize*23;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*8;
        gp.mon[map][i].worldY = gp.tileSize*35;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*27;
        gp.mon[map][i].worldY = gp.tileSize*31;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*30;
        gp.mon[map][i].worldY = gp.tileSize*39;
        i++;
        
        gp.mon[map][i] = new MON_Slime(gp);
        gp.mon[map][i].worldX = gp.tileSize*22;
        gp.mon[map][i].worldY = gp.tileSize*48;
        i++;
        
        //Map 2
        map = 1;
        i = 0;
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*7;
        gp.mon[map][i].worldY = gp.tileSize*9;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*12;
        gp.mon[map][i].worldY = gp.tileSize*13;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*43;
        gp.mon[map][i].worldY = gp.tileSize*4;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*32;
        gp.mon[map][i].worldY = gp.tileSize*11;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*14;
        gp.mon[map][i].worldY = gp.tileSize*16;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*19;
        gp.mon[map][i].worldY = gp.tileSize*21;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*37;
        gp.mon[map][i].worldY = gp.tileSize*19;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*47;
        gp.mon[map][i].worldY = gp.tileSize*30;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*12;
        gp.mon[map][i].worldY = gp.tileSize*26;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*3;
        gp.mon[map][i].worldY = gp.tileSize*28;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*20;
        gp.mon[map][i].worldY = gp.tileSize*34;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*27;
        gp.mon[map][i].worldY = gp.tileSize*43;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*12;
        gp.mon[map][i].worldY = gp.tileSize*42;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*38;
        gp.mon[map][i].worldY = gp.tileSize*44;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*33;
        gp.mon[map][i].worldY = gp.tileSize*19;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*11;
        gp.mon[map][i].worldY = gp.tileSize*23;
        i++;
        
        gp.mon[map][i] = new MON_Fairy(gp);
        gp.mon[map][i].worldX = gp.tileSize*45;
        gp.mon[map][i].worldY = gp.tileSize*5;
        i++;
        
        //Map 4
        map = 3;
        i = 0;
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*7;
        gp.mon[map][i].worldY = gp.tileSize*45;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*10;
        gp.mon[map][i].worldY = gp.tileSize*45;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*7;
        gp.mon[map][i].worldY = gp.tileSize*36;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*2;
        gp.mon[map][i].worldY = gp.tileSize*39;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*13;
        gp.mon[map][i].worldY = gp.tileSize*40;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*12;
        gp.mon[map][i].worldY = gp.tileSize*31;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*3;
        gp.mon[map][i].worldY = gp.tileSize*29;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*19;
        gp.mon[map][i].worldY = gp.tileSize*42;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*35;
        gp.mon[map][i].worldY = gp.tileSize*47;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*1;
        gp.mon[map][i].worldY = gp.tileSize*26;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*12;
        gp.mon[map][i].worldY = gp.tileSize*2;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*4;
        gp.mon[map][i].worldY = gp.tileSize*5;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*17;
        gp.mon[map][i].worldY = gp.tileSize*7;
        i++;
        
        gp.mon[map][i] = new MON_RockMonster(gp);
        gp.mon[map][i].worldX = gp.tileSize*1;
        gp.mon[map][i].worldY = gp.tileSize*5;
        i++;
        
        gp.mon[map][i] = new MON_Minotaur(gp);
        gp.mon[map][i].worldX = gp.tileSize*44;
        gp.mon[map][i].worldY = gp.tileSize*41;
        i++;
    }
    
    public void setInteractives(){
        int map = 0;
        int i = 0;
        gp.iObject[map][i] = new DestTree(gp, 12, 3);
    }
    
}
