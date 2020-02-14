package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.ImageParser;
import simple.engine.Engine;

import java.awt.image.BufferedImage;

public class TreeStompVoxel implements Voxel {

    BufferedImage texture = ImageParser.split(Engine.storageModule.getImage("TreeStomp.png"))[0];

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
        return 1f;
    }
}
