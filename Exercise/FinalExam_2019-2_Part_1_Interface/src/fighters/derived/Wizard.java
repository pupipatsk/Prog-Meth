package fighters.derived;

import fighters.base.Attackable;
import fighters.base.Unit;


public class Wizard extends Unit implements Attackable{

	public Wizard(int maxHealth, int speed, int power, int location) {
		super("Wizard" , "w", maxHealth, speed, location, true);
		setPower(power);
		setRange(2);
	}
	
	public int attack(Unit e) {
		if (this.sameTeam(e) || logic.BattleUtils.validRange(this.getRange(), this.getLocation(), e.getLocation() )) {
			return -1;
		}
		else {
			return logic.BattleUtils.calculateDamage(this.getPower(), this);
		}
	}
}
