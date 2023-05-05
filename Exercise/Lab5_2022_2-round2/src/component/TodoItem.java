package component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import pane.RootPane;

public class TodoItem extends HBox{

	public TodoItem(String value) {
		this.setSpacing(10);
		Text text = new Text(value);
		text.setStyle("-fx-font-size:20;");
		Button btn = new Button("Delete");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				RootPane.getDisplayPane().removeTodoItem(TodoItem.this);
			}
		});
		this.getChildren().addAll(text, btn);
	}
}
