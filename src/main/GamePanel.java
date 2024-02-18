package main;

import event.Event;
import inputs.KeyboardInputs;
import entity.*;
import interactives.InteractiveObject;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    //Screen
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale;//48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels
    
    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;
    
    //FPS
    int FPS = 60;
    
    //System
    public TileManager tile = new TileManager(this);
    public KeyboardInputs key = new KeyboardInputs(this);
    Sound music = new Sound();
    Sound SE = new Sound();
    public CollisionChecker cc = new CollisionChecker(this);
    public AssetSetter asset = new AssetSetter(this);
    public UI ui = new UI(this);
    public Event event = new Event(this);
    Thread gameThread;
    
    //Entity and Object
    public Player player = new Player(this, key);
    public InteractiveObject iObject[][] = new InteractiveObject[maxMap][50];
    public Entity[][] obj = new Entity[maxMap][250];
    public Entity[][] npc = new Entity[maxMap][20];
    public Entity[][] mon = new Entity[maxMap][20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //GameState
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int menuState = 4;
    public final int gameOverState = 5;
    public final int sceneState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    
    //Scene
    public String scenePos = "";
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    public void setupGame(){
        asset.setObject();
        asset.setNPC();
        asset.setMonster();
        asset.setInteractives();
        playMusic(2);
        gameState = titleState;
        ui.scene = 0;
        currentMap = 0;
    }
    public void retry(){
        switch(currentMap){
            case 0:
                player.setDefaultPosition();
                break;
            case 1:
                player.setDefaultPosition(1, 2, "down");
                break;
            case 2:
                player.setDefaultPosition();
                break;
            case 3:
                player.setDefaultPosition(3, 47, "right");
                break;
        }
        
        player.restoreStatus();
        asset.setMonster();
        asset.setNPC();
    }
    public void restart(){
        player.setDefaultValues();
        player.setItems();
        asset.setMonster();
        asset.setNPC();
        asset.setObject();
        ui.scene = 0;
        currentMap = 0;
        ui.boss = false;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if (timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
        
    }
    public void update(){
        if (gameState == playState) {
            //Player
            player.update();
            
            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            
            //Monsters
            for (int i = 0; i < mon[1].length; i++) {
                if (mon[currentMap][i] != null) {
                    if (mon[currentMap][i].alive && !mon[currentMap][i].dead) {
                        mon[currentMap][i].update();
                    }
                    if (!mon[currentMap][i].alive) {
                        mon[currentMap][i].checkDrop();
                        mon[currentMap][i] = null;
                    }
                }
            }
            
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
            
            for (int i = 0; i < iObject[1].length; i++) {
                if (iObject[currentMap][i] != null) {
                    iObject[currentMap][i].update();
                }
            }
            
        }
        else {
            //Nothing
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Debug
        long drawStart = 0;
        if (key.debugButton) {
            drawStart = System.nanoTime();
        }
        
        //Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //Play
        else {
            
            //Tile
            tile.draw(g2);
            
            for (int i = 0; i < iObject[1].length; i++) {
                if (iObject[currentMap][i] != null) {
                    iObject[currentMap][i].draw(g2);
                }
            }
            
            //Add Entities to list
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < mon[1].length; i++) {
                if (mon[currentMap][i] != null) {
                    entityList.add(mon[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            
            //Sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            
            //Draw Entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            
            //Empty the list (Otherwise it will get larger)
            entityList.clear();

            //UI
            ui.draw(g2);
        }
        
        if (key.debugButton == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }
    
}
