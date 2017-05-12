package entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private int x, y, money, seeds, multi;
	private boolean discount, barn;
	private String current_tool, last_screen;
	private ArrayList<Animal> animals;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.current_tool = "hoe";
		this.money = 200;
		this.seeds = 4;
		this.multi = 2;
		this.discount = false;
		this.barn = false;
		this.last_screen = "town";
		this.animals = new ArrayList<>();
	}
	
	public void addAnimals(Animal a) {
		this.animals.add(a);
	}
	
	public ArrayList getAnimals() {
		return this.animals;
	}
	
	public boolean isBarn() {
		return barn;
	}

	public void setBarn(boolean barn) {
		this.barn = barn;
	}

	public String getLast_screen() {
		return last_screen;
	}

	public void setLast_screen(String last_screen) {
		this.last_screen = last_screen;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, 80, 80);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getMulti() {
		return this.multi;
	}
	
	public void setMulti(int i) {
		this.multi = i;
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
