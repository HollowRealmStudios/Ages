package hollowrealm.studios.game.ages;

import hollowrealm.studios.game.map.voxels.*;

public class TestAge extends Age {

    public TestAge() {
        super(10, 10, 10);
    }

    @Override
    public void generateWorld() {
        voxelMap.fillLayer(new StoneVoxel(), 0);
        voxelMap.fillLayer(new StoneVoxel(), 1);
        voxelMap.fillLayer(new StoneVoxel(), 2);
        voxelMap.fillLayer(new DirtVoxel(), 3);
        voxelMap.fillLayer(new DirtVoxel(), 4);
        voxelMap.fillLayer(new GrassVoxel(), 5);
        voxelMap.setVoxel(new TreeStompVoxel(), 0, 0, 6);
    }
}
