package hollowrealm.studios.game.map;

import java.util.Arrays;

public class MapRotator {

    private static int[][] rotateCW(int[][] mat) {
        final int height = mat.length;
        final int width = mat[0].length;
        int[][] rotated = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotated[x][height - 1 - y] = mat[y][x];
            }
        }
        return rotated;
    }

    private static void rotateMatrix(int[][] mat) {
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
