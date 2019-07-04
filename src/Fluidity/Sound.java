package Fluidity;

import org.jfugue.Pattern;
import org.jfugue.Player;

public class Sound {

	private static Player player = new Player();
	private static Pattern pattern = new Pattern(
			" I[ACOUSTIC_GRAND] C3s E3s G4s E4s C4s E4s G5s E5s A5s B5s G5s E5s C5s C4q");
	
	public static synchronized void play() {
		new Thread(new Runnable() {
			public void run() {
				player.play(pattern); // GUItar heheh
			}
		}).start();
	}
	
	public static void stop() {
		player.close();
	}
}