package fighters.derived;

import fighters.base.Attackable;
import fighters.base.Guardable;
import fighters.base.Unit;

public class Guildmaster extends Unit implements Attackable, Guardable{

	public Guildmaster(int maxHealth, int speed, int power, int defense, int location) {
		super("Guildmaster", "G", maxHealth, speed, location, false);
		setPower(power);
		setRange(1);
	}
	
	public boolean move(int spaces) {
		return move(-1);
	}
	public void guard() {}
	public int attack(Unit e) {
		if (this.sameTeam(e) || logic.BattleUtils.validRange(this.getRange(), this.getLocation(), e.getLocation() )) {
			return -1;
		}
		else {
			return logic.BattleUtils.calculateDamage(this.getPower(), this);
		}
	}
}
