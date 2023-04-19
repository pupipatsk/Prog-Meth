package model;

public class MaterialProcessor extends Bot {

	@Override
	public void work() {
		// TODO Auto-generated method stub
		material-=2;
		System.out.println("Retrieved 2 mats from the warehouse. Remaining: "+material);
	}

}
