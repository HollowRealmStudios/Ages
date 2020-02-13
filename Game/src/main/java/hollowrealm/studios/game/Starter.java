package hollowrealm.studios.game;

import hollowrealm.studios.game.tiles.GrassTile;
import hollowrealm.studios.game.tiles.TestTile;
import hollowrealm.studios.game.tiles.TileMap;
import hollowrealm.studios.game.tiles.TileRenderer;
import javafx.application.Application;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.util.ArrayList;

public class Starter extends Application {

    private static final ArrayList<Rhomb> rhombs = new ArrayList<>();
    private static final TileMap map = new TileMap(10, 10, 10);

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        // Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        map.setTile(new GrassTile(), 0, 0, 0);
        map.setTile(new GrassTile(), 1, 0, 0);
        map.setTile(new TestTile(), 0, 1, 0);
        map.setTile(new TestTile(), 0, 0, 1);
        /*Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(map.getTexture(), 0, 0, null);
            }
        }, 0);*/
        TileRenderer tr = new TileRenderer(config);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                tr.render(graphics2D);
            }
        }, 0);
    }

    @Override
    public void start(Stage primaryStage) {
        Starter.start(new GameConfig(), new ArrayList<>());
    }
}
