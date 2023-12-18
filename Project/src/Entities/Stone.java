package Entities;

import java.util.Random;

import Game.logic.GameUtil;
import Game.logic.Mineral;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Stone {
	private int maxHp;
	private int hp;
	private int lvl;
	private Bag dropItems = new Bag();
	private ImageView icon;

	public Stone(int lvl) {

		ImageView stoneIcon = new ImageView(new Image(ClassLoader.getSystemResource("picture/entities/Rock.png").toString()));
		stoneIcon.setFitWidth(80);
		stoneIcon.setFitHeight(60);
		this.setLvl(lvl);
		setIcon(stoneIcon);

		hp = (int) (10 * Math.pow(5, lvl) * (1 + Math.random()));
		maxHp = hp;
		dropItemsGenerate();

	}

	public void dropItemsGenerate() {
		Random random = new Random();
		int randomValue = random.nextInt(100);
		int cumulativeRate = 0;
		int amount = (lvl - 1) / 2;
		for (int i = 0; true; i++) {
			cumulativeRate += 120 / Math.pow(2, i + 1);
			amount++;
			if (randomValue < cumulativeRate) {
				break;
			}
		}
		for (int i = 0; i < amount; i++) {
			dropItems.addItem(new Item(Mineral.getRandom((lvl - 1) / 2), 1));
		}

	}

	public Bag getDropItems() {
		return this.dropItems;
	}
	
	public void setDropItems(Bag dropItems) {
		this.dropItems = dropItems;
	}

	public void upToBoss(int level) {
		this.maxHp *= 2;
		this.hp *= 2;
		icon.setFitHeight(120);
		icon.setFitWidth(160);
	}

	public void damaged() {
		
		this.setHp(hp - GameUtil.user.getWeapon().getDamage());
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = Math.max(hp, 0);
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
	}

}
