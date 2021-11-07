package ch.noseryoung.inputformat;

import java.util.ArrayList;

public class CreatePictureInput {
    private String colorScheme;
    private int amountOfShades;
    private String layout;
    private boolean evenSpacing;
    private int stripeThickness;
    private int[] layers;


    public CreatePictureInput(String colorScheme, int amountOfShades, String layout, boolean evenSpacing, int stripeThickness, int[] layers) {
        this.colorScheme = colorScheme;
        this.amountOfShades = amountOfShades;
        this.layout = layout;
        this.evenSpacing = evenSpacing;
        this.stripeThickness = stripeThickness;
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

public int[] getLayers() {
    return layers;
}

public void setLayers(int[] layers) {
    this.layers = layers;
}
}
