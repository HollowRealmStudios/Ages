package hollowrealm.studios.game.tiles;

import java.awt.image.BufferedImage;

public interface Tile {

    BufferedImage getTexture();
    boolean isPassable();
    float getHardness();

}
