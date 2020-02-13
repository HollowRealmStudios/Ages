package hollowrealm.studios.game.tiles;

import java.awt.image.BufferedImage;

public abstract class Voxel {

    private boolean dirty = true;

    public abstract BufferedImage getTexture();

    public abstract boolean isPassable();

    public abstract float getHardness();

    public boolean isDirty() {
        return dirty;
    }

    public void markClean() {
        dirty = false;
    }

    public void markDirty() {
        dirty = true;
    }
}
