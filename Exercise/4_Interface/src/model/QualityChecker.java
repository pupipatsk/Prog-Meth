package model;
public class QualityChecker {
	

	private void sleep(){
		System.out.println("Zzzzz");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stressLevel=1;
	}
	

}
