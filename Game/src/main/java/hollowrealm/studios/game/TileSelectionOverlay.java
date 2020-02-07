package hollowrealm.studios.game;

import simple.engine.Engine;
import simple.engine.gui.components.StaticWidget;
import simple.engine.modules.MouseMoveListener;

import java.awt.*;

public class TileSelectionOverlay extends StaticWidget {

    private int x, y;

    public TileSelectionOverlay() {
        super(0, 0, Engine.getConfig().getWidth(), Engine.getConfig().getHeight());
        Engine.mouseModule.addMouseMoveListener(new MouseMoveListener() {
            @Override
            public void onMouseMove(Point point) {
                System.out.println(point.toString());
                y = (int) (Math.floor(point.y / 64) * 64);
                x = y % 2 == 0 ? (int) (Math.floor(point.x / 128) * 128) : (int) (Math.floor((point.x + 64) / 128) * 128);
            }
        });
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.drawPolygon(new int[] {0, 128, 128, 0}, new int[] {0, 0, 128, 128}, 4);
    }
}
