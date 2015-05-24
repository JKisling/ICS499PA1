package core;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Plays audio from a resource.
 * Resource can be local file or a URL.
 *
 */
public class Audio {

	public Audio(String filePath) throws Exception {
		if(filePath.indexOf("http") != -1)
		{
			try{
				URL url = new URL(filePath);
				Clip clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream( url );
				clip.open(ais);
				clip.start();
			}
			catch (Exception e) {
				throw e;
			}
		}
		else
		{
			try {
				Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
		        clip.start();
		      } catch (Exception e) {
		        throw e;
		      }
		}
	}
} 