package Entities;

import java.util.ArrayList;
import java.util.Comparator;

import Game.logic.BagItem;
import Game.logic.Stackable;

public class Bag {
	private ArrayList<BagItem> allItem = new ArrayList<>();

	public ArrayList<BagItem> getAllItem() {

		return allItem;
	}

	public void setAllItem(ArrayList<BagItem> allItem) {
		this.allItem = allItem;
	}

	public void addAllItem(ArrayList<BagItem> Item) {
		for (BagItem i : Item) {
			this.addItem(i);
		}
	}

	public Item getitem(String name) {
		for (BagItem i : allItem) {
			if (i.getName().equals(name))
				return (Item) i;
		}
		return null;
	}

	public void addItem(BagItem i) {
		if (!(i instanceof Stackable) || !this.allItem.contains(i)) {
			this.allItem.add(i);
		} else {
			for (BagItem j : this.allItem) {
				if (j instanceof Stackable)
					((Item) j).merge((Item) i);
			}
		}
		this.allItem.sort(Comparator.comparing(BagItem::getId));

	}

	public void setAmountof(BagItem bi, int amount) {
		if (amount == 0)
			this.allItem.remove(bi);
		else
			((Item) bi).setAmount(amount);
	}

	public boolean hasItem(BagItem item) {
		for (BagItem i : allItem) {
			if (i.getName().equals(item.getName()))
				return true;
		}
		return false;
	}
}
