package component;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pane.RootPane;

public class PageButton extends StackPane{
	
	private Circle circle;
	
	public PageButton(final int pageNumber) {
		Circle temp = new Circle(25);
		temp.setFill(Color.LIGHTGRAY);
		Text text = new Text(pageNumber + "");
		text.setStyle("-fx-font-size:25;");
		this.setCircle(temp);
		this.setCursor(Cursor.HAND);
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RootPane.getNavigationPane().setCurrentPage(pageNumber);
			}
		});
		this.getChildren().addAll(this.getCircle(), text);
	}
	
	public void setActive(boolean value) {
		if (value) {
			this.circle.setFill(Color.WHITE);
		}
		else {
			this.circle.setFill(Color.LIGHTGRAY);
		}
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
}
