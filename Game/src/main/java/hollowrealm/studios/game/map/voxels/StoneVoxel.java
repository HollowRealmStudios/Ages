package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

public class StoneVoxel extends Voxel {

	StoneVoxel() {
		super(Engine.storageModule.getImage("Stone.png"), false, 0.4f);
	}
}
