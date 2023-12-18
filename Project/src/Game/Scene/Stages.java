package Game.Scene;
public class Stages {
	private int currentLevel;
	private boolean isClear = false;
	
	public Stages(int currentLevel) {
		super();
		this.currentLevel = currentLevel;
	}


	public void GameShow() {
		new StageMap(currentLevel, this).startStage();
	}
	
	
	public boolean isClear() {
		return isClear;
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}


	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
}
