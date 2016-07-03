package breakout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaddleListener implements KeyListener {


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Paddle.speedX = -2;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Paddle.speedX = 2;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Paddle.speedX = 0;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
