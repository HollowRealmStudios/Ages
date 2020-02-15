package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class DebugVoxel extends Voxel {

    public DebugVoxel() {
        super(Engine.storageModule.getImage("Debug.png"), false, 0f);
    }
}
