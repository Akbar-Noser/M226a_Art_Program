package ch.noseryoung.processing;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DiamondGeneratorThread extends Thread {
    private final Point startPoint;
    private int color;
    private final int sideLength;
    List<Color> colors;


    public DiamondGeneratorThread(Point startPoint, int color, int sideLength, List<Color> colors) {
        this.startPoint = startPoint;
        this.color = color;
        this.sideLength = sideLength;
        this.colors = colors;
    }

    /**
     * Main flow of the thread, every thread generates all the diamonds in an x-axis,
     * starting the next thread in the beginning for the line beneath itself if it's
     * partially still in bounds
     */
    @Override
    public void run() {
        if (checkDiamondBounds()) {
            Thread newLine = new DiamondGeneratorThread(new Point(0, startPoint.getY() + sideLength * 2 - 1), getDifferentColor(),
                    sideLength, colors);
            newLine.start();
            while (checkDiamondBounds()) {
                drawDiamond();
            }
            try {
                newLine.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * draws a single diamond
     */
    public void drawDiamond() {
        for (int i = 0; i < sideLength - 1; i++) {
            for (int j = -i; j <= i; j++) {
                try {
                    Controller.getCanvas().getCANVAS2D()[startPoint.getY() - (sideLength - 1) + i][startPoint.getX() + j] = color;
                } catch (IndexOutOfBoundsException e) {
                    //diamond has reached end of canvas, has to keep generating to make cut off diamonds
                }

            }
        }
        for (int i = -(sideLength - 1); i < sideLength; i++) {
            try {
                Controller.getCanvas().getCANVAS2D()[startPoint.getY()][startPoint.getX() + i] = color;
            } catch (IndexOutOfBoundsException e) {
                //diamond has reached end of canvas, has to keep generating to make cut off diamonds
            }
        }
        for (int i = 0; i < sideLength - 1; i++) {
            for (int j = -i; j <= i; j++) {
                try {
                    Controller.getCanvas().getCANVAS2D()[startPoint.getY() + (sideLength - 1) - i][startPoint.getX() + j] = color;
                } catch (IndexOutOfBoundsException e) {
                    //diamond has reached end of canvas, has to keep generating to make cut off diamonds
                }
            }
        }
        startPoint.setX(startPoint.getX() + sideLength * 2 - 1);
    }

    /**
     * checks the bounds of the diamond edges which will be created in relation to the
     * canvas
     * @return will return true if even one of the edges is in bounds
     */
    public boolean checkDiamondBounds() {
        int gapBetweenDiamond = sideLength - 1;
        return gridBounds(startPoint.getX() - gapBetweenDiamond, startPoint.getY()) ||
                gridBounds(startPoint.getX() + gapBetweenDiamond, startPoint.getY()) ||
                gridBounds(startPoint.getX(), startPoint.getY() - gapBetweenDiamond) ||
                gridBounds(startPoint.getX(), startPoint.getY() + gapBetweenDiamond);
    }


    /**
     * checks the bounds of a point in relation to the canvas
     * @param x coordinate of the x-axis, resembles the second array in the 2D array
     * @param y coordinate of the y-axis, resembles the first array in the 2D array
     * @return true if the point is inside the bounds of the canvas
     */
    public boolean gridBounds(int x, int y) {
        return x >= 0 && x < Controller.getCanvas().getCANVAS2D()[0].length &&
                y >= 0 && y < Controller.getCanvas().getCANVAS2D().length;
    }

    /**
     * loops through the list of colors to get a different color from the one used in this object
     * of DiamondGeneratorThread
     * @return the color differing from the one used in this thread
     */
    public int getDifferentColor() {
        int differentColor =  colors.get(ThreadLocalRandom.current().nextInt(colors.size())).getRGB();
        while (differentColor == color) {
            differentColor = colors.get(ThreadLocalRandom.current().nextInt(colors.size())).getRGB();
        }
        return differentColor;
    }

    public int getColor() {
        return color;
    }
}
