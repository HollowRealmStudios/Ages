package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

import java.util.Arrays;

public class MapRotate {

    public static Voxel[][][] rotateCW(Voxel[][][] voxels) {
        Arrays.stream(voxels).forEach(MapRotate::rotateCW);
        return voxels;
    }

    public static void rotateCW(Voxel[][] voxels) {
        final int height = voxels.length;
        final int width = voxels[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                voxels[x][height - 1 - y] = voxels[y][x];
            }
        }
    }

    public static Voxel[][][] rotateCCW(Voxel[][][] voxels) {
        Arrays.stream(voxels).forEach(MapRotate::rotateCCW);
        return voxels;
    }

    public static void rotateCCW(Voxel[][] voxels) {
        int width = voxels.length;
        for (int x = 0; x < width / 2; x++) {
            for (int y = x; y < width - x - 1; y++) {
                Voxel temp = voxels[x][y];
                voxels[x][y] = voxels[y][width - 1 - x];
                voxels[y][width - 1 - x] = voxels[width - 1 - x][width - 1 - y];
                voxels[width - 1 - x][width - 1 - y] = voxels[width - 1 - y][x];
                voxels[width - 1 - y][x] = temp;
            }
        }
    }
}
