package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Merchant extends Entity{
    
    public NPC_Merchant(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;
        
        setItems();
        getImage();
        setDialogue();
    }
    
    public void getImage(){
        //Player image into variable
        up1 = setup("/npc/MerchantBack1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/MerchantBack2", gp.tileSize, gp.tileSize);
        up3 = setup("/npc/MerchantBack3", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/MerchantFront1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/MerchantFront2", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/MerchantFront3", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/MerchantLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/MerchantLeft2", gp.tileSize, gp.tileSize);
        left3 = setup("/npc/MerchantLeft3", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/MerchantRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/MerchantRight2", gp.tileSize, gp.tileSize);
        right3 = setup("/npc/MerchantRight3", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue(){
        dialogue[0] = "Pedagang: \nKau punya uang? Aku bisa menukarkan\nDengan barang bagus.";
    }
    
    public void setAction(){
        if (onPath) {
            int goalCol = 13;
            int goalRow = 3;
            
            searchPath(goalCol, goalRow);
        }
        else{
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
        }
    }
    
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
        
        onPath = true;
    }
    
    public void setItems(){
        inventory.add(new OBJ_Consumables_Potion(gp){{
            setValue(5, 5*2);
        }});
        
        inventory.add(new OBJ_Consumables_Potion(gp){{
            setValue(10, 10*2);
        }});
        
        inventory.add(new OBJ_Consumables_Potion(gp){{
            setValue(30, 30*2);
        }});
    }
    
}
