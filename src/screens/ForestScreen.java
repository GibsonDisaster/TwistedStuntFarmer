package screens;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Log;
import entities.Player;

public class ForestScreen extends BasicGameState {
	
	private Random rand;
	private ArrayList<Log> logs;
	private Player player;
	private Image bg, log_img, player_img, forest_exit;
	
	public ForestScreen(int xwwwwww) {
		player = FarmScreen.getPlayer();
		logs = new ArrayList<>();
		rand = new Random();
		
		initLogs();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		initLogs();
		player.setLastScreen(getID());
		player.setX(560);
		player.setY(640);
	}
	
	public void initLogs() {
		logs.clear();
		
		for (int i = 0; i <= 4; i++) {
			logs.add(new Log(rand.nextInt(1120), rand.nextInt(560)));
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Image("res/forest_background.png");
		log_img = new Image("res/log_img.png");
		player_img = new Image("res/player_forest.png");
		forest_exit = new Image("res/town_exit.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.draw(0, 0);
		g.drawString("Wood: " + Integer.toString(player.getWood()), 0, 690);
		
		for (Log l : logs) {
			if (l.getHealth() > 0)
				log_img.draw(l.getX(), l.getY());
		}
		
		forest_exit.draw(560, 640);
		
		player_img.draw(player.getX(), player.getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		player.levelUp();
		
		if (input.isKeyPressed(input.KEY_UP)) {
			if (player.getY()-80 >= 0)
				player.move("up");
		}
		else if (input.isKeyPressed(input.KEY_DOWN)) {
			if (player.getY()+80 < 720)
				player.move("down");
		}
		else if (input.isKeyPressed(input.KEY_RIGHT)) {
			if (player.getX()+80 < 1280)
				player.move("right");
		}	
		else if (input.isKeyPressed(input.KEY_LEFT)) {
			if (player.getX()-80 >= 0)
				player.move("left");
		} else if (input.isKeyPressed(input.KEY_SPACE)) {
			for (Log b : logs) {
				if (checkBounds(player, b) && b.getHealth() > 0) {
					b.takeDamage(1);
					player.giveWood(2 + player.getWood_level());
					player.setWood_xp(1);
				}
			}
		}
		
		if (input.isKeyPressed(input.KEY_ENTER)) {
			if (checkBounds(player, 560, 640, 160, 80))
				sbg.enterState(1);
		}
		
		if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(12);
	}

	@Override
	public int getID() {
		return 6;
	}

	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
	public boolean checkBounds(Player player, Log b) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(b.getX(), b.getY(), 160, 160);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
}
