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

public class EnchantScene {

	public static ArrayList<StackPane> itemSlotBox = new ArrayList<>();
	public static Scene enchantScene;
	public static VBox weaponStatBox;
	public static Pickaxe target;

	public static void sceneSetup() {
		target = GameUtil.user.getWeapon();
		HBox layout = new HBox();
		weaponStatBoxSetup();

		VBox weaponBox = createWeaponBox();

		GridPane bagSlot = new GridPane();
		bagSlotSetup(bagSlot);

		layout.setBackground(new Background(
				new BackgroundFill(Color.color((float) 152 / 255, (float) 61 / 255, (float) 6 / 255), null, null)));
		layout.getChildren().addAll(weaponBox, bagSlot);

		enchantScene = new Scene(layout, 1000, 700);
	}

	private static VBox createWeaponBox() {
		Text headText = new Text("Enchantment");
		headText.setFont(Font.font("Colonna MT", FontWeight.BOLD, 60));
		StackPane headTextBox = new StackPane(headText);
		headTextBox.setPrefHeight(100);

		Text subhead = new Text("WeaponStats");
		subhead.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 40));
		VBox weaponBox = new VBox(headTextBox, subhead, weaponStatBox);
		weaponBox.setPrefWidth(400);
		weaponBox.setSpacing(20);
		weaponBox.setAlignment(Pos.TOP_CENTER);
		weaponBox.setPadding(new Insets(20));
		weaponBox.setBackground(
				new Background(new BackgroundFill(Color.color((float) 254 / 255, (float) 204 / 255, (float) 131 / 255),
						null, new Insets(10, 0, 10, 10))));
		return weaponBox;
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

	private static void weaponStatBoxSetup() {
		weaponStatBox = new VBox();
		weaponStatBox.setPrefHeight(450);
		weaponStatBox.setSpacing(20);
		weaponStatBox.setAlignment(Pos.TOP_LEFT);
		weaponStatBox.setStyle("-fx-padding: 20;" + "-fx-border-style: solid outside;" + "-fx-border-width: 5;"
				+ "-fx-border-radius: 20;" + "-fx-border-color: rgb(177,78,5);"
				+ "-fx-background-color: rgb(251,227,163);" + "-fx-background-radius: 20;");

		weaponStatBox.getChildren().add(GameUtil.createNewText(25, null));
		weaponStatBox.getChildren().add(GameUtil.createNewText(25, null));
		weaponStatBox.getChildren()
				.add(new StackPane(new Rectangle(69, 69, Color.GRAY), GameUtil.user.getWeapon().getIcon(64, 64)));
		weaponStatBox.getChildren().add(GameUtil.createNewText(20, null));
		weaponStatBox.getChildren().add(GameUtil.createNewText(20, null));
		weaponStatBox.getChildren().add(GameUtil.createNewText(20, null));

		Text upgrade = GameUtil.createNewTextButton(20, "upgrade");
		weaponStatBox.getChildren().add(new StackPane(upgrade));
		upgrade.setOnMouseClicked(event -> {

			GameUtil.upgradePickaxe(target);
			sceneUpdate();
		});
		weaponStatBox.getChildren().add(GameUtil.createNewText(20, null));
	}

	public static void sceneUpdate() {

		((Text) weaponStatBox.getChildren().get(0)).setText("Attack : " + target.getDamage());
		((Text) weaponStatBox.getChildren().get(1)).setText("Weapon : " + target.getName());
		((Pane) weaponStatBox.getChildren().get(2)).getChildren().set(1, target.getIcon(64, 64));
		((Text) weaponStatBox.getChildren().get(3))
				.setText("Minarals : " + target.getMineral() + " " + (int) Math.pow(2, target.getPlus()) + " bars");
		((Text) weaponStatBox.getChildren().get(4)).setText("Rate : " + 100 / Math.pow(2, target.getPlus()) + "%");
		((Text) weaponStatBox.getChildren().get(5))
				.setText("Evlove " + target.getEnchantCount() + " / " + target.getNextEvoTime());

		for (StackPane i : itemSlotBox) {
			i.getChildren().clear();
			i.getChildren()
					.add(new Rectangle(55, 55, Color.color((float) 55 / 255, (float) 54 / 255, (float) 64 / 255)));
		}
		bagUpdate();

		GameUtil.game.setScene(enchantScene);
	}

	private static void bagUpdate() {
		ArrayList<BagItem> allItem = GameUtil.user.getBag().getAllItem();

		for (int i = 0; i < allItem.size(); i++) {
			((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
			});
			itemSlotBox.get(i).getChildren().clear();
			if (allItem.get(i).equals(target)) {
				itemSlotBox.get(i).getChildren().add(new Rectangle(60, 60, Color.GOLD));
			} else {
				int a = i;
				if (allItem.get(i) instanceof Pickaxe) {
					((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
						target = (Pickaxe) allItem.get(a);
						sceneUpdate();
					});
				}
			}
			itemSlotBox.get(i).getChildren().add(allItem.get(i).getIcon(55, 55));
			if (allItem.get(i) instanceof Stackable) {
				Text amountItems = GameUtil.createNewText(20, "" + ((Stackable) allItem.get(i)).getAmount());
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
