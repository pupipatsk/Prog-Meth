package Entities.Weapon;

import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class DiamondPickaxe extends Pickaxe {

		public DiamondPickaxe(int plus) {
			super((int) Math.pow(10, 9), plus,"Diamond Pickaxe",6) ;
			this.setMineral(Mineral.Bronze);
		}
}
