package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class AudioTest {

	public static Clip clip;
	
	public static void sounds(String str, String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		switch (str) {
		      case "S":
		    	  File file = new File(filename);
				  AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				  clip = AudioSystem.getClip();
				  clip.open(audioStream);
		          clip.start();
		          break;
		      case "P":
		          clip.stop();
		          break;
		      default:
		          System.out.println("Not a valid response");
		}		   
	}
}

