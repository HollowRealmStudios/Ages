package hollowrealm.studios.game.map.tiles;

import java.awt.image.BufferedImage;

public interface Voxel {

    BufferedImage getTexture();

    boolean isPassable();

    float getHardness();

}
