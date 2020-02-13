package hollowrealm.studios.game.tiles;

import java.awt.*;

public class TileMapRenderer {

    private final int tileSize;
    private VoxelMap voxelMap;

    public TileMapRenderer(int tileSize, VoxelMap voxelMap) {
        this.tileSize = tileSize;
        this.voxelMap = voxelMap;
    }

    public void paint(Graphics2D g) {

    }
}
