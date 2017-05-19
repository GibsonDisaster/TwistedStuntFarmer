package entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private int x, y, money, seeds, multi, wood, woodYield, stoneYield, animalYield, lastScreen;
	private boolean discount, barn, quarry, house, kitchen, bathroom, cellar;
	private String current_tool;
	private ArrayList<Animal> animals;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.current_tool = "hoe";
		this.money = 20;
		this.seeds = 4;
		this.multi = 2;
		this.wood = 0;
		this.discount = false;
		this.quarry = false;
		this.barn = false;
		this.house = false;
		this.kitchen = false;
		this.bathroom = false;
		this.cellar = false;
		this.lastScreen = 1;
		this.animals = new ArrayList<>();
	}
	
	public void giveWood(int s) { // ;D
		this.wood += s;
	}
	
	public int getWood() { // ;D
		return this.wood;
	}
	
	public void setQuarry(boolean x) {
		this.quarry = x;
	}
	
	public boolean isQuarry() {
		return this.quarry;
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

	public int getLastScreen() {
		return this.lastScreen;
	}

	public void setLastScreen(int last_screen) {
		this.lastScreen = last_screen;
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
	
	public boolean isHouse() {
		return house;
	}

	public void setHouse(boolean house) {
		this.house = house;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isBathroom() {
		return bathroom;
	}

	public void setBathroom(boolean bathroom) {
		this.bathroom = bathroom;
	}

	public boolean isCellar() {
		return cellar;
	}

	public void setCellar(boolean cellar) {
		this.cellar = cellar;
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
