package zombieSwarm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field extends JPanel{
	Gun gun;
	HUD hud;
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Power> powers = new ArrayList<Power>();
	int zombieTimer =0;
	int swarmLevel = 0;
	int numOfZombies = 1;
	int spawnRadius = 250;
	boolean end = false;
	
	Image zombieImg =  new ImageIcon(getClass().getResource("/images/zombieR.png")).getImage();
	Field(Gun gun) {
		this.gun=gun;
		hud = new HUD(zombies);
	}
	public void tick() {
		hud.tick();
		zombieTimer++;
		if (zombieTimer>250) {
			zombieTimer = 0;
			swarmLevel++;
			
		}
		if (swarmLevel == 1) {
			swarmLevel =0;
			for (int i = 0; i < numOfZombies; i++) {
				
//			
				double randomDistance = spawnRadius + (Math.random() * 200);  

			    double randomAngle = Math.random() * Math.PI * 2;

			    // Calculate the zombie's X and Y positions using polar coordinates
			    int zombieX = (int) (Gun.xCircle + randomDistance * Math.cos(randomAngle));
			    int zombieY = (int) (Gun.yCircle + randomDistance * Math.sin(randomAngle));

			   // System.out.println("Zombie " + i + " position: (" + zombieX + ", " + zombieY + ")");
			    
			    zombies.add(new Zombie(zombieX, zombieY));
			}
			numOfZombies+=2;
		} 
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).tick();
			if (zombies.get(i).y>750 ||zombies.get(i).y<0 ||zombies.get(i).x>750 ||zombies.get(i).x<0) {
				zombies.remove(i);
			}
		}
		gun.tick();
		for (int i = 0; i < zombies.size(); i++) {
			for (int j = 0; j < gun.bullets.size(); j++) {
				try {
				if (gun.bullets.get(j).getBulletHitBox().intersects(zombies.get(i))) {
					zombies.get(i).randomPower();
					zombies.remove(i);
					break;
				}
				} catch(Exception e) {
					System.out.println("error");
				}
			}
		}
		for (int i = 0; i < zombies.size(); i++) {
			if (gun.rectangle.intersects(zombies.get(i))) {
				end = true;
				hud.stopLevels();
				zombies.clear();
			}
		}
		if (!Gun.powerUp) {
			for (int i = 0; i < powers.size(); i++) {
				if (gun.rectangle.intersects(powers.get(i))) {
					powers.remove(i);
					Gun.coolPowerUp();
					Gun.powerUp = true;
				}
			}
		}
	}
	public static void addPower(int x, int y) {
		powers.add(new Power(x,y));
	}
	public void render() {
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!end) {
			
		
		for(int i = 0; i < zombies.size(); i++) {
			zombies.get(i).render(g);;
		}
		for (int i = 0; i < powers.size(); i++) {
			powers.get(i).render(g);
		}
		hud.render(g);
		gun.render(g);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 800);
			
			g.setFont(new Font("arial", 1, 60));
			g.setColor(Color.RED);
			g.drawString("You died on level "+ hud.level/500, 100, 150);
			g.drawImage(zombieImg, 225,275,320,320, null);
		}
		
	}
}
