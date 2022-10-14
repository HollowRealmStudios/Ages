package hollowrealm.studios.game.map.voxels;

import com.google.common.annotations.Beta;
import org.reflections.Reflections;

import java.util.HashMap;

public class VoxelRegistry {

	private static final HashMap<Class<? extends Voxel>, Voxel> voxels = new HashMap<>();

	@Beta
	public static void registerAll() {
		Reflections reflections = new Reflections();
		reflections.getSubTypesOf(Voxel.class).forEach(c -> {
			try {
				voxels.put(c, c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	public static void registerVoxel(Class<? extends Voxel> v) {
		if (!voxels.containsKey(v)) {
			try {
				voxels.put(v, v.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static Voxel getInstance(Class<? extends Voxel> v) {
		if (voxels.size() == 0) {
			System.err.println("No voxels were registered before accessing them. Falling back to automatic registration");
			registerAll();
		}
		return voxels.get(v);
	}

}
