package hollowrealm.studios.game;

import org.imgscalr.Scalr;
import simple.engine.Engine;
import simple.engine.modules.FrameListener;
import simple.engine.modules.MouseClickListener;
import simple.engine.modules.MouseWheelListener;
import simple.engine.util.GameConfig;
import simple.engine.util.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Starter {

    private static final ArrayList<TileShape> shapes = new ArrayList<>();
    private static float zoom = 1f;
    private static int x, y;

    public static void start(GameConfig config, ArrayList<Plugin> plugins) {
        Engine.initialize(config);
        Logger.disableLevel("fps");
        plugins.forEach(Plugin::start);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> plugins.forEach(Plugin::stop)));
        BufferedImage background = Scalr.resize(Engine.storageModule.getImage("Gradient.png"), Engine.getConfig().getWidth(), Engine.getConfig().getHeight());
        Engine.graphicModule.addFrameListener(new FrameListener() {
            @Override
            public void onNextFrame(Graphics2D graphics2D) {
                graphics2D.drawImage(background, 0, 0, null);
                graphics2D.scale(zoom, zoom);
                graphics2D.translate(x, y);
                boolean offset = false;
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 20; x++) {
                        graphics2D.drawImage(Engine.storageModule.getImage("Grass.png"), x * 129 + (offset ? 64 : 0), y * 32, null);
                        if(shapes.size() != 400) shapes.add(new TileShape(
                                new GFG.Point(x * 129 + (offset ? 64 : 0), y * 32 + 32),
                                new GFG.Point(x * 129 + (offset ? 64 : 0) + 64, y * 32),
                                new GFG.Point(x * 129 + (offset ? 64 : 0) + 128, y * 32 + 32),
                                new GFG.Point(x * 129 + (offset ? 64 : 0) + 64, y * 32 + 64)
                        ));
                    }
                    offset = !offset;
                }
                shapes.forEach(tileShape -> {
                    graphics2D.setColor(tileShape.getColor());
                    graphics2D.drawPolygon(tileShape.getXCoordinates(), tileShape.getYCoordinates(), tileShape.getPointAmount());
                });
            }
        }, 0);

        Engine.mouseModule.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void onMouseScroll(int i) {
                zoom += i > 0 ? -0.1f : 0.1f;
            }
        });
        Engine.keyModule.addKeyListener(KeyEvent.VK_W, () -> y--);
        Engine.keyModule.addKeyListener(KeyEvent.VK_S, () -> y++);
        Engine.keyModule.addKeyListener(KeyEvent.VK_A, () -> x--);
        Engine.keyModule.addKeyListener(KeyEvent.VK_D, () -> x++);
        Engine.mouseModule.addMouseClickListener(MouseEvent.BUTTON1, new MouseClickListener() {
            @Override
            public void onClick(MouseEvent mouseEvent) {
                shapes.forEach(tileShape -> {
                    if (GFG.isInside(tileShape.getPoints(), tileShape.getPointAmount(), new GFG.Point(mouseEvent.getX(), mouseEvent.getY()))){
                        tileShape.setColor(Color.GREEN);
                    }
                    else {
                        tileShape.setColor(Color.RED);
                    }
                });
            }
        });
    }
}
