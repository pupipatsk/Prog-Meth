package fighters.derived;

import fighters.base.Attackable;
import fighters.base.Guardable;
import fighters.base.Unit;
import logic.GameManager;

public class Guildmaster extends Unit implements Attackable, Guardable{

	public Guildmaster(int maxHealth, int speed, int power, int defense, int location) {
		super("Guildmaster", "G", maxHealth, speed, location, false);
		setPower(power);
		setDefense(defense);
		setRange(1);
	}
	@Override
	public boolean move(int spaces) {
		spaces = -1;
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
	public void guard() {}
	public int attack(Unit e) {
		if (this.sameTeam(e) || !logic.BattleUtils.validRange(this.getRange(), this.getLocation(), e.getLocation() )) {
			return -1;
		}
		else {
			return logic.BattleUtils.calculateDamage(this.getPower(), e);
		}
	}
}
