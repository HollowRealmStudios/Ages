package hollowrealm.studios.game;

import java.awt.image.BufferedImage;

public class ImageSplitter {

    public static BufferedImage[] split(BufferedImage image) {
        return new BufferedImage[]{
                image.getSubimage(4, 4, 129, 129),
                image.getSubimage(141, 4, 129, 129),
                image.getSubimage(278, 4, 129, 129),
                image.getSubimage(415, 4, 129, 129)
        };
    }

}
