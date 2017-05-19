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

	private Image player_img, barn_background, barn_exit, pig_img, chicken_img, sheep_img, cow_img, player_shears, player_basket, player_milker, 
	default_player, sheep_sheared_img;
	private Player player;
	private ArrayList<Animal> animals;
	private String current_tool;
	
	public BarnScreen(int barnScreen) {
		player = FarmScreen.getPlayer();
		current_tool = "nothing";
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setY(640);
		player.setLastScreen(getID());
		animals = player.getAnimals();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_img = new Image("res/player_town.png");
		player_shears = new Image("res/player_shears.png");
		player_milker = new Image("res/player_milker.png");
		player_basket = new Image("res/player_egg_basket.png");
		default_player = new Image("res/player_town.png");
		barn_background = new Image("res/barn_background.png");
		barn_exit = new Image("res/town_exit.png");
		pig_img = new Image("res/pig_img.png");
		chicken_img = new Image("res/chicken_img.png");
		sheep_img = new Image("res/sheep_img.png");
		sheep_sheared_img = new Image("res/sheep_sheared_img.png");
		cow_img = new Image("res/cow_img.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		barn_background.draw(0, 0);
		barn_exit.draw(560, 640);
		g.drawString("Money: " + Integer.toString(player.getMoney()), 0, 690);
		
		for (Animal a : animals) {
			switch(a.getSpecies()) {
			case "pig":
				pig_img.draw(a.getX(), a.getY());
				break;
			case "chicken":
				chicken_img.draw(a.getX(), a.getY());
				break;
			case "sheep":
				if (a.getCharge() >= 3)
					sheep_img.draw(a.getX(), a.getY());
				else
					sheep_sheared_img.draw(a.getX(), a.getY());
				break;
			case "cow":
				cow_img.draw(a.getX(), a.getY());
				break;
			}
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
		
		if (input.isKeyPressed(input.KEY_1)) {
			current_tool = "milker";
		} else if (input.isKeyPressed(input.KEY_2)) {
			current_tool = "shears";
		} else if (input.isKeyPressed(input.KEY_3)) {
			current_tool = "basket";
		}
		
		if (input.isKeyPressed(input.KEY_SPACE)) {
			for (Animal a : animals) {
				switch(current_tool) {
				case "shears":
					if (checkBounds(player, a) && a.getSpecies().equals("sheep") && a.getCharge() >= 3) {
						player.giveMoney(1);
						a.giveCharge(0);
					}
					break;
				case "basket":
					if (checkBounds(player, a) && a.getSpecies().equals("chicken") && a.getCharge() >= 3) {
						player.giveMoney(1);
						a.giveCharge(0);
					}
					break;
				case "milker":
					if (checkBounds(player, a) && a.getSpecies().equals("cow") && a.getCharge() >= 3) {
						player.giveMoney(1);
						a.giveCharge(0);
					}
					break;
				}
			}
		}
		
		for (Animal a : animals) {
			a.pickRandLocation();
			a.update();
			System.out.println(a.getCharge());
		}
		
		switch(current_tool) {
		case "milker":
			player_img = player_milker;
			break;
		case "shears":
			player_img = player_shears;
			break;
		case "basket":
			player_img = player_basket;
			break;
		case "default":
			player_img = default_player;
			break;
		}
		
		if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(12);
	}
	
	public boolean checkBounds(Player player, int x, int y, int width, int height) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(x, y, width, height);
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
	public boolean checkBounds(Player player, Animal a) {
		Rectangle rect1 = new Rectangle(player.getX(), player.getY(), 80, 80);
		Rectangle rect2 = new Rectangle(a.getX(), a.getY(), a.getWidth(), 80);
		
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
