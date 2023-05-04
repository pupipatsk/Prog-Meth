package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.RootPane;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // TODO: FILL CODE HERE
    	RootPane rp = new RootPane();
    	Scene scene = new Scene(rp, 800, 640);
    	stage.setScene(scene);
    	stage.setTitle("SIMPLE(?) TO DO LISTS");
    	stage.setResizable(false);
    	stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}