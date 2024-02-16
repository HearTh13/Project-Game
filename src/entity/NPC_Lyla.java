package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Lyla extends Entity{
    
    public NPC_Lyla(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
    }
    
    public void getImage(){
        //Player image into variable
        up1 = setup("/npc/LylaBack1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/LylaBack2", gp.tileSize, gp.tileSize);
        up3 = setup("/npc/LylaBack3", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/LylaFront1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/LylaFront2", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/LylaFront3", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/LylaLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/LylaLeft2", gp.tileSize, gp.tileSize);
        left3 = setup("/npc/LylaLeft3", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/LylaRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/LylaRight2", gp.tileSize, gp.tileSize);
        right3 = setup("/npc/LylaRight3", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue(){
        dialogue[0] = "???: \nSelamat datang di rumahku!";
        dialogue[1] = "???: \nKau bisa mengambil kotak yang ada disana.";
        dialogue[2] = "???: \nAda perlu apa?";
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
    }
    
    public void speak(){
        super.speak();
    }
    
}
