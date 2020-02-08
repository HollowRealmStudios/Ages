package hollowrealm.studios.game;

import hollowrealm.studios.game.tiles.TileMap;
import simple.engine.Engine;
import simple.engine.modules.Module;

public class TileMapModule extends Module {

    private final TileMap tileMap;

    public TileMapModule(int width, int depth, int height) {
        super(Engine.getConfig());
        tileMap = new TileMap(width, depth, height);
    }

}
