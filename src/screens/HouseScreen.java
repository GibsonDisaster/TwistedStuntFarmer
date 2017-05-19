package screens;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;

public class HouseScreen extends BasicGameState {

	private Player player;
	private Image bg, player_img, farm_portal, kitchen_portal, bathroom_portal, cellar_portal;
	
	public HouseScreen(int houseScreen) {
		player = FarmScreen.getPlayer();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setX(560);
		player.setY(640);
		player.setLastScreen(getID());
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Image("res/house_background.png");
		player_img = new Image("res/player_town.png");
		farm_portal = new Image("res/town_exit.png");
		kitchen_portal = new Image("res/kitchen_portal.png");
		bathroom_portal = new Image("res/bathroom_portal.png");
		cellar_portal = new Image("res/cellar_portal.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.draw(0, 0);
		
		farm_portal.draw(560, 640);
		
		if (player.isKitchen())
			kitchen_portal.draw(1120, 0);
		
		if (player.isBathroom())
			bathroom_portal.draw(1120, 640);
		
		if (player.isCellar())
			cellar_portal.draw(0, 640);
		
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
		} else if (input.isKeyPressed(input.KEY_ENTER)) {
			if (checkBounds(player, 560, 640, 160, 80))
				sbg.enterState(1);
			if (checkBounds(player, 1120, 0, 160, 80))
				sbg.enterState(9);
			if (checkBounds(player, 1120, 640, 160, 80))
				sbg.enterState(10);
			if (checkBounds(player, 0, 640, 180, 80))
				sbg.enterState(11);
		}
		
		if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(12);
	}

	@Override
	public int getID() {
		return 8;
	}
	
	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
}
