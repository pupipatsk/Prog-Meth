package pane;

import java.util.ArrayList;

import component.CreatePageButton;
import component.PageButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NavigationPane extends VBox {
	
    private final ArrayList<PageButton> pageButtons = new ArrayList<>();
    private int currentPage;

    public NavigationPane() {
        this.setPrefWidth(80);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        this.setSpacing(15);
        this.setPadding(new Insets(15));

        CreatePageButton createPageButton = new CreatePageButton();
        this.getChildren().add(createPageButton);
    }

    public void addPage() {
        // TODO: FILL CODE HERE
    	PageButton temp = new PageButton(pageButtons.size());
    	pageButtons.add(temp);
    	RootPane.getNavigationPane().getChildren().add(pageButtons.size()-1, temp);
    	RootPane.getDisplayPane().addTodoList();
    	this.setCurrentPage(pageButtons.size()-1);
    }
    public int getCurrentPage() {
        // TODO: FILL CODE HERE
    	return this.currentPage;
    }
    public void setCurrentPage(int pageNumber) {
        // TODO: FILL CODE HERE
    	if (0<=pageNumber && pageNumber<pageButtons.size()) {
    		pageButtons.get(this.getCurrentPage()).setActive(false);
    		currentPage = pageNumber;
    		pageButtons.get(this.getCurrentPage()).setActive(true);
    		RootPane.getDisplayPane().setActiveTodoList(pageNumber);
    	}
    }
}
