package hollowrealm.studios.game.tiles;

import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

import java.awt.*;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class TileRenderer extends Module {

    private final TileMap tm;

    public TileRenderer(GameConfig config) {
        super(config);
        int size = 5;
        tm = new TileMap(size, size, size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    tm.setTile(new AirTile(), x, y, z);
                }
            }
        }/*
        TileMap tm2 = new TileMap(size, size, size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    tm2.setTile(new GrassTile(), x, y, z);
                }
            }
        }
        tm.setTile(tm2, size - 1, size - 1, size - 1);
    */
    }

    public void setTile(Tile tile, int x, int y, int z) {
        tm.setTile(tile, x, y, z);
    }

    public void render(Graphics2D g) {
        g.drawImage(tm.getTexture(), 0, 0, config.getWidth(), config.getHeight(), null);
    }
}
