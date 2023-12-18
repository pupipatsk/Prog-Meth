package Entities.Weapon;

import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class BronzePickaxe extends Pickaxe {

	public BronzePickaxe(int plus) {
		super((int) Math.pow(10, 3), plus,"Bronze Pickaxe",3) ;
		this.setMineral(Mineral.Silver);
	}
}
