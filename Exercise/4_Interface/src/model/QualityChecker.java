package model;

public class QualityChecker implements Worker{
	
	private int stressLevel=1;
	
	private void sleep() {
		System.out.println("Zzzzz");
		
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stressLevel=1;
	}
	public void work() {
		if (stressLevel>=5) {
			sleep();
		}
		System.out.println("Checking a product");
	}
	public void increaseStress() {
		stressLevel += 1;
	}
	// Getter & Setter
	public int getStressLevel() {
		return stressLevel;
	}
}
