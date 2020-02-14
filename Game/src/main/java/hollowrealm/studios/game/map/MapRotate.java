package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

public class MapRotate {

    public static void rotateCW(Voxel[][] voxels) {
        final int height = voxels.length;
        final int width = voxels[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                voxels[x][height - 1 - y] = voxels[y][x];
            }
        }
    }

    public static void rotateCCW(int[][] mat) {
        int width = mat.length;
        for (int x = 0; x < width / 2; x++) {
            for (int y = x; y < width - x - 1; y++) {
                int temp = mat[x][y];
                mat[x][y] = mat[y][width - 1 - x];
                mat[y][width - 1 - x] = mat[width - 1 - x][width - 1 - y];
                mat[width - 1 - x][width - 1 - y] = mat[width - 1 - y][x];
                mat[width - 1 - y][x] = temp;
            }
        }
    }
}
