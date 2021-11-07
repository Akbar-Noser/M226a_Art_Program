package ch.noseryoung.tests;

import ch.noseryoung.Canvas;
import ch.noseryoung.Controller;
import ch.noseryoung.DiamondGeneratorThread;
import ch.noseryoung.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiamondGeneratorThreadTest {
    DiamondGeneratorThread diamondGeneratorThread;
    Controller controller;
    int lengthX;
    int lengthY;

    @BeforeEach
    void setUp() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(51, 231, 164));
        colors.add(new Color(151, 21, 114));
        diamondGeneratorThread = new DiamondGeneratorThread(new Point(0, 0),
                colors.get(0).getRGB(), 5, colors);
        controller = new Controller();
        lengthX = Canvas.getCanvasX();
        lengthY = Canvas.getCanvasY();
    }

    /**
     * checking the outer bounds (edges of the grids)
     */
    @Test
    void gridBounds() {
        assertFalse(diamondGeneratorThread.gridBounds(-1, 0));
        assertFalse(diamondGeneratorThread.gridBounds(0, -1));
        assertFalse(diamondGeneratorThread.gridBounds(lengthX, 0));
        assertFalse(diamondGeneratorThread.gridBounds(0, lengthY));
        assertTrue(diamondGeneratorThread.gridBounds(0, 0));
        assertTrue(diamondGeneratorThread.gridBounds(lengthX - 1, lengthY - 1));
    }

    /**
     * return value doesn't equal current color
     */
    @Test
    void getDifferentColour() {
        assertNotEquals(diamondGeneratorThread.getColor(), diamondGeneratorThread.getDifferentColor());
    }
}