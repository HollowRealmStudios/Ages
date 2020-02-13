package hollowrealm.studios.game.tiles;

import hollowrealm.studios.game.Rhomb;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TileMap implements Tile {

    private final Tile[][][] tiles;
    private final int width;
    private final int height;
    private final int depth;

    private final ArrayList<TileDepthWrapper> depthList;

    public TileMap(int width, int depth, int height) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        tiles = new Tile[width][depth][height];
        depthList = new ArrayList<>();
    }

    private void fillDepthList() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < depth; y++) {
                for (int z = 0; z < height; z++) {
                    Tile t = get(x, y, z);
                    if (t != null) {
                        TileDepthWrapper tdw = new TileDepthWrapper(t, x, y, z);
                        if (!depthList.contains(tdw)) {
                            depthList.add(tdw);
                        }
                    }
                }
            }
        }
    }

    public void setTile(Tile tile, int x, int y, int z) {
        tiles[x][y][z] = tile;
        fillDepthList();
    }

    public Tile get(int x, int y, int z) {
        return tiles[x][y][z];
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

    public Stream<TileDepthWrapper> getTiles() {
        return depthList.stream();
    }

    private int toScreenX(TileDepthWrapper t) {
        return t.getX() - t.getY();
    }

    private int toScreenY(TileDepthWrapper t) {
        return t.getY() + t.getX() - t.getZ() * 2;
    }

    @Override
    public BufferedImage getTexture() {
        int wt = depthList.get(0).getTexture().getWidth();
        int ht = depthList.get(0).getTexture().getHeight();
        int w = (width / 2 + depth / 2) * wt;
        int h = ((width / 2 + depth / 2) / 2 + height / 2) * ht;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        getTiles().forEach(e -> g.drawImage(e.getTexture(), toScreenX(e) * wt / 2 + w / 2, toScreenY(e) * ht / 4 + h / 2, wt, ht, null));
        return bi;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 0;
    }
}
