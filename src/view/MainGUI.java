package src.view;

import src.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

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
    private static final ImageIcon PAINT_ICON = new ImageIcon("src/images/projectpete.png");

    /** The JFrame that everything is built on. */
    private final JFrame myFrame;

    /** The panel that contains the username/email dialog boxes. */
    private final LoginPanel myUserInfo;

    /** The panel that contains the about btn and functionality*/
    private final AboutPanel myAboutPanel;

    /** The panel that contains the project search bar and functionality*/
    private final ProjectListPanel myPLPanel;

    /** The panel that contains the project list*/
    private ProjectViewPanel myPVPanel;

    private User myUser;

    /**
     * The no argument constructor for the PaintGUI class
     * that initializes the fields and sets up the frame.
     * @author Alex Ewing
     */
    public MainGUI() {
        super();

        myFrame = new JFrame(APP_TITLE);
        myUserInfo = new LoginPanel();
        myAboutPanel = new AboutPanel();
        myPLPanel = new ProjectListPanel();
        myPVPanel = null;

        // setup and display the GUI
        start();
    }

    /**
     * Performs all tasks necessary to display the UI.
     * @author Alex Ewing
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
     * @author Alex Ewing
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

        /**
         * Sets up the LoginPanel.
         * @author Alex Ewing
         */
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
                 * @author Owen Orlic
                 * @param arg0 the event to be processed
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

                                makeUser(currentUser, currentEmail);

                                //changes the frame
                                myUserInfo.setVisible(false);
                                myFrame.add(myAboutPanel, BorderLayout.SOUTH);
                                myFrame.add(myPLPanel, BorderLayout.NORTH);
                                myPVPanel = new ProjectViewPanel();
                                myFrame.add(myPVPanel,BorderLayout.CENTER);
                            }
                        }

                        //if there is no registered user with that username
                        if (notFound) {
                            JOptionPane.showMessageDialog(null, "No User Found", "Sorry",
                                                           JOptionPane.OK_OPTION, makePeteSmall());
                        }
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Issue Finding FilePath");
                    }

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
                        try {
                            //Scanner temp = new Scanner("src/Users.txt");
                            File projects = new File(dir, "Projects.txt");

                            projects.createNewFile();

                            //Show successful registration
                            JOptionPane.showMessageDialog(null, "Thank you for registering! Please sign in with your new credentials.",
                                                            "Successful Registration", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }

                }
            });
            this.add(registerBtn);

            // add the Project Partner logo to fill the space
            try {
                BufferedImage logo = ImageIO.read(new File("src/images/projectpartner.png"));
                JLabel logoLabel = new JLabel(new ImageIcon(logo));
                //ImageIcon projectPartnerLogo = new ImageIcon("src/Project Partner.png");
                this.add(logoLabel);
            } catch (IOException e) {
                System.out.print(e);
            }


        }

        /**
         * Adds the name and email to the users.txt file if not already included.
         *  @author Alex Ewing
         */
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
         * Creates the user that is currently using the program so that their info
         * can be accessed by the methods. Instantiates the myUser field.
         *
         * @author Owen Orlic
         * @param theUsername the user's username
         * @param theEmail the user's email
         */
        private void makeUser(String theUsername, String theEmail) {

            String[] projectPaths = readProjects(theUsername);
            Budget[] projectBudgets = readBudgets(projectPaths);
            FileGroup[] projectFiles = readFiles(projectPaths);
            Journal[] projectJournals = readJournals(projectPaths);
            ArrayList<Project> projects = makeProjectList(projectPaths, projectBudgets, projectFiles, projectJournals);

            //instantiate the User with this infomation
            myUser = new User(theUsername, theEmail, projects);
        }

        /**
         * Creates an array list of the user's projects.
         *
         * @author Owen Orlic
         * @param thePaths an array of the pathnames to the projects
         * @param theBudgets an array of the budgets of the projects
         * @return an arraylist of all the user's projects
         */
        private ArrayList<Project> makeProjectList(String[] thePaths, Budget[] theBudgets, FileGroup[] theFiles, Journal[] theJournals) {
            ArrayList<Project> projects = new ArrayList<>();
            for (int i = 0; i < thePaths.length; i++) {
                String[] pathname = thePaths[i].split("/"); //split up the pathname so that
                String projectName = pathname[pathname.length - 1]; //we can get the project name
                Project proj;
                if (checkIfEndsInNums(thePaths[i])) {
                    String pin = getPinNumbers(thePaths[i]);
                    proj = new Project(projectName, pin, theBudgets[i], theFiles[i], theJournals[i]);
                } else {
                    proj = new Project(projectName, theBudgets[i], theFiles[i], theJournals[i]);
                }

                projects.add(proj);
            }
            return projects;
        }

        /**
         * Creates an array of the pathnames to all the project folders
         * the user has. Excludes the Projects.txt file.
         *
         * @author Owen Orlic
         * @param theUsername the user whose projects we want
         * @return an array of the pathnames to each project directory
         */
        private String[] readProjects(String theUsername) {
            File userDir = new File("src/" + theUsername);
            File[] dirs = userDir.listFiles();
            //stores the strings of project paths besides the Projects.txt file
            String[] projects = new String[dirs.length - 1];
            //used to avoid out of bounds on projects[]
            int counter = 0;
            for (int i = 0; i < dirs.length; i++) {
                //if the path name isn't for the Projects.txt file
                if (dirs[i].isDirectory()) {
                    if (checkPrivate(dirs[i].toString())) {
                        String pin = getPinFromFile(theUsername, dirs[i].toString());
                        projects[counter] = dirs[i].toString() + pin;
                    } else {
                        projects[counter] = dirs[i].toString();
                    }
                    counter++;
                }
            }
            return projects;
        }

        /**
         * Goes through a list of pathnames that are directories, creates a scanner
         * for the Budget.txt file in those directories, passes that scanner to the
         * readBudgetFile() method.
         *
         * @author Owen Orlic
         * @param thePaths an array of the project pathnames
         * @return an array of Budget objects for each project
         */
        private Budget[] readBudgets(String[] thePaths) {
            Budget[] budgets = new Budget[thePaths.length];
            for (int i = 0; i < thePaths.length; i++) {
                String path = takeOffPinFromName(thePaths[i]);
                budgets[i] = readBudgetFile(path + "/Budget.txt");
            }
            return budgets;
        }

        /**
         * Reads a Budget.txt file to create a budget object with the total and
         * total expenses and expense items if there are any.
         *
         * @author Owen Orlic
         * @param thePath the Budget.txt file to be read
         * @return the Budget object representing the Budget.txt file
         */
        private Budget readBudgetFile(String thePath) {
            Budget budget;
            double total = 0;
            double totalExpenses = 0;
            ArrayList<ExpenseItem> expenses = new ArrayList<>();
            BufferedReader reader = null;
            int lines = 0;
            try {
                //System.out.println(thePath);
                reader = new BufferedReader(new FileReader(thePath));
                while (reader.readLine() != null) {
                    lines++;
                    //reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (lines >= 2) {
                try (Scanner scan = new Scanner(new File(thePath))) {
                    while (scan.hasNextLine()) {
                        String next = scan.nextLine();
                        if (next.equals("+")) {
                            scan.next(); //skip type word
                            scan.next(); //skip bar character
                            String totalStr = scan.next(); //take the total
                            //String[] mainInfo = nextLine.split("| ");
                            total = Double.parseDouble(totalStr);

                        } else if (next.equals("----")) {
                            String expenseName = scan.nextLine(); //get the name of the expense
                            String expenseCostStr = scan.nextLine(); //get the cost
                            double expenseCost = Double.parseDouble(expenseCostStr); //turn String to double
                            totalExpenses += expenseCost; //add expense to total expenses
                            ExpenseItem expense = new ExpenseItem(expenseName, expenseCost); //create new ExpenseItem
                            expenses.add(expense);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Main GUI, readBudgetFile()");
                }
            }

            //if the project has no expenses
            if (expenses.size() == 0) {
                budget = new Budget(total);
            } else {
                budget = new Budget(total, totalExpenses, expenses);
            }
            return budget;
        }

        /**
         * Goes through a list of pathnames that are directories, creates a scanner
         * for the Budget.txt file in those directories, passes that scanner to the
         * readBudgetFile() method.
         *
         * @author Owen Orlic
         * @param thePaths an array of the project pathnames
         * @return an array of Budget objects for each project
         */
        private FileGroup[] readFiles(String[] thePaths) {
            FileGroup[] files = new FileGroup[thePaths.length];
            for (int i = 0; i < thePaths.length; i++) {
                String path = takeOffPinFromName(thePaths[i]);
                files[i] = readFilesDir(path + "/Files.txt");
            }
            return files;
        }

        /**
         * Reads a Files directory to create a FileGroup object with the
         * single file items if there are any.
         *
         * @author Owen Orlic
         * @param thePath the Files.txt file to be read
         * @return the FileGroup object representing the Files.txt file
         */
        private FileGroup readFilesDir(String thePath) {
            //Journal journal;
            ArrayList<SingleFile> files = new ArrayList<>();
            BufferedReader reader = null;
            int lines = 0;
            try {
                reader = new BufferedReader(new FileReader(thePath));
                while (reader.readLine() != null) {
                    lines++;
                    //reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (lines > 2) {
                try (Scanner scan = new Scanner(new File(thePath))) {
                    while (scan.hasNextLine()) {
                        String next = scan.nextLine();
                        if (next.equals("+")) {
                            scan.next(); //skip type word
                            scan.next(); //skip bar character
                            String mainTitle = scan.next(); //take the main title
                            //String[] mainInfo = nextLine.split("| ");

                        } else if (next.equals("----")) {
                            String fileName = scan.nextLine(); //get the title of the entry
                            String pathName = scan.nextLine(); //get the content of the entry
                            File fileFile = new File(pathName);
                            SingleFile file = new SingleFile(fileName, fileFile); //create new JournalEntry
                            files.add(file);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Main GUI, readJournalFile()");
                }
            }
            return new FileGroup(files);
        }





        /**
         * Goes through a list of pathnames that are directories, creates a scanner
         * for the Journal.txt file in those directories, passes that scanner to the
         * readJournalFile() method.
         *
         * @author Owen Orlic
         * @param thePaths an array of the project pathnames
         * @return an array of Journal objects for each project
         */
        private Journal[] readJournals(String[] thePaths) {
            Journal[] journals = new Journal[thePaths.length];
            for (int i = 0; i < thePaths.length; i++) {
                String path = takeOffPinFromName(thePaths[i]);
                journals[i] = readJournalFile(path + "/Journal.txt");
            }
            return journals;
        }

        /**
         * Reads a Journal.txt file to create a journal object with the
         * journal entry items if there are any.
         *
         * @author Owen Orlic
         * @param thePath the Journal.txt file to be read
         * @return the Journal object representing the Journal.txt file
         */
        private Journal readJournalFile(String thePath) {
            //Journal journal;
            ArrayList<JournalEntry> entries = new ArrayList<>();
            BufferedReader reader = null;
            int lines = 0;
            try {
                reader = new BufferedReader(new FileReader(thePath));
                while (reader.readLine() != null) {
                    lines++;
                    //reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (lines > 2) {
                try (Scanner scan = new Scanner(new File(thePath))) {
                    while (scan.hasNextLine()) {
                        String next = scan.nextLine();
                        if (next.equals("+")) {
                            scan.next(); //skip type word
                            scan.next(); //skip bar character
                            String mainTitle = scan.next(); //take the main title
                            //String[] mainInfo = nextLine.split("| ");

                        } else if (next.equals("----")) {
                            String entryTitle = scan.nextLine(); //get the title of the entry
                            String entryContent = scan.nextLine(); //get the content of the entry
                            JournalEntry entry = new JournalEntry(entryTitle, entryContent); //create new JournalEntry
                            entries.add(entry);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Main GUI, readJournalFile()");
                }
            }

            return new Journal(entries);
        }

        /**
         * Gets the current Username.
         *
         * @return String currentUser the current username
         * @author Alex Ewing
         */
        public String getCurrentUser() {
            return currentUser;
        }

        /**
         * Gets the current Email.
         *
         * @return String currentEmail the current username
         * @author Alex Ewing
         */
        public String getCurrentEmail() {
            return currentEmail;
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

        /**
         * Checks if a project is private by looking for a ~ at the start
         * of the project name.
         *
         * @author Owen Orlic
         * @param thePathname a pathname to the project
         * @return if the project is private
         */
        private boolean checkPrivate(String thePathname) {
            String[] split = thePathname.split("/");
            return split.length > 2 && split[2].charAt(0) == '~';
        }

        /**
         * Takes a pathname to a project and looks through the user's Projects.txt
         * file to find the project name that matches the pathname. Once it finds it,
         * the pin numbers are taken from that project name. If no project is found that matches
         * the pathname, -1 is returned.
         *
         * @author Owen Orlic
         * @param theUsername the username of the current user
         * @param thePath the pathname to the project
         * @return the pin
         */
        private String getPinFromFile(String theUsername, String thePath) {
            String pin = "-1";
            System.out.println("getPinFromFile: " + thePath);
            try (Scanner scan = new Scanner(new File("src/" + theUsername + "/Projects.txt"))) {
                int strLen = thePath.length();
                String projName = thePath.split("/")[2];
                while (scan.hasNext()) {
                    //System.out.println("projName: " + projName);
                    String next = scan.next();
                    if (next.length() >= projName.length() && next.substring(0, next.length() - 4).equals(projName)) {
                        pin = next.substring(projName.length());
                        System.out.println("Made it inside: " + pin);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            System.out.println("Inside getPinFromFile() outside if: " + pin);
            return pin;
        }

        /**
         * Sends thePath to checkIfEndsInNums() to see if there are numbers at
         * the end of the pathname denoting pin numbers for the project.
         * If there are, it removes the four numbers at the end and if it doesn't
         * it returns exactly what was passed in.
         *
         * @author Owen Orlic
         * @param thePath a pathname to a project folder
         * @return the pathname with no pin numbers
         */
        private String takeOffPinFromName(String thePath) {
            if (checkIfEndsInNums(thePath)) {
                thePath = thePath.substring(0, thePath.length() - 4);
            }
            return thePath;
        }

        /**
         * Checks if the pathname ends in numbers. If it does,
         * that means the project has a pin.
         *
         * @author Owen Orlic
         * @param thePath pathname to the project
         * @return if the project has a pin
         */
        private boolean checkIfEndsInNums(String thePath) {
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            boolean isDigit = false;
            //checks if the pathname ends in a digit, if it does then remove the pin from the end
            for (int i = 0; i < digits.length; i++) {
                if (thePath.charAt(thePath.length() - 1) == digits[i]) {
                    isDigit = true;
                }
            }
            return isDigit;
        }

        private String getPinNumbers(String thePath) {
            String pin = "-1";
            if (checkIfEndsInNums(thePath)) {
                pin = thePath.substring(thePath.length() - 4);
            }

            return pin;
        }

    }

    /**
     * Represents the about section of the GUI.
     *
     * @version 1.1
     * @author Alex Ewing
     * */
    private final class AboutPanel extends JPanel {

        /** The version of the project. */
        public static String VERSION = "2.3";

        /** The no args constructor for the AboutPanel class. */
        public AboutPanel() {
            setup();
        }

        /** Sets up the AboutPanel.
         *
         * @author Alex Ewing
         */
        private void setup() {
            JButton aboutBtn = new JButton("About");
            this.add(aboutBtn);
            aboutBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    JOptionPane.showMessageDialog(null, "This app is registered to: " + myUserInfo.getCurrentUser() + " (" + myUserInfo.getCurrentEmail() + ")" + "\n \n"  +
                            "This app provided by: Team Emerald\nAlexander Dean Ewing - Alex for short\nOwen Orlic - He's cool\n" +
                            "Lucas Perry - GitHub Guy\nDaniel Alberto Sanchez Aguilar - GUI Guy\n \nVersion: " + VERSION + "\n <-- Project Pete!",
                            "About", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/images/projectpete.png"));
                }
            });
        }


    }

    /**
     * Represents the project search section of the GUI.
     *
     * @version 1.00
     * @author Alex Ewing
     * */
    private final class ProjectListPanel extends JPanel {
        /** Text field for the searched project. */
        private final JTextField projectField;

        /** Current searched project. */
        private String currentProject;

        /**
         * The no args constructor for the ProjectListPanel class.
         * @author Alex Ewing
         */
        public ProjectListPanel() {
            projectField = new JTextField(20);
            currentProject = "";
            setup();
        }

        /**
         * Sets up the Project searchPanel.
         * @author Alex Ewing
         */
        private void setup() {
            JLabel projectLabel = new JLabel("Project");
            this.add(projectLabel);
            this.add(projectField);

            JButton searchBtn = new JButton("Search");
            searchBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // TODO: Implement Project Search

                    new SearchFrame(myUser, projectField.getText());

                    //projectLabel.setText("File not found");
                    //projectLabel.setForeground(Color.RED);
                    //projectLabel.setVisible(true);


                }
            });
            this.add(searchBtn);

            JButton addProj = new JButton("Add Project");
            addProj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    //         pass in the currentUser
                    CreateProjectFrame createFrame = new CreateProjectFrame(myUser);
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

    /** Represents the project list view portion of the gui
     * @author Daniel Sanchez, Owen Orlic
     */
    private final class ProjectViewPanel extends JPanel {
        /**
         * the constructor of the panel
         */
        public ProjectViewPanel() {
            setup();
        }

        /**
         * the setup of the project view panel
         */
        private void setup() {
            this.setLayout(new GridLayout(0, 1));
            updateProjectList();
        }

        /**
         * allows the list to populate itself and update whenever
         * a new project is added.
         * @author Daniel Sanchez, Owen Orlic
         */
        public void updateProjectList() {
            this.removeAll(); // Clear the panel

            JLabel projectLabel = new JLabel("Project List:");
            projectLabel.setHorizontalAlignment(JLabel.CENTER);
            this.add(projectLabel);

            Scanner sc = null;
            try {
                sc = new Scanner(new File("src/" + myUserInfo.getCurrentUser() + "/Projects.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //gets an array of {"Project Name: 'name', "Project Budget: 'budget'}
                String[] nameBudge = line.split("\t");
                //splits the name portion again to just have the name itself and not "Project Name"
                String projName = nameBudge[0].split(": ")[1];

                //checking if the project should be private
                boolean isPrivate = false;
                String pin = "";
                char[] charProjName = new char[projName.length()];
                projName.getChars(0, projName.length() - 1, charProjName, 0);

                //set up string for displaying the project info on project search screen
                String displayName = "Project Name: ";

                //if projects name start with a ~ then they are private
                if ('~' == charProjName[0]) {
                    isPrivate = true;
                    pin = projName.substring(projName.length() - 4, projName.length());
                    //add only the name of the project to the display name, not the ~ or pin
                    displayName += projName.substring(1, projName.length() - 4);
                } else {
                    displayName += projName;
                }

                //add the budget to the display name
                displayName += " | " + nameBudge[1];

                JButton button = new JButton(displayName);
                String finalPin = pin;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent theEvent) {

                        boolean enteredCorrectly = false;
                        while (!enteredCorrectly && '~' == charProjName[0]) {
                            String givenPin = (String) JOptionPane.showInputDialog(null,"Please Enter PIN",
                                                                            "Locked Project", JOptionPane.INFORMATION_MESSAGE,
                                                                            LoginPanel.makePeteSmall(), null, null);
                            if (givenPin.equals(finalPin)) {
                                enteredCorrectly = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "PIN Not Recognized. Please Enter Again.",
                                                                "Uh Oh", JOptionPane.INFORMATION_MESSAGE, LoginPanel.makePeteSmall());

                            }
                        }

                        //System.out.println(projName);
                        new OptionFrame(myUser, projName);
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
