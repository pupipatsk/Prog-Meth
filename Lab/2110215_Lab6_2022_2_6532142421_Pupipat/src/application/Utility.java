package application;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Utility {
	
	public static GameCoreWrapper setUpGameCore(GameCoreWrapper gameCoreWrapper, BeatMap beatMap, NoteTiles noteTiles) {
		if (gameCoreWrapper != null) {
			gameCoreWrapper.interrupt();
		}
		
		GameCoreWrapper tempGameCoreWrapper = new GameCoreWrapper(beatMap,noteTiles);
		tempGameCoreWrapper.start();
		
		return tempGameCoreWrapper;
	}

	public static ScoreProcessorWrapper setUpScoreProcessor(ScoreProcessorWrapper scoreProcessorWrapper,
			BeatMap beatMap, KeyPressTiles keyPressTiles, Label hitScoreLabel, Label missScoreLabel) {
		if (scoreProcessorWrapper != null) {
			scoreProcessorWrapper.interrupt();
		}
		
		ScoreProcessorWrapper tempScoreProcessorWrapper = new ScoreProcessorWrapper(beatMap, keyPressTiles, hitScoreLabel, missScoreLabel);
		tempScoreProcessorWrapper.start();
		
		return tempScoreProcessorWrapper;
	}

	public static void updateLabel(Label label, String newText) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				label.setText(newText);
			}
		});
	}

	public static void cleanUp(Menu menu) {
		menu.getGameCoreWrapper().interrupt();
		menu.getScoreProcessorWrapper().interrupt();
	}
}