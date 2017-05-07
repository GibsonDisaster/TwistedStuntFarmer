package logistics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TextBox {

	private int x, y, width, height;
	private boolean done;
	private String msg, name;
	
	public TextBox(String msg, String name) {
		this.x = 0;
		this.y = 480;
		this.width = 1280;
		this.height = 240;
		this.msg = msg;
		this.name = name;
		this.done = false;
	}
	
	public void drawBox(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.white);
		g.drawString(this.name + ":", this.x + 20, this.y + 20);
		g.drawString(this.msg, this.x+20, this.y+60);
	}

	//Getters and Setters
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
