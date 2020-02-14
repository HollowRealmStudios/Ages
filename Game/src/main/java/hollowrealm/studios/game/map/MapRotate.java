package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

//http://blog.icodejava.com/1833/matrix-2d-array-clockwise-and-counterclockwise-rotation-with-extra-buffer-java-implementation/
public class MapRotate {

    public static Voxel[][][] rotateCW(Voxel[][][] voxels) {
        for (int i = 0; i < voxels.length; i++) {
            voxels[i] = rotateCW(voxels[i]);
        }
        return voxels;
    }

    public static Voxel[][][] rotateCCW(Voxel[][][] voxels) {
        for (int i = 0; i < voxels.length; i++) {
            voxels[i] = rotateCCW(voxels[i]);
        }
        return voxels;
    }

    public static Voxel[][] rotateCW(Voxel[][] matrix) {
        Voxel[][] rotated = new Voxel[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                rotated[i][j] = matrix[matrix.length - j - 1][i];
            }
        }
        return rotated;
    }

    public static Voxel[][] rotateCCW(Voxel[][] matrix) {
        Voxel[][] rotated = new Voxel[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                rotated[i][j] = matrix[j][matrix[0].length - i - 1];
            }
        }
        return rotated;
    }
}
