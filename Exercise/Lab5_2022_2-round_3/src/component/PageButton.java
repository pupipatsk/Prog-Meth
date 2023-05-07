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
	
	public PageButton(int pageNumber) {
		Circle c = new Circle(25);
		c.setFill(Color.LIGHTGRAY);
		this.setCircle(c);
		
		Text t = new Text(Integer.toString(pageNumber));
		t.setStyle("-fx-font-size:25;");
		
		this.setCursor(Cursor.HAND);
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RootPane.getNavigationPane().setCurrentPage(pageNumber);
			}
		});
		this.getChildren().addAll(c, t);
	}
	
	public void setActive(boolean value) {
		if (value == true) {
			this.getCircle().setFill(Color.WHITE);
		}
		else {
			this.getCircle().setFill(Color.LIGHTGRAY);
		}
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
}
