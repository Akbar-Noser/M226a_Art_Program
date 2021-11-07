package ch.noseryoung;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Does the error handling for the IO class
 * the IO class should only be called through this
 * class as the direct usage of IO is unsafe
 */
public class ExceptionHandlerIO {
    private IO io;

    public ExceptionHandlerIO() {
        io = new IO();
    }

    /**
     * Checks that input is a number and that it is in bounds
     *
     * @param smallestOption smallest acceptable Input
     * @param largestOption largest acceptable Input
     * @return user input that passed all checks
     */
    public int handleLayoutInput(int smallestOption, int largestOption) {
        int answer = smallestOption - 1;
        while (answer < smallestOption || answer > largestOption) {
            try {
                answer = io.getLayout();
                if (answer < smallestOption || answer > largestOption) {
                    io.exceptionChooseAnOption();
                }
            } catch (InputMismatchException e) {
                consumeNewLineBuffer();
                io.exceptionChooseAnOption();
            }
        }
        return answer;
    }

    /**
     * Checks that user input is a number and larger than 0
     *
     * @return user input (positive integer)
     */
    public int handleAmountOfShadesInput() {
        int answer = 0;
        while (answer < 1) { //amount can't be 0 or less, the picture would end up empty
            try {
                answer = io.getAmountOfShades();
                if (answer < 1) {
                    io.exceptionSmallerThanMin(1);
                }
            } catch (InputMismatchException e) {
                consumeNewLineBuffer();
                io.exceptionNotAnInt();
            }
        }
        return answer;
    }

    /**
     * Checks that user input is a number and larger than 0
     *
     * @return user input (positive integer)
     */
    public int handleStripeThicknessInput() {
        int answer = 0;
        while (answer < 1) { //thickness can't be 0 or less, the picture would end up empty
            try {
                answer = io.getStripeThickness();
                if (answer < 1) {
                    io.exceptionSmallerThanMin(1);
                }
            } catch (InputMismatchException e) {
                consumeNewLineBuffer();
                io.exceptionNotAnInt();
            }
        }
        return answer;
    }

    /**
     * Checks that input is a number and that it is in bounds
     *
     * @param smallestOption smallest acceptable Input
     * @param largestOption largest acceptable Input
     * @return user input that passed all checks
     */
    public int handlePicturePatternInput(int smallestOption, int largestOption) {
        int answer = smallestOption - 1;
        while (answer < smallestOption || answer > largestOption) {
            try {
                answer = io.getPicturePattern();
                if (answer < smallestOption || answer > largestOption) {
                    io.exceptionChooseAnOption();
                }
            } catch (InputMismatchException e) {
                consumeNewLineBuffer();
                io.exceptionChooseAnOption();
            }
        }
        return answer;
    }

    /**
     * Checks that user input is a number and larger than 0
     *
     * @return user input (positive integer)
     */
    public int handleDiamondSideLengthInput() {
        int answer = 0;
        while (answer < 1) { //side length can't be 0 or less, the picture would end up empty
            try {
                answer = io.getDiamondSideLength();
                if (answer < 1) {
                    io.exceptionSmallerThanMin(1);
                }
            } catch (InputMismatchException e) {
                consumeNewLineBuffer();
                io.exceptionNotAnInt();
            }
        }
        return answer;
    }

    /**
     * consumes new line buffer of input stream linked to local io
     */
    private void consumeNewLineBuffer() {
        io.getSc().nextLine();
    }
}
