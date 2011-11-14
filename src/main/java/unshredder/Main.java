package unshredder;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

import static java.lang.Math.min;

/**
 * TODO: Edit this
 * <p/>
 * User: sam
 * Date: 11/13/11
 * Time: 4:02 PM
 */
public class Main {
  static class Diff implements Comparable<Diff> {
    int x1;
    int x2;
    int diff;

    @Override
    public String toString() {
      return "Diff{" +
              "x1=" + x1 +
              ", x2=" + x2 +
              ", diff=" + diff +
              '}';
    }

    public int compareTo(Diff o) {
      return diff - o.diff;
    }
  }

  public static void main(String[] args) throws IOException {
    URL url = new URL("http://instagram-static.s3.amazonaws.com/images/TokyoPanoramaShredded.png");
    BufferedImage bufferedImage = ImageIO.read(url);

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    List<Diff> list = new ArrayList<Diff>(width * width);
    Raster data = bufferedImage.getData();
    for (int x1 = 0; x1 < width; x1++) {
      for (int x2 = x1; x2 < width; x2++) {
        if (x2 - x1 > 10) {
          Diff diff = new Diff();
          diff.x1 = x1;
          diff.x2 = x2;
          for (int y = 0; y < height; y++) {
            int[] pixel1 = data.getPixel(x1, y, (int[]) null);
            int[] pixel2 = data.getPixel(x2, y, (int[]) null);
            for (int i = 0; i < pixel1.length; i++) {
              int d = pixel1[i] - pixel2[i];
              diff.diff += d * d;
            }
          }
          list.add(diff);
        }
      }
    }

    Collections.sort(list);
    for (Diff diff : list.subList(0, min(1000, list.size()))) {
      System.out.println(diff);
    }
  }
}
