package Entities;

import Entities.Weapon.WoodPickaxe;
import Game.logic.Pickaxe;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
	private Pickaxe weapon;
	private Bag bag = new Bag();
	private int coin = 100;
	private IntegerProperty coinWithProperty = new SimpleIntegerProperty(100);

	public Player() {
		super();
		this.weapon = new WoodPickaxe(0);
		this.bag.addItem(this.weapon);
	}

	public Pickaxe getWeapon() {
		return weapon;
	}

	public void setWeapon(Pickaxe weapon) {
		this.weapon = weapon;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
		coinWithProperty.set(coin);

	}

	public IntegerProperty getCoinWithProperty() {
		return coinWithProperty;
	}

	public void setCoinWithProperty(IntegerProperty coinWithProperty) {
		this.coinWithProperty = coinWithProperty;
	}

}
