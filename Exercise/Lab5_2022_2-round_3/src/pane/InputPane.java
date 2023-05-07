package pane;

import component.TodoItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    	
    	TextField tf = new TextField();
    	tf.setPrefWidth(300);
    	
    	Button btn = new Button("Submit");
    	btn.setOnAction( new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e) {
    			if (!tf.getText().isEmpty()) {
    				TodoItem temp = new TodoItem(tf.getText());
    				RootPane.getDisplayPane().addTodoItem(temp);
    				tf.clear();
    			}
    		}
    	});
    	
    	this.getChildren().addAll(tf, btn);
    }
}
