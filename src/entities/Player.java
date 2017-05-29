package entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private int x, y, money, seeds, multi, wood, woodYield, stoneYield, animalYield, lastScreen, chopped_wood, wood_xp, animal_xp, stone_xp, wood_level, animal_level, 
	stone_level;
	private boolean discount, barn, quarry, house, kitchen, bathroom, cellar;
	private String current_tool;
	private ArrayList<Animal> animals;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.current_tool = "hoe";
		this.money = 2000000;
		this.seeds = 4;
		this.multi = 2;
		this.wood = 1000000000;
		this.discount = false;
		this.quarry = false;
		this.barn = false;
		this.house = false;
		this.kitchen = false;
		this.bathroom = false;
		this.cellar = false;
		this.lastScreen = 1;
		this.animals = new ArrayList<>();
		this.animal_xp = 0;
		this.animal_level = 0;
		this.wood_xp = 0;
		this.wood_level = 0;
		this.stone_xp = 0;
		this.stone_level = 0;
	}
	
	public void levelUp() {
		this.stone_level = Math.floorDiv(this.stone_xp, 10);
		this.wood_level = Math.floorDiv(this.wood_xp, 10);
		this.animal_level = Math.floorDiv(this.animal_xp, 10);
	}
	
	//Getters and Setters
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

	public int getWoodYield() {
		return woodYield;
	}

	public void setWoodYield(int woodYield) {
		this.woodYield = woodYield;
	}

	public int getStoneYield() {
		return stoneYield;
	}

	public void setStoneYield(int stoneYield) {
		this.stoneYield = stoneYield;
	}

	public int getAnimalYield() {
		return animalYield;
	}

	public void setAnimalYield(int animalYield) {
		this.animalYield = animalYield;
	}

	public int getChopped_wood() {
		return chopped_wood;
	}

	public void setChopped_wood(int chopped_wood) {
		this.chopped_wood = chopped_wood;
	}

	public int getWood_xp() {
		return wood_xp;
	}

	public void setWood_xp(int wood_xp) {
		this.wood_xp += wood_xp;
	}

	public int getAnimal_xp() {
		return animal_xp;
	}

	public void setAnimal_xp(int animal_xp) {
		this.animal_xp += animal_xp;
	}

	public int getStone_xp() {
		return stone_xp;
	}

	public void setStone_xp(int stone_xp) {
		this.stone_xp += stone_xp;
	}

	public int getWood_level() {
		return wood_level;
	}

	public void setWood_level(int wood_level) {
		this.wood_level = wood_level;
	}

	public int getAnimal_level() {
		return animal_level;
	}

	public void setAnimal_level(int animal_level) {
		this.animal_level = animal_level;
	}

	public int getStone_level() {
		return stone_level;
	}

	public void setStone_level(int stone_level) {
		this.stone_level = stone_level;
	}

	public String getCurrent_tool() {
		return current_tool;
	}

	public void setCurrent_tool(String current_tool) {
		this.current_tool = current_tool;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public void setAnimals(ArrayList<Animal> animals) {
		this.animals = animals;
	}
	
}
