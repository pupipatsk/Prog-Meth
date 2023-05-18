import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class Target {
    private int diameter = 0;
    private int xPos = 0;
    private int yPos = 0;
    private boolean isCorrect = false;
    private boolean inUse = false;
    private Color firstColor = Color.BLACK;
    private Color secondColor = Color.WHITE;

    public Target(int diameter, int minimumSize) {
        Random rand = new Random();
        this.setDiameter(rand.nextInt(diameter - minimumSize) + minimumSize);
        this.setxPos(rand.nextInt((diameter - this.getDiameter())));
        this.setyPos(rand.nextInt((diameter - this.getDiameter())));
        this.setCorrect(false);
        this.setInUse(false);
        Color[] color = new Color[2];
        color = randColor();
        this.setFirstColor(color[0]);
        this.setSecondColor(color[1]);
    }

    private Color[] randColor() {
        Color[] ary = {
                Color.MAGENTA,
                Color.CYAN,
                Color.GREEN,
                Color.YELLOW,
                Color.RED
        };
        ArrayList<Color> aryl = new ArrayList<>();
        for (Color i : ary) {
            aryl.add(i);
        }
        Color[] ans = new Color[2];
        Random rand = new Random();
        int x = rand.nextInt(aryl.size());
        ans[0] = aryl.get(x);
        aryl.remove(x);
        x = rand.nextInt(aryl.size());
        ans[1] = aryl.get(x);
        return ans;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
    }
}
