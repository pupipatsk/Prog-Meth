package Entities.Weapon;
import Game.logic.Mineral;
import Game.logic.Pickaxe;

public class IronPickaxe extends Pickaxe {

	public IronPickaxe(int plus) {
		super((int) Math.pow(10, 2), plus,"Iron Pickaxe",2) ;
		this.setMineral(Mineral.Bronze);
	}
	
}
