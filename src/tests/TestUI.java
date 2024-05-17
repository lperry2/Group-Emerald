package src.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import src.view.MainGUI;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UI and components.
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 */
public class TestUI {
    // The main GUI object for testing
    private MainGUI mainGUI;

    /**
     * Set up the test fixture.
     */
    @BeforeEach
    public void setUp() {
        mainGUI = new MainGUI();
    }

    /**
     * Clean up after each test.
     */
    @AfterEach
    public void tearDown() {
        // Clean up or reset the UI state after each test
        mainGUI.dispose();
    }

    /**
     * Test the UI components.
     */
    @Test
    public void testUIComponents() {
        // Test the initial state of the UI
        // Verify that the UI components are rendered correctly
        assertNotNull(MainGUI.VERSION);
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
