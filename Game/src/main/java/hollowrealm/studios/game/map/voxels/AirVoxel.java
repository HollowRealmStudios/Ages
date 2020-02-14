package hollowrealm.studios.game.map.voxels;

import java.awt.image.BufferedImage;

public class AirVoxel implements Voxel {

    @Override
    public BufferedImage getTexture() {
        return new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public float getHardness() {
        return 0;
    }
}