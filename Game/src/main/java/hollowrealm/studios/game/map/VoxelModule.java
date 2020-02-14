package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.AirVoxel;
import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

import java.awt.*;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class VoxelModule extends Module {

    private final VoxelMap voxelMap;

    public VoxelModule(GameConfig config) {
        super(config);
        int size = 5;
        voxelMap = new VoxelMap(size, size, size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    voxelMap.setVoxel(new AirVoxel(), x, y, z);
                }
            }
        }
    }

    public VoxelMap getMap() {
        return voxelMap;
    }

    public void render(Graphics2D g) {
        g.drawImage(voxelMap.getTexture(), 0, 0, config.getWidth(), config.getHeight(), null);
    }
}
