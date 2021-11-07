package ch.noseryoung.processing;

import ch.noseryoung.processing.ColorCalculator;

import java.awt.*;
import java.util.List;

public class Layout {
    private ColorCalculator colorCalculator;
    private List<Color> colors;

    public Layout() {
    }

    public Layout(ColorCalculator colorCalculator, List<Color> colors) {
        this.colorCalculator = colorCalculator;
        this.colors = colors;
    }

    /**
     * Creates an intersection between the calculator and the controller
     * fills the colors list with the colors returned from the colorCalculator
     * @param amountOfShades amount of shades generated in the analogous spectrum
     */
    public void useAnalogousColors(int amountOfShades) {
        Color initialColor = new Color(colors.get(0).getRGB());
        colors.clear();
        colors.addAll(colorCalculator.calcAnalogousColors(colorCalculator.rgbToHSB(initialColor), amountOfShades));
    }


    /**
     * Creates an intersection between the calculator and the controller
     * fills the colors list with the colors returned from the colorCalculator
     */
    public void useTetradicColors() {
        colors.addAll(colorCalculator.calcTetradicColors(colorCalculator.rgbToHSB(
                new Color(colors.get(0).getRGB()))));
    }

    /**
     * Creates an intersection between the calculator and the controller
     * fills the colors list with the colors returned from the colorCalculator
     */
    public void useTriadicColors() {
        colors.addAll(colorCalculator.calcTriadicColors(colorCalculator.rgbToHSB(
                new Color(colors.get(0).getRGB()))));
    }

    /**
     * Creates an intersection between the calculator and the controller
     * fills the colors list with the colors returned from the colorCalculator
     */
    public void useComplementaryColors() {
        colors.addAll(colorCalculator.calcComplementaryColors(colorCalculator.rgbToHSB(
                new Color(colors.get(0).getRGB()))));
    }

    public void setColorCalculator(ColorCalculator colorCalculator) {
        this.colorCalculator = colorCalculator;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }
}
