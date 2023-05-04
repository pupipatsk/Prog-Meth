package pane;

import component.TodoItem;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class InputPane extends HBox {
    public InputPane() {
        // TODO: FILL CODE
    	this.setPrefHeight(70);
    	this.setBackground(Background.fill(Color.LIGHTBLUE));
    	this.setSpacing(20);
    	this.setAlignment(Pos.CENTER);
    	
    	final TextField tf = new TextField();
    	tf.setPrefWidth(300);
    	Button btn = new Button("Submit");
    	btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
			if (!tf.getText().isBlank()) {
				TodoItem todo = new TodoItem(tf.getText());
				RootPane.getDisplayPane().addTodoItem(todo);
				tf.setText(" ");
			}
			}
		});
    	this.getChildren().addAll(tf, btn);
    }
}