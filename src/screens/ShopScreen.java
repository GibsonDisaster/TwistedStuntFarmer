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

public class ShopScreen extends BasicGameState {
	
	private Player player;
	private Image seeds, pointer;
	private int x, y;
	private ArrayList<Flag> flags;
	
	public ShopScreen(int shopScreen) {
		player = FarmScreen.getPlayer();
		x = 160;
		y = 240;
		flags = new ArrayList<>();
		flags.add(new Flag("seeds", 160, 160));
		flags.add(new Flag("fertilizer", 240, 160));
		flags.add(new Flag("coupon", 320, 160));
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		seeds = new Image("res/seeds.png");
		pointer = new Image("res/pointer.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		seeds.draw(160, 160);
		seeds.draw(240, 160);
		seeds.draw(320, 160);
		
		pointer.draw(x, y);
		
		if (getFlag(x, y).equals("seeds")) {
			g.drawString("Seeds: " + Integer.toString(player.getSeeds()), 0, 660);
			g.drawString("Money: " + Integer.toString(player.getMoney()), 0, 690);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_LEFT) && x-80 >= 160)
			x -= 80;
		if (input.isKeyPressed(input.KEY_RIGHT) && x+80 <= 320)
			x += 80;
		if (input.isKeyPressed(input.KEY_SPACE))
			sbg.enterState(1);
		if (input.isKeyPressed(input.KEY_ENTER)) {
			if (player.getMoney() - 4 > 0) {
				switch(getFlag(x, y)) {
					case "seeds":
						player.giveMoney(-4);
						player.giveSeeds(1);
						break;
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
