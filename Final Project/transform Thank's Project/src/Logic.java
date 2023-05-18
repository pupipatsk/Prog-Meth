import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Logic {
    private GameDriver gameDriver;

    public Logic(GameDriver gameDriver) {
        this.gameDriver = gameDriver;
    }

    protected class CorrectTarget implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            gameDriver.resetTargets();
            gameDriver.correctScreen();
        }
    }

    protected class IncorrectTarget implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            gameDriver.resetTargets();
            gameDriver.incorrectScreen();
        }
    }

    private ArrayList<Integer> ncr(int n, int r) {
        ArrayList<Integer> aryl1 = new ArrayList<>();
        ArrayList<Integer> aryl2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            aryl1.add(i);
        }
        Random rand = new Random();
        for (int i = 0; i < r; i++) {
            int x = rand.nextInt(aryl1.size());
            aryl2.add(aryl1.get(x));
            aryl1.remove(x);
        }
        return aryl2;
    }

    protected Target[] generateLevel(int total, int count) {
        count = Integer.min(total, count);
        Target[] ary = new Target[total];
        for (int i = 0; i < total; i++) {
            ary[i] = new Target(125, 50);
        }
        ArrayList<Integer> chosenPosition = ncr(total, count);
        ary[chosenPosition.get(0)].setInUse(true);
        ary[chosenPosition.get(0)].setCorrect(true);
        chosenPosition.remove(0);
        for (int i : chosenPosition) {
            ary[i].setInUse(true);
        }
        return ary;
    }
}
