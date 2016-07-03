
package breakout;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;

public class Brick {
	private final String rectJpg = "/resources/brick.png";
	static final int WIDTH = 30;
	static final int HEIGHT = 15;
	int posX;
	int posY;
	Image im;
	Point leftButtom;
	Point rightButtom;
	Point leftTop;
	Point rightTop;

	public Brick(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		leftTop = new Point(posX, posY);
		leftButtom = new Point(posX, posY + HEIGHT);
		rightButtom = new Point(posX + WIDTH, posY + HEIGHT);
		rightTop = new Point(posX + WIDTH, posY);
		ImageIcon ii = new ImageIcon(this.getClass().getResource(rectJpg));
		im = ii.getImage();
	}

	public void isVisible() {
		posX = -100;
		posY = -100;// poza okno
		Sound.HIT.play();
	}

	public void paint(Graphics2D g) {
		g.drawImage(im, posX, posY, WIDTH, HEIGHT, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, WIDTH, HEIGHT);
	}

	public Line2D getTopLine() {
		return new Line2D.Float(leftTop, rightTop);
	}

	public Line2D getButtomLine() {
		return new Line2D.Float(leftButtom, rightButtom);
	}

	public Line2D getRightLine() {
		return new Line2D.Float(rightTop, rightButtom);
	}

	public Line2D getLeftLine() {
		return new Line2D.Float(leftTop, leftButtom);
	}

	public int getPosY() {
		return this.posY;
	}

	public int getPosX() {
		return posX;
	}

}
