package hollowrealm.studios.game.tiles;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class TileDepthWrapper {
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
}
