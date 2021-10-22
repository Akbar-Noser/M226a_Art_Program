package ch.noseryoung.inputformat;

import java.util.ArrayList;

public class CreatePictureInput {
    private String colorScheme;
    private int amountOfShades;
    private String layout;
    private boolean evenSpacing;
    private int stripeThickness;
    private ArrayList<Integer> layers;


    /**
     * Scheme: Analogous
     * Layout: Color Template
     */
    public CreatePictureInput(String colorScheme, int amountOfShades, String layout) {
        this.colorScheme = colorScheme;
        this.amountOfShades = amountOfShades;
        this.layout = layout;
    }
    /**
     * Scheme: Analogous
     * Layout: Stripes
     */
    public CreatePictureInput(String colorScheme, int amountOfShades, String layout, boolean evenSpacing, int stripeThickness) {
        this.colorScheme = colorScheme;
        this.amountOfShades = amountOfShades;
        this.layout = layout;
        this.evenSpacing = evenSpacing;
        this.stripeThickness = stripeThickness;
    }

    /**
     * Scheme: Analogous
     * Layout: Diamonds
     */
    public CreatePictureInput(String colorScheme, int amountOfShades, String layout, ArrayList<Integer> layers) {
        this.colorScheme = colorScheme;
        this.amountOfShades = amountOfShades;
        this.layout = layout;
        this.layers = layers;
    }

    /**
     * Scheme: all except analogous
     * Layout: Stripes
     */
    public CreatePictureInput(String colorScheme, String layout, boolean evenSpacing, int stripeThickness) {
        this.colorScheme = colorScheme;
        this.layout = layout;
        this.evenSpacing = evenSpacing;
        this.stripeThickness = stripeThickness;
    }

    /**
     * Scheme: all except analogous
     * Layout: Color Template
     */
    public CreatePictureInput(String colorScheme, String layout) {
        this.colorScheme = colorScheme;
        this.layout = layout;
    }


    /**
     * Scheme: all except analogous
     * Layout: Diamonds
     */
    public CreatePictureInput(String colorScheme, String layout, ArrayList<Integer> layers) {
        this.colorScheme = colorScheme;
        this.layout = layout;
        this.layers = layers;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public int getAmountOfShades() {
        return amountOfShades;
    }

    public void setAmountOfShades(int amountOfShades) {
        this.amountOfShades = amountOfShades;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public boolean isEvenSpacing() {
        return evenSpacing;
    }

    public void setEvenSpacing(boolean evenSpacing) {
        this.evenSpacing = evenSpacing;
    }

    public int getStripeThickness() {
        return stripeThickness;
    }

    public void setStripeThickness(int stripeThickness) {
        this.stripeThickness = stripeThickness;
    }

    public ArrayList<Integer> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<Integer> layers) {
        this.layers = layers;
    }
}
