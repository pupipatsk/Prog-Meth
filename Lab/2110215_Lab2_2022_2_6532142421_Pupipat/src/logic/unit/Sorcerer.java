package logic.unit;

public class Sorcerer extends BaseCompetitor{
	//Method
	public Sorcerer(String name) {
		super(name, 4, 2);
	}
	public Sorcerer(String name, int hp, int power) {
		super(name, hp, power);
	}
	public void lowerPower(BaseCompetitor enemy, int powerDown) {
		if (powerDown >= 0) {
			enemy.setPower( Math.max( 1, enemy.getPower()-powerDown ) );
		}
	}
	
	public void attack(BaseCompetitor enemy) {
		if ( enemy.getType().equals("Tiger")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()/2));
		}
		else if (enemy.getType().equals("Sorcerer") || enemy.getType().equals("BaseCompetitor")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()));
		}
		else {
			enemy.setHp((int)( enemy.getHp() - this.getPower()*3/2));
		}
	}
}