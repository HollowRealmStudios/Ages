package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.tiles.AirVoxel;
import hollowrealm.studios.game.map.tiles.Voxel;
import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

import java.awt.*;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class VoxelRenderer extends Module {

    private final VoxelMap tm;

    public VoxelRenderer(GameConfig config) {
        super(config);
        int size = 5;
        tm = new VoxelMap(size, size, size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    tm.setTile(new AirVoxel(), x, y, z);
                }
            }
        }
    }

    public void setVoxel(Voxel tile, int x, int y, int z) {
        tm.setTile(tile, x, y, z);
    }

    public void render(Graphics2D g) {
        g.drawImage(tm.getTexture(), 0, 0, config.getWidth(), config.getHeight(), null);
    }
}
