package unshredder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.net.URL;

/**
 * TODO: Edit this
 * <p/>
 * User: sam
 * Date: 11/13/11
 * Time: 4:02 PM
 */
public class Main {
  public static void main(String[] args) throws IOException {
    URL url = new URL("http://instagram-static.s3.amazonaws.com/images/TokyoPanoramaShredded.png");
    BufferedImage bufferedImage = ImageIO.read(url);

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int[][] diffs = new int[width][width];
    Raster data = bufferedImage.getData();
    for (int x1 = 0; x1 < width; x1++) {
      for (int x2 = 0; x2 < width; x2++) {
        for (int y = 0; y < height; y++) {
          if (x1 != x2) {
            data.getPixel(x1, y, )
          }
        }
      }
    }
  }
}
