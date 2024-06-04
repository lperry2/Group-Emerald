package src.view;

import src.model.Project;
import src.model.User;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class AbstractPage extends JFrame {
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

    private String myTitle;

    private File myFile;

    protected User myCurrentUser;

    protected Project myCurrentProject;

    protected JLabel myTitleLabel;

    protected JPanel myTitlePanel;

    protected JLabel myContentLabel;

    protected JPanel myContentPanel;

    protected JPanel myButtonPanel;

    public AbstractPage(User theUser, String theProjectName, String theType) {
        super(theType);
        myFile = new File("src/" + theUser.getName() + "/" + theProjectName + "/" + theType + ".txt");
        myCurrentUser = theUser;
        System.out.println(theProjectName);
        myCurrentProject = theUser.getProject(theProjectName);
        setup();

    }

    private void setup()  {

        this.setLocation(SCREEN_WIDTH / 2 - SCREEN_WIDTH / 4,
                SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 4);
        this.setSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        //this.add(myPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
