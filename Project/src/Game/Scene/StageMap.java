package Game.Scene;

import java.util.ArrayList;

import Entities.Bag;
import Entities.Item;
import Entities.Stone;
import Entities.Weapon.BronzePickaxe;
import Entities.Weapon.DiamondPickaxe;
import Entities.Weapon.GoldPickaxe;
import Entities.Weapon.IronPickaxe;
import Entities.Weapon.SilverPickaxe;
import Entities.Weapon.WoodPickaxe;
import Game.logic.BagItem;
import Game.logic.GameUtil;
import Game.logic.Mineral;
import Game.logic.Pickaxe;
import Game.logic.Stackable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StageMap {
	private Stages currentStage;
	private int stageLevel;
	private ArrayList<Stone> allStones = new ArrayList<>();
	private ArrayList<VBox> stoneBox = new ArrayList<>();
	private Bag dropItems = new Bag();
	private int totalKill;

	public StageMap(int stageLevel, Stages currentStage) {
		this.currentStage = currentStage;
		this.stageLevel = stageLevel;

		if (stageLevel % 10 == 0) {
			Stone boss = new Stone((stageLevel / 10) + 1);
			boss.upToBoss(stageLevel);
			boss.dropItemsGenerate();
			allStones.add(boss);
		} else {
			for (int i = 0; i < ((((stageLevel % 10) - 1) / 3) + 1) * 3; i++) {
				allStones.add(new Stone((stageLevel / 10) + 1));
			}
		}
	}
	
	public void startStage() {
		VBox layout = new VBox();
		layout.setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("picture/background/stage_bg.gif").toString()), null, null,null, null)));
		;

		HBox headTextBox = new HBox();
		BorderPane field = new BorderPane();
		HBox stageInfo = new HBox();
		HBox itemDropList = new HBox();
		panePropertySetup(headTextBox,field,stageInfo,itemDropList);
		
		GridPane enemysArea = new GridPane();
		enemysAreaSetup(enemysArea);
		enemySetup(itemDropList);	

		HBox playerInfo = getPlayerInfo();
		
		Text giveup = GameUtil.createNewTextButton(12, "giveup");
		giveup.setOnMouseClicked(e -> {stageClear();});
		
		VBox ButtonList = new VBox(giveup);
		ButtonList.setPrefWidth(100);
		ButtonList.setAlignment(Pos.CENTER);
		ButtonList.setSpacing(20);
		
		ImageView miner = new ImageView(
				new Image(ClassLoader.getSystemResource("picture/entities/miner.png").toString()));
		field.setLeft(miner);
		field.setRight(enemysArea);
		
		stageInfo.getChildren().addAll(itemDropList,playerInfo,ButtonList);
		layout.getChildren().addAll(headTextBox, field, stageInfo);

		GameUtil.game.setScene(new Scene(layout, 1000, 700));
	}
	
	private void enemysAreaSetup(GridPane enemysArea) {
		enemysArea.setHgap(10);
		enemysArea.setVgap(10);
		// boss setup		
				if (stageLevel % 10 == 0) {
					enemysArea.setPadding(new Insets(50));
					enemysArea.setAlignment(Pos.CENTER_LEFT);
					VBox enemy = new VBox();
					enemy.setPrefSize(150, 120);
					enemy.setAlignment(Pos.BOTTOM_CENTER);
					enemysArea.add(enemy, 0, 0);
					stoneBox.add(enemy);
				}
		// normal monster
				else {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							VBox enemy = new VBox();
							enemy.setPrefSize(50, 40);
							enemy.setAlignment(Pos.BOTTOM_CENTER);
							enemysArea.add(enemy, i, j);
							stoneBox.add(enemy);
						}
					}
				}
	}
	
	private void enemySetup(HBox itemDropList) {
		
		for (int i = 0; i < Math.min(allStones.size(), 9); i++) {
			int currentStone = i;
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					
					enemyGetAttack(currentStone , itemDropList);
				}
			};
			stoneBox.get(i).getChildren().clear();
			stoneBox.get(i).getChildren().addAll(allStones.get(i).getIcon());
			Text healthBars = new Text(allStones.get(i).getHp() + " / " + allStones.get(i).getMaxHp());
			healthBars.setFont(Font.font("Pixel Operator", FontWeight.BOLD, 15));
			healthBars.setFill(Color.WHITE);
			stoneBox.get(i).getChildren().addAll(healthBars);
			stoneBox.get(i).getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		}
	}
	
	private void enemyGetAttack(int currentStone , HBox itemDropList){
		
		allStones.get(currentStone).damaged();
		((Text) stoneBox.get(currentStone).getChildren().get(1))
				.setText(allStones.get(currentStone).getHp() + " / " + allStones.get(currentStone).getMaxHp());
		if (allStones.get(currentStone).getHp() == 0) {
			stoneBox.get(currentStone).setVisible(false);

			dropItems.addAllItem(allStones.get(currentStone).getDropItems().getAllItem());

			itemDropList.getChildren().clear();
			itemDropList.getChildren().add((GameUtil.createNewText(20, "All Reward : ")));

			for (BagItem dropItem : dropItems.getAllItem()) {
				VBox dropItemBox = new VBox();
				dropItemBox.setPrefWidth(20);
				dropItemBox.setAlignment(Pos.CENTER);
				dropItemBox.getChildren()
						.add(new StackPane(new Rectangle(35, 35, Color.LIGHTGREY), dropItem.getIcon(32, 32)));
				dropItemBox.getChildren()
						.add(GameUtil.createNewText(15, ((Stackable) dropItem).getAmount() + " " + dropItem.getName()));
				itemDropList.getChildren().add(dropItemBox);
			}
			totalKill++;
			if (totalKill == allStones.size())stageClear();
		}
	}

	private void panePropertySetup(HBox headTextBox,BorderPane field,HBox stageInfo,HBox itemDropList) {
		Text headText = new Text("Stage  " + this.stageLevel);
		headText.setFill(Color.WHITE);
		headText.setFont(Font.font("Daydream", FontWeight.BOLD, 65));
		
		headTextBox.getChildren().add(headText);
		headTextBox.setPrefHeight(300);
		headTextBox.setPadding(new Insets(20,0,0,30));

		field.setPrefHeight(275);
		field.setPadding(new Insets(50, 20, 30, 80));

		stageInfo.setPrefHeight(125);
		stageInfo.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		stageInfo.setAlignment(Pos.CENTER_LEFT);
		stageInfo.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 5;");
		stageInfo.setSpacing(10);

		itemDropList.setPadding(new Insets(0, 0, 0, 15));
		itemDropList.setSpacing(10);
		itemDropList.setPrefWidth(420);
		itemDropList.setAlignment(Pos.CENTER_LEFT);
		itemDropList.getChildren().add(GameUtil.createNewText(20, "All Reward : "));
		itemDropList.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
	}
	
	private HBox getPlayerInfo() {
		HBox playerInfo = new HBox();
		playerInfo.getChildren().add(
				new StackPane(new Rectangle(60, 60, Color.DEEPSKYBLUE), GameUtil.user.getWeapon().getIcon(55, 55)));
		playerInfo.getChildren()
				.add(new VBox(GameUtil.createNewText(20, "Curruent Weapon : " + GameUtil.user.getWeapon().getName()),
						GameUtil.createNewText(20, "Curruent Damage : " + GameUtil.user.getWeapon().getDamage())));
		((VBox) playerInfo.getChildren().get(1)).setAlignment(Pos.CENTER_LEFT);
		playerInfo.setAlignment(Pos.CENTER);
		playerInfo.setPrefWidth(450);
		playerInfo.setSpacing(50);
		playerInfo.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		return playerInfo;
		
	}

	private void stageClear() {
		HomeScene.mapSceneSetup();
		Text headText = new Text("So Close");
		headText.setFill(Color.WHITE);
		headText.setFont(Font.font("Daydream", 65));
		HBox headTextBox = new HBox(headText);
		headTextBox.setAlignment(Pos.CENTER);
		headTextBox.setPrefHeight(200);
		
		if (totalKill == allStones.size()) {
			headText.setText("Victory");
			if (stageLevel == 100)
				headText.setText("congratulations");
			if (!currentStage.isClear())
				dropItems.addItem(getStageRewardDrop());
			currentStage.setClear(true);
		}
		
		VBox layout = new VBox();
		layout.setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("picture/background/stage_bg.gif").toString()), null, null,
				null, null)));

		HBox actionBox = getClearactionBotton();
		HBox dropItemListBox = getClearDropItemListBox();
		
		layout.getChildren().addAll(headTextBox, dropItemListBox, actionBox);
		
		GameUtil.game.setScene(new Scene(layout, 1000, 700));
	}
	
	private HBox getClearDropItemListBox() {
		HBox dropItemListBox = new HBox();
		dropItemListBox.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		dropItemListBox.setSpacing(10);
		dropItemListBox.setPrefHeight(300);
		dropItemListBox.setAlignment(Pos.CENTER);
		if (dropItems.getAllItem().size() == 0)
			dropItemListBox.getChildren().add(GameUtil.createNewText(30, "All Reward : No Items Drops"));
		else
			dropItemListBox.getChildren().add(GameUtil.createNewText(30, "All Reward : "));

		for (BagItem i : dropItems.getAllItem()) {
			VBox item = new VBox();
			item.setAlignment(Pos.CENTER);
			
			item.getChildren().add(new StackPane(new Rectangle(60, 60, Color.LIGHTGREY), i.getIcon(55, 55)));
			if (i instanceof Stackable)
				item.getChildren().add(GameUtil.createNewText(20, ((Stackable) i).getAmount() + " " + i.getName()));
			else
				item.getChildren().add(GameUtil.createNewText(20, i.getName()));
			dropItemListBox.getChildren().add(item);
		}
		return dropItemListBox;
	}
	
	private HBox getClearactionBotton(){
		Text home = GameUtil.createNewTextButton(16, "home");
		home.setOnMouseClicked(arg0 -> {
			HomeScene.homeUpdate();
		});

		Text map = GameUtil.createNewTextButton(16, "Map");
		map.setOnMouseClicked(arg0 -> {
			HomeScene.mapUpdate();
		});

		Text retry = GameUtil.createNewTextButton(16, "retry");
		retry.setOnMouseClicked(arg0 -> {
			GameUtil.stages.get(stageLevel - 1).GameShow();
		});

		Text next = GameUtil.createNewTextButton(16, "next");
		next.setOnMouseClicked(arg0 -> {
			GameUtil.stages.get(stageLevel).GameShow();
		});

		StackPane box1 = new StackPane(home);
		box1.setPrefSize(100, 75);
		StackPane box2 = new StackPane(map);
		box2.setPrefSize(100, 75);
		StackPane box3 = new StackPane(retry);
		box3.setPrefSize(100, 75);
		StackPane box4 = new StackPane(next);
		box4.setPrefSize(100, 75);

		HBox actionBox = new HBox(box1, box2, box3);
		if (stageLevel != 100)
			actionBox.getChildren().add(box4);
		actionBox.setSpacing(20);
		actionBox.setPadding(new Insets(20));
		actionBox.setPrefHeight(75);
		actionBox.setAlignment(Pos.CENTER);
		return actionBox;
	}

	private BagItem getStageRewardDrop() {

		Mineral mineral = Mineral.Iron;

		switch ((int) (stageLevel / 20)) {
		case 0:
			mineral = Mineral.Iron;
			break;
		case 1:
			mineral = Mineral.Bronze;
			break;
		case 2:
			mineral = Mineral.Silver;
			break;
		case 3:
			mineral = Mineral.Gold;
			break;
		case 4:
			mineral = Mineral.Diamond;
			break;

		}
		int dropPlus = ((stageLevel / 10) % 2) * 4;
		Pickaxe drop = new WoodPickaxe(dropPlus);

		switch (stageLevel / 20) {
		case 0:
			drop = new WoodPickaxe(dropPlus);
			break;
		case 1:
			drop = new IronPickaxe(dropPlus);
			break;
		case 2:
			drop = new BronzePickaxe(dropPlus);
			break;
		case 3:
			drop = new SilverPickaxe(dropPlus);
			break;
		case 4:
			drop = new GoldPickaxe(dropPlus);
			break;
		case 5:
			drop = new DiamondPickaxe(99);
			break;

		}
		if (stageLevel % 10 == 0)
			return drop;

		return new Item(mineral, stageLevel % 20);
	}

	
	public Stages getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stages currentStage) {
		this.currentStage = currentStage;
	}

	public int getStageLevel() {
		return stageLevel;
	}

	public void setStageLevel(int stageLevel) {
		this.stageLevel = stageLevel;
	}

	public ArrayList<Stone> getAllStones() {
		return allStones;
	}

	public void setAllStones(ArrayList<Stone> allStones) {
		this.allStones = allStones;
	}

	public ArrayList<VBox> getStoneBox() {
		return stoneBox;
	}

	public void setStoneBox(ArrayList<VBox> stoneBox) {
		this.stoneBox = stoneBox;
	}

	public Bag getDropItems() {
		return dropItems;
	}

	public void setDropItems(Bag dropItems) {
		this.dropItems = dropItems;
	}

	public int getTotalKill() {
		return totalKill;
	}

	public void setTotalKill(int totalKill) {
		this.totalKill = totalKill;
	}

	

}
