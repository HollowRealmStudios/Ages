package hollowrealm.studios.game.tiles;

import hollowrealm.studios.game.Rhomb;

import java.awt.*;
import java.util.ArrayList;

public class TileMap {

    private final Tile[][][] tiles;
    private final int width;
    private final int height;
    private final int depth;

    public TileMap(int width, int depth, int height) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        tiles = new Tile[width][depth][height];
    }

    public void setTile(Tile tile, int x, int y, int z) {
        tiles[x][y][z] = tile;
    }

    public Tile get(int x, int y, int z) {
        return tiles[x][y][z];
    }

    public void paint(Graphics2D g) {
        boolean offset = false;
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    if (get(x, y, z) != null)
                        g.drawImage(get(x, y, z).getTexture(), x * 129 + (offset ? 64 : 0), y * 32 - z * 32, null);
                }
            }
            offset = !offset;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public CollisionMap createCollisionMap() {
        ArrayList<Rhomb> rhombs = new ArrayList<>();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    //rhombs.add(new Rhomb(x, y, ))
                }
            }
        }
        return new CollisionMap(rhombs.toArray(new Rhomb[]{}));
    }
}
