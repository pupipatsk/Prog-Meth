package Game.logic;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Mineral for use in Item
public enum Mineral {
	Iron(11, "Iron", 70, 40, 25, 15, 5, 1000), Bronze(12, "Bronze", 20, 40, 35, 20, 10, 10000),
	Silver(13, "Silver", 9, 15, 30, 45, 35, 100000), Gold(14, "Gold", 1, 5, 9, 15, 35, 1000000),
	Diamond(15, "Diamond", 0, 0, 1, 5, 15, 10000000);

	private String name;
	private int randomRate0;
	private int randomRate1;
	private int randomRate2;
	private int randomRate3;
	private int randomRate4;
	private ImageView icon;
	private int id;
	private int cost;

	Mineral(int id, String name, int randomRate0, int randomRate1, int randomRate2, int randomRate3, int randomRate4,
			int cost) {
		this.cost = cost;
		this.id = id;
		this.name = name;
		this.randomRate0 = randomRate0;
		this.randomRate1 = randomRate1;
		this.randomRate2 = randomRate2;
		this.randomRate3 = randomRate3;
		this.randomRate4 = randomRate4;
		this.icon = new ImageView(
				new Image(ClassLoader.getSystemResource("picture/minerals/" + name + ".png").toString()));
	}

	public int getRandomRate(int stageGroup) {
		switch (stageGroup) {
		case 0: {
			return randomRate0;
		}
		case 1: {
			return randomRate1;
		}
		case 2: {
			return randomRate2;
		}
		case 3: {
			return randomRate3;
		}
		case 4: {
			return randomRate4;
		}
		default:
			return randomRate0;
		}

	}

	// get random Mineral with random rate of each stageGroup
	public static Mineral getRandom(int stageGroup) {
		Random random = new Random();
		int totalRates = 0;

		for (Mineral rank : values()) {
			totalRates += rank.getRandomRate(stageGroup);
		}
		int randomValue = random.nextInt(totalRates);
		int cumulativeRate = 0;
		for (Mineral rank : values()) {
			cumulativeRate += rank.getRandomRate(stageGroup);
			if (randomValue < cumulativeRate) {
				return rank;
			}
		}

		// In case of unexpected scenarios, return a default rank
		return Iron;
	}

	// get icon with scale
	public ImageView getIcon(int width, int height) {
		icon.setFitHeight(height);
		icon.setFitWidth(width);
		return icon;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return cost;
	}

}
