package hollowrealm.studios.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageSplitter {

    public static BufferedImage[] split(BufferedImage image) {
        if (image.getHeight() != 137) {
            BufferedImage old = image;
            image = new BufferedImage(image.getWidth(), 137, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(old, 0, 137-old.getHeight(), null);
        }
        return new BufferedImage[]{
                image.getSubimage(4, 4, 129, 129),
                image.getSubimage(141, 4, 129, 129),
                image.getSubimage(278, 4, 129, 129),
                image.getSubimage(415, 4, 129, 129)
        };
    }

}
