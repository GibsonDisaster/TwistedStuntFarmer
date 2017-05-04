package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import screens.FarmScreen;
import screens.StartScreen;

public class Game extends StateBasedGame {
	
	private final static int WIDTH = 1280;
	private final static int HEIGHT = 720;
	private final int startScreen = 0;
	private final int farmScreen = 1;

	public Game(String name) {
		super(name);
		this.addState(new StartScreen(startScreen));
		this.addState(new FarmScreen(farmScreen));
	}
	
	public static void main(String[] args) {
		AppGameContainer app;
		
		try {
			app = new AppGameContainer(new Game("Harvest Moon"));
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(startScreen).init(gc, this);
		this.getState(farmScreen).init(gc, this);
		this.enterState(startScreen);
	}

}
