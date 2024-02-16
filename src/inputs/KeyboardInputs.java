package inputs;

import java.awt.event.*;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enter, shoot;
    
    public KeyboardInputs(GamePanel gp){
        this.gp = gp;
    }
    
    //Debug
    public boolean debugButton = false;
    
    public KeyboardInputs(){}
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    //If pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //TitleState
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        
        //Play State
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        //Pause State
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //Dialogue State
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        //Menu State
        else if (gp.gameState == gp.menuState) {
            menuState(code);
        }
        
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        else if (gp.gameState == gp.sceneState) {
            sceneState(code);
        }
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        
    }
    
    public void titleState(int code){
        if (gp.ui.sceneCounter == 0) {
            switch(code){
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    gp.ui.command--;
                    if (gp.ui.command < 0) {
                        gp.ui.command = 1;
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    gp.ui.command++;
                    if (gp.ui.command > 1) {
                        gp.ui.command = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_F:
                    if (gp.ui.command == 0) {
                        gp.gameState = gp.sceneState;
                        gp.ui.command = -1;
                    }
                    if (gp.ui.command == 1) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
    
    public void playState(int code){
        switch(code){
            case KeyEvent.VK_SHIFT:
                gp.player.speed = 7;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                enter = true;
                break;
            case KeyEvent.VK_E:
                if (gp.player.currentWeapon.type == gp.player.typeStaff) {
                    shoot = true;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.pauseState;
                break;
            case KeyEvent.VK_Q:
                gp.gameState = gp.menuState;
                break;
            
            //Debug
            case KeyEvent.VK_F1:
                if (debugButton == false) {
                    debugButton = true;
                }
                else if (debugButton == true) {
                    debugButton = false;
                }
                break;
        }
    }
    
    public void pauseState(int code){
        switch(code){
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.playState;
                break;
        }
    }
    
    public void dialogueState(int code){
        switch(code){
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                gp.gameState = gp.playState;
                break;
        }
    }
    
    public void menuState(int code){
        playerInventory(code);
        switch(code){
            case KeyEvent.VK_Q:
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.playState;
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                gp.player.selectItem();
                break;
        }
    }
    
    public void playerInventory(int code){
        switch(code){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (gp.ui.playerSlotRow != 0) {
                    gp.ui.playerSlotRow--;
                }
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (gp.ui.playerSlotCol != 0) {
                    gp.ui.playerSlotCol--;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (gp.ui.playerSlotRow != 3) {
                    gp.ui.playerSlotRow++;
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if(gp.ui.playerSlotCol != 4){
                    gp.ui.playerSlotCol++;
                }
                break;
        }
    }
    
    public void npcInventory(int code){
        switch(code){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (gp.ui.npcSlotRow != 0) {
                    gp.ui.npcSlotRow--;
                }
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (gp.ui.npcSlotCol != 0) {
                    gp.ui.npcSlotCol--;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (gp.ui.npcSlotRow != 3) {
                    gp.ui.npcSlotRow++;
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if(gp.ui.npcSlotCol != 4){
                    gp.ui.npcSlotCol++;
                }
                break;
        }
    }
    
    public void gameOverState(int code){
        switch(code){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                gp.ui.command--;
                if (gp.ui.command < 0) {
                    gp.ui.command = 1;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                gp.ui.command++;
                if (gp.ui.command > 1) {
                    gp.ui.command = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                if (gp.ui.command == 0) {
                    gp.stopMusic();
                    gp.playMusic(0);
                    gp.gameState = gp.playState;
                    gp.retry();
                }
                else if (gp.ui.command == 1) {
                    gp.ui.command = -1;
                    gp.stopMusic();
                    gp.playMusic(2);
                    gp.gameState = gp.titleState;
                    gp.restart();
                }
        }
    }
    
    public void sceneState(int code){
        switch(code){
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                gp.ui.sceneCounter++;
                break;
        }
    }
    
    private void tradeState(int code) {
        
        switch (code){
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                enter = true;
                break;
        }
        
        if (gp.ui.subState == 0) {
            switch(code){
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    gp.ui.command--;
                    if (gp.ui.command < 0) {
                        gp.ui.command = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    gp.ui.command++;
                    if (gp.ui.command > 2) {
                        gp.ui.command = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_F:
                    enter = true;
                    break;
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            switch (code){
                case KeyEvent.VK_ESCAPE:
                case KeyEvent.VK_Q:
                    gp.ui.subState = 0;
                    break;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            switch (code){
                case KeyEvent.VK_ESCAPE:
                case KeyEvent.VK_Q:
                    gp.ui.subState = 0;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_SHIFT:
                gp.player.speed = 4;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_F:
                enter = false;
                break;
            case KeyEvent.VK_E:
                if (gp.player.currentWeapon.type == gp.player.typeStaff) {
                    shoot = false;
                }
                break;
        }
    }

    
    
}
