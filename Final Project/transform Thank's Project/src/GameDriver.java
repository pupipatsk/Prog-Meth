import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameDriver {
    private int level = 1;

    private Stage stage;
    private Pane root;

    private Button start;
    private Button reset;
    private Button[] targets = new Button[32];

    private Label score;
    private Label yourScore;
    private int point;

    private Label correct;
    private Label incorrect;
    private Label gameOver;
    private Label lastPoint;

    private Timeline timeline;
    private Logic logic;

    public void initialize(Stage primaryStage) {
        logic = new Logic(this);
        stage = primaryStage;
        stage.setTitle("Game");

        root = new Pane();
        root.setStyle("-fx-background-color: darkgray;");
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        start = Painter.paintButton("Start", 72, 490, 260, 300, 200);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameLevel(1);
                start.setVisible(false);
            }
        });
        root.getChildren().add(start);

        reset = Painter.paintButton("Reset", 40, 540, 625, 200, 50);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = 1;
                startScreen();
                resetTargets();
            }
        });
        reset.setVisible(false);
        root.getChildren().add(reset);

        score = Painter.paintLabel(" Score :", 50, 10, 600, 200, 100);
        yourScore = Painter.paintLabel("0", 50, 220, 605, 200, 100);
        correct = Painter.paintLabel(" correct !! ", 100, 460, 250, 800, 200);
        incorrect = Painter.paintLabel(" incorrect ;-; ", 75, 430, 60, 800, 200);
        gameOver = Painter.paintLabel("GAMEOVER", 100, 330, 200, 800, 200);
        lastPoint = Painter.paintLabel(" Last Point ", 75, 460, 350, 800, 200);

        root.getChildren().addAll(score, yourScore, correct, incorrect, gameOver, lastPoint);

        startScreen();

        stage.show();
    }

    protected void startScreen() {
        start.setVisible(true);
        reset.setVisible(false);
        score.setVisible(false);
        yourScore.setVisible(false);
        point = 0;
        yourScore.setText(Integer.toString(point));
        correct.setVisible(false);
        incorrect.setVisible(false);
        gameOver.setVisible(false);
        lastPoint.setVisible(false);
    }

    protected void gameLevel(int level) {
        correct.setVisible(false);
        incorrect.setVisible(false);
        score.setVisible(true);
        yourScore.setVisible(true);
        Target[] target = logic.generateLevel(32, 3 + level);
        int topGap = 40;
        int leftGap = 140;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                int posY = topGap + i * 125;
                int posX = leftGap + j * 125;
                int index = (8 * i) + j;
                Target data = target[index];
                if (!data.isInUse()) {
                    targets[index] = Painter.paintButton(null, 0, 0, 0, 0, 0);
                    continue;
                }
                targets[index] = Painter.paintButton(null, 0, posX + data.getxPos(), posY + data.getyPos(),
                        data.getDiameter(), data.getDiameter());
                root.getChildren().add(targets[index]);
                targets[index].setStyle("-fx-background-color: " + toRGBCode(data.getFirstColor()) + ";");
                targets[index].setVisible(true);
            }
        }
        reset.setVisible(true);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(2000), event -> resetTargets()),
                new KeyFrame(Duration.millis(3000), event -> {
                    reset.setVisible(true);
                    for (int i = 0; i < 32; i++) {
                        Target data = target[i];
                        if (data.isInUse()) {
                            targets[i].setVisible(true);
                            if (data.isCorrect()) {
                                targets[i].setStyle("-fx-background-color: " + toRGBCode(data.getSecondColor()) + ";");
                                targets[i].setOnAction(logic.new CorrectTarget());
                            } else {
                                targets[i].setOnAction(logic.new IncorrectTarget());
                            }
                        }
                        targets[i].setVisible(true);
                    }
                })
        );
        timeline.play();
    }

    protected void correctScreen() {
        yourScore.setText(Integer.toString(++point));
        correct.setVisible(true);
        reset.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(1500), event -> {
            level++;
            gameLevel(level);
        }));
        timeline.play();
    }

    protected void incorrectScreen() {
        incorrect.setVisible(true);
        gameOver.setVisible(true);
        lastPoint.setText("SCORE : " + Integer.toString(point));
        lastPoint.setVisible(true);
        score.setVisible(false);
        yourScore.setVisible(false);
    }

    protected void resetTargets() {
        for (Button button : targets) {
            button.setVisible(false);
        }
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
