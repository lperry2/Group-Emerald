package src.view;

import src.model.Budget;
import src.model.User;
import src.model.Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The frame that allows users to create new projects
 * @author Daniel Sanchez, Owen Orlic
 */
public class CreateProjectFrame extends JFrame {

    /**
     * support for firing property change events from this class.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
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
    private static final int SCALE = 3;

    /** Text field for the username . */
    private final JTextField nameField;

    /** Text field for the email. */
    private final JTextField budgetField;

    /** Name of the project being created. */
    private String newProjectName;

    /** Budget of the new project. */
    private String newProjectBudget;

    /** Panel that holds all the text fields. */
    private JPanel creationPanel;

    private JTextField pinField;

    /** Tracks if the project should be private. */
    private boolean isPrivate;

    /** The current user. */
    private User myCurrentUser;

    /**
     * The constructor of the Create Project frame
     * @param theCurrentUser the current user logged in
     * @author Owen Orlic, Daniel Sanchez
     */
    public CreateProjectFrame(User theCurrentUser) {
        super("Create New Project");
        //this.setLayout(new BorderLayout());
        creationPanel = new JPanel();
        newProjectName = "";
        newProjectBudget = "";
        nameField = new JTextField(10);
        budgetField = new JTextField(10);
        pinField = new JTextField(5);
        myCurrentUser = theCurrentUser;
        start();
    }

    /**
     * Sets up the create project frame's location and visibility. Calls setupComponents() to
     * add text fields and buttons.
     *
     * @author Daniel Sanchez Aguilar, Owen Orlic
     */
    private void start() {
        // Set the size of the JFrame to 1/2 (current scaling factor) of the screen
        this.setSize(SCREEN_WIDTH / SCALE + 135, SCREEN_HEIGHT / SCALE);

        // Set the location of the JFrame to the center
        this.setLocation(SCREEN_WIDTH / 2 - this.getWidth() / 2,
                SCREEN_HEIGHT / 2 - this.getHeight() / 2);

        // Set the default close operation to close on exit
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //adds name and budget text fields and create project button
        setupComponents();

        // Set the JFrame to visible
        this.setVisible(true);
    }

    /**
     * Sets up the name and budget text fields and adds the create project button action.
     *
     * @author Owen Orlic, Daniel Sanchez
     */
    private void setupComponents() {
        JLabel projectNameLabel = new JLabel("Project Name");
        creationPanel.add(projectNameLabel);
        creationPanel.add(nameField);

        JLabel budgetLabel = new JLabel("Budget");
        creationPanel.add(budgetLabel);
        creationPanel.add(budgetField);

        JLabel pinLabel = new JLabel("Enter PIN"); // make label but don't add immediately
        pinField.setVisible(false);

        JCheckBox privateCheckBox = new JCheckBox("Private");
        creationPanel.add(privateCheckBox);

        pinLabel.setVisible(false);
        creationPanel.add(pinLabel);

        creationPanel.add(pinField);
        privateCheckBox.addActionListener(new ActionListener() {
            /**
             * If the private box is selected, a text field to enter the
             * pin will appear. If it is not selected, then it will not
             * appear on the frame.
             *
             * @param e the event to be processed
             * @author Owen Orlic
             */
            public void actionPerformed(ActionEvent e) {

                if (isPrivate) {
                    pinLabel.setVisible(false);
                    pinField.setVisible(false);
                    isPrivate = false;

                } else {
                    pinLabel.setVisible(true);
                    pinField.setVisible(true);
                    isPrivate = true;
                }
            }
        });

        this.add(creationPanel, BorderLayout.NORTH);
        JButton createProjectBtn = new JButton("Create Project");
        createProjectBtn.addActionListener(new ActionListener() {
            /**
             * creates a new project directory with their respective element files
             * upon clicking
             * @param arg0 the event to be processed
             * @author Daniel Sanchez, Owen Orlic
             */
            public void actionPerformed(ActionEvent arg0) {
                // Get project name and budget from the text fields
                newProjectName = nameField.getText();
                newProjectBudget = budgetField.getText();


                // Create Budget object (if necessary for other operations)
                Budget projectBudget = new Budget(Double.parseDouble(newProjectBudget));

                // Write the project data to a file
                try (FileWriter writer = new FileWriter("src/" + myCurrentUser.getName() + "/Projects.txt", true)) {
                    if (privateCheckBox.isSelected()) {
                        //if projects are private they will take the form "~ProjectName1234" with 1234 being the pin
                        writer.write("Project Name: ~" + newProjectName + pinField.getText() + "\t" + "Project Budget: " + newProjectBudget + "\n");
                    } else {
                        writer.write("Project Name: " + newProjectName + "\t" + "Project Budget: " + newProjectBudget + "\n");
                    }
                    writer.close();

                    //Creation of project files is here! File initializers should be worked on a separate method for each
                    File dir;
                    String dirName;
                    String userName = myCurrentUser.getName();
                    if (privateCheckBox.isSelected()) {
                        dir = new File("src/" + userName + "/" + "~" + newProjectName);
                        dirName = "src/" + userName + "/" + "~" + newProjectName;
                    } else {
                        dir = new File("src/" + userName + "/" + newProjectName);
                        dirName = "src/" + userName + "/" + newProjectName;
                    }
                    dir.mkdirs();
                    File budgetFile = new File(dir, "Budget.txt");
                    budgetFile.createNewFile();
                    fileInitializer(budgetFile, "Budget | " + budgetField.getText());
                    File journalFile = new File(dir, "Journal.txt");
                    journalFile.createNewFile();
                    fileInitializer(journalFile, "Journal | " + nameField.getText());
                    File fileFile = new File(dir, "Files.txt");
                    fileFile.createNewFile();
                    fileInitializer(fileFile, "Files | " + nameField.getText());

                    // Load the custom PNG file
                    ImageIcon icon = new ImageIcon("src/images/projectpete.png");
                    Image img = icon.getImage();
                    // Resize the image to 50x50 pixels
                    Image resizedImg = img.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImg);

                    // Show success message with the resized custom icon
                    JOptionPane.showMessageDialog(null, "Project data saved successfully!",
                                                "Success", JOptionPane.INFORMATION_MESSAGE, resizedIcon);

                    //add project to the user's project list
                    if (privateCheckBox.isSelected()) {
                        myCurrentUser.addProject(new Project(newProjectName, pinField.getText(), new Budget(newProjectBudget)));
                    } else {
                        myCurrentUser.addProject(new Project(newProjectName, new Budget(newProjectBudget)));
                    }

                    myPCS.firePropertyChange("repaint", null, null);
                    dispose();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                                            "An error occurred while saving the project data.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });

        JPanel createButtonPanel = new JPanel();

        createButtonPanel.add(createProjectBtn, BorderLayout.SOUTH);

        this.add(createButtonPanel);
    }

    /**
     * allows for the frame to have a property change listener
     * @param actionListener  the PropertyChangeListener to be added
     * @author Daniel Sanchez
     */
    public void addPropertyChangeListener(PropertyChangeListener actionListener) {
        myPCS.addPropertyChangeListener(actionListener);
    }

    /**
     * Initializes the files for budgets and journals
     * @param theFile the file we are writing to
     * @param type the type of file we are writing to
     * @author Daniel Sanchez
     */
    public void fileInitializer(File theFile, String type) {
        try {
            FileWriter writer = new FileWriter(theFile, true);
            writer.write("+\n" + type + "\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
