package hollowrealm.studios.game;

import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.util.GameConfig;
import simple.engine.util.GuiUtils;

import java.awt.*;

public class Starter {

    public static void start(GameConfig config) {
        Engine.initialize(config);
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.setColor(GuiUtils.randomColor());
                graphics2D.fillRect(0, 0, 100, 100);
            }
        }, 0);
    }
}
