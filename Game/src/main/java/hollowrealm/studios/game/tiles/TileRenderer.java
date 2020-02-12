package hollowrealm.studios.game.tiles;

import org.javatuples.Pair;
import org.javatuples.Tuple;
import simple.engine.gui.WidgetPack;
import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class TileRenderer extends Module {
    private DepthMap dm;

    public TileRenderer(GameConfig config) {
        super(config);
        int size = 5;
        TileMap tm = new TileMap(size, size, size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    tm.setTile(new GrassTile(), x, y, z);
                }
            }
        }
        dm = new DepthMap(tm);
    }

    private int toScreenX(TileDepthWrapper t) {
        return t.getX() - t.getY();
    }
    private int toScreenY(TileDepthWrapper t) {
        return t.getY() + t.getX() - t.getZ() * 2;
    }

    public void render(Graphics2D g) {
        dm.getTiles().forEach(e->g.drawImage(e.getTile().getTexture(), toScreenX(e) * 192/3, toScreenY(e) * 192/6, null));
    }
}
