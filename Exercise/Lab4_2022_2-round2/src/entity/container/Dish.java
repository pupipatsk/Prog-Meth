package entity.container;

import entity.base.Container;
import entity.base.Ingredient;

public class Dish extends Container{

	private int dirty;
	
	public Dish() {
		super("Dish", 4);
		setDirty(0);
	}
	public Dish(int dirty) {
		super("Dish", 4);
		setDirty(dirty);
	}
	
	public boolean isDirty() {
		if (this.getDirty()>0) {
			return true;
		}
		return false;
	}
	public boolean verifyContent(Ingredient i) {
		if (!isDirty() && i.isEdible()) {
			return true;
		}
		return false;
	}
	public void clean(int amount) {
		setDirty(this.getDirty() - amount);
	}
	public String toString() {
		if (isDirty()) {
			return logic.StringUtil.formatNamePercentage(this.getName(), this.getDirty());			
		}
		else {
			return super.toString();
		}
	}
	// Getter & Setter
	public int getDirty() {
		return this.dirty;
	}
	public void setDirty(int dirty) {
		if (dirty<0) {
			dirty = 0;
		}
		this.dirty = dirty;
		
		if (dirty>0) {
			setName("Dirty Dish");
		}
		else {
			setName("Dish");
		}
	}
}
