package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class GrassVoxel extends Voxel {

	GrassVoxel() {
		super(Engine.storageModule.getImage("Grass.png"), false, 0.1f);
	}
}
