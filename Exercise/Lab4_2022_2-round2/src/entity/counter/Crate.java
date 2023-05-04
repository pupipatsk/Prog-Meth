package entity.counter;

import logic.InvalidIngredientException;
import logic.Player;

public class Crate extends Counter{

	private String myIngredient;
	
	public Crate(String s) {
		super(s+"Crate");
		this.setMyIngredient(s);
	}
	
	public void interact(Player p) {
		if (!p.isHandEmpty() || !isPlacedContentEmpty()) {
			super.interact(p);
		}
		else {
			try {				
				p.setHoldingItem(logic.LogicUtil.createIngredientFromName(this.getMyIngredient()));
			}
			catch (InvalidIngredientException e) {
				p.setHoldingItem(null);
			}
		}
	}
	// Getter & Setter
	public String getMyIngredient() {
		return myIngredient;
	}
	public void setMyIngredient(String myIngredient) {
		this.myIngredient = myIngredient;
	}
}
