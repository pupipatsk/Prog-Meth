package main;
import java.util.ArrayList;

import model.*;

public class Main {
	
	/*
	 * Code here
	 * */
	workers;
	
	public static void trigger(Worker w){
		/*
		 * Code here
		 * */
	}
	
	public static void produce(){
		/*
		 * Code here
		 * */
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bot A = new MaterialProcessor();
		Bot B = new ProductAssembler();
		Bot C = new Packager();
		QualityChecker D= new QualityChecker();
		QualityChecker E= new QualityChecker();
		
		workers.add(A);workers.add(B);workers.add(C);workers.add(D);workers.add(E);
		
		while(Bot.material>0){
			produce();
		}
		System.out.println("Total Product today =" + Bot.finishedProduct);
		
	}

}
