package Entities.Weapon;
import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class WoodPickaxe extends Pickaxe {

	public WoodPickaxe(int plus) {
		super((int) Math.pow(10, 1), plus,"Wood Pickaxe",1);
		this.setMineral(Mineral.Iron);
		
	}
	
}
