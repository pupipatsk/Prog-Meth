package Game.logic;
import javafx.scene.image.ImageView;

// Interface refer this can store in player's bag
public interface BagItem {
	String getName();
	ImageView getIcon(int width, int height);
	int getCost();
	int getId();
}
