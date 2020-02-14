package hollowrealm.studios.game;

import simple.engine.Engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player implements Drawable {

    private final BufferedImage[] images = ImageParser.split(Engine.storageModule.getImage("Player.png"));
    private final Point position = new Point(0, 0);
    private float speed = 1;
    private boolean inFluid;

    public Player() {
    }

    public void setInFluid(boolean inFluid) {
        this.inFluid = inFluid;
    }

    public void moveTop() {
        position.y -= speed * (inFluid ? 5 : 2.5f);
    }

    public void moveBottom() {
        position.y += speed * (inFluid ? 5 : 2.5f);
    }

    public void moveLeft() {
        position.x -= speed * (inFluid ? 5 : 2.5f);
    }

    public void moveRight() {
        position.x += speed * (inFluid ? 5 : 2.5f);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(images[0], position.x - images[0].getWidth() / 2, position.y - images[0].getHeight() / 2, null);
    }

    public Point getPosition() {
        return position;
    }
}
