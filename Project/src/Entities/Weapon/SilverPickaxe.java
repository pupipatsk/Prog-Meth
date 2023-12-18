package Entities.Weapon;

import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class SilverPickaxe extends Pickaxe {

	public SilverPickaxe(int plus) {
		super((int) Math.pow(10, 4), plus,"Silver Pickaxe",4) ;
		this.setMineral(Mineral.Gold);
	}
}
