package ch.noseryoung;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private ColorCalculator colorCalculator;
    private Layout layout;
    private Painter painter;
    private static Canvas canvas;
    private List<Color> colors;
    private ExceptionHandlerIO exceptionHandlerIO;

    public Controller() {
        colors = new ArrayList<>();
        colorCalculator = new ColorCalculator();
        layout = new Layout(colorCalculator, colors);
        painter = new Painter();
        canvas = new Canvas();
        exceptionHandlerIO = new ExceptionHandlerIO();
    }

    /**
     * Main flow of the program, which guides the user from input to input
     *
     * @throws IOException checked exception caused by the painter's functions
     */
    public void start() throws IOException {
        int answer = -1;
        while (answer != 0){
            colors.clear();
            colors.add(colorCalculator.randomColor());
            switch (answer = exceptionHandlerIO.handleLayoutInput(0, 4)) {
                case 1 -> layout.useAnalogousColors(exceptionHandlerIO.handleAmountOfShadesInput());
                case 2 -> layout.useTetradicColors();
                case 3 -> layout.useTriadicColors();
                case 4 -> layout.useComplementaryColors();
            }
            if (answer == 0)
                continue;
            switch (answer = exceptionHandlerIO.handlePicturePatternInput(0, 3)) {
                case 1 -> painter.createColorTemplate(colors);
                case 2 -> painter.createStripes(true, exceptionHandlerIO.handleStripeThicknessInput(), colors);
                case 3 -> painter.createDiamondPattern(colors, exceptionHandlerIO.handleDiamondSideLengthInput());
            }
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

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Controller.canvas = canvas;
    }


}
