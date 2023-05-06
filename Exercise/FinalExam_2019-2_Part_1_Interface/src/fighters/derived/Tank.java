package fighters.derived;

import fighters.base.Guardable;
import fighters.base.Unit;
import logic.GameManager;

public class Tank extends Unit implements Guardable{

	public Tank(int maxHealth, int speed, int defense, int location) {
		super("Tank", "t", maxHealth, speed, location, true);
		setDefense(defense);
	}
	
	public void guard() {
		setOnGuard(true);
	}
	@Override
	public boolean move(int spaces) {
		setOnGuard(false);
		if(Math.abs(spaces) > speed) return false;
		if(spaces == 0) return false;
		if(this.getLocation() + spaces < 0) return false;
		if(this.getLocation() + spaces >= GameManager.BOARD_SIZE) return false;
		if(!GameManager.gb.getCell(this.getLocation() + spaces).isEmpty()) return false;
		for(int i = 1; i < spaces; i ++) {
			if(!GameManager.gb.getCell(this.getLocation() + i).isEmpty()) {
				Unit otherUnit = GameManager.gb.getCell(this.getLocation() + i).getUnit();
				if(!this.sameTeam(otherUnit)) {
					return false;
				}
			}
		}
		
		this.setLocation(this.getLocation() + spaces);
		return true;
	}
}
