package ch.noseryoung.processing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Painter {

    /**
     * Will create a file, which uses the chosen colors to
     * distribute them evenly across the canvas. For example
     * if there are 2 colors in the list, one hal fof the canvas
     * will be one color and half of it will be the other
     * <p>
     * (Possible to create color gradients if enough colors exist)
     *
     * @param colors the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public BufferedImage createColorTemplate(List<Color> colors) throws IOException {
        int colorLength =  ((float) ch.noseryoung.processing.Canvas.getCanvasX() / colors.size()) % 1.0 != 0.0 ?
                (ch.noseryoung.processing.Canvas.getCanvasX() / colors.size()) + 1 :
                (ch.noseryoung.processing.Canvas.getCanvasX() / colors.size());
        System.out.println(colorLength);
        BufferedImage bi = new BufferedImage(ch.noseryoung.processing.Canvas.getCanvasX(),
                ch.noseryoung.processing.Canvas.getCanvasY(), BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < colors.size(); i++) {
                for (int k = 0; k < colorLength; k++) {
                    try {
                        for (int j = 0; j < ch.noseryoung.processing.Canvas.getCanvasY(); j++) {
                            bi.setRGB(k + colorLength * i, j, colors.get(i).getRGB());
                        }
                    }catch (ArrayIndexOutOfBoundsException e) {
                        // if x length is not dividable by amount of colors the loop will throw this
                        // error as the loop is configured to "overfill" the canvas, error occurs and loop will break
                        break;
                    }
                }
            }

       return bi;
    }

    /**
     * Will create a file where the colors are chosen at random to create a picture of stripes
     * colors will appear in random order, if evenSpaces = true, two adjacent stripes
     * won't have the same color.
     *
     * @param evenSpaces decides if stripes will always maintain the same thickness
     * @param weight     describes the thickness of the stripes in pixels (absolute if evenSpaces is true, otherwise
     *                   it describes the minimum thickness)
     * @param colors     the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public BufferedImage createStripes(boolean evenSpaces, int weight, List<Color> colors) throws IOException {
        Color usedColor = colors.get(ThreadLocalRandom.current().nextInt(colors.size()));
        int weightCounter = 0;
        BufferedImage bi = new BufferedImage(ch.noseryoung.processing.Canvas.getCanvasX(), ch.noseryoung.processing.Canvas.getCanvasY(), BufferedImage.TYPE_INT_RGB);
        if (evenSpaces) {
            for (int k = 0; k < ch.noseryoung.processing.Canvas.getCanvasX(); k++) {
                if (weight == weightCounter) {
                    weightCounter = 0;
                    Color oldColor;
                    do {
                        oldColor = usedColor;
                        usedColor = colors.get(ThreadLocalRandom.current().nextInt(colors.size()));
                    } while (oldColor.getRGB() == usedColor.getRGB());
                }
                weightCounter++;
                for (int j = 0; j < ch.noseryoung.processing.Canvas.getCanvasY(); j++) {
                    bi.setRGB(k, j, usedColor.getRGB());
                }
            }
        } else {
            for (int k = 0; k < ch.noseryoung.processing.Canvas.getCanvasX(); k++) {
                if (weight == weightCounter) {
                    weightCounter = 0;
                    usedColor = colors.get(ThreadLocalRandom.current().nextInt(colors.size()));
                }
                weightCounter++;
                for (int j = 0; j < ch.noseryoung.processing.Canvas.getCanvasY(); j++) {
                    bi.setRGB(k, j, usedColor.getRGB());
                }
            }
        }

        return bi;
    }

    /**
     * creates a pattern of diamonds, diamonds may be layered on top of each other
     * but the requirement is the full loop of all the inputs from the user, layering can't be
     * applied in a single round
     * <p>
     * Diamond creation is multithreaded
     *
     * @param colors the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public BufferedImage createDiamondPattern(List<Color> colors, BufferedImage bi, int sideLength) throws IOException {
        DiamondGeneratorThread startThread = new DiamondGeneratorThread(new Point(0, 0),
                colors.get(ThreadLocalRandom.current().nextInt(colors.size())).getRGB(),
                sideLength, colors);
        startThread.start();
        try {
            startThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int y = 0; y < Controller.getCanvas().getCANVAS2D().length; y++) {
            for (int x = 0; x < Controller.getCanvas().getCANVAS2D()[0].length; x++) {
                bi.setRGB(x, y, Controller.getCanvas().getCANVAS2D()[y][x]);
            }
        }
        System.out.println(System.getProperty("user.dir"));
        return bi;
    }

}