package entities;

import java.util.Random;

public class Boulder {
	
	private int x, y, variant, health;

	public Boulder(int x, int y) {
		this.x = x;
		this.y = y;
		this.variant = pickVariant();
		this.health = 3;
	}
	
	public int pickVariant() {
		Random rand = new Random();
		
		switch(rand.nextInt(3)) {
			case 0:
				return 1;
			case 1:
				return 2;
			case 2:
				return 3;
			default:
				return 1;
		}
	}

	//Getter and Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVariant() {
		return variant;
	}

	public void setVariant(int variant) {
		this.variant = variant;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void takeDamage(int dmg) {
		this.health -= dmg;
	}

}
