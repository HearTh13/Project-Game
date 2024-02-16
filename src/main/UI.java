package main;

import entity.Entity;
import entity.OBJ_Bar;
import entity.OBJ_Gold;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage bar, healthBar, manaBar, gold;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameOver = false;
    public String currentDialogue = "";
    public int command = -1;
    public int scene = 0;
    public int sceneCounter = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    private boolean sceneMusic = false;
    public boolean boss = false;
    public int subState = 0;
    int counter;
    public Entity npc;
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        try {
            InputStream is = getClass().getResourceAsStream("/font/font.fnt");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Create HUD Object
        Entity heart = new OBJ_Bar(gp);
        healthBar = heart.image;
        manaBar = heart.image2;
        bar = heart.image3;
        Entity coin = new OBJ_Gold(gp);
        gold = coin.image;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        //Title State
        if (gp.gameState == gp.titleState) {
            drawTitle();
        }
        
        //Play State
        if (gp.gameState == gp.playState) {
            drawPlayerHealth();
            drawMessage();
        }
        //Pause State
        else if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }
        //Dialogue State
        else if (gp.gameState == gp.dialogueState) {
            drawDialogue();
        }
        //Menu State
        else if (gp.gameState == gp.menuState) {
            drawMenuScreen();
            drawInventory(gp.player, true);
        }
        //Game Over State
        else if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        else if (gp.gameState == gp.sceneState) {
            drawScene();
        }
        else if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        else if (gp.gameState == gp.tradeState) {
            drawTradingUI();
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x, y;
        String text;
        g2.setFont(g2.getFont().deriveFont(font.BOLD, 110f));
        
        text = "Game Over";
        g2.setColor(Color.black);
        x = getXCentered(text)+4;
        y = gp.tileSize*4+4;
        g2.drawString(text, x, y);
        
        g2.setColor(Color.white);
        x -= 4;
        y -= 4;
        g2.drawString(text, x, y);
        
        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.setColor(Color.white);
        text = "Ulang";
        x = getXCentered(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (command == 0) {
            g2.setColor(Color.white);
            g2.fillRect(x-gp.tileSize, y-gp.tileSize+8, gp.screenWidth, gp.tileSize);
            g2.setColor(Color.black);
            g2.drawString(text, x-gp.tileSize, y);
        }
        
        //Back to title
        g2.setColor(Color.white);
        text = "Menu";
        x = getXCentered(text);
        y += 55;
        g2.drawString(text, x, y);
        if (command == 1) {
            g2.setColor(Color.white);
            g2.fillRect(x-gp.tileSize, y-gp.tileSize+8, gp.screenWidth, gp.tileSize);
            g2.setColor(Color.black);
            g2.drawString(text, x-gp.tileSize, y);
        }
    }
    public void drawScene(){
        switch (scene) {
            case 0:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/forest.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nAkhirnya, aku sampai juga di hutan ini.";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nMonster Minotaur itu pasti menculik\nsang putri.";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nKalau begitu aku tidak akan tinggal\ndiam!";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nAku akan menyelamatkan sang putri!";
                    drawDialogue();
                }
                else if (sceneCounter == 4) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nLalu aku akan menikahinhya hehe...";
                    drawDialogue();
                }
                else if (sceneCounter == 5) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                }   break;
            case 1:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/forest.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nSebelum mulai mencari,\nsebaiknya aku refreshing terlebih dahulu.";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    currentDialogue = "--Tutorial--";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    currentDialogue = "Tutorial:\nUntuk bergerak tekan WASD atau tombol panah\npada Keyboard.";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    currentDialogue = "Tutorial:\nUntuk menyerang atau OK, tekan\ntombol F, ENTER, atau SPACE pada\nKeyboard.";
                    drawDialogue();
                }
                else if (sceneCounter == 4) {
                    currentDialogue = "Tutorial:\nUntuk pause bisa tekan ESC.";
                    drawDialogue();
                }
                else if (sceneCounter == 5) {
                    currentDialogue = "Tutorial:\nUntuk membuka Menu screen,\nbisa tekan Q.";
                    drawDialogue();
                }
                else if (sceneCounter == 6) {
                    currentDialogue = "Tutorial:\nJika mendapat senjata tongkat,\nbisa tekan E untuk menembak\nBola Api";
                    drawDialogue();
                }
                else if (sceneCounter == 7) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nBaik, sekian dulu refreshingnya.";
                    drawDialogue();
                }
                else if (sceneCounter == 8) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nMari Kita buru si Minotaur!";
                    drawDialogue();
                }
                else if (sceneCounter == 9) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                }   break;
            case 2:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/forest.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nHuft, sebenarnya seberapa dalam sih,\nhutan ini?.";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nMonster disini kuat-kuat.\nSepertinya aku harus hati-hati.";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    currentDialogue = "--Tutorial--";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    currentDialogue = "Tutorial:\nAir dapat menyembuhkanmu!";
                    drawDialogue();
                }
                else if (sceneCounter == 4) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                    gp.event.teleport(1, 1, 1);
                }   break;
            case 3:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/cave.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nIni pasti tempat tinggal sang Minotaur.\nTidak salah lagi!";
                    drawDialogue();
                }   else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nGua ini terlihat cukup dalam...";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nTenang tuan putri, aku pasti akan\nmenyelamatkanmu!";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                    gp.event.teleport(3, 2, 47);
                }   break;
            case 4:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/cave.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nKau pasti sang Minotaur yang\nmenculik sang putri!";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Minotaur:\nHahaha, Kau benar!";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/minotaurDim.png"));
                        g2.drawImage(image, gp.screenWidth/2, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nDimana kau mengurung tuan\nputri!?";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Minotaur:\nHahaha, dia ada di lantai bawah\ntempat ini!";
                    drawDialogue();
                }
                else if (sceneCounter == 4) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Minotaur:\nTetapi sebelum kau menemuinya,\nkau akan menemui ajalmu dahulu!";
                    drawDialogue();
                }
                else if (sceneCounter == 5) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                }   break;
            case 5:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/cave.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nPertarungan yang melelahkan.\nTapi aku berhasil menang!";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nSekarang waktunya menyelamatkan\nsang putri!";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(0);
                    gp.event.teleport(4, 2, 4);
                }   break;
            case 6:
                if(!sceneMusic){
                    gp.stopMusic();
                    gp.playMusic(1);
                    sceneMusic = true;
                }   try{
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/cave.png"));
                    g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
                }catch(IOException e){
                    e.printStackTrace();
                }   if (sceneCounter == 0) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri:\nS-siapa disana!?";
                    drawDialogue();
                }
                else if (sceneCounter == 1) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nTenang putri, ini aku! Sang pahlawan!";
                    drawDialogue();
                }
                else if (sceneCounter == 2) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nAku kesini atas perintah raja.\nDikirim untuk menyelamatkan anda,\nyang mulia.";
                    drawDialogue();
                }
                else if (sceneCounter == 3) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nMari kita pulang yang mulia.";
                    drawDialogue();
                }
                else if (sceneCounter == 4) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri:\nNggak mau.";
                    drawDialogue();
                }
                else if (sceneCounter == 5) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri2.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nBaiklah kalau begit-.";
                    drawDialogue();
                }
                else if (sceneCounter == 6) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri2.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nMaaf yang mulia, tadi anda bilang apa?";
                    drawDialogue();
                }
                else if (sceneCounter == 7) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri:\nNggak mau, aku nggak mau pulang. Hmph.";
                    drawDialogue();
                }
                else if (sceneCounter == 8) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri2.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player4.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nK-kenapa yang mulia?!";
                    drawDialogue();
                }
                else if (sceneCounter == 9) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Minotaur:\nT-tunggu!";
                    drawDialogue();
                }
                else if (sceneCounter == 10) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/minotaur.png"));
                        g2.drawImage(image, gp.screenWidth/2, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/minotaurDim.png"));
                        g2.drawImage(image, gp.screenWidth/2, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nK-kau masih hidup?!";
                    drawDialogue();
                }
                else if (sceneCounter == 11) {
                    currentDialogue = "*Crank*";
                    drawDialogue();
                }
                else if (sceneCounter == 12) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\n?!";
                    drawDialogue();
                }
                else if (sceneCounter == 13) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Minotaur(?):\nJauhkan tanganmu dari Alice!";
                    drawDialogue();
                }
                else if (sceneCounter == 14) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/jack3.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jackDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nManusia?!";
                    drawDialogue();
                }
                else if (sceneCounter == 15) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nTunggu, biar kujelaskan...";
                    drawDialogue();
                }
                else if (sceneCounter == 16) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nKau tahu, ayahku- Sang raja itu egois.";
                    drawDialogue();
                }
                else if (sceneCounter == 17) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nBeliau selalu menjodohkanku dengan\npangeran yang aku tidak kenal.";
                    drawDialogue();
                }
                else if (sceneCounter == 18) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nKarena itu aku kabur dari istana...";
                    drawDialogue();
                }
                else if (sceneCounter == 19) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nLantas, bagaimana anda bertemu dengan\nsi merah peniru Minotaur ini?";
                    drawDialogue();
                }
                else if (sceneCounter == 20) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Si Merah Peniru Minotaur:\nJaga ucapanmu, bocil.\nAku adalah penyihir yang dapat berubah wujud!";
                    drawDialogue();
                }
                else if (sceneCounter == 21) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack5.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Si Merah Peniru Minotaur:\nLihat betapa kerennya topi ini.";
                    drawDialogue();
                }
                else if (sceneCounter == 22) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nMa-mari kembali ke topik...";
                    drawDialogue();
                }
                else if (sceneCounter == 23) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nSaat aku pergi, aku meninggalkan jejak\nseperti aku diculik Minotaur.";
                    drawDialogue();
                }
                else if (sceneCounter == 24) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nAku melakukan itu agar prajurit\nkerajaanpun tidak mau mencariku.";
                    drawDialogue();
                }
                else if (sceneCounter == 25) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player3.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nHmmm... Manuk akal...";
                    drawDialogue();
                }
                else if (sceneCounter == 26) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nSetelah aku masuk ke hutan,\naku hampir diserang oleh monster.";
                    drawDialogue();
                }
                else if (sceneCounter == 27) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nDisitulah Jack datang untuk\nmenyelamatkanku.";
                    drawDialogue();
                }
                else if (sceneCounter == 28) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nDia juga memberiku tempat tinggal,\npakaian dan makanan.";
                    drawDialogue();
                }
                else if (sceneCounter == 29) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nDia juga bersedia membantuku\nmenipu bahwa aku diculik Minotaur.";
                    drawDialogue();
                }
                else if (sceneCounter == 30) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri4.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nSetelah itu, kamipun menikah.";
                    drawDialogue();
                }
                else if (sceneCounter == 31) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri4.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player4.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nAPA!?";
                    drawDialogue();
                }
                else if (sceneCounter == 32) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player4.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Jack:\nMenikah!?";
                    drawDialogue();
                }
                else if (sceneCounter == 33) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nKenapa kamu juga kaget?!";
                    drawDialogue();
                }
                else if (sceneCounter == 34) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player2.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Jack:\nAku juga baru dengar soal ini!";
                    drawDialogue();
                }
                else if (sceneCounter == 35) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri4.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nTidak apa-apa, kita kan sudah menghabiskan\nwaktu bersama.";
                    drawDialogue();
                }
                else if (sceneCounter == 36) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Putri Alice:\nMemangnya kamu tidak suka padaku?";
                    drawDialogue();
                }
                else if (sceneCounter == 37) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri3.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack6.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Jack:\nSuka sih, tapi...";
                    drawDialogue();
                }
                else if (sceneCounter == 38) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Jack:\nB-berarti, k-kalian berdua saling mencintai??";
                    drawDialogue();
                }
                else if (sceneCounter == 39) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/player1.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/playerDim.png"));
                        g2.drawImage(image, gp.screenWidth, 16, -gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putri4.png"));
                        g2.drawImage(image, 112, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack4.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 0, gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Jack & Alice:\nIya.";
                    drawDialogue();
                }
                else if (sceneCounter == 40) {
                    try{
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/putri4.png"));
                        g2.drawImage(image, 112, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/putriDim.png"));
                        g2.drawImage(image, 112, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatBack.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/jack4.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatFront.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/hatDim.png"));
                        g2.drawImage(image, 0, 16, gp.screenWidth/2, gp.screenHeight, null);
                        image = ImageIO.read(getClass().getResourceAsStream("/images/player4.png"));
                        g2.drawImage(image, gp.screenWidth, 0, -gp.screenWidth/2, gp.screenHeight, null);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    currentDialogue = "Pahlawan:\nTIDAAAAAAAAAAAK!!!";
                    drawDialogue();
                }
                else if (sceneCounter == 41) {
                    currentDialogue = "-The End-";
                    drawDialogue();
                }
                else if (sceneCounter == 42) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    scene++;
                    sceneCounter = 0;
                    sceneMusic = false;
                    gp.playMusic(2);
                    gp.restart();
                    gp.gameState = gp.titleState;
                }   break;
            default:
                break;
        }
    }
    public void drawPlayerHealth(){
        
        int x = gp.tileSize + 8;
        int y = gp.tileSize/2 + 8;
        
        //Draw Empty Health
        g2.drawImage(bar, x, y, gp.tileSize*5,gp.tileSize , null);

        //Draw Current Health
        double oneScale = (double)gp.tileSize*5/gp.player.maxHealth;
        double hpBarValue = oneScale * gp.player.health;
        
        g2.drawImage(healthBar, x-8, y-8, (int)hpBarValue, gp.tileSize, null);
        
        //Draw Empty Mana
        y += gp.tileSize/2;
        g2.drawImage(bar, x, y, gp.tileSize*5,gp.tileSize , null);

        //Draw Current Health
        oneScale = (double)gp.tileSize*5/gp.player.maxMana;
        hpBarValue = oneScale * gp.player.mana;
        
        g2.drawImage(manaBar, x-8, y-8, (int)hpBarValue, gp.tileSize, null);
        
    }
    public void drawMessage(){
        int messageX = gp.tileSize/2;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
        
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i)+1;
                messageCounter.set(i, counter);
                messageY += 50;
                
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    
    public void drawTradingUI(){
        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.key.enter = false;
    }
    
    public void trade_select(){
        drawDialogue();
        
        int x = gp.tileSize*11;
        int y = gp.tileSize*3;
        int width = gp.tileSize*3;
        int height = (int)(gp.tileSize*3.5);
        drawSubWindow(x, y, width, height);
        
        x += gp.tileSize/2;
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString("Buy", x, y);
        if (command == 0) {
            g2.setColor(Color.white);
            g2.fillRoundRect(x-(gp.tileSize/2), y-gp.tileSize+10, gp.screenWidth, gp.tileSize, 20, 20);
            g2.setColor(Color.black);
            g2.drawString("Buy", x, y);
            if (gp.key.enter) {
                subState = 1;
            }
        }
        
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString("Sell", x, y);
        if (command == 1) {
            g2.setColor(Color.white);
            g2.fillRoundRect(x-(gp.tileSize/2), y-gp.tileSize+10, gp.screenWidth, gp.tileSize, 20, 20);
            g2.setColor(Color.black);
            g2.drawString("Sell", x, y);
            if (gp.key.enter) {
                subState = 2;
            }
        }
        
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString("Cancel", x, y);
        if (command == 2) {
            g2.setColor(Color.white);
            g2.fillRoundRect(x-(gp.tileSize/2), y-gp.tileSize+10, gp.screenWidth, gp.tileSize, 20, 20);
            g2.setColor(Color.black);
            g2.drawString("Cancel", x, y);
            if (gp.key.enter) {
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Pedagang: \nDatang lagi, yaa!";
                gp.ui.command = -1;
            }
        }
    }
    
    public void trade_buy(){
        drawInventory(gp.player, false);
        drawInventory(npc, true);
        
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC, Q] Back", x+24, y+60);
        
        x = gp.tileSize*9;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Gold: "+gp.player.money, x+24, y+60);
        
        int itemIndex = getItemSlotIndex(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(gold, x+10, y+8, 32, 32, gp);
            
            int price = npc.inventory.get(itemIndex).price;
            String text = ""+price;
            
            x = getXAlignR(text, gp.tileSize*8-20);
            g2.drawString(text, x, y+32);
            
            if (gp.key.enter) {
                if (npc.inventory.get(itemIndex).price > gp.player.money) {
                    subState = 0;
                    gp.ui.command = -1;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Pedagang: \nUangmu tidak cukup...";
                }
                else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                    subState = 0;
                    gp.ui.command = -1;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Pedagang: \nKau tidak bisa membawa barang lebih\nbanyak lagi...";
                    
                }
                else{
                    gp.player.money -= npc.inventory.get(itemIndex).price;
                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }
        }
    }
    
    public void trade_sell(){
        drawInventory(gp.player, true);
        drawInventory(npc, false);
        
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC, Q] Back", x+24, y+60);
        
        x = gp.tileSize*9;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Gold: "+gp.player.money, x+24, y+60);
        
        int itemIndex = getItemSlotIndex(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int)(gp.tileSize*9);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(gold, x+10, y+8, 32, 32, gp);
            
            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = ""+price;
            
            x = getXAlignR(text, (int)(gp.tileSize*11.5-20));
            g2.drawString(text, x, y+32);
            
            if (gp.key.enter) {
                if (gp.player.inventory.get(itemIndex) != gp.player.currentProtectives ||
                        gp.player.inventory.get(itemIndex) != gp.player.currentWeapon) {
                    gp.player.inventory.remove(itemIndex);
                    gp.player.money += price;
                }
            }
        }
    }
    
    public void drawTitle(){
        if (sceneCounter == 0) {
            
            //Title Text
            g2.setColor(Color.darkGray);
            g2.fillOval(gp.tileSize, gp.tileSize, (gp.tileSize*9)+5, (gp.tileSize*4)+5);
            g2.setColor(new Color(70, 120, 80));
            g2.fillOval(gp.tileSize, gp.tileSize, gp.tileSize*9, gp.tileSize*4);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
            String text = "2D Adventure";
            int x = getXCentered(text);
            int y = gp.tileSize*3;
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //Menu
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40f));
            text = "Play";
            x = gp.tileSize*5;
            y += gp.tileSize*5;
            g2.drawString(text, x, y);
            if (command == 0) {
                g2.setColor(Color.white);
                g2.fillRoundRect(x-gp.tileSize, y-gp.tileSize+16, gp.screenWidth, gp.tileSize, 10, 10);
                g2.setColor(Color.black);
                g2.drawString(text, x-gp.tileSize, y);
            }
            
            g2.setColor(Color.white);
            text = "QUIT";
            x = gp.tileSize*5;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (command == 1) {
                g2.setColor(Color.white);
                g2.fillRoundRect(x-gp.tileSize, y-gp.tileSize+16, gp.screenWidth, gp.tileSize, 10, 10);
                g2.setColor(Color.black);
                g2.drawString(text, x-gp.tileSize, y);
            }

            //Character Image
            try{
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/title.png"));
                g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "-PAUSED-";
        int x = getXCentered(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    private void drawDialogue(){
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        
    }
    private void drawMenuScreen(){
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10 + (gp.tileSize/2);
        
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //Draw Texts
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;
        
        g2.drawString("Level ", textX, textY);
        textY += lineHeight;
        g2.drawString("Health ", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana ", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength ", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity ", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack ", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense ", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP ", textX, textY);
        textY += lineHeight;
        g2.drawString("Next ", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold ", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon ", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Gear ", textX, textY);
        
        //Values
        int valueX = (frameX + frameWidth) - 30;
        
        //Reset textY
        textY = frameY + gp.tileSize;
        String textValue;
        
        textValue = String.valueOf(gp.player.level);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.health+"/"+gp.player.maxHealth);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.strength);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.dexterity);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.attack);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.defense);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.exp);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.toNextLevel);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        textValue = String.valueOf(gp.player.money);
        textX = getXAlignR(textValue, valueX);
        g2.drawString(textValue, textX, textY);
        textY += lineHeight;
        
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(valueX - gp.tileSize, textY-24, gp.tileSize, gp.tileSize, 10, 10);
        g2.drawImage(gp.player.currentWeapon.image, valueX - gp.tileSize, textY-24, null);
        textY += gp.tileSize;
        
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(valueX - gp.tileSize, textY-24, gp.tileSize, gp.tileSize, 10, 10);
        g2.drawImage(gp.player.currentProtectives.image, valueX - gp.tileSize, textY-24, null);
        
    }
    
    private void drawTransition() {
        
        counter++;
        g2.setColor(new Color(0, 0, 0, counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.event.tempMap;
            gp.player.worldX = gp.tileSize * gp.event.tempCol;
            gp.player.worldY = gp.tileSize * gp.event.tempRow;
            gp.event.previousX = gp.player.worldX;
            gp.event.previousY = gp.player.worldY;
        }
    }
    
    public void drawInventory(Entity entity, boolean cursor){
        
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        
        if (entity == gp.player) {
            frameX = gp.tileSize*9;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else{
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        
        //Frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize+3;
        
        //Draw Items
        for (int i = 0; i < entity.inventory.size(); i++) {
            
            //Equip cursor
            if (entity.inventory.get(i) == entity.currentProtectives
                    || entity.inventory.get(i) == entity.currentWeapon) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            
            g2.drawImage(entity.inventory.get(i).image, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
        
        //Cursor
        if (cursor) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //Draw Cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //Description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;

            //Description Text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(20f));

            int itemIndex = getItemSlotIndex(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line: entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
        
    }
    public int getItemSlotIndex(int slotCol, int slotRow){
        return slotCol + (slotRow*5);
    }
    private void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXAlignR(String text, int valueX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = valueX - length;
        return x;
    }

    
    
}
