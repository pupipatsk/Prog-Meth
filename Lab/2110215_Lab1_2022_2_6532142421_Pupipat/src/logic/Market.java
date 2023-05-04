package logic;

import java.util.ArrayList;

public class Market {

	// fields
	ArrayList<Item> allItems;

	// constructors
	public Market() {
		//FILL CODE
		setAllItems(new ArrayList<Item>());
	}

	public Market(ArrayList<Item> items) {
		//FILL CODE
		setAllItems(new ArrayList<Item>());
		addAllItems(items);
	}

	// methods
	public String toString() {
		String out = "\n";
		for (int i = 0; i < allItems.size(); i++) {
			out += i + 1;
			out += ". ";
			out += allItems.get(i).toString();
			out += "\n";
		}
		return out;
	}

	public void addAllItems(ArrayList<Item> items) {
		//FILL CODE
		//System.out.println(items);
		//System.out.println(allItems);
		//System.out.println(items.get(0).getItemName().equals(allItems.get(0).getItemName()));
		if (items == null) return;
		for (int i = 0; i < items.size(); i++) {
			boolean ch = true;
			
			for (int j = 0; j < allItems.size(); j++) {
				if (items.get(i).getItemName().equals(allItems.get(j).getItemName())) {
					ch = false;
					System.out.println( "\"" + items.get(i).getItemName() + "\"" + " is duplicated, Item will not be added" );
				}
			}
			
			if (ch) {				
				allItems.add(items.get(i));
				System.out.println( "\"" + items.get(i).toString() + "\"" + " Added to the market." );
			}
		}
	}

	//getter setters 
	//FILL CODE
	public ArrayList<Item> getAllItems() {
		return allItems;
	}
	public void setAllItems(ArrayList<Item> allItems) {
		this.allItems = allItems;
	}

}
