package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class DirtVoxel extends Voxel {

    public DirtVoxel() {
        super(Engine.storageModule.getImage("Dirt.png"), false, 0.25f);
    }

}
