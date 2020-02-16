package hollowrealm.studios.game;

import hollowrealm.studios.game.ages.TestAge;
import hollowrealm.studios.game.map.VoxelModule;
import javafx.application.Application;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Starter extends Application {

    private static int gradientCount = 0;
    private static GradientPaint[] gradients = new GradientPaint[50];

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Engine.addModules(new PlayerModule(config));
        Engine.addModules(new VoxelModule(config, new TestAge()));
        for (int i = 0; i < 50; i++) {
            int clr = Engine.storageModule.getImage("BackgroundGradient.png").getRGB(i, 0);
            Color color = new Color((clr & 0x00ff0000) >> 16, (clr & 0x0000ff00) >> 8, clr & 0x000000ff);
            gradients[i] = new GradientPaint(Engine.getConfig().getWidth() / 50f * i, 0, color, Engine.getConfig().getWidth() - Engine.getConfig().getWidth() / 50f * i, 5f * Engine.getConfig().getHeight(), Color.WHITE);
        }
        registerKeys();
        registerGraphics();
        doStuff();
        managePlugins(plugins);
    }

    private static void managePlugins(ArrayList<Plugin> plugins) {
        if (plugins == null) return;
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));

    }

    private static void doStuff() {
        Engine.get(PlayerModule.class).player.setPosition(256, 256);
    }

    private static void registerGraphics() {
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.setPaint(gradients[gradientCount]);
                graphics2D.fillRect(0, 0, Engine.getConfig().getWidth(), Engine.getConfig().getHeight());
                Engine.get(VoxelModule.class).render(graphics2D);
                Engine.get(PlayerModule.class).player.paint(graphics2D);
            }
        }, 0);
        Engine.timingModule.scheduleRepeatedly(() -> {
            if (gradientCount == 49) gradientCount = 0;
            else gradientCount++;
        }, 0, 2000);
    }

    private static void registerKeys() {
        Engine.keyModule.addKeyListener(KeyEvent.VK_W, () -> Engine.get(PlayerModule.class).player.moveTop());
        Engine.keyModule.addKeyListener(KeyEvent.VK_S, () -> Engine.get(PlayerModule.class).player.moveBottom());
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> Engine.get(PlayerModule.class).player.moveLeft());
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> Engine.get(PlayerModule.class).player.moveRight());
        Engine.keyModule.addKeyListener(KeyEvent.VK_ESCAPE, () -> System.exit(0));
        Engine.keyModule.addKeyListener(KeyEvent.VK_LEFT, () -> Engine.get(VoxelModule.class).getAge().getVoxelMap().rotateCW());
        Engine.keyModule.addKeyListener(KeyEvent.VK_RIGHT, () -> Engine.get(VoxelModule.class).getAge().getVoxelMap().rotateCCW());
    }

    @Override
    public void start(Stage primaryStage) {
        Starter.start(new GameConfig(), new ArrayList<>());
    }
}
