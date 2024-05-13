package src.model;

import src.view.MainGUI;

/**
 * The driver for the Project Partner Application.
 *
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v1.00
 */
public class Driver {
    /* private constructor to inhibit instantiation. */
    private Driver() {}

    /**
     * The start point for the Project Partner application.
     *
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String[] theArgs) {
        new MainGUI();
    }
}
