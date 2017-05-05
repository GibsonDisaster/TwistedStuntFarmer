package entities;

public class Player {

	private int x, y, money, seeds;
	String current_tool;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.current_tool = "hoe";
		this.money = 0;
		this.seeds = 4;
	}
	
	public int getSeeds() {
		return this.seeds;
	}
	
	public void giveSeeds(int s) {
		this.seeds += s;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void giveMoney(int m) {
		this.money += m;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getCurrentTool() {
		return this.current_tool;
	}
	
	public void setTool(String tool) {
		this.current_tool = tool;
	}
	
	public void move(String dir) {
		switch(dir) {
			case "up":
				this.y -= 80;
				break;
			case "down":
				this.y += 80;
				break;
			case "left":
				this.x -= 80;
				break;
			case "right":
				this.x += 80;
				break;
		}
	}
	
}
