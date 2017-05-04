package entities;

public class Tile {

	private int x, y, stage;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.stage = 0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getStage() {
		return this.stage;
	}
	
	public void setStage(int s) {
		this.stage = s;
	}
	
}
