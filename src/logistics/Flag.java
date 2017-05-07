package logistics;

public class Flag {

	private String id;
	private int x, y;
	
	public Flag(String id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
}
