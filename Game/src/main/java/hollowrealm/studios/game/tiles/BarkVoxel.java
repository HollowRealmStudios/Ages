package hollowrealm.studios.game.tiles;

import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class BarkVoxel extends Voxel {

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
        return 0.3f;
    }
}
