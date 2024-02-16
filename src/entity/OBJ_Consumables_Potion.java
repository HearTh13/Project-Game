
package entity;

import main.GamePanel;

public class OBJ_Consumables_Potion extends Entity{
    
    GamePanel gp;
    
    public OBJ_Consumables_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = typeConsumables;
        name = "Ramuan";
        image = setup("/objects/potion" ,gp.tileSize, gp.tileSize);
        down1 = image;
    }
    
    public void use (Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Kamu meminum "+name+"!\nDarahmu bertambah "+value+" poin.";
        entity.health += value;
        gp.playSE(26);
    }
    
    public void setValue(int val, int price){
        this.price = price;
        value = val;
        description = "["+name+"]\nMengembalikan "+value+" poin darah.\nLabel: Tidak berbahaya kok~.";
    }
    
}
