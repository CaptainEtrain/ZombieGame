package zombieSwarm;

import javax.swing.JFrame;

public class Game extends JFrame {
	boolean running = true;
	Field field;
	Gun gun;

	Game() {// Around 400 lines of code
		gun = new Gun("gun");
		field = new Field(gun);

		this.setSize(750, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Zombie Swarm");

		this.addMouseMotionListener(gun);
		this.addMouseListener(gun);
		this.addKeyListener(gun);
		this.add(field);
		this.setVisible(true);

		long now;
		long updateTime;
		long wait;

		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (running) {
			now = System.nanoTime();

			render();
			tick();

			updateTime = System.nanoTime() - now;
			wait = (OPTIMAL_TIME - updateTime) / 1000000;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void tick() {
		field.tick();
	}

	private void render() {
		field.render();
	}

	public static void main(String[] args) {

		new Game();
	}

}
