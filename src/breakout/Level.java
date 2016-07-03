package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Level extends JPanel {
	Ball ball = new Ball(this);
	Paddle paddle = new Paddle();
	private final String bg = "/resources/bg.png";
	ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));
	Image image = ii.getImage();
	static final int WIDTH = 286;
	static final int HEIGHT = 400;
	static final int NUMBER_OF_BRICKS = 45;
	private boolean setUpTime = true;
	int numberOfLevel = 1;
	Brick[] bricks = new Brick[NUMBER_OF_BRICKS];

	public Level() {
		initRects();
		beginLevel();
	}

	private void initRects() {
		int posX = -25;
		int posY = 5;
		for (int i = 0; i < NUMBER_OF_BRICKS; i++) {
			if (i % 5 == 0)
				posY = 5;
			if (i % 9 == 0)
				posX = -25;
			bricks[i] = new Brick(posX += Brick.WIDTH, posY += Brick.HEIGHT + 1);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, null, this);
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Verdana", Font.BOLD, 12));

		if (!setUpTime) {
			for (int i = 0; i < NUMBER_OF_BRICKS; i++) {
				bricks[i].paint(g2d);
			}
			ball.paint(g2d);
			paddle.paint(g2d);
			g2d.drawString("SCORE: " + GameController.overallScore, 3, 14);
			g2d.drawString("                                                     LEVEL: " + numberOfLevel, 3, 14);
		} else if (setUpTime) {
			g2d.drawString("GET READY!", 100, 180);
			setUpTime = false;
		}
	}

	public void beginLevel() {
		JFrame frame = new JFrame("Crazy Breakout");
		this.addKeyListener(new PaddleListener());
		this.setFocusable(true);
		
		frame.add(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(image);

		try {
			Thread.sleep(1000);// czas przygotowania
			while (!GameController.endGameFail() && !GameController.endGameSuccess()) {
				ball.move();
				paddle.move();
				this.repaint();
				Thread.sleep(16);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.dispose();
	}

	public void reset() {
		setUpTime = true;
		initRects();
		ball.posX = WIDTH / 2;
		ball.posY = HEIGHT / 2;
		Ball.gameOver = false;
	}

	public void resetAfterFail() {
		reset();
		numberOfLevel = 1;
		GameController.overallScore = 0;
	}

	public void resetAfterWin() {
		reset();
		GameController.overallScore++;
		numberOfLevel++;
	}
}
