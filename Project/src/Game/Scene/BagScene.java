package Game.Scene;

import java.util.ArrayList;

import Game.logic.BagItem;
import Game.logic.GameUtil;
import Game.logic.Pickaxe;
import Game.logic.Stackable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BagScene {

	public static ArrayList<StackPane> itemSlotBox = new ArrayList<>();
	public static Scene bagScene;
	public static VBox playerStatusBox;

	public static void sceneSetup() {
		HBox layout = new HBox();

		playerStatusBoxSetup();
		VBox playerBox = createPlayerBox();

		GridPane bagSlot = new GridPane();
		bagSlotSetup(bagSlot);

		layout.setBackground(new Background(
				new BackgroundFill(Color.color((float) 152 / 255, (float) 61 / 255, (float) 6 / 255), null, null)));
		layout.getChildren().addAll(playerBox, bagSlot);

		bagScene = new Scene(layout, 1000, 700);
	}

	private static VBox createPlayerBox() {
		Text headText = new Text("Inventory");
		headText.setFont(Font.font("Colonna MT", FontWeight.BOLD, 70));
		StackPane headTextBox = new StackPane(headText);
		headTextBox.setPrefHeight(100);

		Text subHeadText = GameUtil.createNewText(40, "PlayerStats");
		subHeadText.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 40));

		VBox playerBox = new VBox(headTextBox, subHeadText, playerStatusBox);
		playerBox.setPrefWidth(400);
		playerBox.setSpacing(20);
		playerBox.setAlignment(Pos.TOP_CENTER);
		playerBox.setPadding(new Insets(20));
		playerBox.setBackground(
				new Background(new BackgroundFill(Color.color((float) 254 / 255, (float) 204 / 255, (float) 131 / 255),
						null, new Insets(10, 0, 10, 10))));

		return playerBox;

	}

	private static void bagSlotSetup(GridPane bagSlot) {
		bagSlot.setPadding(new Insets(25, 0, 0, 0));
		bagSlot.setAlignment(Pos.TOP_CENTER);
		bagSlot.setVgap(10);
		bagSlot.setHgap(10);
		bagSlot.setPrefWidth(600);
		bagSlot.setBackground(new Background(new BackgroundFill(Color.BLACK, null, new Insets(10))));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				StackPane show = new StackPane();
				show.setAlignment(Pos.CENTER);
				show.setPrefSize(60, 60);
				show.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
				bagSlot.add(show, j, i);
				itemSlotBox.add(show);
			}

		}
		Text back = GameUtil.createNewTextButton(11, "Back");
		back.setOnMouseClicked(event -> {
			HomeScene.homeUpdate();
		});
		bagSlot.add(back, 7, 5);
	}

	private static void playerStatusBoxSetup() {
		playerStatusBox = new VBox();
		playerStatusBox.setSpacing(20);
		playerStatusBox.setAlignment(Pos.TOP_LEFT);
		playerStatusBox.setStyle("-fx-padding: 20;" + "-fx-border-style: solid outside;" + "-fx-border-width: 5;"
				+ "-fx-border-radius: 20;" + "-fx-border-color: rgb(177,78,5);"
				+ "-fx-background-color: rgb(251,227,163);" + "-fx-background-radius: 20;");
		playerStatusBox.setPrefWidth(300);
		playerStatusBox.getChildren().add(GameUtil.createNewText(25, null));
		playerStatusBox.getChildren().add(GameUtil.createNewText(25, null));
		playerStatusBox.getChildren().add(GameUtil.createNewText(25, null));
		playerStatusBox.getChildren().add(new StackPane(new Rectangle(69, 69, Color.GRAY),
				new Rectangle(62, 62, Color.LIGHTGRAY), GameUtil.user.getWeapon().getIcon(56, 56)));
	}

	public static void sceneUpdate() {

		((Text) playerStatusBox.getChildren().get(0)).setText("Coin : " + GameUtil.user.getCoin());
		((Text) playerStatusBox.getChildren().get(1)).setText("Attack : " + GameUtil.user.getWeapon().getDamage());
		((Text) playerStatusBox.getChildren().get(2)).setText("Weapon : " + GameUtil.user.getWeapon().getName());
		((Pane) playerStatusBox.getChildren().get(3)).getChildren().set(2, GameUtil.user.getWeapon().getIcon(64, 64));

		for (StackPane i : itemSlotBox) {
			i.getChildren().clear();
			i.getChildren()
					.add(new Rectangle(55, 55, Color.color((float) 55 / 255, (float) 54 / 255, (float) 64 / 255)));
		}
		bagUpdate();
		GameUtil.game.setScene(bagScene);
	}

	private static void bagUpdate() {
		ArrayList<BagItem> allItem = GameUtil.user.getBag().getAllItem();
		for (int i = 0; i < allItem.size(); i++) {
			((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
			});
			itemSlotBox.get(i).getChildren().clear();
			if (allItem.get(i).equals(GameUtil.user.getWeapon())) {
				itemSlotBox.get(i).getChildren().add(new Rectangle(60, 60, Color.GOLD));
			} else {
				int a = i;
				if (allItem.get(i) instanceof Pickaxe) {
					((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
						GameUtil.user.setWeapon((Pickaxe) allItem.get(a));
						sceneUpdate();
					});
				}
			}
			itemSlotBox.get(i).getChildren().add(allItem.get(i).getIcon(55, 55));
			if (allItem.get(i) instanceof Stackable) {
				Text amountItems = new Text("" + ((Stackable) allItem.get(i)).getAmount());
				amountItems.setFill(Color.WHITE);
				amountItems.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 18));
				StackPane amount = new StackPane(amountItems);
				amount.setPadding(new Insets(0, 4, 2, 0));
				amount.setAlignment(Pos.BOTTOM_RIGHT);
				itemSlotBox.get(i).getChildren().add(amount);
			}
		}
	}
}
