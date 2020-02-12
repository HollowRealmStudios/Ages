package hollowrealm.studios.game.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class DepthMap {
    private ArrayList<TileDepthWrapper> depthList;
    private TileMap tm;

    public DepthMap(TileMap tm) {
        this.tm = tm;
        depthList = new ArrayList<TileDepthWrapper>();
        for (int x = 0; x < tm.getWidth(); x++) {
            for (int y = 0; y < tm.getDepth(); y++) {
                for (int z = 0; z < tm.getHeight(); z++) {
                    depthList.add(new TileDepthWrapper(tm.get(x, y, z), x, y, z));
                }
            }
        }
    }

    public Stream<TileDepthWrapper> getTiles() {
        return depthList.stream();
    }

    public TileMap getCartesianMap() {
        return this.tm;
    }

}
