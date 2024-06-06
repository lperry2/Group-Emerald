package src.view;

import src.model.Project;
import src.model.User;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public abstract class AbstractPage extends JFrame {
    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 2;
    /**
     * A ToolKit.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The Dimension of the screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** The current user. */
    protected User myCurrentUser;

    /** The current project. */
    protected Project myCurrentProject;

    /** The title label. */
    protected JLabel myTitleLabel;

    /** The title panel. */
    protected JPanel myTitlePanel;

    /** The content label. */
    protected JLabel myContentLabel;

    /** The content label. */
    protected JPanel myContentPanel;

    /** The button panel. */
    protected JPanel myButtonPanel;

    /**
     * The public constructor for the AbstractPage class.
     * @param theUser The user
     * @param theProjectName The project name
     * @param theType The type
     */
    public AbstractPage(User theUser, String theProjectName, String theType) {
        super(theType);
        myCurrentUser = theUser;
        System.out.println(theProjectName);
        myCurrentProject = theUser.getProject(theProjectName);
        setup();

    }

    /**
     * The method that sets up the page.
     */
    private void setup()  {

        this.setLocation(SCREEN_WIDTH / 2 - SCREEN_WIDTH / 4,
                SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 4);
        this.setSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
