package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class BarkVoxel extends Voxel {

    BarkVoxel() {
        super(Engine.storageModule.getImage("TreeStomp.png"), false, 0.2f);
    }

}
