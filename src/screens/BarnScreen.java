package screens;

import java.awt.Rectangle;
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

public class BarnScreen extends BasicGameState {

	private Image player_img, barn_background, barn_exit, pig_img;
	private Player player;
	private ArrayList<Animal> animals;
	
	public BarnScreen(int barnScreen) {
		player = FarmScreen.getPlayer();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setY(640);
		player.setLast_screen("barn");
		animals = player.getAnimals();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_img = new Image("res/player_town.png");
		barn_background = new Image("res/barn_background.png");
		barn_exit = new Image("res/town_exit.png");
		pig_img = new Image("res/pig_img.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		barn_background.draw(0, 0);
		barn_exit.draw(560, 640);
		
		for (Animal a : animals) {
			pig_img.draw(a.getX(), a.getY());
		}
		
		player_img.draw(player.getX(), player.getY());
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
		
		if (input.isKeyPressed(input.KEY_ENTER)) {
			if (checkBounds(player, 560, 640, 160, 80))
				sbg.enterState(1);
		}
		
		for (Animal a : animals) {
			a.pickRandLocation();
			a.update();
		}
		
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
		return 4;
	}

}
