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

import entities.Animal;
import entities.Player;
import logistics.Flag;

public class ShopScreen extends BasicGameState {
	
	private Player player;
	private Image seeds, pointer, value_up, barn, background, pig, quarry, chicken, cow, sheep;
	private int x, y, seed_price, pig_price, value_up_price, barn_price, quarry_price, chicken_price, cow_price, sheep_price;
	private ArrayList<Flag> flags;
	private boolean valueUpIsBought = false;
	private Random rand;
	
	public ShopScreen(int shopScreen) {
		rand = new Random();
		player = FarmScreen.getPlayer();
		x = 160;
		y = 240;
		flags = new ArrayList<>();
		flags.add(new Flag("seeds", 160, 160));
		flags.add(new Flag("barn", 240, 160));
		flags.add(new Flag("value up", 320, 160));
		flags.add(new Flag("pig", 400, 160));
		flags.add(new Flag("quarry", 480, 160));
		flags.add(new Flag("cow", 560, 160));
		flags.add(new Flag("chicken", 640, 160));
		flags.add(new Flag("sheep", 720, 160));
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.isDiscount()) {
			seed_price = 2;
			pig_price = 25;
			value_up_price = 50;
			barn_price = 50;
			quarry_price = 100;
			chicken_price = 10;
			cow_price = 75;
			sheep_price = 40;
		} else {
			seed_price = 4;
			pig_price = 50;
			value_up_price = 100;
			barn_price = 100;
			quarry_price = 200;
			chicken_price = 20;
			cow_price = 150;
			sheep_price = 80;
		}
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
		quarry = new Image("res/quarry_shop.png");
		sheep = new Image("res/sheep_shop.png");
		chicken = new Image("res/chicken_shop.png");
		cow = new Image("res/cow_shop.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		
		seeds.draw(160, 160);
		g.drawString("$" + Integer.toString(seed_price), 160, 120);
		
		if (!player.isBarn()) {
			barn.draw(240, 160);
			g.drawString("$" + Integer.toString(barn_price), 240, 120);
		}
		
		if (player.isBarn()) {
			pig.draw(400, 160);
			g.drawString("$" + Integer.toString(pig_price), 400, 120);
		
			cow.draw(560, 160);
			g.drawString("$" + Integer.toString(cow_price), 540, 120);
			
			chicken.draw(640, 160);
			g.drawString("$" + Integer.toString(chicken_price), 620, 120);
			
			sheep.draw(720, 160);
			g.drawString("$" + Integer.toString(sheep_price), 720, 120);
		}
		
		if (!valueUpIsBought) {
			value_up.draw(320, 160);
			g.drawString("$" + Integer.toString(value_up_price), 320, 120);
		}
		
		if (!player.isQuarry()) {
			quarry.draw(480, 160);
			g.drawString("$" + Integer.toString(quarry_price), 480, 120);
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
		if (input.isKeyPressed(input.KEY_RIGHT) && x+80 <= 720)
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
						if (player.getMoney() - value_up_price >= 0) {
							player.giveMoney(-value_up_price);
							player.setMulti(2);
							valueUpIsBought = true;
						}
						break;
					case "barn":
						if (player.getMoney() - barn_price >= 0 && !player.isBarn()) {
							player.giveMoney(-barn_price);
							player.setBarn(true);
						}
						break;
					case "pig":
						if (player.getMoney() - pig_price >= 0 && player.isBarn()) {
							player.giveMoney(-pig_price);
							player.addAnimals(new Animal("pig", rand.nextInt(1200), rand.nextInt(640)));
						}
						break;
					case "quarry":
						if (player.getMoney() - quarry_price >= 0 && !player.isQuarry()) {
							player.giveMoney(-quarry_price);
							player.setQuarry(true);
						}
						break;
					case "cow":
						if (player.getMoney() - cow_price >= 0 && player.isBarn()) {
							player.giveMoney(-cow_price);
							player.addAnimals(new Animal("cow", rand.nextInt(1200), rand.nextInt(640)));
						}
						break;
					case "chicken":
						if (player.getMoney() - chicken_price >= 0 && player.isBarn()) {
							player.giveMoney(-chicken_price);
							player.addAnimals(new Animal("chicken", rand.nextInt(1200), rand.nextInt(640)));
						}
						break;
					case "sheep":
						if (player.getMoney() - sheep_price >= 0 && player.isBarn()) {
							player.giveMoney(-sheep_price);
							player.addAnimals(new Animal("sheep", rand.nextInt(1200), rand.nextInt(640)));
						}
						break;
			}
		}
		System.out.println(getFlag(x, y));
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
