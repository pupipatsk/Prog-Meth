package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exception.NameBlankException;

public class ItemUtil {

	public static Scanner sc = new Scanner(System.in);

	public static boolean itemNameIsExist(Market market, String itemName) {
		//FILL CODE
		for (Item e : market.getAllItems()) {
			if (e.getItemName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasEnoughMoneytoBuy(Inventory toBuy, Item item, int amount) {
		//FILL CODE
		if (toBuy.getMoney() >= item.getPrice() * amount) {
			return true;
		}
		return false;
	}

	public static ArrayList<Item> getItemFromFile(String filename) {
		
		File fileToRead = new File(filename);
		ArrayList<Item> itemsFromFile = new ArrayList<Item>();

		//FILL CODE
		try {
			Scanner sc = new Scanner(fileToRead);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] arrayNamePrice = line.split(" ");
				Item newItem = new Item(arrayNamePrice[0], Integer.parseInt(arrayNamePrice[1]));
				itemsFromFile.add(newItem);
			}
			sc.close();
			return itemsFromFile;
		}
		catch (Exception e) {
			if (itemsFromFile.size()<=0) {
				System.out.println("Cannot find file!");
				return null;
			}
			return itemsFromFile;
		}
	}

	public static void playerSellplayer(Inventory toSell, Inventory toBuy, Item item, int amount) {
		if (!hasEnoughMoneytoBuy(toBuy, item, amount)) {
			System.out.println(toBuy.getPlayerName() + " dont't have enough money to buy this item.");
		} else {
			System.out.println("=========SELL CONFIRMATION=========");
			System.out.println("    Selling " + item.getItemName() + " x" + amount
					+ " to " + toBuy.getPlayerName());
			System.out.println("            for $" + item.getPrice() * amount
					+ "             ");
			System.out.println(" >> Type \"1\" to confirm selling  ");
			System.out.println(" >> Type anything else to cancel ");
			System.out.println("===================================");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				playerSellPlayerConfirmed(toSell, toBuy, item, amount);
				System.out.println("<<TRANSACTION COMPLETE>>");
				break;
			default:
				System.out.println("<<SELL CANCEL>>");
				break;
			}
		}
	}
	
	public static void playerSellPlayerConfirmed(Inventory toSell, Inventory toBuy, Item item, int amount) {
		//FILL CODE
		int transactionAmount = item.getPrice() * amount;
		//sell
		toSell.removeItem(item, amount);
		toSell.setMoney(toSell.getMoney() + transactionAmount);
		//buy
		toBuy.addItem(item, amount);
		toBuy.setMoney(toBuy.getMoney() - transactionAmount);
	}

	public static void playerSellMarket(Inventory toSell, Item item, int amount) {
		System.out.println("=========SELL CONFIRMATION=========");
		System.out.println("    Selling " + item.getItemName() + " x" + amount
				+ " to market      ");
		System.out.println(
				"            for $" + item.getPrice() * amount + "             ");
		System.out.println(" >> Type \"1\" to confirm selling  ");
		System.out.println(" >> Type anything else to cancel ");
		System.out.println("===================================");
		String input = sc.nextLine();
		switch (input) {
		case "1":
			playerSellMarketConfirmed(toSell, item, amount);
			System.out.println("<<TRANSACTION COMPLETE>>");
			break;
		default:
			System.out.println("<<SELL CANCEL>>");
			break;
		}
	}
	
	public static void playerSellMarketConfirmed(Inventory toSell, Item item, int amount) {
		//FILL CODE
		int transactionAmount = item.getPrice() * amount;
		//sell
		toSell.removeItem(item, amount);
		toSell.setMoney(toSell.getMoney() + transactionAmount);
	}
	
	public static void playerBuyMarket(Inventory toBuy, Item item, int amount) {
		if (!ItemUtil.hasEnoughMoneytoBuy(toBuy, item, amount)) {
			System.out.println(toBuy.getPlayerName() + " Dont't have enough money\nto buy this item.");
		} else {
			System.out.println("=========BUY CONFIRMATION=========");
			System.out.println("	" + toBuy.getPlayerName() + " is buying ");
			System.out.println("	" + item.getItemName() + " x" + amount + " for $"
					+ item.getPrice() * amount);
			System.out.println(" >> Type \"1\" to confirm buying  ");
			System.out.println(" >> Type anything else to cancel");
			System.out.println("==================================");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				playerBuyMarketConfirmed(toBuy, item, amount);
				System.out.println("TRANSACTION COMPLETE!");
				break;
			default:
				System.out.println("BUY CANCEL!");
				break;
			}
		}
	}
	
	public static void playerBuyMarketConfirmed(Inventory toBuy, Item item, int amount) {
		//FILL CODE
		int transactionAmount = item.getPrice() * amount;
		toBuy.addItem(item, amount);
		toBuy.setMoney(toBuy.getMoney() - transactionAmount);
	}

}
