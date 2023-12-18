package Entities;
import java.util.Objects;

import Game.logic.BagItem;
import Game.logic.Mineral;
import Game.logic.Stackable;
import javafx.scene.image.ImageView;

public class Item implements BagItem,Stackable {
	private Mineral Type;
	private int amount;
	private String name;
	private int id;

	public Item(Mineral type, int amount) {
		Type = type;
		this.id = type.getId();
		name = Type.getName();
		this.amount = amount;
	}
	
	public void merge(Item r) {
		if(this.equals(r))this.amount += r.getAmount();
	}
		

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BagItem person = (BagItem) obj;
        return Objects.equals(name, person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return Type.getName();
	}

	public ImageView getIcon(int width, int height) {
		return Type.getIcon(width,height);
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return Type.getCost();
	}
}
