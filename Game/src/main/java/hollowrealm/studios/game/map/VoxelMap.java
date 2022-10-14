package hollowrealm.studios.game.map;

import hollowrealm.studios.game.map.voxels.Voxel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VoxelMap {

	private final int width;
	private final int height;
	private final int depth;
	private int rotation;
	private Voxel[][][] voxels;

	public VoxelMap(int width, int depth, int height) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		voxels = new Voxel[width][depth][height];
	}

	public void rotateCCW() {
		for (int i = 0; i < height; i++) {
			voxels[i] = rotateCCW(voxels[i]);
		}
		if (rotation == 0) rotation = 3;
		else rotation--;
	}

	public void rotateCW() {
		for (int i = 0; i < height; i++) {
			voxels[i] = rotateCW(voxels[i]);
		}
		if (rotation == 3) rotation = 0;
		else rotation++;
	}

	private Voxel[][] rotateCW(Voxel[][] matrix) {
		Voxel[][] rotated = new Voxel[matrix[0].length][matrix.length];
		for (int i = 0; i < matrix[0].length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				rotated[i][j] = matrix[matrix.length - j - 1][i];
			}
		}
		return rotated;
	}

	private Voxel[][] rotateCCW(Voxel[][] matrix) {
		Voxel[][] rotated = new Voxel[matrix[0].length][matrix.length];
		for (int i = 0; i < matrix[0].length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				rotated[i][j] = matrix[j][matrix[0].length - i - 1];
			}
		}
		return rotated;
	}

	public void fillLayer(Voxel voxel, int z) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < depth; y++) {
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

	public BufferedImage getTexture() {
		int w = (width / 2 + depth / 2) * 128;
		int h = ((width / 2 + depth / 2) / 2 + height / 2) * 128;
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		for (int z = 0; z < height; z++) {
			for (int y = 0; y < depth; y++) {
				for (int x = 0; x < width; x++) {
					Voxel v = getVoxel(x, y, z);
					g.drawImage(v.getTexture(rotation, false), toScreenX(x, y) * 128 / 2 + w / 2, toScreenY(x, y, z) * 128 / 4 + h / 2, 128, 128, null);
				}
			}
		}
		return bi;
	}
}
