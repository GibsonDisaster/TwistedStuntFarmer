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

import entities.Boulder;
import entities.Player;

public class QuarryScreen extends BasicGameState {
	
	private Player player;
	private Image boulder_1, boulder_2, boulder_3, player_img, bg, farm_portal;
	private ArrayList<Boulder> boulders;

	public QuarryScreen(int quarryScreen) {
		boulders = new ArrayList<>();
		player = FarmScreen.getPlayer();
		
		initBoulders();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		initBoulders();
		player.setX(560);
		player.setY(640);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		boulder_1 = new Image("res/boulder_1.png");
		boulder_2 = new Image("res/boulder_2.png");
		boulder_3 = new Image("res/boulder_3.png");
		player_img = new Image("res/player_quarry.png");
		bg = new Image("res/farm.png");
		farm_portal = new Image("res/town_exit.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.draw(0, 0);
		g.drawString("Money: " + Integer.toString(player.getMoney()), 0, 690);
		
		for (Boulder b : boulders) {
			if (b.getHealth() > 0) {
				switch(b.getVariant()) {
				case 1:
					boulder_1.draw(b.getX(), b.getY());
					break;
				case 2:
					boulder_2.draw(b.getX(), b.getY());
					break;
				case 3:
					boulder_3.draw(b.getX(), b.getY());
					break;
				}
			}
		}
		
		farm_portal.draw(560, 640);
		
		player_img.draw(player.getX(), player.getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
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
			for (Boulder b : boulders) {
				if (checkBounds(player, b) && b.getHealth() > 0) {
					b.takeDamage(1);
					player.giveMoney(2);
				}
			}
		}
		
		if (input.isKeyPressed(input.KEY_ENTER)) {
			if (checkBounds(player, 560, 640, 160, 80))
				sbg.enterState(1);
		}
	}

	@Override
	public int getID() {
		return 5;
	}
	
	public void initBoulders() {
		Random rand = new Random();
		boulders.clear();
		
		for (int i = 0; i <= 4; i++) {
			boulders.add(new Boulder(rand.nextInt(1120), rand.nextInt(560)));
		}
	}

	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
	public boolean checkBounds(Player player, Boulder b) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(b.getX(), b.getY(), 160, 160);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
}
