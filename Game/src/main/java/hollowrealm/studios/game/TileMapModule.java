package hollowrealm.studios.game;

import hollowrealm.studios.game.tiles.VoxelMap;
import simple.engine.Engine;
import simple.engine.modules.Module;

public class TileMapModule extends Module {

    private final VoxelMap voxelMap;

    public TileMapModule(int width, int depth, int height) {
        super(Engine.getConfig());
        voxelMap = new VoxelMap(width, depth, height);
    }

}
