package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class WorkBenchVoxel extends Voxel {

    WorkBenchVoxel() {
        super(Engine.storageModule.getImage("WorkBench.png"), false, 0.45f);
    }
}
