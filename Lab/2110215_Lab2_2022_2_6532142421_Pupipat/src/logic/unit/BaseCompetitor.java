package logic.unit;

public class BaseCompetitor {
	private String name;
	private int hp;
	private int power;
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = Math.max(0, hp);
	}
	
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		if (power < 1) {
			this.power = 1;
		}
		else {
			this.power = power;			
		}
	}
	
	
	//Method
	public BaseCompetitor(String name) {
		setName(name);
		setHp(5);
		setPower(3);
	}
	public BaseCompetitor(String name, int hp, int power) {
		setName(name);
		setHp(hp);
		setPower(power);
	}
	public void attack(BaseCompetitor enemy) {
		enemy.setHp(enemy.getHp() - this.getPower());
	}
	public String getType() {
		if ( this.getClass().equals(Sorcerer.class) ) return "Sorcerer";
		else if ( this.getClass().equals(Tiger.class) ) return "Tiger";
		else if ( this.getClass().equals(ToughMan.class) ) return "ToughMan";
		return "BaseCompetitor";
	}
}
