package entity.counter;

import entity.base.Updatable;
import entity.container.Dish;
import logic.Player;

public class DishWasher extends Counter implements Updatable{

	public DishWasher() {
		super("Dish Washer");
	}
	
	public void interact(Player p) {
		if (!this.isPlacedContentEmpty()) {
			super.interact(p);
		}
		else {
			if (!p.isHandEmpty() && p.getHoldingItem().getClass() == Dish.class && ((Dish) p.getHoldingItem()) .isDirty() ) {
				super.interact(p);
			}				
		}
	}
	
	public void update() {
		if (this.getPlacedContent().getClass() == Dish.class) {
			((Dish) this.getPlacedContent()) .clean(15);;
		}
	}
}