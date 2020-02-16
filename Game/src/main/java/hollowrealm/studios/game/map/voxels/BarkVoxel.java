package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class BarkVoxel extends Voxel {

    BarkVoxel() {
        super(Engine.storageModule.getImage("TreeStomp.png"), false, 0.2f);
    }

}
