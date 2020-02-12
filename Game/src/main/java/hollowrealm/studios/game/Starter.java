package hollowrealm.studios.game;

import hollowrealm.studios.game.tiles.GrassTile;
import hollowrealm.studios.game.tiles.TestTile;
import hollowrealm.studios.game.tiles.TileMap;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Starter {

    private static final ArrayList<Rhomb> rhombs = new ArrayList<>();
    private static final TileMap map = new TileMap(10, 10, 10);

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        BufferedImage[] images = ImageSplitter.split(Engine.storageModule.getImage("Grass.png"));
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(images[0], 0, 0, null);
                graphics2D.drawImage(images[0], 0, 130, null);
                graphics2D.drawImage(images[0], 130, 0, null);
                graphics2D.drawImage(images[0], 130, 130, null);

            }
        }, 0);
    }
}
