package src.model;

import src.view.MainGUI;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The driver for the Project Partner Application.
 *
 * @author Alex Ewing
 * @version v1.00
 */
public class Driver {
    /**
     * private constructor to inhibit instantiation.
     * @author Alex Ewing
     */
    private Driver() {}

    /**
     * The start point for the Project Partner application.
     *
     * @param theArgs command line arguments - ignored in this program
     * @author Alex Ewing
     */
    public static void main(final String[] theArgs) throws FileNotFoundException {
        new MainGUI();
    }
}
