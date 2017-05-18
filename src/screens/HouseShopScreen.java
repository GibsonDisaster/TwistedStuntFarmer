package screens;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;
import logistics.Flag;

public class HouseShopScreen extends BasicGameState {
	
	private int x, y, house_g, kitchen_g, cellar_g, bathroom_g, house_w, kitchen_w, cellar_w, bathroom_w;
	private Player player;
	private ArrayList<Flag> flags;
	private Image house_img, kitchen_img, bathroom_img, cellar_img, pointer;

	public HouseShopScreen(int houseShopScreen) {
		house_g = 500;
		house_w = 100;
		kitchen_g = 700;
		kitchen_w = 200;
		cellar_g = 1000;
		cellar_w = 500;
		bathroom_g = 300;
		bathroom_w = 50;
		x = 160;
		y = 240;
		player = FarmScreen.getPlayer();
		flags = new ArrayList<>();
		flags.add(new Flag("House", 160, 160));
		flags.add(new Flag("Kitchen", 240, 160));
		flags.add(new Flag("Bathroom", 320, 160));
		flags.add(new Flag("Cellar", 400, 160));
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		house_img = new Image("res/house_shop.png");
		kitchen_img = new Image("res/kitchen_shop.png");
		bathroom_img = new Image("res/bathroom_shop.png");
		cellar_img = new Image("res/cellar_shop.png");
		pointer = new Image("res/pointer.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (!player.isHouse())
			house_img.draw(160, 160);
		
		if (player.isHouse() && !player.isKitchen())
			kitchen_img.draw(240, 160);
		
		if (player.isHouse() && !player.isBathroom())
			bathroom_img.draw(320, 160);
		
		if (player.isHouse() && !player.isCellar())
			cellar_img.draw(400, 160);
		
		pointer.draw(x, y);
		
		if (getFlag(x, y).equals("House")) {
			g.drawString("Gold: " + Integer.toString(house_g) + " " + "Wood: " + Integer.toString(house_w), 0, 20);
		} else if (getFlag(x, y).equals("Kitchen")) {
			g.drawString("Gold: " + Integer.toString(kitchen_g) + " " + "Wood: " + Integer.toString(kitchen_w), 0, 20);
		} else if (getFlag(x, y).equals("Cellar")) {
			g.drawString("Gold: " + Integer.toString(cellar_g) + " " + "Wood: " + Integer.toString(cellar_w), 0, 20);
		} else if (getFlag(x, y).equals("Bathroom")) {
			g.drawString("Gold: " + Integer.toString(bathroom_g) + " " + "Wood: " + Integer.toString(bathroom_w), 0, 20);
		} 
		
		g.drawString("Current Wood: " + Integer.toString(player.getWood()), 10, 700);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_LEFT) && x-80 >= 0)
			x -= 80;
		if (input.isKeyPressed(input.KEY_RIGHT) && x+80 <= 1280)
			x += 80;
		if (input.isKeyPressed(input.KEY_SPACE))
			sbg.enterState(3);
		if (input.isKeyPressed(input.KEY_ENTER)) {
			if (getFlag(x, y).equals("House")) {
				if (player.getMoney() - house_g >= 0 && player.getWood() - house_w >= 0 && !player.isHouse()) {
					player.giveMoney(-house_g);
					player.giveWood(-house_w);
					player.setHouse(true);
				}
			} else if (getFlag(x, y).equals("Kitchen")) {
				if (player.getMoney() - kitchen_g >= 0 && player.getWood() - kitchen_w >= 0 && player.isHouse()) {
					player.giveMoney(-kitchen_g);
					player.giveWood(-kitchen_w);
					player.setKitchen(true);
				}
			} else if (getFlag(x, y).equals("Cellar")) {
				if (player.getMoney() - cellar_g >= 0 && player.getWood() - cellar_w >= 0 && player.isHouse()) {
					player.giveMoney(-cellar_g);
					player.giveWood(-cellar_w);
					player.setCellar(true);
				}
			} else if (getFlag(x, y).equals("Bathroom")) {
				if (player.getMoney() - bathroom_g >= 0 && player.getWood() -bathroom_w >= 0 && player.isHouse()) {
					player.giveMoney(-bathroom_g);
					player.giveWood(-bathroom_w);
					player.setBathroom(true);
				}
			} 
		}
	}

	@Override
	public int getID() {
		return 7;
	}
	
	public String getFlag(int x, int y) {
		String thing = "";
		for (Flag f : flags) {
			if (f.getX() == x && f.getY() == y - 80)
				thing = f.getId();
		}
		return thing;
	}

}
