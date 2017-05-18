package entities;

public class Log {
	
	private int x, y, health;

	public Log(int x, int y) {
		this.x = x;
		this.y = y;
		this.health = 5;
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
	
	public int getHealth() {
		return this.health;
	}
	
	public void takeDamage(int dmg) {
		this.health -= dmg;
	}

}
