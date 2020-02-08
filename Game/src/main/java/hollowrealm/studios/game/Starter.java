package hollowrealm.studios.game;

import hollowrealm.studios.game.tiles.GrassTile;
import hollowrealm.studios.game.tiles.TestTile;
import hollowrealm.studios.game.tiles.TileMap;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;

import java.awt.*;
import java.util.ArrayList;

public class Starter {

    private static final ArrayList<Rhomb> rhombs = new ArrayList<>();
    private static final TileMap map = new TileMap(10, 10, 10);

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        map.setTile(new GrassTile(), 0, 0, 0);
        map.setTile(new GrassTile(), 1, 0, 0);
        map.setTile(new TestTile(), 0, 1, 0);
        map.setTile(new TestTile(), 0, 0, 1);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                map.paint(graphics2D);
            }
        }, 0);
    }
}
