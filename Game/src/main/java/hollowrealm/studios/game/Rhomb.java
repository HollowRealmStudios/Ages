package hollowrealm.studios.game;

import java.awt.*;

public class Rhomb {

    private static final int INF = 10000;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private Color color = Color.WHITE;

    public Rhomb(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.drawPolygon(new int[]{x + width / 2, x + width, x + width / 2, x}, new int[]{y, y + height / 2, y + height, y + height / 2}, 4);
    }

    public boolean contains(int x, int y) {
        /*return isInside(new Point[]{
                new Point(0, (int) (y + height / 2)),
                new Point((int) (x + width / 2), 0),
                new Point((int) x + width, (int) (y + height / 2)),
                new Point((int) (x + width / 2), (int) y + height),
        }, 4, new Point((int) x, (int) y));*/
        return isInside(new Point[]{
                new Point(this.x + width / 2, this.y),
                new Point(this.x + width, this.y + height / 2),
                new Point(this.x + width / 2, this.y + height),
                new Point(this.x, this.y + height / 2),
        }, 4, new Point(x, y));
    }

    private boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y);
    }

    private int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) {
            return 0;
        }
        return (val > 0) ? 1 : 2;
    }

    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }
        return o4 == 0 && onSegment(p2, q1, q2);
    }

    private boolean isInside(Point[] polygon, int n, Point p) {
        if (n < 3) {
            return false;
        }
        Point extreme = new Point(INF, p.y);
        int count = 0, i = 0;
        int next = (i + 1) % n;
        if (doIntersect(polygon[i], polygon[next], p, extreme)) {
            if (orientation(polygon[i], p, polygon[next]) == 0) {
                return onSegment(polygon[i], p,
                        polygon[next]);
            }
            count++;
        }
        i = next;
        while (i != 0) {
            next = (i + 1) % n;
            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    return onSegment(polygon[i], p,
                            polygon[next]);
                }
                count++;
            }
            i = next;
        }
        return (count % 2 == 1);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
