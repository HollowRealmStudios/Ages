package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.tiles.Voxel;

import java.awt.image.BufferedImage;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class TileDepthWrapper implements Voxel {
    private final Voxel t;
    private final int x;
    private final int y;
    private final int z;

    public TileDepthWrapper(Voxel t, int x, int y, int z) {
        this.t = t;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Voxel getTile() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileDepthWrapper that = (TileDepthWrapper) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        if (z != that.z) return false;
        return t.equals(that.t);
    }

    @Override
    public int hashCode() {
        int result = t.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public float getHardness() {
        return t.getHardness();
    }
}
