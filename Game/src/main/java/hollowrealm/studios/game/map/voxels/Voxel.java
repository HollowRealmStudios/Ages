package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.ImageParser;

import java.awt.image.BufferedImage;

public abstract class Voxel {

    private final BufferedImage image;
    private final boolean passable;
    private final float hardness;

    public Voxel(BufferedImage image, boolean passable, float hardness) {
        this.image = ImageParser.split(image)[0];
        this.passable = passable;
        this.hardness = hardness;
    }

    public BufferedImage getTexture() {
        return image;
    }

    public boolean isPassable() {
        return passable;
    }

    public float getHardness() {
        return hardness;
    }
}
