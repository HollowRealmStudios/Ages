package hollowrealm.studios.game.tiles;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class GrassTile implements Tile {

    @Override
    public BufferedImage getTexture() {
        return Engine.storageModule.getImage("Grass.png");
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 0.1f;
    }
}
