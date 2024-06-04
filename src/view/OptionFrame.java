package src.view;

import src.model.User;

import javax.swing.*;
import java.awt.*;

public class OptionFrame extends JFrame {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    private JMenuBar myMenu;

    private String myProjName;

    private User myCurrentUser;

    public OptionFrame(User theUser, String theProjectName) {

        myProjName = checkIfPrivate(theProjectName);
        myCurrentUser = theUser;

        myMenu = new MenuBar(myProjName, myCurrentUser);
        this.setJMenuBar(myMenu);

        setup();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setup() {
        //set to cover the previous frame
        this.setLocation(SCREEN_WIDTH / 2 - SCREEN_WIDTH / 4,
                SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 4);
        this.setSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);


    }

    /**
     * Used to check if a project is private by seeing if the last
     * character of the project name is a number.
     *
     * @author Owen Orlic
     * @param theName a project name
     * @return if the project is private
     */
    private static String checkIfPrivate(String theName) {
        //don't include pin numbers in project name
        int len = theName.length();
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 10; i++) {

            //checks if there is a number in the name
            //means no numbers allowed in project names for now!!!
            if (theName.contains(nums[i])) {
                theName = theName.substring(0, len - 4);

            }
        }
        return theName;
    }
}
