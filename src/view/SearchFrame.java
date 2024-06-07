package src.view;

import src.model.Project;
import src.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JFrame that is used for the search function. Uses buttons
 * to allow the user to open a project from the frame.
 *
 * @author Owen Orlic
 */
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

    /** User of the application. */
    private User myCurrentUser;

    /** The search word. */
    private String mySearch;

    /**
     * Title's the frame "Here's What We Found" and calls setup().
     *
     * @author Owen Orlic
     * @param theCurrentUser user using the application
     * @param theSearch what the user searched
     */
    public SearchFrame(User theCurrentUser, String theSearch) {
        super("Here's What We Found");
        myCurrentUser = theCurrentUser;
        mySearch = theSearch;

        setup();
    }

    /**
     * Sets the location and size of the search frame, makes the
     * search list, and makes the frame visible. Adds the back button
     * to the frame.
     *
     * @author Owen Orlic
     */
    private void setup() {
        this.setLocation(SCREEN_WIDTH / SCALE,
                SCREEN_HEIGHT / SCALE);
        this.setSize(SCREEN_WIDTH/ SCALE, SCREEN_HEIGHT / SCALE);
        makeSearchList();

        JPanel btnPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchFrame.this.dispose();
            }
        });
        btnPanel.add(backBtn, new FlowLayout());
        this.add(btnPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Takes whatever was searched and looks for any project that has
     * that search word anywhere in its name. If it does, then a button is made
     * with that project name and it is added to the projectList panel.
     * The buttons are passed to addBtnAction to give them the ability to open projects.
     *
     * @author Owen Orlic
     */
    private void makeSearchList() {
        String[] projects = myCurrentUser.getProjectNames();
        JPanel projectList = new JPanel(new GridLayout(0, 1, 0, 4));

        int searchLen = mySearch.length();
        boolean oneFound = false;
        for (int i = 0; i < projects.length; i++) {
            String name = projects[i];
            int j = 0;
            while (j <= projects[i].length() - searchLen) {
                //checks if the search is a part of a project name or if it is the project name itself
                if (name.length() >= mySearch.length() && name.substring(j, j + searchLen).equals(mySearch)
                        || name.equals(mySearch)) {

                    String displayName = projects[i];
                    if (projects[i].substring(0, 1).equals("~")) {
                        displayName = displayName.substring(1, displayName.length() - 4);
                    }
                    JButton btn = new JButton(displayName);
                    addBtnAction(btn, projects[i]);
                    projectList.add(btn);
                    oneFound = true;
                    break;
                }
                j++;
            }
        }
        if (!oneFound) {
            JLabel label = new JLabel("Sorry, no projects found.", 0);
            projectList.add(label, BorderLayout.CENTER);
        }
        this.add(projectList);
    }

    /**
     * Makes it so these buttons can open up projects. Looks to see if a
     * project is private to see if it should prompt for the pin before
     * opening.
     *
     * @author Owen Orlic
     * @param theButton the button that needs its action listener
     * @param theProjectName the name of the project the button will open
     */
    private void addBtnAction(JButton theButton, String theProjectName) {
        String pin = "-1";
        //if the project is private
        if (theProjectName.charAt(0) == '~') {
            Project proj = myCurrentUser.getProject(theProjectName);
            pin = proj.getPin();
        }

        String finalPin = pin;
        theButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean enteredCorrectly = false;
                while (!enteredCorrectly && '~' == theProjectName.charAt(0)) {
                    String givenPin = (String) JOptionPane.showInputDialog(null,"Please Enter PIN",
                                                              "Locked Project", JOptionPane.INFORMATION_MESSAGE,
                                                              makePeteSmall(), null, null);
                    if (givenPin.equals(finalPin)) {
                        enteredCorrectly = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "PIN Not Recognized. Please Enter Again.",
                                "Uh Oh", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());

                    }
                }

                new OptionFrame(myCurrentUser, theProjectName);
                SearchFrame.super.dispose();
            }
        });
    }

    /**
     * Method that returns a small pete for JOptionPanes.
     *
     * @author Daniel
     * @return little version of pete
     */
    private static ImageIcon makePeteSmall() {
        //code to make pete normal-sized
        ImageIcon icon = new ImageIcon("src/images/projectpete.png");
        Image img = icon.getImage();
        // Resize the image to 50x50 pixels
        Image resizedImg = img.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        return resizedIcon;
    }

}
