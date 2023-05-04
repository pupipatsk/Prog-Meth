package entity.ingredient;

import entity.base.Cookable;
import entity.base.Ingredient;

public class Egg extends Ingredient
	implements Cookable{
	
	private int cookedPercentage;
	
	public Egg() {
		super("Egg");
		setCookedPercentage(0);
	}
	public void setCookedPercentage(int cookedPercentage) {
		this.cookedPercentage = cookedPercentage;
	}
	public int getcookedPercentage() {
		return this.cookedPercentage;
	}
	public void cook() {
		setCookedPercentage( this.getcookedPercentage() + 12 );
		
		int cp = this.getcookedPercentage();
		if (0<cp && cp<=50) {
			setName("Raw Egg");
			setEdible(false);
		}
		else if (50<cp && cp<=80) {
			setName("Sunny Side Egg");
			setEdible(true);
		}
		else if (80<cp && cp<=100) {
			setName("Fried Egg");
			setEdible(true);
		}
		else if (cp>100) {
			setName("Burnt Egg");
			setEdible(false);
		}
	}
	public boolean isBurnt() {
		if (this.getcookedPercentage()>100) {
			return true;
		}
		return false;
	}
	public String toString() {
		return logic.StringUtil.formatNamePercentage(this.getName(), this.getcookedPercentage()); 
	}
}
