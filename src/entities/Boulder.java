package entities;

public class Boulder {
	
	private int x, y, variant;

	public Boulder(int x, int y, int variant) {
		this.x = x;
		this.y = y;
		this.variant = variant;
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

}
