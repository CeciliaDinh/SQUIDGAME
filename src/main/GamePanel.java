package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Checker.*;
import GUI.MenuState;
import GUI.PauseState;
import GUI.UI;
import Map.*;
import entity.*;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	public static final int orignialTileSize = 16;
	public static final int scale = 3;
	public static final int tilesize = orignialTileSize * scale; // 48X48 tile
	public static final int maxScreenCol = 20;// horizontal, ngang
	public static final int maxScreenRow = 16; // vertical,doc
	public static final int screenWidth = tilesize * maxScreenRow; // 256 ngang
	public static final int screenHeight = tilesize * maxScreenCol;// 320 doc

	// SYSTEM
	KeyHandler keyH = new KeyHandler();
	public static Thread gameThread;// to start and stop the game whenever you want to
	private AssetSetter aSetter = new AssetSetter(this);
	public Collision c = new Collision();
	public static Sound sound = new Sound();
	public Time_Win time_win = new Time_Win(this);
	public UI u = new UI(this);

	// ENTITY &OBJECT
	public Player player = new Player(this, keyH);
	public Boss boss = new Boss(this);
	public NPC obj[] = new NPC[7];
	public PlayerNPC NPC[] = new PlayerNPC[10];
	public double playTime = 60.00;
	int FPS = 60;

	// GAME STATE
	public static int gameState;
	public final static int menuState = 0;
	public final static int playState = 1;
	public static boolean pauseState = false;
	private MenuState menu = new MenuState(keyH);
	private PauseState pause = new PauseState(keyH);

	// Background
	private Background bg = new Background("/background/background2.png");

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setVisible(true);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		// playMusic(0);
		gameState = 0;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawinterval = 1000000000 / FPS;
		double delta = 0;
		long lasttime = System.nanoTime();
		long currenTime;
		long timer = 0;
		int DrawCount = 0;
		while (gameThread != null) {
			currenTime = System.nanoTime();
			delta += (currenTime - lasttime) / drawinterval;
			timer += (currenTime - lasttime);
			lasttime = currenTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				DrawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("Fps" + DrawCount);
				DrawCount = 0;
				timer = 0;
			}

		}
	}

	public void update() {
		if (gameState == playState && pauseState == false) {

			time_win.upcounter();
			boss.update();

			for (int z = 0; z < NPC.length; z++) {
				if (NPC[z] != null) {
					if (NPC[z].getwin() == false) {
						if (NPC[z].getalive() == true && NPC[z].getdying() == false) {
							NPC[z].update();
						}
						if (NPC[z].getalive() == false) {
							NPC[z] = null;
						}
					}
				}
			}
			if (player != null) {
				if (player.getwin() == false) {
					if (player.getalive() == true && player.getdying() == false) {
						player.update();
					}
					if (player.getalive() == false) {
						player = null;
					}
				}
			}

		} else {
			menu.update();
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (gameState == 0) {
			menu.draw(g2);
		}

		if (gameState == 1) {

			bg.draw(g2);
			u.draw(g2);
			boss.draw(g2);

			if (player != null) {
				player.draw(g2);
			}

			for (int j = 1; j <= 6; j++) {
					obj[j].draw(g2);
			}

			for (int i = 0; i < NPC.length; i++) {
				if (NPC[i] != null) {
					NPC[i].draw(g2);
				}
			}

			if (pauseState == true) {
				pause.draw(g2);
				// System.out.println("drawed");
			}
			g2.dispose();
		}
	}

	public static void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}

}
