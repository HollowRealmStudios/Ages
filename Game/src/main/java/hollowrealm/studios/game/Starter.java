package hollowrealm.studios.game;

import hollowrealm.studios.game.map.tiles.GrassVoxel;
import hollowrealm.studios.game.map.VoxelRenderer;
import javafx.application.Application;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.modules.MouseWheelListener;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Starter extends Application {

    private static float zoom = 1f;
    private static final Point p = new Point();

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        // Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        VoxelRenderer tr = new VoxelRenderer(config);
        tr.setVoxel(new GrassVoxel(), 0, 0, 0);
        tr.setVoxel(new GrassVoxel(), 1, 0, 0);
        tr.setVoxel(new GrassVoxel(), 0, 1, 0);
        tr.setVoxel(new GrassVoxel(), 0, 0, 1);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.translate(p.x, p.y);
                graphics2D.scale(zoom, zoom);
                tr.render(graphics2D);
            }
        }, 0);
        Engine.keyModule.addKeyListener(KeyEvent.VK_W, () -> p.translate(0, -5));
        Engine.keyModule.addKeyListener(KeyEvent.VK_S, () -> p.translate(0, 5));
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> p.translate(-5, 0));
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> p.translate(5, 0));
        Engine.keyModule.addKeyListener(KeyEvent.VK_ESCAPE, () -> System.exit(0));
        Engine.mouseModule.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void onMouseScroll(int i) {
                zoom += i > 0 ? -0.2f : 0.2f;
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        Starter.start(new GameConfig(), new ArrayList<>());
    }
}
