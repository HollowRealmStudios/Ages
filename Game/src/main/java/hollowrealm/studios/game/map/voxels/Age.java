package hollowrealm.studios.game.map.voxels;

import hollowrealm.studios.game.map.VoxelMap;

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
        for (int z = 0; z < getVoxelMap().getHeight(); z++) {
            for (int y = 0; y < getVoxelMap().getDepth(); y++) {
                for (int x = 0; x < getVoxelMap().getWidth(); x++) {
                    if (getVoxelMap().getVoxel(x, y, z) == null) getVoxelMap().setVoxel(new AirVoxel(), x, y, z);
                }
            }
        }
    }

    public abstract void generateWorld();

}
