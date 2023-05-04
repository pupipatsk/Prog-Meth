package logic;

import exception.NameBlankException;

public class Item {

	private String itemName;
	private int price;

	// constructor
	public Item(String itemName) throws NameBlankException {
		//FILL CODE
		//this.itemName = itemName;
		setItemName(itemName);
		setPrice(0);
	}

	public Item(String itemName, int price) throws NameBlankException{
		//FILL CODE
		//this.itemName = itemName;
		setItemName(itemName);
		setPrice(price);
	}

	// methods
	public boolean equals(Item other) {
		//FILL CODE
		if (this.getItemName() == other.getItemName()) {
			return true;			
		}
		else {
			return false;
		}
	}

	public String toString() {
		//FILL CODE
		return itemName + " $" + price;
	}

	// getter & setter
	public String getItemName() {
		//FILL CODE
		return itemName;
	}

	public void setItemName(String itemName) throws NameBlankException {
		//FILL CODE
		if (itemName.isBlank()) {
			System.out.println("Item name cannot be blank! This item will not be added.");
			throw new NameBlankException();
		}
		this.itemName = itemName; 
	}

	public int getPrice() {
		//FILL CODE
		return price;
	}

	public void setPrice(int price) {
		//FILL CODE
		if (price <= 0) {
			this.price = 0;
			return;
		}
		this.price = price;
	}

}
