package logic;

import java.util.ArrayList;

public class Inventory {
	
	//fields
	private String playerName;
	private int money;
	private ArrayList<ItemCounter> items;
	
	//constructors
	public Inventory(String playerName) {
		//FILL CODE
		try {
			setPlayerName(playerName);
			setMoney(0);
			setItems(new ArrayList<ItemCounter>());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public Inventory(String playerName, int money) {
		//FILL CODE
		try {
			setPlayerName(playerName);
			setMoney(money);
			setItems(new ArrayList<ItemCounter>());
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Inventory(String playerName, int money, ArrayList<ItemCounter> items) {
		//FILL CODE
		try {
			setPlayerName(playerName);
			setMoney(money);
			setItems(items);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//methods
	public String toString() {
		if (items.size() == 0) {
			return "EMPTY INVENTORY";
		}
		String out = "\n";
		for (int i=0; i<items.size(); i++) {
			out += i+1;
			out += ". ";
			out += items.get(i).toString();
			out += "\n";
		}
		return out;
	}
	public boolean existsInInventory(Item item) {
		//FILL CODE
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItem().equals(item) && items.get(i).getCount() > 0) {
				return true;
			}
		}
		return false;
	}

	public void addItem(Item newItem, int count){
		//FILL CODE
		if (count <= 0) return;
		if (existsInInventory(newItem)) {
			for (int i = 0; i < this.items.size(); i++) {
				if (items.get(i).getItem().equals(newItem)) {
					items.get(i).setCount( items.get(i).getCount() + count );
				}
			}			
		} 
		else {
			items.add(new ItemCounter(newItem, count));
		}
		
	}

	public void removeItem(Item toRemove, int count) {

		// if the amount is zero or negative, just return. nothing is removed.
		if (count <= 0)
			return;

		ItemCounter removeIfNeg = null;

		for (ItemCounter ic : items) {
			if (ic.getItem().equals(toRemove)) {
				// Remove the card equal to count.
				ic.setCount(ic.getCount() - count);
				removeIfNeg = ic;
			}
		}

		// If removeIfNeg isn't null (meaning something got removed) then we need to
		// check if it is negative.
		if (removeIfNeg != null) {
			// If it goes into the negative, then remove this entry from the deck entirely.
			// You cannot modify a for loop while it's inside, so this has to be done
			// outside.
			if (removeIfNeg.getCount() <= 0) {
				items.remove(removeIfNeg);
			}
		}

	}
	
	
	//getters setters
	//FILL CODE
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		if (playerName.isBlank()) {
			this.playerName = "Untitled Player";
			return;
		}
		this.playerName = playerName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money <= 0) {
			this.money = 0;
			return;
		}
		this.money = money;
	}

	public ArrayList<ItemCounter> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemCounter> items) {
		this.items = items;
	}
	
}
