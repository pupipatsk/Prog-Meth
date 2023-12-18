package Game.logic;

import java.util.ArrayList;
import java.util.Random;

import Entities.Bag;
import Entities.Player;
import Entities.Item;
import Game.Scene.EnchantScene;
import Game.Scene.Stages;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// controls game's mechanic
public class GameUtil {

	public static Player user = new Player();
	public static Stage game;
	public static ArrayList<Stages> stages = new ArrayList<>();

	// Item's upgrade logic
	public static void upgradePickaxe(Pickaxe p) {
		Bag currentBag = user.getBag();
		Item target = currentBag.getitem(p.getMineral().getName());

		if (target != null && target.getAmount() >= Math.pow(2, p.getPlus())) {
			Random random = new Random();
			double randomValue = random.nextDouble();
			currentBag.setAmountof((BagItem) target, (int) (target.getAmount() - Math.pow(2, p.getPlus())));

			if (randomValue <= 1 / Math.pow(2, p.getPlus())) {
				p.addPlus();
				((Text) EnchantScene.weaponStatBox.getChildren().get(7)).setText("Success");
				System.out.println("");
			} else {
				p.downPlus();
				System.out.println("fail");
				((Text) EnchantScene.weaponStatBox.getChildren().get(7)).setText("Fail");
			}
			if (p.addEnchantCount()) {
				((Text) EnchantScene.weaponStatBox.getChildren().get(7))
						.setText("'Weapon Evolve'Now Base Plus : " + p.getBasePlus());
			}

		}

	}

	// Create new Text with default font family
	public static Text createNewText(int size, String text) {
		Text t = new Text(text);
		t.setFont(Font.font("Pixel Operator", FontWeight.NORMAL, size));
		return t;

	}

	// Item'sold logic
	public static void sellItem(BagItem i) {
		if (user.getBag().getAllItem().contains(i)) {
			Bag currentBag = user.getBag();
			if (i instanceof Stackable) {
				Item target = currentBag.getitem(i.getName());
				currentBag.setAmountof((BagItem) target, (int) (target.getAmount() - 1));
			} else {
				user.getBag().getAllItem().remove(i);
			}
			user.setCoin(user.getCoin() + i.getCost());
		}
	}

	// Item's bought logic
	public static void buyItem(BagItem i) {
		user.getBag().addItem(i);
		user.setCoin(user.getCoin() - i.getCost());
	}

	// Create Text can interact with player with default setting
	public static Text createNewTextButton(int size, String text) {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.0f, 0.0f, 0.0f));
		Text t = new Text(text);
		t.setStyle("-fx-font-size: " + size + "; -fx-fill:  white;");
		t.setFont(Font.font("Daydream", FontWeight.BOLD, size));
		t.setOnMouseEntered(event -> t.setStyle("-fx-font-size: " + (size + 2) + "; -fx-fill: lightgray;"));
		t.setOnMouseExited(event -> t.setStyle("-fx-font-size: " + (size) + "; -fx-fill:  white;"));
		t.setEffect(ds);
		return t;
	}
}
