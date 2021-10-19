package ch.noseryoung.processing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ColorCalculator {

    /**
     * creates a random RGB Color
     * @return a Color object with set RGB values
     */
    public Color randomColor () {
        return new Color(ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256));
    }

    /**
     * Converts an RGB object to a hsb float array
     *
     * @param initialColour a color object with set RGB Values
     * @return a float array with 3 elements containing HSB values
     */
    public float[] rgbToHSB (Color initialColour) {
        return Color.RGBtoHSB(initialColour.getRed(), initialColour.getGreen(), initialColour.getBlue(), null);
    }


    /**
     * Creates a list of colors, which resemble the analogous color theory
     * (splits the analogous spectrum evenly with the amountOfShades)
     *
     * @param hsbValue hsb values of the initial value resembles the shade in the middle
     * @param amountOfShades amount of color objects stored in the returned list
     * @return list of colors, size=amountOfShades
     */
    public List<Color> calcAnalogousColors(float[] hsbValue, int amountOfShades) {
        double degrees =  (double) 90 / amountOfShades;
        long initialColourDegrees = Math.round((double) amountOfShades / 2);
        List<Color> colors = new ArrayList<>();
        for (int i = 1; i <= amountOfShades; i++) {
            colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float) (i * degrees - initialColourDegrees * degrees)
                    / 360, hsbValue[1], hsbValue[2])));
        }
        return colors;
    }

    /**
     * Creates a list of colors based on the tetradic color scheme
     *
     *
     * @param hsbValue the initial color in HSB form (base of tetradic scheme)
     * @return A list with the size of 4 containing Color objects
     */
    public List<Color> calcTetradicColors(float[] hsbValue) {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float)90 / 360, hsbValue[1], hsbValue[2])));
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float)180 / 360, hsbValue[1], hsbValue[2])));
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float)270 / 360, hsbValue[1], hsbValue[2])));
        return colors;
    }

    /**
     * Creates a list of colors based on the triadic color scheme
     *
     *
     * @param hsbValue the initial color in HSB form (base of triadic scheme)
     * @return A list with the size of 3 containing Color objects
     */
    public List<Color> calcTriadicColors(float[] hsbValue) {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float)120 / 360, hsbValue[1], hsbValue[2])));
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + (float)240 / 360, hsbValue[1], hsbValue[2])));
        return colors;
    }

    /**
     * Creates a list of colors based on the complementary color scheme
     *
     * @param hsbValue the initial color in HSB form (base of complementary scheme)
     * @return A list with the size of 2 containing Color objects
     */
    public List<Color> calcComplementaryColors(float[] hsbValue) {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(Color.HSBtoRGB(hsbValue[0] + 0.5f, hsbValue[1], hsbValue[2])));
        return colors;
    }
}
