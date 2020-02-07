package hollowrealm.studios.game;

import java.awt.*;

public class TileShape {

    private final GFG.Point[] points;

    private Color color = Color.WHITE;

    public TileShape(GFG.Point... points) {
        this.points = points;
    }

    public GFG.Point[] getPoints() {
        return points;
    }

    public int getPointAmount() {
        return points.length;
    }

    public int[] getXCoordinates() {
        int[] xs = new int[getPointAmount()];
        for (int i = 0; i < getPointAmount(); i++) {
            xs[i] = points[i].x;
        }
        return xs;
    }

    public int[] getYCoordinates() {
        int[] ys = new int[getPointAmount()];
        for (int i = 0; i < getPointAmount(); i++) {
            ys[i] = points[i].y;
        }
        return ys;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
