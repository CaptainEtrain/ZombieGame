package zombieSwarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Gun implements MouseMotionListener, MouseListener, KeyListener{
	private String ID;
	static int xCircle = 375;
	static int yCircle = 650;
	int xVel = 0;
	int yVel = 0;
	int radius = 35;
	double rotate = 0;
	static int coolDown = 60;
	static boolean powerUp = false;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	Rectangle rectangle;
	static int coolPowerUp = 400;
	Gun(String ID) {
		rectangle = new Rectangle(xCircle-17, yCircle-17, 22,22);
		this.ID=ID;
	}
	public static void coolPowerUp() {
		if (powerUp) {
			if (coolPowerUp > 0) {
				coolPowerUp--;
			} else {
				coolPowerUp = 400;
				powerUp = false;
			}
		}
	}
	public void tick() {
		coolPowerUp();
		xCircle+=xVel;
		yCircle+=yVel;
		rectangle.setLocation(xCircle, yCircle);
		coolDown++;
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
	}
	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform orgTransform = g2d.getTransform();
		RenderingHints rh = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);	
		g2d.setRenderingHints(rh);
		Rectangle2D.Double r;
		if (!powerUp) {
			r = new Rectangle2D.Double(xCircle-radius+27,yCircle-radius*3+7,20,100);
			g2d.rotate(rotate, xCircle,yCircle);
		} else {//Power up 
			r = new Rectangle2D.Double(xCircle-radius+27,yCircle-radius*3+87,20,800);
			AffineTransform powerRotate = new AffineTransform();
			powerRotate.rotate(rotate+Math.PI, xCircle, yCircle);
			Shape pr = powerRotate.createTransformedShape(r);
			for (int i = 0; i < Field.zombies.size(); i++) {
				if (pr.intersects(Field.zombies.get(i))) {
					//Field.zombies.get(i).randomPower(); Makes game easer
					Field.zombies.remove(i);
					
				}
			
			}
			g.setColor(Color.YELLOW);
			g2d.rotate(rotate+Math.PI, xCircle,yCircle-0);
		}
		
		g2d.fill(r);
		g2d.setTransform(orgTransform);
		
		g.setColor(Color.BLACK);

		g.fillOval(xCircle-radius, yCircle-radius, radius*2, radius*2);

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		double x = e.getX()-xCircle;
		double y = e.getY()-yCircle;
		rotate = Math.atan2(y,x)+Math.PI/2;
		//System.out.println(rotate);
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!Gun.powerUp) {
//		if (coolDown < 60) {//Makes it harder
//			
//			return;
//		}
			coolDown -= 60;
			double gunAngle = Math.atan2(e.getY() - yCircle, e.getX() - xCircle);
			double speed = 9.0; // Bullet speed
			double bulletVelX = Math.cos(gunAngle) * speed;
			double bulletVelY = Math.sin(gunAngle) * speed;

			bullets.add(new Bullet(xCircle - 9, yCircle - 16, bulletVelX, bulletVelY, rotate));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==e.VK_D) {
			xVel=3;
		}
		if (e.getKeyCode()==e.VK_A) {
			xVel=-3;
		}
		if (e.getKeyCode()==e.VK_W) {
			yVel=-3;
		}
		if (e.getKeyCode()==e.VK_S) {
			yVel=3;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==e.VK_D) {
			xVel=0;
		}
		if (e.getKeyCode()==e.VK_A) {
			xVel=-0;
		}
		if (e.getKeyCode()==e.VK_W) {
			yVel=-0;
		}
		if (e.getKeyCode()==e.VK_S) {
			yVel=0;
		}
	}
	
	
}
