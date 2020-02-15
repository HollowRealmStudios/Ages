package hollowrealm.studios.game;

import com.google.common.base.Stopwatch;
import hollowrealm.studios.game.map.VoxelModule;
import hollowrealm.studios.game.map.voxels.GrassVoxel;
import hollowrealm.studios.game.map.voxels.TreeStompVoxel;
import hollowrealm.studios.game.map.voxels.WorkBenchVoxel;
import javafx.application.Application;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.modules.MouseWheelListener;
import simple.engine.util.ColorOut;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Starter extends Application {

    private static final Stopwatch stopwatch = Stopwatch.createStarted();
    private static final LoadingScreen screen = new LoadingScreen();
    private static float zoom = 1f;

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        screen.increasePercentage(20, "Initializing engine");
        Engine.initialize(config);
        Engine.addModules(new PlayerModule(config));
        Engine.addModules(new VoxelModule(config));
        ColorOut.print(System.out, "Finished Engine initialization at ".concat(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))), ColorOut.CYAN);
        screen.increasePercentage(20, "Registering keys");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registerKeys();
        ColorOut.print(System.out, "Finished registering keys at ".concat(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))), ColorOut.CYAN);
        screen.increasePercentage(20, "Registering Graphics");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registerGraphics();
        ColorOut.print(System.out, "Finished registering graphics at ".concat(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))), ColorOut.CYAN);
        screen.increasePercentage(20, "Generating world");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doStuff();
        ColorOut.print(System.out, "Done at ".concat(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))), ColorOut.CYAN);
        screen.increasePercentage(20, "Finishing up");
        managePlugins(plugins);
    }

    private static void managePlugins(ArrayList<Plugin> plugins) {
        if (plugins == null) return;
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));

    }

    private static void doStuff() {
        Engine.get(VoxelModule.class).getMap().fillLayer(new GrassVoxel(), 0);
        Engine.get(VoxelModule.class).getMap().fillLayer(new WorkBenchVoxel(), 1);
        Engine.get(VoxelModule.class).getMap().setVoxel(new TreeStompVoxel(), 0, 0, 2);
    }

    private static void registerGraphics() {
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.scale(zoom, zoom);
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
        Engine.keyModule.addKeyListener(KeyEvent.VK_LEFT, () -> Engine.get(VoxelModule.class).getMap().rotateCW());
        Engine.keyModule.addKeyListener(KeyEvent.VK_RIGHT, () -> Engine.get(VoxelModule.class).getMap().rotateCCW());
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
