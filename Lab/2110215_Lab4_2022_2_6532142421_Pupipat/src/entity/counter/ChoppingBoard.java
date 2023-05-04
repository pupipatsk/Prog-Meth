package entity.counter;

import entity.base.Choppable;
import entity.base.Ingredient;
import logic.Player;

public class ChoppingBoard extends Counter{

	public ChoppingBoard() {
		super("Chopping Board");
	}
	
	public void interact(Player p) {
		if (!this.isPlacedContentEmpty()) {
			super.interact(p);
		}
		else {
			if (!p.isHandEmpty() && p.getHoldingItem() instanceof Ingredient) {
				if (p.getHoldingItem() instanceof Choppable) {
					((Choppable) p.getHoldingItem()) .chop();
				}
				super.interact(p);
			}
		}
	}
}
