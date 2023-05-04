package logic.unit;

public class ToughMan extends BaseCompetitor{
	int maxHp;
	
	//Method
	public ToughMan(String name) {
		super(name, 8, 4);
		this.setMaxHp(8);
	}
	public ToughMan(String name, int hp, int power) {
		super(name, hp, power);
		this.setMaxHp(hp);
	}
	public void heal(int healHp) {
		if (healHp >=0 ) {
			this.setHp( Math.min(maxHp, this.getHp()+healHp) );			
		}
	}
	public void attack(BaseCompetitor enemy) {
		if ( enemy.getType().equals("Sorcerer")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()/2));
		}
		else if (enemy.getType().equals("ToughMan") || enemy.getType().equals("BaseCompetitor")) {
			enemy.setHp((int)( enemy.getHp() - this.getPower()));
		}
		else {
			//System.out.println("dd");
			//System.out.println(enemy.getHp());
			//System.out.println(this.getPower());
			//System.out.println(enemy.getHp() - this.getPower()*3/2);
			enemy.setHp((int)( enemy.getHp() - this.getPower()*3/2));
		}
	}
	//getter setter
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp){
		if (maxHp < 0) {
			this.maxHp = 0;
			this.setHp(0);
		}
		else if (maxHp < this.getHp()) {
			this.maxHp = maxHp;
			this.setHp(this.maxHp);
		}
		else {
			this.maxHp = maxHp;			
		}
	}
}