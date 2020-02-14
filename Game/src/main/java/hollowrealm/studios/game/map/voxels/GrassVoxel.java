package hollowrealm.studios.game.map.voxels;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class GrassVoxel implements Voxel {

    @Override
    public BufferedImage getTexture() {
        return Engine.storageModule.getImage("Grass.png");
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 0.1f;
    }
}
