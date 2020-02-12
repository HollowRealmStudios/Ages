package hollowrealm.studios.game.tiles;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class TestTile implements Tile {

    @Override
    public BufferedImage getTexture() {
        return Engine.storageModule.getImage("Pyramid.png");
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 1f;
    }
}
