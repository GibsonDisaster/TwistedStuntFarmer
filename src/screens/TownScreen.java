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
import entities.Tile;
import logistics.TextBox;

public class TownScreen extends BasicGameState{
	
	private Image background, player_img, town_exit, talking_img, npc_img, elise_img, front_stall, side_stall;
	private Player player;
	private ArrayList<TextBox> text;
	private ArrayList<Tile> tiles;
	private ArrayList<Point> walls;

	public TownScreen(int townScreen) {
		player = FarmScreen.getPlayer();
		text = new ArrayList<>();
		tiles = new ArrayList<>();
		walls = new ArrayList<>();
		walls.add(new Point(1120, 400));
		walls.add(new Point(1200, 400));
		walls.add(new Point(1120, 480));
		walls.add(new Point(1120, 560));
		walls.add(new Point(1200, 560));
		tiles.add(new Tile(80, 240, "Hello! How are you today?", "Jeff"));
		tiles.add(new Tile(800, 560, "I'm a cutie!", "Elise"));
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.getLast_screen().equals("farm"))
			player.setY(0);
		player.setLast_screen("town");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/town_background.png");
		player_img = new Image("res/player_town.png");
		npc_img = new Image("res/npc.png");
		town_exit = new Image("res/town_exit.png");
		talking_img = new Image("res/talking_point.png");
		elise_img = new Image("res/elise.png");
		front_stall = new Image("res/front_stall.png");
		side_stall = new Image("res/side_stall.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		town_exit.draw(560, 0);
		player_img.draw(player.getX(), player.getY());
		
		elise_img.draw(800, 640);
		npc_img.draw(0, 240);
		npc_img.draw(1200, 480);
		front_stall.draw(1120, 400);
		side_stall.draw(1200, 560);
		side_stall.draw(1200, 400);
		
		for (Tile t : tiles) {
			talking_img.draw(t.getX(), t.getY());
		}
		
		for (TextBox t : text) {
			t.drawBox(g);
		}
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
			if (checkBounds(player, 560, 0, 160, 80))
				sbg.enterState(1);
		}
		if (input.isKeyPressed(input.KEY_SPACE)) {
			if (text.isEmpty()) {
					if (isOnTile(player)) {
						Tile t = findTile(player.getX(), player.getY());
						text.add(new TextBox(t.getMsg(), t.getName()));
					}
			} else {
				text.clear();
			}
			
			if (player.getX() == 1040 && player.getY() == 480) {
				player.setDiscount(true);
				sbg.enterState(2);
			}
		}
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
	
	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
	public boolean isOnTile(Player player) {
		boolean f = false;
		for (Tile t : tiles) {
			if (player.getX() == t.getX() && player.getY() == t.getY())
				f = true;
		}
		return f;
	}
	
	public Tile findTile(int x, int y) {
		Tile tile = new Tile(0, 4999);
		for (Tile t : tiles) {
			if (x == t.getX() && y == t.getY())
				tile = t;
		}
		return tile;
	}

	@Override
	public int getID() {
		return 3;
	}

}
