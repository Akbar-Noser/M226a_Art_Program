package ch.noseryoung.processing;

import ch.noseryoung.inputformat.CreatePictureInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private ColorCalculator colorCalculator;
    private Layout layout;
    private Painter painter;
    private static ch.noseryoung.processing.Canvas canvas;
    private List<Color> colors;
    private static final String INPUT_STRIPES = "Please enter the weight for the stripes";

    public Controller() {
        colors = new ArrayList<>();
        colorCalculator = new ColorCalculator();
        layout = new Layout(colorCalculator, colors);
        painter = new Painter();
        canvas = new ch.noseryoung.processing.Canvas();
    }

    /**
     * Main flow of the program, which guides the user from input to input
     *
     * @throws IOException checked exception caused by the painter's functions
     */
    public void start() throws IOException {
        do {
            colors.clear();
            colors.add(colorCalculator.randomColor());
            System.out.println("""
                    [1] Analogous Colors
                    [2] Tetradic Colors
                    [3] Triadic Colors
                    [4] Complementary Colors""");

            switch (new Scanner(System.in).nextInt()) {
                case 1 -> {
                    System.out.println("Enter amount of shades");
                    layout.useAnalogousColors(new Scanner(System.in).nextInt());
                }
                case 2 -> layout.useTetradicColors();
                case 3 -> layout.useTriadicColors();
                case 4 -> layout.useComplementaryColors();
            }
            System.out.println("""
                    [1] Create Template
                    [2] Create Stripes
                    [3] Create Diamonds""");
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> painter.createColorTemplate(colors);
                case 2 -> {
                    System.out.println(INPUT_STRIPES);
                    painter.createStripes(true, new Scanner(System.in).nextInt(), colors);
                }
            }
        } while (true); //temporary condition for testing


    }


    public File createDiamondPicture(CreatePictureInput input) throws IOException {
        BufferedImage bi =  new BufferedImage(ch.noseryoung.processing.Canvas.getCanvasX(), ch.noseryoung.processing.Canvas.getCanvasY(), BufferedImage.TYPE_INT_RGB);
        for (int sideLength : input.getLayers()) {
            useScheme(input);
            bi = painter.createDiamondPattern(colors, bi, sideLength);
        }
        File writtenFile = new File("./src/main/java/ch/noseryoung/temppictures/" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_diamond_" +
                colors.size() + "_shades.png");
        ImageIO.write(bi, "PNG", writtenFile);
        return writtenFile;
    }

    public File createStripesPicture(CreatePictureInput input) throws IOException {
        BufferedImage bi;
        useScheme(input);
        bi = painter.createStripes(input.isEvenSpacing(), input.getStripeThickness(), colors);
        File writtenFile = new File("./src/main/java/ch/noseryoung/temppictures/" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_diamond_" +
                colors.size() + "_shades.png");
        ImageIO.write(bi, "PNG", writtenFile);
        return writtenFile;
    }

    public File createTemplatePicture(CreatePictureInput input) throws IOException {
        BufferedImage bi;
        useScheme(input);
        bi = painter.createColorTemplate(colors);
        File writtenFile = new File("./src/main/java/ch/noseryoung/temppictures/" +
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "_diamond_" +
                colors.size() + "_shades.png");
        ImageIO.write(bi, "PNG", writtenFile);
        return writtenFile;
    }

    private void useScheme(CreatePictureInput input) {
        colors.clear();
        colors.add(colorCalculator.randomColor());
        switch (input.getColorScheme()) {
            case "analogous" -> layout.useAnalogousColors(input.getAmountOfShades());
            case "tetradic" -> layout.useTetradicColors();
            case "triadic" -> layout.useTriadicColors();
            case "complementary" -> layout.useComplementaryColors();

        }
    }

    public ColorCalculator getColourCalculator() {
        return colorCalculator;
    }

    public void setColourCalculator(ColorCalculator colorCalculator) {
        this.colorCalculator = colorCalculator;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public static ch.noseryoung.processing.Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Controller.canvas = canvas;
    }


}
