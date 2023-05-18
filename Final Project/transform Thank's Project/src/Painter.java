

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Painter {

    protected static Button paintButton(String text, int fontSize, int x, int y, int width, int height) {
        Button button = new Button(text);
        button.setFont(new Font("serif", fontSize));
        button.setStyle("-fx-border-color: lightgray; -fx-border-width: 4px;");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefSize(width, height);
        button.setVisible(false);
        button.setStyle("-fx-background-color: white;");
        return button;
    }

    protected static Label paintLabel(String text, int fontSize, int x, int y, int width, int height) {
        Font font = new Font("serif", fontSize);
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(font);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        return label;
    }
}
