package hollowrealm.studios.game.tiles;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class VoxelWorld {

    private final DefaultUndirectedGraph<Age, DefaultEdge> worldGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public <T extends Age> void addAge(T age) {
        worldGraph.addVertex(age);
    }

}
