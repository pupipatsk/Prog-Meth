package logic;

//import exception.NameBlankException;

public class ItemCounter {
	//field
	private Item item;
	private int amount;
	
	// constructor
	public ItemCounter(Item item) {
		setItem(item);
		setCount(1);
	}
	public ItemCounter(Item item,int amount) {
		setItem(item);
		if (amount <= 0) {
			setCount(1);
		}
		else {
			setCount(amount);
		}
		//setCount(amount);
	}
	
	//getter & setter
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getCount() {
		return amount;
	}
	public void setCount(int amount) {
		if (amount <= 0) {
			this.amount = 0;
			return;
		}
		this.amount = amount;
	}
	
	// methods
	public String toString() {
		return this.getItem() + " x" + this.getCount();
	}
}