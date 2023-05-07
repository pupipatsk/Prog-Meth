package application;

import javafx.scene.control.Label;

public class Utility {
	public static GameCoreWrapper setUpGameCore(GameCoreWrapper gameCoreWrapper, BeatMap beatMap, NoteTiles noteTiles) {
		new GameCore(beatMap, noteTiles).executeGameCore();
		return null;
	}

	public static ScoreProcessorWrapper setUpScoreProcessor(ScoreProcessorWrapper scoreProcessorWrapper,
			BeatMap beatMap, KeyPressTiles keyPressTiles, Label hitScoreLabel, Label missScoreLabel) {
		new ScoreProcessor(beatMap, keyPressTiles, hitScoreLabel, missScoreLabel).executeScoreProcessor();
		return null;
	}

	public static void updateLabel(Label label, String newText) {
	}

	public static void cleanUp(Menu menu) {
	}
}
