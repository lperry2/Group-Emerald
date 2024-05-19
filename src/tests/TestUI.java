package src.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import src.view.MainGUI;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UI and components.
 *
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 */
public class TestUI {

    /*
    "Aiming to test private variables is the first sign that something wrong with the current design.
    Private variables are part of the implementation, tests should focus on behavior rather of implementation details."
        -Someone on Stack Overflow
     */

    /** The main GUI object for testing */
    private MainGUI testGUI;

    /**
     * Set up the test fixture.
     */
    @BeforeEach
    public void setUp() {
        testGUI = new MainGUI();
    }

    /**
     * Clean up after each test.
     */
    @AfterEach
    public void tearDown() {
        // Clean up or reset the UI state after each test
        testGUI.dispose();
    }

    /**
     * Test the UI components.
     */
    @Test
    public void testUIComponents() {
        // Test the initial state of the UI
        // Verify that the UI components are rendered correctly
    }

    /**
     * Test the visibility of UI components.
     */
    @Test
    public void testUIComponentVisibility() {

    }

    /**
     * Test the login button click.
     */
    @Test
    public void testLoginButtonClick() {

    }

}
