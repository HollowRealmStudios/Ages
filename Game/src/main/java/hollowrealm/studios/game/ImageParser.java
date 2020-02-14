package hollowrealm.studios.game;

import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.*;

/**
 * @author NastyGamer
 */
public class ImageParser {

    public static BufferedImage[] split(BufferedImage image) {
        if(image.getHeight() != 137 || image.getWidth() != 548) image = Scalr.resize(image, 548, 137);
        return new BufferedImage[]{
                Scalr.resize(image.getSubimage(4, 4, 129, 129), 128, 128),
                Scalr.resize(image.getSubimage(141, 4, 129, 129), 128, 128),
                Scalr.resize(image.getSubimage(278, 4, 129, 129), 128, 128),
                Scalr.resize(image.getSubimage(415, 4, 129, 129), 128, 128)
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
