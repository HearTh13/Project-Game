package main;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/Normal Town.wav");
        soundURL[1] = getClass().getResource("/sound/Mysteries Crossed.wav");
        soundURL[2] = getClass().getResource("/sound/8bitDreamLand.wav");
        soundURL[18] = getClass().getResource("/sound/walk.wav");
        soundURL[19] = getClass().getResource("/sound/energy.wav");
        soundURL[20] = getClass().getResource("/sound/health.wav");
        soundURL[21] = getClass().getResource("/sound/coin2.wav");
        soundURL[22] = getClass().getResource("/sound/fire.wav");
        soundURL[23] = getClass().getResource("/sound/tadaFanfare.wav");
        soundURL[24] = getClass().getResource("/sound/swordStrike.wav");
        soundURL[25] = getClass().getResource("/sound/damageReceived.wav");
        soundURL[26] = getClass().getResource("/sound/powerUp.wav");
        soundURL[27] = getClass().getResource("/sound/coin.wav");
        soundURL[28] = getClass().getResource("/sound/doorLock.wav");
        soundURL[29] = getClass().getResource("/sound/doorOpen.wav");
        
    }
    
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            
        }
    }
    
    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
    
}
