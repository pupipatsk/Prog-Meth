package model;

public abstract class Bot implements Worker{

	public static int material=20;
	public static int finishedProduct=0;
	
	public abstract void work();
}
