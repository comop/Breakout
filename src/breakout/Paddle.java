package breakout;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Paddle {
	private final String racquetJpg = "/resources/paddle.png";
	private static final int POS_Y = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int posX;
	static int speedX;
	Image image;

	public Paddle() {
		posX = Level.WIDTH / 2 - WIDTH / 2;
		speedX = 0;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(racquetJpg));
		image = ii.getImage();
	}

	public void move() {
		if (posX + speedX > 0 && posX + speedX < Level.WIDTH - 60)
			posX += speedX;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, posX, POS_Y, WIDTH, HEIGHT, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, POS_Y, WIDTH, HEIGHT);
	}

}
