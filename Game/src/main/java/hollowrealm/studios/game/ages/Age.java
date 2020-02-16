package hollowrealm.studios.game.ages;

import hollowrealm.studios.game.map.VoxelMap;
import hollowrealm.studios.game.map.voxels.AirVoxel;
import hollowrealm.studios.game.map.voxels.VoxelRegistry;

public abstract class Age {

    protected final VoxelMap voxelMap;

    protected Age(int width, int depth, int height) {
        voxelMap = new VoxelMap(width, depth, height);
        generateWorld();
        fill();
    }

    public VoxelMap getVoxelMap() {
        return voxelMap;
    }

    private void fill() {
        for (int y = 0; y < getVoxelMap().getHeight(); y++) {
            for (int z = 0; z < getVoxelMap().getDepth(); z++) {
                for (int x = 0; x < getVoxelMap().getWidth(); x++) {
                    System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));
                    if (getVoxelMap().getVoxel(x, y, z) == null)
                        getVoxelMap().setVoxel(VoxelRegistry.getInstance(AirVoxel.class), x, y, z);
                }
            }
        }
    }

    public abstract void generateWorld();

}
