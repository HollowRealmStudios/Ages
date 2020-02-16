package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

public class VoxelLayer {

    private Voxel[][] voxels;
    private final int width;
    private final int depth;

    public VoxelLayer(int width, int depth) {
        this.width = width;
        this.depth = depth;
        voxels = new Voxel[width][depth];
    }

    public Voxel get(int x, int z) {
        return voxels[x][z];
    }

    public void setVoxel(Voxel v, int x, int z) {
        voxels[x][z] = v;
    }

    public Voxel[][] getVoxels() {
        return voxels;
    }

    public void fill(Voxel v) {
        for(int z = 0; z < depth; z++) {
            for(int x = 0; x < width; x++) {
                voxels[x][z] = v;
            }
        }
    }

    public void setVoxels(Voxel[][] voxels) {
        this.voxels = voxels;
    }
}
