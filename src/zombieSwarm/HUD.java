package zombieSwarm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class HUD {
	ArrayList<Zombie> zombies;
	int level = 0;
	boolean stop = false;
	HUD(ArrayList<Zombie> zombies) {
		this.zombies = zombies;
	}
	public void stopLevels() {
		stop = true;
	}
	public void tick() {
		if (!stop) {
			level++;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		Font font = new Font("arial", 1, 20);
		g.setFont(font);
		g.drawString("Zombie Live Count: " + zombies.size(), 20, 20);
		g.drawString("Level " + (level/500+1), 20, 45);
	}
}
