package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VoxelMap implements Voxel {

    private final int width;
    private final int height;
    private final int depth;
    private Voxel[][][] voxels;

    public VoxelMap(int width, int depth, int height) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        voxels = new Voxel[width][depth][height];
    }

    public void fillLayer(Voxel voxel, int z) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setVoxel(voxel, x, y, z);
            }
        }
    }

    public Voxel[][][] getVoxels() {
        return voxels;
    }

    public void setVoxels(Voxel[][][] voxels) {
        this.voxels = voxels;
    }

    public void setVoxel(Voxel voxel, int x, int y, int z) {
        voxels[z][y][x] = voxel;
    }

    public Voxel getVoxel(int x, int y, int z) {
        return voxels[z][y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    private int toScreenX(int x, int y) {
        return x - y;
    }

    private int toScreenY(int x, int y, int z) {
        return y + x - z * 2;
    }

    @Override
    public BufferedImage getTexture() {
        int w = (width / 2 + depth / 2) * 128;
        int h = ((width / 2 + depth / 2) / 2 + height / 2) * 128;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    Voxel v = getVoxel(x, y, z);
                    g.drawImage(v.getTexture(), toScreenX(x, y) * 128 / 2 + w / 2, toScreenY(x, y, z) * 128 / 4 + h / 2, 128, 128, null);
                }
            }
        }
        return bi;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public float getHardness() {
        return 0;
    }
}
