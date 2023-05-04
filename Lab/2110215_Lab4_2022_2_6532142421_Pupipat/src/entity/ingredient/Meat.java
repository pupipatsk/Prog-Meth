package entity.ingredient;

import entity.base.Choppable;
import entity.base.Cookable;
import entity.base.Ingredient;

public class Meat extends Ingredient 
	implements Choppable, Cookable{
	
	private boolean chopState;
	private int cookedPercentage;
	
	public Meat() {
		super("Meat");
		setChopState(false);
		setCookedPercentage(0);
	}

	public void chop() {
		if (this.isChopped()==false || this.getCookedPercentage()==0) {
			setChopState(true);
			setName("Minced Meat");
		}
	}
	
	public boolean isChopped() {
		return chopState;
	}
	
	public void cook() {
		int cp = this.getCookedPercentage();
		if (this.isChopped()) {
			cp += 15;
			if (cp>0 && cp<=80) {
				setName("Raw Burger");
				setEdible(false);
			}
			else if (cp>80 && cp<=100) {
				setName("Cooked Burger");
				setEdible(true);
			}
			else if (cp>100) {
				setName("Burnt Burger");
				setEdible(false);
			}
		}
		else {
			cp += 10;
			if (cp>0 && cp<=50) {
				setName("Raw Meat");
				setEdible(false);
			}
			else if (cp>50 && cp<=80) {
				setName("Medium Rare Steak");
				setEdible(true);
			}
			else if (cp>80 && cp<=100) {
				setName("Well Done Steak");
				setEdible(true);
			}
			else if (cp>100) {
				setName("Burnt Steak");
				setEdible(false);
			}
		}
		setCookedPercentage(cp);
	}
	
	public boolean isBurnt() {
		if (this.getCookedPercentage()>100)
			return true;
		return false;
	}
	
	public String toString() {
		return logic.StringUtil.formatNamePercentage(this.getName(), this.getCookedPercentage());
	}
	
	public void setChopState(boolean chopState) {
		this.chopState = chopState;
	}

	public int getCookedPercentage() {
		return cookedPercentage;
	}
	public void setCookedPercentage(int cookedPercentage) {
		this.cookedPercentage = cookedPercentage;
	}
}
