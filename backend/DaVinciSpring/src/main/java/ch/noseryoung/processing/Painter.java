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
     *
     * (Possible to create color gradients if enough colors exist)
     *
     * @param colors the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public void createColorTemplate(List<Color> colors) throws IOException {
        BufferedImage bi = new BufferedImage(ch.noseryoung.processing.Canvas.getCanvasX(), ch.noseryoung.processing.Canvas.getCanvasY(), BufferedImage.TYPE_INT_RGB);
        for (int i = 1; i <= colors.size(); i++) {
            for (int k = 0; k < ch.noseryoung.processing.Canvas.getCanvasX() / colors.size(); k++) {
                for (int j = 0; j < ch.noseryoung.processing.Canvas.getCanvasY(); j++) {
                    bi.setRGB(k + ch.noseryoung.processing.Canvas.getCanvasX() / colors.size() * (i - 1), j, colors.get(i - 1).getRGB());
                }
            }
        }
        ImageIO.write(bi, "PNG", new File("C:\\Users\\pette_j7ckdwu\\IdeaProjects\\DaVinci\\Pictures\\" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_ColourTemplate_" +
                colors.size() + "_shades.png"));
    }

    /**
     * Will create a file where the colors are chosen at random to create a picture of stripes
     * colors will appear in random order, if evenSpaces = true, two adjacent stripes
     * won't have the same color.
     *
     * @param evenSpaces decides if stripes will always maintain the same thickness
     * @param weight describes the thickness of the stripes in pixels (absolute if evenSpaces is true, otherwise
     *               it describes the minimum thickness)
     * @param colors the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public void createStripes(boolean evenSpaces, int weight, List<Color> colors) throws IOException {
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
            ImageIO.write(bi, "PNG", new File("C:\\Users\\pette_j7ckdwu\\IdeaProjects\\DaVinci\\Pictures\\" +
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_stripes_" +
                    colors.size() + "_shades.png"));
        }
    }

    /**
     * creates a pattern of diamonds, diamonds may be layered on top of each other
     * but the requirement is the full loop of all the inputs from the user, layering can't be
     * applied in a single round
     *
     * Diamond creation is multithreaded
     *
     * @param colors the list of colors available to the painter
     * @throws IOException checked exception from ImageIO.write()
     */
    public void createDiamondPattern(List<Color> colors) throws IOException {
        System.out.println("Enter side length: ");
        DiamondGeneratorThread startThread = new DiamondGeneratorThread(new Point(0, 0),
                colors.get(ThreadLocalRandom.current().nextInt(colors.size())).getRGB(),
                new Scanner(System.in).nextInt(), colors);
        startThread.start();
        try {
            startThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedImage bi = new BufferedImage(ch.noseryoung.processing.Canvas.getCanvasX(), Canvas.getCanvasY(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < Controller.getCanvas().getCANVAS2D().length; y++) {
            for (int x = 0; x < Controller.getCanvas().getCANVAS2D()[0].length; x++) {
                bi.setRGB(x, y, Controller.getCanvas().getCANVAS2D()[y][x]);
            }
        }
        ImageIO.write(bi, "PNG", new File("C:\\Users\\pette_j7ckdwu\\IdeaProjects\\DaVinci\\Pictures\\" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_diamond_" +
                colors.size() + "_shades.png"));
    }

}