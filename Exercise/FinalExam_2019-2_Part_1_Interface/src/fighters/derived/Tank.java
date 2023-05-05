package fighters.derived;

import fighters.base.Guardable;
import fighters.base.Unit;

public class Tank extends Unit implements Guardable{

	public Tank(int maxHealth, int speed, int defense, int location) {
		super("Tank", "t", maxHealth, speed, location, true);
	}
	
	public void guard() {
		setOnGuard(true);
	}
	public boolean move(int spaces) {
		setOnGuard(false);
		return move(spaces);
	}
}
