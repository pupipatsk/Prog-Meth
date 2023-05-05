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
	// Fields
    private final ArrayList<PageButton> pageButtons = new ArrayList<>();
    private int currentPage;

    // Constructors
    public NavigationPane() {
        this.setPrefWidth(80);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        this.setSpacing(15);
        this.setPadding(new Insets(15));

        CreatePageButton createPageButton = new CreatePageButton();
        this.getChildren().add(createPageButton);
    }

    // Methods
    public void addPage() {
        // TODO: FILL CODE HERE
    	PageButton btn = new PageButton(this.getPageButtons().size());
    	this.getPageButtons().add(btn);
    	RootPane.getNavigationPane().getChildren().add( RootPane.getNavigationPane().getChildren().size()-1, btn);
    	RootPane.getDisplayPane().addTodoList();
    	this.setCurrentPage(this.getPageButtons().size() - 1);
    }
    // Getter & Setter
    public int getCurrentPage() {
        // TODO: FILL CODE HERE
    	return this.currentPage;
    }
    public void setCurrentPage(int pageNumber) {
        // TODO: FILL CODE HERE
    	if (0<=pageNumber && pageNumber<this.getPageButtons().size()) {
    		this.pageButtons.get(this.getCurrentPage()).setActive(false);
    		this.currentPage = pageNumber;
    		this.pageButtons.get(this.getCurrentPage()).setActive(true);
    		RootPane.getDisplayPane().setActiveTodoList(this.getCurrentPage());
    	}
    }
	public ArrayList<PageButton> getPageButtons() {
		return pageButtons;
	}
}
