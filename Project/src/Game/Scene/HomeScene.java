package Game.Scene;

import Game.logic.GameUtil;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeScene {
	
	public static Scene homeScene;
	public static Scene mapScene;
	
	public static void sceneSetup(){
		
		VBox actionList = new VBox();
		actionListSetup(actionList);
		
		Text headName = new Text("Rock Smasher");
		setHeadNameEffect(headName);
		
		StackPane headBox = new StackPane(headName) ;
		headBox.setPrefHeight(200);
		
		VBox layout = new VBox(headBox,actionList);
		
		layout.setAlignment(Pos.CENTER);
		layout.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("picture/background/home_bg.jpg").toString()), null, null, null, null)));
		homeScene = new Scene(layout, 1000, 700);
	}
	
	private static void actionListSetup(VBox actionList) {
		actionList.setPadding(new Insets(90,40,40,90));
		actionList.setSpacing(20);
		
		Text playText = createTextButton("Play",40);
		playText.setOnMouseClicked(arg0 -> {mapUpdate();});
		
		Text shopText = createTextButton("Shop",25);
		shopText.setOnMouseClicked(arg0 -> {ShopScene.sceneUpdate();});
		
		Text enchantText = createTextButton("Enchant",25);
		enchantText.setOnMouseClicked(arg0 -> {EnchantScene.target = GameUtil.user.getWeapon();EnchantScene.sceneUpdate();});
		
		Text bagText = createTextButton("Bag",30);
		bagText.setOnMouseClicked(arg0 -> {BagScene.sceneUpdate();});
		
		Text exitText = createTextButton("Exit",20);
		exitText.setOnMouseClicked(arg0 -> {GameUtil.game.close();});
		
		actionList.getChildren().addAll(playText,bagText,enchantText,shopText,exitText);
		
		actionList.setPrefHeight(500);
	}
	
	private static void setHeadNameEffect(Text headName) {
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		headName.setEffect(ds);
		headName.setFill(Color.PERU);
		headName.setFont(Font.font("Daydream", FontWeight.BOLD, 65));

		headName.setTranslateX(0);
		headName.setTranslateY(50);
				
		 ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(1.0), headName);
	        scaleUp.setFromX(1.0);
	        scaleUp.setFromY(1.0);
	        scaleUp.setToX(1.2);
	        scaleUp.setToY(1.2);

	        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(1.0), headName);
	        scaleDown.setFromX(1.2);
	        scaleDown.setFromY(1.2);
	        scaleDown.setToX(1.0);
	        scaleDown.setToY(1.0);
	        
	        scaleUp.setOnFinished(event -> scaleDown.play());
	        scaleDown.setOnFinished(event -> scaleUp.play());
	        
	        scaleUp.play();
	}
	
	private static Text createTextButton(String text,int fontSize) {
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		Text target = new Text(text);
		target.setEffect(ds);
		target.setFill(Color.color((float)75/255,(float)182/255,(float)175/255));
		target.setFont(Font.font("Daydream", FontWeight.BOLD, fontSize));
		target.setOnMouseEntered(event -> target.setStyle("-fx-font-size: "+(fontSize+2)+"; -fx-fill: #387994;"));
		target.setOnMouseExited(event -> target.setStyle("-fx-font-size: " +fontSize +"; -fx-fill:  rgb(75,182,175);"));

		return target;
	}

	
	public static void mapSceneSetup() {
		FlowPane layout = new FlowPane();
		layout.setPadding(new Insets(20,10,20,10));
		layout.setVgap(30);
		layout.setHgap(1);
		
		layout.getChildren().add(mapHeadTextSetup());
		mapStageSetup(layout);
		
		Text back = GameUtil.createNewTextButton(30, "Back");
		back.setOnMouseClicked(event -> {HomeScene.homeUpdate();});
		
		StackPane actionBox = new StackPane(back);
		actionBox.setPrefWidth(1000);
		
		layout.getChildren().add(actionBox);
		layout.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("picture/background/map_bg.png").toString()), null, null, null, null)));

		mapScene = new Scene(layout, 1000, 700);
	}
	
	private static StackPane mapHeadTextSetup() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color((float)56/255, (float)43/255, (float)70/255));
		
		Text headText = new Text("Stage");
		headText.setEffect(ds);
		headText.setStyle("-fx-fill:  #783f53;");
		headText.setFont(Font.font("Daydream", FontWeight.BOLD, 65));
		
		StackPane headBox = new StackPane(headText);
		headBox.setPrefWidth(1000);
		
		return headBox;
	}
	
	private static void mapStageSetup(FlowPane layout) {
		
		for(int i = 1 ; i <= 100; i++) {
			Text stageLevelLabel = GameUtil.createNewText(15, ""+ i);
			stageLevelLabel.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 18));
			stageLevelLabel.setTranslateY(-3);
			stageLevelLabel.setFill(Color.WHITE);
			
			StackPane stageBox = new StackPane();
			
			Stages currentStage = GameUtil.stages.get(i-1);
			ImageView stageLevelBG ;
			if(currentStage.isClear()) {
				stageLevelLabel.setFill(Color.ROYALBLUE);
				if(i%10 == 0) stageLevelBG = new ImageView(new Image(ClassLoader.getSystemResource("picture/stage/boss_clear.png").toString()));
				else stageLevelBG = new ImageView(new Image(ClassLoader.getSystemResource("picture/stage/stage_clear.png").toString()));
			}
			else {
				if(i%10 == 0) stageLevelBG = new ImageView(new Image(ClassLoader.getSystemResource("picture/stage/stage_boss.png").toString()));
				else stageLevelBG = new ImageView(new Image(ClassLoader.getSystemResource("picture/stage/stage_not.png").toString()));
			}
			
			stageLevelBG.setFitHeight(48);
			stageLevelBG.setFitWidth(48);
			stageBox.getChildren().addAll(stageLevelBG,stageLevelLabel);

			stageBox.setOnMouseClicked(event ->{currentStage.GameShow();});
			layout.getChildren().addAll(stageBox);
		}
	}
	
	
	
	public static void mapUpdate() {
		GameUtil.game.setScene(mapScene);
	}

	public static void homeUpdate() {
		GameUtil.game.setScene(homeScene);
	}
}
