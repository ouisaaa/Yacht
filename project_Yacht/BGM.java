package YachtDice;

import java.io.File;
import javax.sound.sampled.*;

public class BGM extends Thread{
    File backgroundmusic = new File("utill/BGM/FloralLife.wav");
    Clip clip;
    FloatControl ctrl;
    private float vol=(-11.184f);
    public void play(float vol) {
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        try {
            stream = AudioSystem.getAudioInputStream(backgroundmusic);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            ctrl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            ctrl.setValue(vol);
        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }
    public void deplay(){
        if(clip!=null)clip.close();
    }
    public void run(){
        while(true) {
            try {
                play(getVol());
                Thread.sleep(137000); // 3초에 한번씩 재생하도록 설정
            } catch(InterruptedException e) {
                deplay();
                break;
            }
        }
    }
    public void setvol(float vol){
        this.vol=vol;
    }
    public float getVol(){return vol;}
}

