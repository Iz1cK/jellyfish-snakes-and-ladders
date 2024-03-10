package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class AudioTest {

	public static void sounds(String str) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		  
		  
		  File file = new File("correctanswer.wav");
		  AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		  Clip clip = AudioSystem.getClip();
		  clip.open(audioStream);
		 
		  
		  switch (str) {
      case "S":
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

