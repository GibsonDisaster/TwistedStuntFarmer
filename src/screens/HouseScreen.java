package screens;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;

public class HouseScreen extends BasicGameState {

	private Player player;
	private Image bg, player_img, farm_portal, kitchen_portal, bathroom_portal, cellar_portal, table_img, chair_left, chair_right, chair_back,
	chair_front;
	private ArrayList<Point> walls;
	
	public HouseScreen(int houseScreen) {
		player = FarmScreen.getPlayer();
		walls = new ArrayList<>();
		walls.add(new Point(640, 240));
		walls.add(new Point(720, 240));
		walls.add(new Point(640, 320));
		walls.add(new Point(720, 320));
		walls.add(new Point(580, 240));
		walls.add(new Point(800, 240));
		walls.add(new Point(640, 160));
		walls.add(new Point(640, 400));
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
		table_img = new Image("res/table_img.png");
		chair_left = new Image("res/chair_left.png");
		chair_right = new Image("res/chair_right.png");
		chair_back = new Image("res/chair_back.png");
		chair_front = new Image("res/chair_front.png");
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
		
		table_img.draw(640, 240);
		chair_left.draw(580, 240);
		chair_right.draw(800, 240);
		chair_front.draw(640, 160);
		chair_back.draw(640, 400);
		
		player_img.draw(player.getX(), player.getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_UP)) {
			if (player.getY()-80 >= 0 && !isSafe("up"))
				player.move("up");
		}
		else if (input.isKeyPressed(input.KEY_DOWN)) {
			if (player.getY()+80 < 720 && !isSafe("down"))
				player.move("down");
		}
		else if (input.isKeyPressed(input.KEY_RIGHT)) {
			if (player.getX()+80 < 1280 && !isSafe("right"))
				player.move("right");
		}	
		else if (input.isKeyPressed(input.KEY_LEFT)) {
			if (player.getX()-80 >= 0 && !isSafe("left"))
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
	
	public boolean isSafe(String dir) {
		boolean f = false;
		for (Point p : walls) {
			switch(dir) {
				case "up": 
					if (player.getX() == p.getX() && player.getY()-80 == p.getY())
						f = true;
					break;
				case "down":
					if (player.getX() == p.getX() && player.getY()+80 == p.getY())
						f = true;
					break;
				case "left":
					if (player.getX()-80 == p.getX() && player.getY() == p.getY())
						f = true;
					break;
				case "right":
					if (player.getX()+80 == p.getX() && player.getY() == p.getY())
						f = true;
					break;
			}
		}
		return f;
	}
}
