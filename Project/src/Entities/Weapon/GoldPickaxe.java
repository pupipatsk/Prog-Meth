package Entities.Weapon;

import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class GoldPickaxe extends Pickaxe {
	public GoldPickaxe(int plus) {
		super((int) Math.pow(10, 5), plus,"Gold Pickaxe",5) ;
		this.setMineral(Mineral.Diamond);
	}
}
