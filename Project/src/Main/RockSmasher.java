package Main;

import Game.Scene.BagScene;
import Game.Scene.EnchantScene;
import Game.Scene.HomeScene;
import Game.Scene.ShopScene;
import Game.Scene.Stages;
import Game.logic.GameUtil;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RockSmasher extends Application {

	@Override
	public void start(Stage primaryStage) {

		// font load
		Font.loadFont(ClassLoader.getSystemResource("fonts/ColonnaMT.ttf").toString(), 14);
		Font.loadFont(ClassLoader.getSystemResource("fonts/PixelOperator.ttf").toString(), 14);
		Font.loadFont(ClassLoader.getSystemResource("fonts/PixelOperator-Bold.ttf").toString(), 14);
		Font.loadFont(ClassLoader.getSystemResource("fonts/Daydream.ttf").toString(), 50);
		for (int i = 1; i <= 100; i++) {
			GameUtil.stages.add(new Stages(i));
		}

		// game setup
		GameUtil.game = primaryStage;
		BagScene.sceneSetup();
		EnchantScene.sceneSetup();
		ShopScene.sceneSetup();
		HomeScene.sceneSetup();
		HomeScene.mapSceneSetup();
		HomeScene.homeUpdate();

		// start scene
		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("picture/icon.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.setTitle("RockSmasher");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
