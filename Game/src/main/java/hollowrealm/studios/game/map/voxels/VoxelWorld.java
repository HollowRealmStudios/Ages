package hollowrealm.studios.game.map.voxels;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class VoxelWorld {

    private final DefaultUndirectedGraph<Class<? extends Age>, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public void registerAge(Class<? extends Age> age, Class<? extends Age> after) {
        graph.addVertex(age);
        graph.addEdge(age, after);
    }

}
