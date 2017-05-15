package screens;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Boulder;

public class QuarryScreen extends BasicGameState {
	
	private ArrayList<Boulder> boulders;

	public QuarryScreen(int quarryScreen) {
		boulders = new ArrayList<>();
		
		initBoulders();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for (Boulder b : boulders) {
			g.fillRect(b.getX(), b.getY(), 10, 10);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 5;
	}
	
	public void initBoulders() {
		Random rand = new Random();
		boulders.clear();
		
		for (int i = 0; i <= 4; i++) {
			boulders.add(new Boulder(rand.nextInt(1200), rand.nextInt(640), 1));
		}
	}

	
}
