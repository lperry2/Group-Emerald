package src.view;

import src.model.OptionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Scanner;

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

    /** The icon for project Pete. */
    private static final ImageIcon PAINT_ICON = new ImageIcon("src/project pete.png");

    // This is the version constant for the project
    public static String VERSION = "1.0";

    /** The JFrame that everything is built on. */
    private final JFrame myFrame;

    /** The panel that contains the username/email dialog boxes. */
    private final LoginPanel myUserInfo;

    /** The panel that contains the about btn and functionality*/
    private final AboutPanel myAboutPanel;

    /** The panel that contains the project search bar and functionality*/
    private final ProjectListPanel myPLPanel;

    /** The panel that contains the project list*/
    private final ProjectViewPanel myPVPanel;

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
        myPVPanel = new ProjectViewPanel();

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

        // replace the default JFrame icon
        myFrame.setIconImage(PAINT_ICON.getImage());

        myFrame.add(myUserInfo, BorderLayout.NORTH);

        // Set the JFrame to visible
        myFrame.setVisible(true);
    }

    public void dispose() {
        myFrame.dispose();
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
            nameField = new JTextField(15);
            emailField = new JTextField(15);
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
                /**
                 * Searches for the registered user. If one is found, then it changes
                 * to the project page. If not a JOptionPane pops up explaining no user
                 * was found.
                 *
                 * @param arg0 the event to be processed
                 * @author Owen Orlic
                 */
                public void actionPerformed(ActionEvent arg0) {
                    // adds the user to the users.txt file if not included
                    try (Scanner scan = new Scanner(new File("src/Users.txt"))) {

                        //marks if the user has been found yet
                        boolean notFound = true;
                        while (scan.hasNext() && notFound) {
                            //if the user has registered before
                            if (scan.next().equals(nameField.getText())) {
                                currentUser = nameField.getText();
                                currentEmail = emailField.getText();
                                notFound = false; //mark the user as found

                                //changes the frame
                                myUserInfo.setVisible(false);
                                myFrame.add(myAboutPanel, BorderLayout.SOUTH);
                                myFrame.add(myPLPanel, BorderLayout.NORTH);
                                myFrame.add(myPVPanel,BorderLayout.CENTER);
                            }
                        }

                        //if there is no registered user with that username
                        if (notFound) {
                            JOptionPane.showMessageDialog(null, "No User Found", "Sorry", JOptionPane.OK_OPTION);
                        }
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Issue Finding FilePath");
                    }

                    // Sets up the main/about panels
//                    myUserInfo.setVisible(false);
//                    myFrame.add(myAboutPanel, BorderLayout.SOUTH);
//                    myFrame.add(myPLPanel, BorderLayout.NORTH);
//                    myFrame.add(myPVPanel,BorderLayout.CENTER);
                }
            });
            this.add(loginBtn);

            JButton registerBtn = new JButton("Register");
            registerBtn.addActionListener(new ActionListener() {
                /**
                 * registers a user and creates their own directory.
                 *
                 * @param e the event to be processed
                 * @author Owen Orlic
                 */
                public void actionPerformed(ActionEvent e) {
                    addUser();

                    //make new directory for the user
                        File dir = new File("src/" + currentUser);
                        dir.mkdirs();

                        //try catch was being weird!! needs fixed
                        try (Scanner temp = new Scanner("src/Users.txt")) {
                            File projects = new File(dir, "Projects");

                            projects.createNewFile();
                            projects.mkdirs();


                            JOptionPane.showConfirmDialog(null, "Thank you for registering! Please sign in with your new credentials.");
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }

                }
            });
            this.add(registerBtn);
        }

        /** Adds the name and email to the users.txt file if not already included. */
        private void addUser() {
            //TODO: Implement the user data search feature (do not add if exists)
            try {
                PrintStream myStream  = new PrintStream(new FileOutputStream("src/Users.txt", true));
                myStream.println(nameField.getText() + " | " + emailField.getText());
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
        public String getCurrentEmail() {
            return currentEmail;
        }
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
                            "Lucas Perry - GitHub Guy\nDaniel Alberto Sanchez Aguilar - GUI Guy\nVersion: " + VERSION,
                            "About", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/project pete.png"));
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
                    projectLabel.setText("File not found");
                    projectLabel.setForeground(Color.RED);
                    projectLabel.setVisible(true);

                    // Trying to create a popup window to tell the user that the project was not found
//                    JLabel messageLabel = new JLabel("Project not found");
//                    messageLabel.setVisible(true);
//                    messageLabel.setForeground(Color.RED);
                }


            });
            this.add(searchBtn);

            JButton addProj = new JButton("Add Project");
            addProj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    CreateProjectFrame createFrame = new CreateProjectFrame();
                    createFrame.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if ("repaint".equals(evt.getPropertyName())) {
                                myPVPanel.updateProjectList();
                            }
                        }
                    });
                }
            });

            this.add(addProj);
        }
    }

    private final class ProjectViewPanel extends JPanel {
        public ProjectViewPanel() {
            setup();
        }

        private void setup() {
            this.setLayout(new GridLayout(0, 1));
            updateProjectList();
        }

        public void updateProjectList() {
            this.removeAll(); // Clear the panel

            JLabel projectLabel = new JLabel("Project List:");
            projectLabel.setHorizontalAlignment(JLabel.CENTER);
            this.add(projectLabel);

            Scanner sc = null;
            try {
                sc = new Scanner(new File("project_data.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] nameBudge = line.split("\t");
                String projName = nameBudge[0].split(": ")[1];
                //File f = new File("src/"+projName);
                JButton button = new JButton(line);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent theEvent) {
                        //if(f.isDirectory()) {
                            //System.out.println("here");
                            //class(file)
                        //}
                        new OptionFrame(projName);
                    }
                });

                this.add(button);
            }

            sc.close();


            this.revalidate(); // Revalidate the panel to update the UI
            super.repaint();// Repaint the panel to reflect changes
        }

    }
}
