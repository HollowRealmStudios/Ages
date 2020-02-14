package hollowrealm.studios.game;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoadingScreen {

    private JFrame frame;

    private int percentage = 0;

    public LoadingScreen() {
        Thread t = new Thread(() -> {
            frame = new JFrame() {
                @Override
                public void paint(Graphics g) {
                    g.setColor(Color.PINK);
                    g.fillRect(0, 0, percentage * 4, 50);
                }
            };
            frame.setSize(400, 50);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            frame.repaint();
        });
        t.start();
    }

    public void increasePercentage(int amount) {
        percentage += amount;
        if (percentage >= 100) {
            frame.dispose();
            System.gc();
        }
        else frame.repaint();
    }
}
