package hollowrealm.studios.game.tiles;

import hollowrealm.studios.game.Rhomb;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VoxelMap {

    private final VoxelMapLayer[] voxelMapLayers;
    private final int width;
    private final int height;
    private final int depth;

    public VoxelMap(int width, int depth, int height) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        voxelMapLayers = new VoxelMapLayer[depth];
        for (int i = 0; i < depth; i++) voxelMapLayers[i] = new VoxelMapLayer(width, height);
        voxelMapLayers[0].fill(new GrassVoxel());
    }

    public Voxel[] getStack(int x, int y) {
        Voxel[] voxels = new Voxel[depth];
        for (int i = 0; i < depth; i++) voxels[i] = get(x, y, i);
        return voxels;
    }

    public boolean mustBeRenderer(int x, int y, int z) {
        return get(x, y + 1, z) != null || get(x, y, z + 1) != null;
    }

    public void setTile(Voxel voxel, int x, int y, int z) {
        voxelMapLayers[z].set(x, y, voxel);
    }

    public Voxel get(int x, int y, int z) {
        return voxelMapLayers[z].get(x, y);
    }

    public void paint(Graphics2D g) {
        Arrays.stream(voxelMapLayers).forEach(layer -> {
            boolean offset = false;
            for (int y = 0; y < layer.getHeight(); y++) {
                for (int x = 0; x < layer.getWidth(); x++) {
                    Voxel[] voxels = getStack(x, y);
                    for (int z = 0; z < depth; z++) {
                        if (voxels[z] != null && mustBeRenderer(x, y, z) && get(x, y, z).isDirty()) {
                            g.drawImage(voxels[z].getTexture(), x * 129 + (offset ? 64 : 0), y * 32 - z * 64, null);
                            get(x, y, z).markClean();
                        }
                    }
                }
                offset = !offset;
            }
        });
    }

    public CollisionMap createCollisionMap() {
        ArrayList<Rhomb> rhombs = new ArrayList<>();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    //rhombs.add(new Rhomb(x, y, ))
                }
            }
        }
        return new CollisionMap(rhombs.toArray(new Rhomb[]{}));
    }
}
