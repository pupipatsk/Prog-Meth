package pane;

import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
	
	// Fields
    private static NavigationPane navigationPane;
    private static DisplayPane displayPane;
    private static InputPane inputPane;

    // Constructor
    public RootPane() {
        // TODO: FILL CODE
    	
    	navigationPane = new NavigationPane();
    	displayPane = new DisplayPane();
    	inputPane = new InputPane();
    	
    	navigationPane.addPage();
    	
    	this.setLeft(navigationPane);
    	this.setCenter(displayPane);
    	this.setBottom(inputPane);
    }

    // Methods
    public static NavigationPane getNavigationPane() {
        return navigationPane;
    }

    public static DisplayPane getDisplayPane() {
        return displayPane;
    }

    public static InputPane getInputPane() {
        return inputPane;
    }
}
