
package breakout;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("/resources/ball.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("/resources/gameover.wav"));
	public static final AudioClip WIN = Applet.newAudioClip(Sound.class.getResource("/resources/win.wav"));
	public static final AudioClip HIT = Applet.newAudioClip(Sound.class.getResource("/resources/hit.wav"));
}