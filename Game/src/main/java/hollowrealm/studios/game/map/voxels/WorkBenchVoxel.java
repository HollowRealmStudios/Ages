package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.ImageParser;
import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class WorkBenchVoxel implements Voxel {

    private final BufferedImage texture = ImageParser.split(Engine.storageModule.getImage("WorkBench.png"))[0];

    @Override
    public BufferedImage getTexture() {
        return texture;
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
