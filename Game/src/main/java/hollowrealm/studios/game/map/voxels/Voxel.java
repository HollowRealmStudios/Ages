package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.ImageParser;

import java.awt.image.BufferedImage;

public abstract class Voxel {

    private final BufferedImage[] opaque;
    private final BufferedImage[] transparent;
    private final boolean passable;
    private final float hardness;

    protected Voxel(BufferedImage image, boolean passable, float hardness) {
        opaque = ImageParser.split(image);
        transparent = ImageParser.split(ImageParser.transparize(image));
        this.passable = passable;
        this.hardness = hardness;
    }

    public BufferedImage getTexture(int rotation, boolean transparent) {
        return transparent ? this.transparent[rotation] : opaque[rotation];
    }

    public boolean isPassable() {
        return passable;
    }

    public float getHardness() {
        return hardness;
    }
}
