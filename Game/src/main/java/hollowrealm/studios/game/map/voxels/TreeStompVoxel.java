package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class TreeStompVoxel implements Voxel {

    @Override
    public BufferedImage getTexture() {
        return Engine.storageModule.getImage("TreeStomp.png");
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 1f;
    }
}
