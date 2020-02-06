package hollowrealm.studios.game;

import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;

import java.awt.*;
import java.util.ArrayList;

public class Starter {

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(Engine.storageModule.getImage("TreeStomp.png"), 0, 0, null);
            }
        }, 0);
    }
}
