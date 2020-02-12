package hollowrealm.studios.game.tiles;

import java.awt.image.BufferedImage;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class TileDepthWrapper implements Tile {
    private Tile t;
    private int x, y, z;

    public TileDepthWrapper(Tile t, int x, int y, int z) {
        this.t = t;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Tile getTile() {
        return t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public BufferedImage getTexture() {
        return t.getTexture();
    }

    @Override
    public boolean isPassable() {
        return t.isPassable();
    }

    @Override
    public float getHardness() {
        return t.getHardness();
    }
}
