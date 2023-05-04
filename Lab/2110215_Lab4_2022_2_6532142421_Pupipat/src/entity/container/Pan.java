package entity.container;

import entity.base.Container;
import entity.base.Cookable;
import entity.base.Ingredient;

public class Pan extends Container{

	public Pan() {
		super("Pan", 1);
	}
	
	public boolean verifyContent(Ingredient i) {
		return i instanceof Cookable;
	}

	public void cook() {
		if (!this.isEmpty()) {
			for (Ingredient e : this.getContent()) {
				((Cookable) e).cook();
			}
		}
	}
}