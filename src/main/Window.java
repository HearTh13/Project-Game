package main;

import javax.swing.*;

public class Window {
    
    public Window(){}
    
    public void createWindow(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("2D Adventure");
        
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    
}
