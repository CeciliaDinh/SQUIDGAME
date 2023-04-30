package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound{
    Clip clip;
    URL soundUrl[]= new URL[30];
    public Sound(){
        soundUrl[0]=getClass().getResource("/sound/Squid Game Red Light Green Light Sound.wav");
        soundUrl[1]=getClass().getResource("/sound/gameover.wav");
        soundUrl[2]=getClass().getResource("/sound/levelup.wav");
        
        


    }
    public void setFile(int i){
        try {
            AudioInputStream ais=AudioSystem.getAudioInputStream(soundUrl[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();
    }
}