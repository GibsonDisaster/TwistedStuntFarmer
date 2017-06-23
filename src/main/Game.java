package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import screens.BarnScreen;
import screens.BathroomScreen;
import screens.CellarScreen;
import screens.FarmScreen;
import screens.ForestScreen;
import screens.HelpScreen;
import screens.HouseScreen;
import screens.HouseShopScreen;
import screens.KitchenScreen;
import screens.QuarryScreen;
import screens.ShopScreen;
import screens.StartScreen;
import screens.TownScreen;

public class Game extends StateBasedGame {
	
	private final static int WIDTH = 1280;
	private final static int HEIGHT = 720;
	private final int startScreen = 0;
	private final int farmScreen = 1;
	private final int shopScreen = 2;
	private final int townScreen = 3;
	private final int barnScreen = 4;
	private final int quarryScreen = 5;
	private final int forestScreen = 6;
	private final int houseShopScreen = 7;
	private final int houseScreen = 8;
	private final int kitchenScreen = 9;
	private final int bathroomScreen = 10;
	private final int cellarScreen = 11;
	private final int helpScreen = 12;

	public Game(String name) {
		super(name);
		this.addState(new StartScreen(startScreen));
		this.addState(new FarmScreen(farmScreen));
		this.addState(new ShopScreen(shopScreen));
		this.addState(new TownScreen(townScreen));
		this.addState(new BarnScreen(barnScreen));
		this.addState(new QuarryScreen(quarryScreen));
		this.addState(new ForestScreen(forestScreen));
		this.addState(new HouseShopScreen(houseShopScreen));
		this.addState(new HouseScreen(houseScreen));
		this.addState(new KitchenScreen(kitchenScreen));
		this.addState(new BathroomScreen(bathroomScreen));
		this.addState(new CellarScreen(cellarScreen));
		this.addState(new HelpScreen(helpScreen));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(startScreen).init(gc, this);
		this.getState(farmScreen).init(gc, this);
		this.getState(shopScreen).init(gc, this);
		this.getState(townScreen).init(gc, this);
		this.getState(barnScreen).init(gc, this);
		this.getState(quarryScreen).init(gc, this);
		this.getState(forestScreen).init(gc, this);
		this.getState(houseScreen).init(gc, this);
		this.getState(houseShopScreen).init(gc, this);
		this.getState(kitchenScreen).init(gc, this);
		this.getState(helpScreen).init(gc, this);
		this.enterState(farmScreen);
	}

}
