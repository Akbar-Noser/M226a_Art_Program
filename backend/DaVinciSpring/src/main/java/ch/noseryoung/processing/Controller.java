package ch.noseryoung.processing;

import ch.noseryoung.inputformat.CreatePictureInput;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
                case  1 -> painter.createColorTemplate(colors);
                case 2 -> {
                    System.out.println(INPUT_STRIPES);
                    painter.createStripes(true, new Scanner(System.in).nextInt(), colors);
                }
            }
        } while (true); //temporary condition for testing


    }


    public File createDiamondPicture(CreatePictureInput input) throws IOException {
        File file = null;
        switch (input.getColorScheme()) {
            case "analogous" -> {
                for (Integer sideLength : input.getLayers()) {
                    layout.useAnalogousColors(input.getAmountOfShades());
                    file = painter.createDiamondPattern(colors, sideLength);
                }
            }
            case "tetradic" -> {
                for (Integer sideLength: input.getLayers()) {
                    layout.useTetradicColors();
                    file = painter.createDiamondPattern(colors, sideLength);
                }
            }
            case "triadic" -> {
                for (Integer sideLength: input.getLayers()) {
                    layout.useTriadicColors();
                    file = painter.createDiamondPattern(colors, sideLength);
                }
            }
            case "complementary" -> {
                for (Integer sideLength: input.getLayers()) {
                    layout.useComplementaryColors();
                    file = painter.createDiamondPattern(colors, sideLength);
                }
            }
        }
        return file;
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
