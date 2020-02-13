package hollowrealm.studios.game.map;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class DepthMap {
    private final ArrayList<TileDepthWrapper> depthList;
    private final VoxelMap tm;

    public DepthMap(VoxelMap tm) {
        this.tm = tm;
        depthList = new ArrayList<>();
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

    public VoxelMap getCartesianMap() {
        return this.tm;
    }

}
