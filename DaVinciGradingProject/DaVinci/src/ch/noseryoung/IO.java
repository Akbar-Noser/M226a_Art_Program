package ch.noseryoung;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Contains all used inputs and outputs including error outputs
 * Exception output methods always start with "exception" in the name
 * Where Input is given, the method will start with "get"
 */
public class IO {
    private Scanner sc;

    public IO() {
        sc = new Scanner(System.in);
    }

    public void exceptionChooseAnOption() {
        System.out.println("You have to choose an appropriate option the numbers in the brackets []");
    }

    public void exceptionSmallerThanMin(int min) {
        System.out.println("Number can't be smaller than " + min);
    }

    public void exceptionNotAnInt() {
        System.out.println("Input must be a number");
    }


    public int getStripeThickness() throws InputMismatchException{
        System.out.println("Please enter the weight for the stripes");
        return sc.nextInt();
    }

    public int getPicturePattern() throws InputMismatchException {
        System.out.println("""
                    [0] Quit
                    [1] Create Template
                    [2] Create Stripes
                    [3] Create Diamonds""");

        return sc.nextInt();
    }

    public int getAmountOfShades() throws InputMismatchException {
        System.out.println("Enter amount of shades: ");
        return sc.nextInt();
    }

    public int getLayout() throws InputMismatchException {
        System.out.println("""
                [0] Quit
                [1] Analogous Colors
                [2] Tetradic Colors
                [3] Triadic Colors
                [4] Complementary Colors""");
        return sc.nextInt();
    }

    public int getDiamondSideLength() throws InputMismatchException {
        System.out.println("Enter side length: ");
        return sc.nextInt();
    }

    public Scanner getSc() {
        return sc;
    }
}
