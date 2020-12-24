package YachtDice;

import java.io.File;
import javax.sound.sampled.*;

public class DiceEffect{
    File bgm;
    Clip clip;
    FloatControl choi;
    private float vol=(-5.2f);
    public void play() {
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        bgm = new File("utill/BGM/RollDice.wav");

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            choi=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            choi.setValue(vol);

        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }
    public void setvol(float vol){
        this.vol=vol;
    }
}
