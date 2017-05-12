package screens;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Animal;
import entities.Player;
import logistics.Flag;

public class ShopScreen extends BasicGameState {
	
	private Player player;
	private Image seeds, pointer, value_up, barn, background, pig;
	private int x, y, seed_price;
	private ArrayList<Flag> flags;
	private boolean valueUpIsBought = false;
	
	public ShopScreen(int shopScreen) {
		player = FarmScreen.getPlayer();
		x = 160;
		y = 240;
		flags = new ArrayList<>();
		flags.add(new Flag("seeds", 160, 160));
		flags.add(new Flag("barn", 240, 160));
		flags.add(new Flag("value up", 320, 160));
		flags.add(new Flag("pig", 400, 160));
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.isDiscount())
			seed_price = 2;
		else 
			seed_price = 4;
	}
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		switch(player.getLast_screen()) {
			case "farm":
				sbg.enterState(1);
				break;
			case "town":
				sbg.enterState(3);
				break;
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		seeds = new Image("res/seeds.png");
		pointer = new Image("res/pointer.png");
		value_up = new Image("res/value.png");
		barn = new Image("res/barn.png");
		background = new Image("res/shop_background.png");
		pig = new Image("res/pig_shop.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		
		seeds.draw(160, 160);
		g.drawString(Integer.toString(seed_price), 160, 120);
		barn.draw(240, 160);
		g.drawString("$100", 240, 120);
		pig.draw(400, 160);
		g.drawString("$50", 400, 120);
		
		if (!valueUpIsBought) {
			value_up.draw(320, 160);
			g.drawString("$100", 320, 120);
		}
		
		pointer.draw(x, y);
		
		if (getFlag(x, y).equals("seeds")) {
			g.drawString("Seeds: " + Integer.toString(player.getSeeds()), 0, 660);
		}
		
		g.drawString("Money: " + Integer.toString(player.getMoney()), 0, 690);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_LEFT) && x-80 >= 160)
			x -= 80;
		if (input.isKeyPressed(input.KEY_RIGHT) && x+80 <= 400)
			x += 80;
		if (input.isKeyPressed(input.KEY_SPACE))
			sbg.enterState(1);
		if (input.isKeyPressed(input.KEY_ENTER)) {
				switch(getFlag(x, y)) {
					case "seeds":
						if (player.getMoney()-seed_price >= 0) {
							player.giveMoney(-seed_price);
							player.giveSeeds(1);
						}
						break;
					case "value up":
						if (player.getMoney() - 100 >= 0) {
							player.giveMoney(-100);
							player.setMulti(2);
							valueUpIsBought = true;
						}
						break;
					case "barn":
						if (player.getMoney() - 100 >= 0 && !player.isBarn()) {
							player.giveMoney(-100);
							player.setBarn(true);
						}
						break;
					case "pig":
						if (player.getMoney() - 50 >= 0 && player.isBarn()) {
							player.giveMoney(-50);
							player.addAnimals(new Animal("pig", 0, 0));
						}
			}
		}
	}
	
	public String getFlag(int x, int y) {
		String thing = "";
		for (Flag f : flags) {
			if (f.getX() == x && f.getY() == y - 80)
				thing = f.getId();
		}
		return thing;
	}

	@Override
	public int getID() {
		return 2;
	}

}
