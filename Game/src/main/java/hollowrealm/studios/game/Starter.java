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

    private static int i = 0;

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        if (plugins != null) {
            plugins.forEach(Plugin::start);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        }
        BufferedImage[] bench = ImageSplitter.split(Engine.storageModule.getImage("WorkBench.png"));
        BufferedImage[] birch = ImageSplitter.split(Engine.storageModule.getImage("BirchWood.png"));
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(bench[i], 0, 0, null);
                graphics2D.drawImage(birch[i], 129, 0, null);
            }
        }, 0);
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> {
            if (i == 3) {
                i = 0;
            } else i++;
        });
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> {
            if (i == 0) {
                i = 3;
            } else i--;
        });
    }
}
