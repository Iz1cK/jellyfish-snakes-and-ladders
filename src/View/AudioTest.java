package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class AudioTest {

	public static Clip clip;
	
	public static AudioTest ATinstance = null;
	
	public static AudioTest getInstance() {
		if(ATinstance == null)
			ATinstance = new AudioTest();
		return ATinstance;
	}
	
	public AudioTest() {}
	
	public void startSounds(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = new File(filename);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();	   
	}
	
	public void stopSound() {
		if(clip == null) return;
		clip.stop();
	}
	
}

