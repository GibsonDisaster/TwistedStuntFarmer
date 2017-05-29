package screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;

public class HelpScreen extends BasicGameState {
	
	private Player player;

	public HelpScreen(int helpScreen) {
		player = FarmScreen.getPlayer();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Gold: " + Integer.toString(player.getMoney()), 10, 20);
		g.drawString("Wood: " + Integer.toString(player.getWood()), 10, 40);
		g.drawString("Seeds: " + Integer.toString(player.getSeeds()), 10, 60);
		g.drawString("Multiplier: " + Integer.toString(player.getMulti()), 10, 80);
		g.drawString("Wood Cutting level: " + Integer.toString(player.getWood_level()), 10, 100);
		g.drawString("Animal Level: " + Integer.toString(player.getAnimal_level()), 10, 120);
		g.drawString("Stone level: " + Integer.toString(player.getStone_level()), 10, 140);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(player.getLastScreen());
	}

	@Override
	public int getID() {
		return 12;
	}

}
