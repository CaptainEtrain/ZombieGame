package zombieSwarm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Bullet{
	double x;
	double y;
	double xVel;
	double yVel;
	Image img;
	double angle;
	Rectangle rectangle;
	Shape rotatedHitBox;
	Bullet(int x, int y, double xVel, double yVel, double angle) {
		img = new ImageIcon(getClass().getResource("/images/bullet.png")).getImage();
		if (x>750||x<0) {
			x = 10000;
		}
		if (y>750||y<0) {
			y = 10000;
		}
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		
		rectangle = new Rectangle(y, x, 18, 32);
		
		
		this.angle = angle;//Get correct angles
		
		
	}
	public Shape getBulletHitBox() {
		AffineTransform transform = new AffineTransform();

		// Rotate the hitbox around its center (bulletX + width/2, bulletY + height/2)
		transform.rotate(angle, x + 18 / 2, y + 32 / 2);
		
		rotatedHitBox = transform.createTransformedShape(rectangle);
		return rotatedHitBox;
	}
	
	public void tick() {
		x+=xVel;
		y+=yVel;
		
		rectangle.setLocation((int)x, (int)y);
		
		
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform originalTransform = g2d.getTransform();
		
		AffineTransform transform = new AffineTransform();
	    transform.rotate(angle, x + rectangle.width / 2, y + rectangle.height / 2);
	    
		g2d.setTransform(transform);
		g2d.drawImage(img, (int)x, (int)y, null);
	
		g2d.setTransform(originalTransform);
	}
}
