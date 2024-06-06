package src.view;

import src.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The frame that allows us to open the budget, file, or journal frame
 * @author Daniel Sanchez, Owen Orlic
 */
public class OptionFrame extends JFrame {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** The menu bar for the JFrame. */
    private JMenuBar myMenu;

    /** The name of the project being worked on. */
    private String myProjName;

    /** The current user of the application. */
    private User myCurrentUser;

    /**
     * The constructor for the option frame
     * @param theUser the name of the current user
     * @param theProjectName the name of the current project
     */
    public OptionFrame(User theUser, String theProjectName) {
        super(checkIfSqwiggle(checkIfPrivate(theProjectName)));
        myProjName = checkIfPrivate(theProjectName);

        myCurrentUser = theUser;

        myMenu = new MenuBar(myProjName, myCurrentUser);
        this.setJMenuBar(myMenu);

        setup();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * sets up the dimensions of the optionframe
     * @author Daniel Sanchez, Owen Orlic
     */
    public void setup() {
        //set to cover the previous frame
        this.setLocation(SCREEN_WIDTH / 2 - SCREEN_WIDTH / 4,
                SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 4);
        this.setSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        JPanel btnPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionFrame.this.dispose();
            }
        });
        btnPanel.add(backBtn, new FlowLayout());
        this.add(btnPanel, BorderLayout.SOUTH);


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

    private static String checkIfSqwiggle(String theName) {
        if (theName.charAt(0) == '~') {
            theName = theName.substring(1);
        }
        return theName;
    }
}
