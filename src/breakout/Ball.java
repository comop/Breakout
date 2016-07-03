package breakout;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

class Ball {
	public static String ballJpg = "/resources/ball.png";
	Random rand = new Random();
	double posX;
	double posY;
	double speedX;
	double speedY;
	private Level game;
	Image image;
	static boolean gameOver = false;
	static final int WIDTH = 15;
	static final int HEIGHT = 15;

	public Ball(Level game) {
		posX = Level.WIDTH / 2;
		posY = Level.HEIGHT / 2;
		speedX = rand.nextDouble() * 3;
		speedY = rand.nextDouble() * 3;
		this.game = game;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(ballJpg));
		image = ii.getImage();
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	void move() {
		if (posX + speedX < 0) {// lewa sciana
			speedX = Math.max(1,rand.nextDouble() * 5);
			Sound.BALL.play();
		}
		if (posX + speedX > Level.WIDTH - WIDTH) { // prawa sciana
			speedX = -Math.max(1,rand.nextDouble() * 5);
			Sound.BALL.play();
		}
		if (posY + speedY < 15) { // gorna sciana
			speedY = Math.max(1,rand.nextDouble() * 5);
			Sound.BALL.play();
		}
		if (posY + speedY > Level.HEIGHT - HEIGHT) { // nizej niz paddle
			gameOver = true;
		}
		if (collisionWithPaddle()) {
			speedY = -Math.max(1,rand.nextDouble() * 5);
			speedX = Paddle.speedX * Math.max(1,rand.nextDouble() * 5);
			Sound.BALL.play();
		}
		for (int i = 0; i < Level.NUMBER_OF_BRICKS; i++) {
			if (collisionWithBrick(i)) {
				if (collisionWithButtomOrTopEdge(i))
					speedY *= (-1);
				else
					speedX *= (-1);
				game.bricks[i].isVisible();
				GameController.overallScore++;
			}
		}
		posX += speedX;
		posY += speedY;

	}

	private boolean collisionWithButtomOrTopEdge(int i) {
		return getBounds().intersectsLine(game.bricks[i].getButtomLine())
				|| getBounds().intersectsLine(game.bricks[i].getTopLine());
	}

	private boolean collisionWithPaddle() {
		return game.paddle.getBounds().intersects(getBounds());
	}

	private boolean collisionWithBrick(int i) {
		return game.bricks[i].getBounds().intersects(getBounds());

	}

	public Rectangle getBounds() {
		return new Rectangle((int) posX + WIDTH / 2 - 1, (int) posY, 1, HEIGHT);
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, (int) posX, (int) posY, WIDTH, HEIGHT, null);
	}
}
