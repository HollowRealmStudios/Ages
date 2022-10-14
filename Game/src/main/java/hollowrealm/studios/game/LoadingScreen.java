package hollowrealm.studios.game;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoadingScreen {

	private final JFrame frame;
	private int percentage;
	private String current;

	public LoadingScreen() {
		frame = new JFrame() {
			@Override
			public void paint(Graphics g) {
				g.clearRect(0, 0, 400, 50);
				g.setColor(Color.PINK);
				g.fillRect(0, 0, percentage * 4, 50);
				g.setColor(Color.BLACK);
				g.drawString(current, 200 - g.getFontMetrics().stringWidth(current) / 2, 20);
			}
		};
		frame.setSize(400, 50);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.repaint();
	}

	public void increasePercentage(int amount, String current) {
		this.current = current;
		percentage += amount;
		if (percentage >= 100) {
			frame.dispose();
			System.gc();
		} else if (frame != null) {
			frame.repaint();
		}
	}
}
