package hollowrealm.studios.game;

import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Starter {

    private static boolean transparent = false;

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        if (plugins != null) {
            plugins.forEach(Plugin::start);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        }
        BufferedImage grass = ImageSplitter.split(Engine.storageModule.getImage("Grass.png"))[0];
        BufferedImage water = ImageSplitter.split(Engine.storageModule.getImage("Water.png"))[0];
        BufferedImage bench = ImageSplitter.split(Engine.storageModule.getImage("WorkBench.png"))[0];
        BufferedImage trans = ImageSplitter.transparize(ImageSplitter.split(Engine.storageModule.getImage("WorkBench.png"))[0]);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(grass, 0, 100, null);
                graphics2D.drawImage(grass, 64, 132, null);
                graphics2D.drawImage(water, 0, 37, null);
                graphics2D.drawImage(transparent ? trans : bench, 64, 69, null);
            }
        }, 0);
        Engine.keyModule.addKeyListener(KeyEvent.VK_SPACE, () -> transparent = !transparent);
    }
}
