package hollowrealm.studios.game;

import hollowrealm.studios.game.map.VoxelModule;
import hollowrealm.studios.game.map.voxels.GrassVoxel;
import javafx.application.Application;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Starter extends Application {

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Engine.addModules(new PlayerModule(config));
        Engine.addModules(new VoxelModule(config));
        registerKeys();
        registerGraphics();
        doStuff();
    }

    private static void doStuff() {
        Engine.get(VoxelModule.class).getMap().fillLayer(new GrassVoxel(), 0);
    }

    private static void registerGraphics() {
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.translate(Engine.get(PlayerModule.class).player.getPosition().x, Engine.get(PlayerModule.class).player.getPosition().y);
                Engine.get(VoxelModule.class).render(graphics2D);
                Engine.get(PlayerModule.class).player.paint(graphics2D);
            }
        }, 0);
    }

    private static void registerKeys() {
        Engine.keyModule.addKeyListener(KeyEvent.VK_W, () -> Engine.get(PlayerModule.class).player.moveTop());
        Engine.keyModule.addKeyListener(KeyEvent.VK_S, () -> Engine.get(PlayerModule.class).player.moveBottom());
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> Engine.get(PlayerModule.class).player.moveLeft());
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> Engine.get(PlayerModule.class).player.moveRight());
        Engine.keyModule.addKeyListener(KeyEvent.VK_ESCAPE, () -> System.exit(0));
    }

    @Override
    public void start(Stage primaryStage) {
        Starter.start(new GameConfig(), new ArrayList<>());
    }
}
