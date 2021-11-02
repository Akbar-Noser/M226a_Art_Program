package ch.noseryoung;

public class Canvas {
    private static final int CANVAS_X = 3840;
    private static final int CANVAS_Y = 2160;
    private final int[][] CANVAS2D = new int[CANVAS_Y][CANVAS_X];

    public static int getCanvasX() {
        return CANVAS_X;
    }

    public static int getCanvasY() {
        return CANVAS_Y;
    }

    public int[][] getCANVAS2D() {
        return CANVAS2D;
    }
}
