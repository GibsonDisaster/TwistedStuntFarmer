package screens;

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
	
	private Player player;
	private Image player_hoe, player_seller, player_seeds, background, dirt, tilled_dirt, plant_stage1, plant_stage2, plant_stage3, plant_stage4;
	private ArrayList<Tile> tiles;

	public FarmScreen(int farmScreen) {
		player = new Player(0, 0);
		tiles = new ArrayList<>();
		
		int j = 0;
		for (int i = 0; i < 16; i++) {
			tiles.add(new Tile(j, 0));
			j += 80;
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_hoe = new Image("res/player_hoe.png");
		player_seller = new Image("res/player_seller.png");
		player_seeds = new Image("res/player_seeds.png");
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
		g.drawString(Integer.toString(player.getMoney()), 0, 690);
		
		for (Tile t : tiles) {
			if (t.getStage() == 0)
				dirt.draw(t.getX(), t.getY());
			else if (t.getStage() == 1)
				plant_stage1.draw(t.getX(), t.getY());
			else if (t.getStage() == 2)
				plant_stage2.draw(t.getX(), t.getY());
			else if (t.getStage() == 3)
				plant_stage3.draw(t.getX(), t.getY());
			else if (t.getStage() == 4)
				plant_stage4.draw(t.getX(), t.getY());
			else if (t.getStage() == 69)
				tilled_dirt.draw(t.getX(), t.getY());
		}
		
		if (player.getCurrentTool().equals("hoe"))
			player_hoe.draw(player.getX(), player.getY());
		else if (player.getCurrentTool().equals("seller"))
			player_seller.draw(player.getX(), player.getY());
		else if (player.getCurrentTool().equals("seeds"))
			player_seeds.draw(player.getX(), player.getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_UP))
			player.move("up");
		else if (input.isKeyPressed(input.KEY_DOWN))
			player.move("down");
		else if (input.isKeyPressed(input.KEY_RIGHT))
			player.move("right");
		else if (input.isKeyPressed(input.KEY_LEFT))
			player.move("left");
		else if (input.isKeyPressed(input.KEY_SPACE)) {
			Tile currentTile = findTile(player.getX(), player.getY());
			
			switch(player.getCurrentTool()) {
				case "hoe":
					currentTile.setStage(69);
					break;
				case "seller":
						player.giveMoney(currentTile.getStage());
						currentTile.setStage(0);
					break;
				case "seeds":
					if (currentTile.getStage() == 69)
						currentTile.setStage(1);
			}
		}
		else if (input.isKeyPressed(input.KEY_1))
			player.setTool("hoe");
		else if (input.isKeyPressed(input.KEY_2))
			player.setTool("seller");
		else if (input.isKeyPressed(input.KEY_3))
			player.setTool("seeds");
		
		for (Tile t : tiles) {
			Random rand = new Random();
			
			if (rand.nextInt(1000) == 5 && t.getStage() > 0 && t.getStage() < 5)
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

	@Override
	public int getID() {
		return 1;
	}

}
