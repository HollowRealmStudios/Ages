package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.ImageParser;
import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class TreeStompVoxel extends Voxel {

    public TreeStompVoxel() {
        super(Engine.storageModule.getImage("TreeStomp.png"), false, 0.7f);
    }

}
