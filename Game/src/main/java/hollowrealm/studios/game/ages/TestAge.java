package hollowrealm.studios.game.ages;

import hollowrealm.studios.game.map.voxels.*;

public class TestAge extends Age {

    public TestAge() {
        super(10, 10, 10);
    }

    @Override
    public void generateWorld() {
        voxelMap.fillLayer(VoxelRegistry.getInstance(StoneVoxel.class), 0);
        voxelMap.fillLayer(VoxelRegistry.getInstance(StoneVoxel.class), 1);
        voxelMap.fillLayer(VoxelRegistry.getInstance(StoneVoxel.class), 2);
        voxelMap.fillLayer(VoxelRegistry.getInstance(DirtVoxel.class), 3);
        voxelMap.fillLayer(VoxelRegistry.getInstance(DirtVoxel.class), 4);
        voxelMap.fillLayer(VoxelRegistry.getInstance(GrassVoxel.class), 5);
        voxelMap.setVoxel(VoxelRegistry.getInstance(TreeStompVoxel.class), 0, 0, 6);
    }
}
