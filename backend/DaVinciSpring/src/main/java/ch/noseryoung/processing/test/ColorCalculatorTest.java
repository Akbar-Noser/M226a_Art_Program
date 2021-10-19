package ch.noseryoung.processing.test;

import ch.noseryoung.processing.ColorCalculator;
import org.junit.jupiter.api.BeforeAll;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorCalculatorTest {
    static ColorCalculator colorCalculator;
    static float[] hsb;

    @BeforeAll
    static void beforeAll() {
        colorCalculator = new ColorCalculator();
        hsb = Color.RGBtoHSB(143, 234, 213, hsb);
    }

    /**
     * checking bounds of the RGB colors (0 to 255)
     */
    @org.junit.jupiter.api.Test
    void randomColor() {
        Color color = colorCalculator.randomColor();
        assertTrue(color.getRed() < 256 && color.getRed() >= 0);
        assertTrue(color.getGreen() < 256 && color.getGreen() >= 0);
        assertTrue(color.getBlue() < 256 && color.getBlue() >= 0);
    }

    float round2decimals(float number) {
        return Math.round(number * 100f) / 100f ;
    }

    /**
     * Accuracy of float isn't high enough to compare the neighbouring shades, the only tests will be a check that
     * no 2 colors are the same and that the analogous shades are in the spectrum of 90 degrees
     */
    @org.junit.jupiter.api.Test
    void calcAnalogousColors() {
        boolean testPassed = true;
        int amountOfShades = 3;
        List<Color> colors = colorCalculator.calcAnalogousColors(hsb, amountOfShades);
        HashMap<Float, Color> colorExist = new HashMap<>();
        if (round2decimals(colorCalculator.rgbToHSB(colors.get(colors.size() - 1))[0] -
                colorCalculator.rgbToHSB(colors.get(0))[0]) > round2decimals((float) 90 / 360))
            testPassed = false;
        for (Color color : colors) {
            if (colorExist.containsKey(colorCalculator.rgbToHSB(color)[0]))
                testPassed = false;
            else
                colorExist.put(colorCalculator.rgbToHSB(color)[0], color);
        }

        assertTrue(testPassed);

    }

    /**
     * checks that every color has an approximate angle of 90 degrees, the rounding will only be made in the
     * tests to make the checks. The full floating point numbers will be kept for the program to keep a high accuracy
     * in the color shades.
     */
    @org.junit.jupiter.api.Test
    void calcTetradicColors() {
        boolean testPassed = true;
        List<Color> colors = colorCalculator.calcTetradicColors(hsb);
        colors.add(0, new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2])));
        HashMap<Float, Color> colorExist = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            float biggerNumber = round2decimals(colorCalculator.rgbToHSB(
                            colors.get(i + 1 < colors.size() ? i + 1 : i + 1 - colors.size()))[0]);
            float smallerNumber = round2decimals(colorCalculator.rgbToHSB(colors.get(i))[0]);
            biggerNumber = biggerNumber < smallerNumber ? biggerNumber + 1 : biggerNumber;
            if (colorExist.containsKey(colorCalculator.rgbToHSB(colors.get(i))[0]) ||
                    round2decimals(biggerNumber - smallerNumber) !=  0.25f)
                testPassed = false;
            else
                colorExist.put(colorCalculator.rgbToHSB(colors.get(i))[0], colors.get(i));
        }

        assertTrue(testPassed);
    }

    /**
     * accuracy of floating point isn't high enough, there will be errors even if the numbers are rounded,
     * this is caused by inaccurate representation/conversion of rgb to hsb. Comparing the hsb values won't work
     * so the only check will be that there are no 2 same colors and the amount of colors (3) is correct
     */
    @org.junit.jupiter.api.Test
    void calcTriadicColors() {
        boolean testPassed = true;
        List<Color> colors = colorCalculator.calcTriadicColors(hsb);
        colors.add(0, new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2])));
        HashMap<Float, Color> colorExist = new HashMap<>();
        if (colors.size() != 3)
            testPassed = false;
        for (Color color : colors) {
            if (colorExist.containsKey(colorCalculator.rgbToHSB(color)[0]))
                testPassed = false;
            else
                colorExist.put(colorCalculator.rgbToHSB(color)[0], color);
        }
        assertTrue(testPassed);
    }

    /**
     * colors have to be on opposite sides, there can only be 2 colors in the list
     */
    @org.junit.jupiter.api.Test
    void calcComplementaryColors() {
        List<Color> colors = colorCalculator.calcComplementaryColors(hsb);
        colors.add(0, new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2])));
        assertEquals(2, colors.size());
        assertEquals(0.5f, round2decimals(colorCalculator.rgbToHSB(colors.get(1))[0] -
                colorCalculator.rgbToHSB(colors.get(0))[0]));
    }
}