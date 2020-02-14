package hollowrealm.studios.game.map.voxels;

import java.awt.image.BufferedImage;

public interface Voxel {

    BufferedImage getTexture();

    boolean isPassable();

    float getHardness();

}
