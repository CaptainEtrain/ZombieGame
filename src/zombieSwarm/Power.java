package zombieSwarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Power extends Rectangle{
	int x;
	int y;
	Image img;
	Power(int x, int y) {
		this.x = x;
		this.y = y;
		this.setBounds(x, y, 48, 48);
		this.img = new ImageIcon(getClass().getResource("/images/chest.png")).getImage();
	}
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		//g.fillRect(x, y, 30, 30);
		g.drawImage(img, x, y, 48,48, null);
	}
}
