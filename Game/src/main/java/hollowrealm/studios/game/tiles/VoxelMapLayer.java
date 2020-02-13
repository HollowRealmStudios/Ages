package hollowrealm.studios.game.tiles;

public class VoxelMapLayer {

    private final Voxel[][] voxels;
    private final int width;
    private final int height;

    public VoxelMapLayer(int width, int height) {
        this.width = width;
        this.height = height;
        voxels = new Voxel[width][height];
    }

    public void fill(Voxel voxel) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                voxels[x][y] = voxel;
            }
        }
    }

    public void set(int x, int y, Voxel voxel) {
        voxels[x][y] = voxel;
    }

    public Voxel get(int x, int y) {
        return voxels[x][y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
