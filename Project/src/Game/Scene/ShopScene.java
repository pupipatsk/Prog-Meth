package Game.Scene;

import java.util.ArrayList;

import Entities.Item;
import Game.logic.BagItem;
import Game.logic.GameUtil;
import Game.logic.Mineral;
import Game.logic.Pickaxe;
import Game.logic.Stackable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ShopScene {

	public static ArrayList<StackPane> itemSlotBox = new ArrayList<>();
	public static Scene shopScene;
	public static VBox shopDetailBox;
	public static Pickaxe targetsell;
	public static Text pickaxeName = GameUtil.createNewText(18, "Items : ");
	public static Text pickaxeCost = GameUtil.createNewText(16, "Cost : ");
	public static Node pickaxeIconBox = new Rectangle(48, 48, Color.LIGHTGRAY);
	public static Button pickaxeSellButton;
	public static ArrayList<BooleanProperty> Buttondisable = new ArrayList<>();

	public static void sceneSetup() {
		HBox layout = new HBox();
		shopDetailBox = new VBox();
		shopDetailBox.getChildren().add(GameUtil.createNewText(25, null));

		pickaxeIconBox = new StackPane(new Rectangle(53, 53, Color.GRAY), new Rectangle(48, 48, Color.GRAY));
		VBox pickaxeDetailBox = new VBox(pickaxeName, pickaxeCost);
		pickaxeDetailBox.setSpacing(5);
		pickaxeDetailBox.setPrefSize(210, 50);
		pickaxeDetailBox.setAlignment(Pos.CENTER_LEFT);

		pickaxeSellButton = new Button("sell");
		pickaxeSellButton.setPrefSize(75, 50);
		pickaxeSellButton.setDisable(true);

		HBox pickaxeSellBox = new HBox(pickaxeDetailBox, pickaxeIconBox, pickaxeSellButton);
		pickaxeSellBox.setAlignment(Pos.CENTER_RIGHT);
		pickaxeSellBox.setSpacing(10);
		shopDetailBox.getChildren().add(pickaxeSellBox);

		shopDetailBoxSetup();
		VBox shopBox = createShopBox();
		GridPane bagSlot = createBagSlot();

		layout.setBackground(new Background(
				new BackgroundFill(Color.color((float) 152 / 255, (float) 61 / 255, (float) 6 / 255), null, null)));
		layout.getChildren().addAll(shopBox, bagSlot);
		shopScene = new Scene(layout, 1000, 700);
	}

	private static void shopDetailBoxSetup() {

		shopDetailBox.setSpacing(20);
		shopDetailBox.setAlignment(Pos.TOP_LEFT);
		shopDetailBox.setStyle("-fx-padding: 20;" + "-fx-border-style: solid outside;" + "-fx-border-width: 5;"
				+ "-fx-border-radius: 20;" + "-fx-border-color: rgb(177,78,5);"
				+ "-fx-background-color: rgb(251,227,163);" + "-fx-background-radius: 20;");

		shopDetailBox.getChildren().add(new StackPane(GameUtil.createNewText(30, "Material")));
		VBox shelf = new VBox();
		shelf.setPrefWidth(400);
		shelf.setSpacing(15);
		shelf.setAlignment(Pos.CENTER);
		for (Mineral mineral : Mineral.values()) {
			HBox slot = createShelfSlot(mineral);
			BooleanProperty comparisonBinding = new SimpleBooleanProperty(false);
			Buttondisable.add(comparisonBinding);
			slot.getChildren().get(2).disableProperty().bind(comparisonBinding);
			shopDetailBox.getChildren().add(slot);
		}
	}

	private static VBox createShopBox() {

		Text head = new Text("Shop");
		head.setFont(Font.font("Colonna MT", FontWeight.BOLD, 70));
		StackPane s = new StackPane(head);
		s.setPrefHeight(90);
		Text subhead = GameUtil.createNewText(40, "Shop Details");
		subhead.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 40));

		VBox shopBox = new VBox(s, subhead, shopDetailBox);
		shopBox.setPrefWidth(400);
		shopBox.setSpacing(10);
		shopBox.setAlignment(Pos.TOP_CENTER);
		shopBox.setPadding(new Insets(20));
		shopBox.setBackground(
				new Background(new BackgroundFill(Color.color((float) 254 / 255, (float) 204 / 255, (float) 131 / 255),
						null, new Insets(10, 0, 10, 10))));

		return shopBox;
	}

	private static GridPane createBagSlot() {
		GridPane bagSlot = new GridPane();
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
				show.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
				bagSlot.add(show, j, i);
				itemSlotBox.add(show);
			}

		}

		Text back = GameUtil.createNewTextButton(11, "Back");
		back.setOnMouseClicked(event -> {
			targetsell = null;
			HomeScene.homeUpdate();
		});
		bagSlot.add(back, 7, 5);
		return bagSlot;
	}

	private static HBox createShelfSlot(Mineral mineral) {
		HBox slot = new HBox();
		slot.setAlignment(Pos.CENTER_RIGHT);
		slot.setSpacing(10);
		slot.setPadding(new Insets(0, 30, 0, 30));

		Text name = GameUtil.createNewText(20, mineral.getName());
		Text cost = GameUtil.createNewText(18, "Cost : " + mineral.getCost());

		VBox textContainer = new VBox(name, cost);
		textContainer.setSpacing(5);
		textContainer.setPrefSize(150, 50);
		textContainer.setAlignment(Pos.CENTER_LEFT);

		Text buy = GameUtil.createNewTextButton(15, "buy");
		buy.disableProperty().bind(Bindings.createBooleanBinding(() -> GameUtil.user.getCoinWithProperty().get() < mineral.getCost(),
				GameUtil.user.getCoinWithProperty()));
		buy.setOnMousePressed(e -> {
			GameUtil.buyItem(new Item(mineral, 1));
			sceneUpdate();
		});

		Text sell = GameUtil.createNewTextButton(15, "sell");
		sell.setOnMousePressed(e -> {
			GameUtil.sellItem(new Item(mineral, 1));
			sceneUpdate();
		});
		slot.getChildren().addAll(textContainer, buy, sell);
		return slot;
	}

	public static void sceneUpdate() {
		int z = 0;
		for (Mineral mineral : Mineral.values()) {
			Buttondisable.get(z).set(!GameUtil.user.getBag().hasItem(new Item(mineral, 0)));
			;
			z++;
		}
		((Text) shopDetailBox.getChildren().get(0)).setText("Coin : " + GameUtil.user.getCoin());
		if (targetsell != null) {
			((Pane) pickaxeIconBox).getChildren().set(1, targetsell.getIcon(50, 50));
			pickaxeName.setText("Item : " + targetsell.getName());
			pickaxeCost.setText("Cost : " + targetsell.getCost());
			pickaxeSellButton.setDisable(false);
			pickaxeSellButton.setOnMouseClicked(e -> {
				GameUtil.sellItem(targetsell);
				targetsell = null;
				sceneUpdate();
			});
		} else {
			((Pane) pickaxeIconBox).getChildren().set(1, new Rectangle(50, 50, Color.LIGHTGRAY));
			pickaxeName.setText("Item : ");
			pickaxeCost.setText("Cost : ");
			pickaxeSellButton.setDisable(true);
		}

		for (StackPane i : itemSlotBox) {
			i.getChildren().clear();
			i.getChildren()
					.add(new Rectangle(55, 55, Color.color((float) 55 / 255, (float) 54 / 255, (float) 64 / 255)));
		}

		bagUpdate();

		GameUtil.game.setScene(shopScene);
	}

	private static void bagUpdate() {
		ArrayList<BagItem> allItem = GameUtil.user.getBag().getAllItem();
		for (int i = 0; i < allItem.size(); i++) {
			((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
			});
			if (allItem.get(i).equals(targetsell)) {
				itemSlotBox.get(i).getChildren().add(new Rectangle(60, 60, Color.GOLD));
			} else if (allItem.get(i).equals(GameUtil.user.getWeapon())) {
				itemSlotBox.get(i).getChildren().add(new Rectangle(60, 60, Color.RED));
			} else {
				int a = i;
				if (allItem.get(i) instanceof Pickaxe) {
					((StackPane) itemSlotBox.get(i)).setOnMouseClicked(event -> {
						targetsell = (Pickaxe) allItem.get(a);
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
