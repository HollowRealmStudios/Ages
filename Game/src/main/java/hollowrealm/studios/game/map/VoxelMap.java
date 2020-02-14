package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class VoxelMap implements Voxel {

    private final int width;
    private final int height;
    private final int depth;
    private final CopyOnWriteArrayList<VoxelDepthWrapper> depthList;
    private Voxel[][][] voxels;

    public VoxelMap(int width, int depth, int height) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        voxels = new Voxel[width][depth][height];
        depthList = new CopyOnWriteArrayList<>();
    }

    private void fillDepthList() {
        depthList.clear();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < depth; y++) {
                for (int z = 0; z < height; z++) {
                    Voxel t = get(x, y, z);
                    if (t != null) {
                        VoxelDepthWrapper tdw = new VoxelDepthWrapper(t, x, y, z);
                        if (!depthList.contains(tdw)) {
                            depthList.add(tdw);
                        }
                    }
                }
            }
        }
    }

    public void fillLayer(Voxel voxel, int z) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setVoxel(voxel, x, y, z);
            }
        }
        fillDepthList();
    }

    public Voxel[][][] getVoxels() {
        return voxels;
    }

    public void setVoxels(Voxel[][][] voxels) {
        this.voxels = voxels;
        fillDepthList();
    }

    public void setVoxel(Voxel voxel, int x, int y, int z) {
        voxels[x][y][z] = voxel;
        fillDepthList();
    }

    public Voxel get(int x, int y, int z) {
        return voxels[x][y][z];
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

    private Stream<VoxelDepthWrapper> streamVoxels() {
        return depthList.stream();
    }

    private int toScreenX(VoxelDepthWrapper t) {
        return t.getX() - t.getY();
    }

    private int toScreenY(VoxelDepthWrapper t) {
        return t.getY() + t.getX() - t.getZ() * 2;
    }

    @Override
    public BufferedImage getTexture() {
        int wt = depthList.get(0).getTexture().getWidth();
        int ht = depthList.get(0).getTexture().getHeight();
        int w = (width / 2 + depth / 2) * wt;
        int h = ((width / 2 + depth / 2) / 2 + height / 2) * ht;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        streamVoxels().forEach(e -> g.drawImage(e.getTexture(), toScreenX(e) * wt / 2 + w / 2, toScreenY(e) * ht / 4 + h / 2, wt, ht, null));
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
