package zombieSwarm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Zombie extends Rectangle{
	private Image imgL;
	private Image imgR;
	double x;
	double y;
	double xVel;
	double yVel;
	Zombie(int x, int y) {
		this.imgL = new ImageIcon(getClass().getResource("/images/zombieL.png")).getImage();
		this.imgR = new ImageIcon(getClass().getResource("/images/zombieR.png")).getImage();
	
		this.x = x;
		this.y = y;
		this.height = 32;
		this.width = 32;
		
		
	}
	public void randomPower() {
		if ((int)(Math.random()*11)==0) {
			Field.addPower((int)x,(int)y);
		}
	}
	public void tick() {
		double deltaX = Gun.xCircle - x; // The difference in the X direction
		double deltaY = Gun.yCircle - y; // The difference in the Y direction
		
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		
		double speed = 1.25; 
		double normX = deltaX / distance; 
		double normY = deltaY / distance; 
		
		xVel = normX * speed;
		yVel = normY * speed;
		x+=xVel;
		y+=yVel;
		this.setLocation((int)x, (int)y);
	}
	public void render(Graphics g) {
		
		if (xVel>0)
			g.drawImage(imgR, (int)x, (int)y, null);
		else 
			g.drawImage(imgL, (int)x, (int)y, null);
	}
}
