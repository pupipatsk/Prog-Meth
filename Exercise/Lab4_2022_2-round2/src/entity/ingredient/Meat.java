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
	
	public void setChopState(boolean chopState) {
		this.chopState = chopState;
	}
	public void setCookedPercentage(int cookedPercentage) {
		this.cookedPercentage = cookedPercentage;
	}
	public void chop() {
		if (!(isChopped() || this.getCookedPercentage()!=0)) {
			setChopState(true);
			setName("Minced Meat");
		}
	}
	public int getCookedPercentage() {
		return cookedPercentage;
	}
	public boolean isChopped() {
		return chopState;
	}
	public void cook() {
		if (!isChopped()) {
			setCookedPercentage( this.getCookedPercentage() + 10 );
			
			int cp = this.getCookedPercentage();
			if (0<cp && cp<=50) {
				setName("Raw Meat");
				setEdible(false);
			}
			else if (50<cp && cp<=80) {
				setName("Medium Rare Steak");
				setEdible(true);
			}
			else if (80<cp && cp<=100) {
				setName("Well Done Stea");
				setEdible(true);
			}
			else if (cp>100) {
				setName("Burnt Steak");
				setEdible(false);
			}
		}
		else {
			setCookedPercentage( this.getCookedPercentage() + 15 );
			
			int cp = this.getCookedPercentage();
			if (0<cp && cp<=80) {
				setName("Raw Burger");
				setEdible(true);
			}
			else if (80<cp && cp<=100) {
				setName("Cooked Burger");
				setEdible(true);
			}
			else if (cp>100) {
				setName("Burnt Burger");
				setEdible(true);
			}
		}
	}
	public boolean isBurnt() {
		if (this.getCookedPercentage()>100) {
			return true;
		}
		return false;
	}
	public String toString() {
		return logic.StringUtil.formatNamePercentage(this.getName(), this.getCookedPercentage());
	}
}
