package pane;

import java.util.ArrayList;

import component.TodoItem;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DisplayPane extends VBox {
	
    private final ArrayList<ArrayList<TodoItem>> todoLists = new ArrayList<ArrayList<TodoItem>>();
    
    public DisplayPane() {
        // TODO: FILL CODE HERE
    	this.setBackground(Background.fill(Color.WHITE));
    	this.setPadding(new Insets(20));
    	this.setSpacing(10);
    }

    public void addTodoList() {
        // TODO: FILL CODE HERE
    	ArrayList<TodoItem> todo = new ArrayList<TodoItem>();
    	todoLists.add(todo);
    }

    public void setActiveTodoList(int index) {
        // TODO: FILL CODE HERE
    	if (index>=0 && index<todoLists.size()) {
    		RootPane.getDisplayPane().getChildren().clear();
    		RootPane.getDisplayPane().getChildren().addAll(todoLists.get(index));
    	}
    }

    public void addTodoItem(TodoItem todoItem) {
        int currentPage = RootPane.getNavigationPane().getCurrentPage();
        this.todoLists.get(currentPage).add(todoItem);
        this.getChildren().add(todoItem);
    }

    public void removeTodoItem(TodoItem todoItem) {
        int currentPage = RootPane.getNavigationPane().getCurrentPage();
        this.todoLists.get(currentPage).remove(todoItem);
        this.getChildren().remove(todoItem);
    }
}
