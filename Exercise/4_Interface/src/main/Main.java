package main;
import java.util.ArrayList;

import model.Bot;
import model.MaterialProcessor;
import model.Packager;
import model.ProductAssembler;
import model.QualityChecker;
import model.Worker;

public class Main {
	
	public static ArrayList<Worker> workers;
	
	
	public static void produce(){
		for (Worker e : workers) {
			e.work();
		}
	}
	
	public static void trigger(Worker w){
		if (w instanceof QualityChecker) {
			((QualityChecker) w).increaseStress();
		}
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
