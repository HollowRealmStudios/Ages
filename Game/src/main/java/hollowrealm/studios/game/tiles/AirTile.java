package hollowrealm.studios.game.tiles;

import java.awt.image.BufferedImage;

public class AirTile implements Tile {

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
