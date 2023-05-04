package logic.unit;

public class Tiger extends BaseCompetitor {
	//Method
	public Tiger(String name) {
		super(name, 7, 5);
	}
	public Tiger(String name, int hp, int power) {
		super(name, hp, power);
	}
	
	public void train(int addPower) {
		if (addPower >= 0) {
			this.setPower( Math.max( 1, this.getPower()+addPower ) );
		}
	}
	public void attack(BaseCompetitor enemy) {
		if ( enemy.getType().equals("ToughMan")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()/2));
		}
		else if (enemy.getType().equals("Tiger") || enemy.getType().equals("BaseCompetitor")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()));
		}
		else {
			enemy.setHp((int)( enemy.getHp() - this.getPower()*3/2));
		}
	}
}