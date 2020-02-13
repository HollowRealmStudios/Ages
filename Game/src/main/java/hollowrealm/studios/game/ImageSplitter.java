package hollowrealm.studios.game;

import java.awt.*;
import java.awt.image.*;

public class ImageSplitter {

    public static BufferedImage[] split(BufferedImage image) {
        if (image.getHeight() != 137) {
            BufferedImage old = image;
            image = new BufferedImage(image.getWidth(), 137, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(old, 0, 137 - old.getHeight(), null);
        }
        return new BufferedImage[]{
                image.getSubimage(4, 4, 129, 129),
                image.getSubimage(141, 4, 129, 129),
                image.getSubimage(278, 4, 129, 129),
                image.getSubimage(415, 4, 129, 129)
        };
    }

    public static BufferedImage transparize(BufferedImage img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bi.getGraphics().drawImage(img, 0, 0, null);
        RasterOp rop = new RescaleOp(new float[]{1.0f, 1.0f, 1.0f, 0.2f}, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, null);
        Raster result = rop.filter(bi.getData(), null);
        ColorModel colorModel = ColorModel.getRGBdefault();
        WritableRaster writableRaster = result.createCompatibleWritableRaster();
        writableRaster.setDataElements(0, 0, result);
        return new BufferedImage(colorModel, writableRaster, colorModel.isAlphaPremultiplied(), null);
    }
}
