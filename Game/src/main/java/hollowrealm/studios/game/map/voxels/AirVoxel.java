package hollowrealm.studios.game.map.voxels;

import java.awt.image.BufferedImage;

public class AirVoxel extends Voxel {

    AirVoxel() {
        super(new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB), true, 0f);
    }
}
