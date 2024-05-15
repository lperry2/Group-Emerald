package src.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import src.view.MainGUI;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the UI and components.
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 */
public class TestUI {

    // Declare any necessary variables or objects for the tests right here
    private MainGUI mainGUI;

    @BeforeEach
    public void setUp() {
        mainGUI = new MainGUI();
    }

    @AfterEach
    public void tearDown() {
        // Clean up or reset the UI state after each test
        mainGUI.dispose();
    }

    @Test
    public void testUI() {
        // Test the initial state of the UI
        // Verify that the UI components are rendered correctly

        // Simulate user interactions (e.g., button clicks, text input)
        // Verify the UI behavior and state after each interaction

        // Test edge cases or error scenarios
        // Verify the UI handles these cases correctly
    }

    @Test
    public void testUIComponentVisibility() {
        // Test the visibility of UI components
        // Verify that the UI components are rendered correctly
//        JPanel loginPanel = mainGUI.myUserInfo;
//        assertTrue(loginPanel.isVisible());
    }

    @Test
    public void testLoginButtonClick() {
//        JButton loginButton = (JButton) mainGUI.myUserInfo.getComponent(4); // Assuming the "Login" button is the 5th component
//        loginButton.doClick();
//
//        // Perform assertions on the expected state after the button click
//        assertFalse(mainGUI.myUserInfo.isVisible());
//        assertTrue(mainGUI.myAboutPanel.isVisible());
//        assertTrue(mainGUI.myPLPanel.isVisible());
    }

}
