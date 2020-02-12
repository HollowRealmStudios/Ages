package hollowrealm.studios.game;

import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.ColorOut;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;
import simple.engine.util.OverflowingInteger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Starter {

    private static int i = 0;

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        BufferedImage[] images = ImageSplitter.split(Engine.storageModule.getImage("WorkBench.png"));
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(images[i], 0, 0, null);
                ColorOut.print(System.out, String.valueOf(i), ColorOut.CYAN);
            }
        }, 0);
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> {
            if(i == 3) {
                i = 0;
            }
            else i++;
        });
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> {
            if(i == 0) {
                i = 3;
            }
            else i--;
        });
    }
}
