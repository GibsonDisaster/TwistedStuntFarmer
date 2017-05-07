package entities;

public class Tile {

	private String msg = "";
	private String name = "";
	private int x, y, stage;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.stage = 0;
	}
	
	public Tile(int x, int y, String msg, String name) {
		this.x = x;
		this.y = y;
		this.msg = msg;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
