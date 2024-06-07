package src.view;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractSelectionFrame extends JFrame {

    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 3;

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** Makes the frame used for editing. */
    public static final int EDIT_OPTION = 0;

    /** Makes the frame used for deleting. */
    public static final int DELETE_OPTION = 1;

    /** Panel that will hold all the buttons with the ExpenseItem's names on them. */
    protected JPanel myPanel;

    /**
     * This denotes whether this is an edit frame or a delete frame.
     * Edit frames are chosen with 0 and delete frames are chosen with 1.
     * If neither 0 or 1 is used, 0 is automatically selected.
     */
    protected int myOption;

    /**
     * A frame for selecting items (ExpenseItem, JournalEntry, SingleFile). theOption denotes
     * whether this is used for editing or deleting.
     *
     * @param theTitle title of the frame
     * @param theOption denotes whether frame used for editing or deleting
     */
    public AbstractSelectionFrame(String theTitle, int theOption) {
        super(theTitle);
        myPanel = new JPanel(new GridLayout(0, 1, 0, 4));
        if (theOption == 0 || theOption == 1) {
            myOption = theOption;
        } else {
            myOption = 0;
        }

        setup();
    }

    /**
     * Sets the location and size of the search frame, makes the
     * search list, and makes the frame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {
        this.setLocation(SCREEN_WIDTH / SCALE,
                SCREEN_HEIGHT / SCALE);
        this.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);
        this.add(myPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
