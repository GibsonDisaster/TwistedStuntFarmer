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

import entities.Player;
import entities.Tile;

public class FarmScreen extends BasicGameState {
	
	private static Player player;
	private Image player_hoe, player_seller, player_seeds, player_buyer, background, dirt,
	tilled_dirt, plant_stage1, plant_stage2, plant_stage3, plant_stage4, town_portal, barn_portal, quarry_portal, forest_portal, 
	house_portal;
	private ArrayList<Tile> tiles;

	public FarmScreen(int farmScreen) {
		player = new Player(1200, 640);
		tiles = new ArrayList<>();
		int j = 0;
		
		j = 80;
		for (int i = 0; i < 14; i++) {
			tiles.add(new Tile(j, 160));
			j += 80;
		}
		
		j = 80;
		for (int i = 0; i < 14; i++) {
			tiles.add(new Tile(j, 240));
			j += 80;
		}
		
		j = 80;
		for (int i = 0; i < 14; i++) {
			tiles.add(new Tile(j, 320));
			j += 80;
		}
		
		j = 80;
		for (int i = 0; i < 14; i++) {
			tiles.add(new Tile(j, 400));
			j += 80;
		}
		
		j = 80;
		for (int i = 0; i < 14; i++) {
			tiles.add(new Tile(j, 480));
			j += 80;
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.getLastScreen() == 3)
			player.setY(640);
		else if (player.getLastScreen() == 4)
			player.setY(0);
		else if (player.getLastScreen() == 5) {
			player.setX(1120);
			player.setY(0);
		} else if (player.getLastScreen() == 6) {
			player.setX(0);
			player.setY(0);
		}
		
		player.setLastScreen(getID());
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_hoe = new Image("res/player_hoe.png");
		player_seller = new Image("res/player_seller.png");
		player_seeds = new Image("res/player_seeds.png");
		player_buyer = new Image("res/player_buyer.png");
		town_portal = new Image("res/town_portal.png");
		barn_portal = new Image("res/barn_entrance.png");
		quarry_portal = new Image("res/quarry_portal.png");
		forest_portal = new Image("res/forest_portal.png");
		house_portal = new Image("res/house_portal.png");
		background = new Image("res/farm.png");
		dirt = new Image("res/dirt.png");
		tilled_dirt = new Image("res/tilled_dirt.png");
		plant_stage1 = new Image("res/plant_stage1.png");
		plant_stage2 = new Image("res/plant_stage2.png");
		plant_stage3 = new Image("res/plant_stage3.png");
		plant_stage4 = new Image("res/plant_stage4.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		g.drawString("Money: " + Integer.toString(player.getMoney()), 0, 690);
		
		for (Tile t : tiles) {
			if (t.getStage() == 0)
				dirt.draw(t.getX(), t.getY());
			else if (t.getStage() == 1)
				plant_stage1.draw(t.getX(), t.getY());
			else if (t.getStage() == 2)
				plant_stage2.draw(t.getX(), t.getY());
			else if (t.getStage() == 3)
				plant_stage3.draw(t.getX(), t.getY());
			else if (t.getStage() >= 4 && t.getStage() != 69)
				plant_stage4.draw(t.getX(), t.getY());
			else if (t.getStage() == 69)
				tilled_dirt.draw(t.getX(), t.getY());
			
		}
		
		if (player.isHouse())
			house_portal.draw(0, 640);
		
		town_portal.draw(560, 640);
		
		forest_portal.draw(0, 0);
		
		if (player.isBarn())
			barn_portal.draw(560, 0);
		
		if (player.isQuarry())
			quarry_portal.draw(1120, 0);
		
		if (player.getCurrentTool().equals("hoe"))
			player_hoe.draw(player.getX(), player.getY());
		else if (player.getCurrentTool().equals("seller"))
			player_seller.draw(player.getX(), player.getY());
		else if (player.getCurrentTool().equals("seeds")) {
			player_seeds.draw(player.getX(), player.getY());
			g.drawString(Integer.toString(player.getSeeds()), 1260, 690);
		} else if (player.getCurrentTool().equals("buyer")) {
			player_buyer.draw(player.getX(), player.getY());
			g.drawString(Integer.toString(findTile(player.getX(), player.getY()).getStage()), 1260, 690);
		}
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
		}
		else if (input.isKeyPressed(input.KEY_SPACE)) {
			Tile currentTile = findTile(player.getX(), player.getY());
			
			switch(player.getCurrentTool()) {
				case "hoe":
					currentTile.setStage(69);
					break;
				case "seller":
					if (currentTile.getStage() != 69) {
						player.giveMoney(currentTile.getStage() * player.getMulti());
						currentTile.setStage(0);
					}
					break;
				case "seeds":
					if (currentTile.getStage() == 69 && player.getSeeds() > 0) {
						currentTile.setStage(1);
						player.giveSeeds(-1);
					}
					break;
				case "buyer":
					player.setDiscount(false);
					sbg.enterState(2);
					break;
			}
		} else if (input.isKeyPressed(input.KEY_ENTER)) {
			if (checkBounds(player, 560, 640, 160, 80))
				sbg.enterState(3);
			if (checkBounds(player, 560, 0, 160, 80) && player.isBarn())
				sbg.enterState(4);
			if (checkBounds(player, 1120, 0, 160, 80))
				sbg.enterState(5);
			if (checkBounds(player, 0, 0, 160, 80))
				sbg.enterState(6);
			if (checkBounds(player, 0, 640, 160, 80) && player.isHouse())
				sbg.enterState(8);
		}
		else if (input.isKeyPressed(input.KEY_1))
			player.setTool("hoe");
		else if (input.isKeyPressed(input.KEY_2))
			player.setTool("seeds");
		else if (input.isKeyPressed(input.KEY_3))
			player.setTool("seller");
		else if (input.isKeyPressed(input.KEY_4))
			player.setTool("buyer");
		else if (input.isKeyPressed(input.KEY_5))
			sbg.enterState(6);
		
		if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(12);
		
		for (Tile t : tiles) {
			Random rand = new Random();
			
			if (rand.nextInt(1000) == 5 && t.getStage() > 0 && t.getStage() < 4)
				t.setStage(t.getStage()+1);
		}
	}
	
	public Tile findTile(int x, int y) {
		Tile tile = new Tile(0, 4999);
		for (Tile t : tiles) {
			if (x == t.getX() && y == t.getY())
				tile = t;
		}
		return tile;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}

	@Override
	public int getID() {
		return 1;
	}

}
