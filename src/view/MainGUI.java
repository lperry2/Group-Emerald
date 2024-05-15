package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * The main GUI class for the Project Partner Application.
 *
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v1.00
 */
public class MainGUI {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 2;

    /** This is the title of the Application. */
    private static final String APP_TITLE = "Project Partner";

    // TODO: replace this file path with a new one later
    ///** The icon for the Paint Program. */
    //private static final ImageIcon PAINT_ICON = new ImageIcon("files/Paint-Palette.png");

    // This is the version constant for the project
    public static String VERSION = "1.0";

    /** The JFrame that everything is built on. */
    private final JFrame myFrame;

    /** The panel that contains the username/email dialog boxes. */
    LoginPanel myUserInfo;

    /** The panel that contains the about btn and functionality*/
    AboutPanel myAboutPanel;

    /** The panel that contains the project search bar and functionality*/
    ProjectListPanel myPLPanel;

    /**
     * The no argument constructor for the PaintGUI class
     * that initializes the fields and sets up the frame.
     */
    public MainGUI() {
        super();

        myFrame = new JFrame(APP_TITLE);
        myUserInfo = new LoginPanel();
        myAboutPanel = new AboutPanel();
        myPLPanel = new ProjectListPanel();

        // setup and display the GUI
        start();
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    private void start() {
        // Set the size of the JFrame to 1/2 (current scaling factor) of the screen
        myFrame.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);

        // Set the location of the JFrame to the center
        myFrame.setLocation(SCREEN_WIDTH / 2 - myFrame.getWidth() / 2,
                SCREEN_HEIGHT / 2 - myFrame.getHeight() / 2);

        // Set the default close operation to close on exit
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Replace icon using the constant PAINT_ICON
        // replace the default JFrame icon
        //myFrame.setIconImage(PAINT_ICON.getImage());

        myFrame.add(myUserInfo, BorderLayout.NORTH);

        // Set the JFrame to visible
        myFrame.setVisible(true);
    }

    /**
     * Represents the login and user info section of the GUI.
     *
     * @version 1.00
     * */
    private final class LoginPanel extends JPanel {
        /** Text field for the username . */
        private final JTextField nameField;
        /** Text field for the email. */
        private final JTextField emailField;
        /** Current username. */
        private String currentUser;
        /** Current email. */
        private String currentEmail;

        /** The no args constructor for the LoginPanel class. */
        public LoginPanel() {
            currentUser = "";
            currentEmail = "";
            nameField = new JTextField(20);
            emailField = new JTextField(20);
            setup();
        }

        /** Sets up the LoginPanel. */
        private void setup() {
            JLabel userNameLabel = new JLabel("Username");
            this.add(userNameLabel);
            this.add(nameField);

            JLabel userEmailLabel = new JLabel("Email");
            this.add(userEmailLabel);
            this.add(emailField);

            JButton loginBtn = new JButton("Login");
            loginBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // adds the user to the users.txt file if not included
                    addUser();

                    // Sets up the main/about panels
                    myUserInfo.setVisible(false);
                    myFrame.add(myAboutPanel, BorderLayout.SOUTH);
                    myFrame.add(myPLPanel, BorderLayout.NORTH);
                }
            });
            this.add(loginBtn);
        }

        /** Adds the name and email to the users.txt file if not already included. */
        private void addUser() {
            //TODO: Implement the user data search feature (do not add if exists)
            try {
                PrintStream myStream  = new PrintStream(new FileOutputStream("src/Users.txt", true));
                myStream.println(nameField.getText() + "|" + emailField.getText());
                myStream.close();
                currentUser = nameField.getText();
                currentEmail = emailField.getText();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        /**
         * Gets the current Username.
         *
         * @return String currentUser the current username
         */
        public String getCurrentUser() {
            return currentUser;
        }

        /**
         * Gets the current Email.
         *
         * @return String currentEmail the current username
         */
        public String getCurrentEmail(){return currentEmail;}
    }

    /**
     * Represents the about section of the GUI.
     *
     * @version 1.00
     * */
    private final class AboutPanel extends JPanel {

        /** The no args constructor for the AboutPanel class. */
        public AboutPanel() {
            setup();
        }

        /** Sets up the AboutPanel. */
        private void setup() {
            JButton aboutBtn = new JButton("About");
            this.add(aboutBtn);
            aboutBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    JOptionPane.showMessageDialog(null, "This app is registered to: " + myUserInfo.getCurrentUser() +"\n" +
                            "This app provided by: Team Emerald\nAlexander Dean Ewing - Alex for short\nOwen Orlic - He's cool\n" +
                            "Lucas Perry - GitHub Guy\nDaniel Alberto Sanchez Aguilar - GUI Guy\nVersion: " + VERSION);
                }
            });
        }
    }

    /**
     * Represents the project search section of the GUI.
     *
     * @version 1.00
     * */
    private final class ProjectListPanel extends JPanel {
        /** Text field for the searched project. */
        private final JTextField projectField;

        /** Current searched project. */
        private String currentProject;

        /** The no args constructor for the ProjectListPanel class. */
        public ProjectListPanel() {
            projectField = new JTextField(20);
            currentProject = "";
            setup();
        }

        /** Sets up the Project searchPanel. */
        private void setup() {
            JLabel projectLabel = new JLabel("Project");
            this.add(projectLabel);
            this.add(projectField);

            // TODO: Implement create project option

            JButton searchBtn = new JButton("Search");
            searchBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // TODO: Implement Project Search
                }
            });
            this.add(searchBtn);
        }
    }
}
