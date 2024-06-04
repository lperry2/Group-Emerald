package src.view;

import src.model.User;

import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {
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

    private User myCurrentUser;

    private String mySearch;

    public SearchFrame(User theCurrentUser, String theSearch) {
        super("Hi");
        myCurrentUser = theCurrentUser;
        mySearch = theSearch;

        setup();
    }

    private void setup() {
        this.setLocation(SCREEN_WIDTH / SCALE,
                SCREEN_HEIGHT / SCALE);
        this.setSize(SCREEN_WIDTH/ SCALE, SCREEN_HEIGHT / SCALE);
        makeSearchList();
        this.setVisible(true);
    }

    private void makeSearchList() {
        String[] projects = myCurrentUser.getProjectNames();
        JPanel projectList = new JPanel();
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].equals(mySearch)) {
                JButton btn = new JButton(projects[i]);
                projectList.add(btn);
            }
        }
        this.add(projectList, BorderLayout.CENTER);
    }
}
