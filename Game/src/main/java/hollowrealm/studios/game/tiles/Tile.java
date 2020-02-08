package hollowrealm.studios.game.tiles;

import java.awt.image.BufferedImage;

public abstract class Tile {

    public abstract BufferedImage getTexture();
    public abstract boolean isPassable();
    public abstract float getHardness();

}
