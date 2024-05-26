package src.view;

import src.model.Budget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    private JPanel creationPanel;

    private JTextField pinField;

    private boolean isPrivate;

    private String userName;

    public CreateProjectFrame(String user) {
        super("Create New Project");
        //this.setLayout(new BorderLayout());
        creationPanel = new JPanel();
        newProjectName = "";
        newProjectBudget = "";
        nameField = new JTextField(15);
        budgetField = new JTextField(10);
        pinField = new JTextField(10);
        userName = user;
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
        this.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);

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
     * @author Owen Orlic
     */
    private void setupComponents() {
        JLabel projectNameLabel = new JLabel("Project Name");
        creationPanel.add(projectNameLabel);
        creationPanel.add(nameField);

        JLabel budgetLabel = new JLabel("Budget");
        creationPanel.add(budgetLabel);
        creationPanel.add(budgetField);

        JLabel pinLabel = new JLabel("Enter PIN"); // make label but don't add immediately
        JTextField privatePin = new JTextField(5);
        privatePin.setVisible(false);

        JCheckBox privateCheckBox = new JCheckBox("Private");
        creationPanel.add(privateCheckBox);

        pinLabel.setVisible(false);
        creationPanel.add(pinLabel);

        creationPanel.add(privatePin);
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
                    privatePin.setVisible(false);
                    isPrivate = false;

                } else {
                    pinLabel.setVisible(true);
                    privatePin.setVisible(true);
                    isPrivate = true;
                }
            }
        });

        this.add(creationPanel, BorderLayout.NORTH);
        JButton createProjectBtn = new JButton("Create Project");
        createProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Get project name and budget from the text fields
                newProjectName = nameField.getText();
                newProjectBudget = budgetField.getText();

                // Create Budget object (if necessary for other operations)
                Budget projectBudget = new Budget(Double.parseDouble(newProjectBudget));

                // Write the project data to a file
                try (FileWriter writer = new FileWriter("project_data.txt", true)) {
                    writer.write("\n"+ "Project Name: " + newProjectName + "\t" + "Project Budget: " + newProjectBudget);
                    writer.close();
                    //Creation of project files is here! File initializers should be worked on a separate method for each
                    File dir = new File("src/" + userName + "/" + newProjectName);
                    dir.mkdirs();
                    File budgetFile = new File(dir, "Budget.txt");
                    budgetFile.createNewFile();
                    fileInitializer(budgetFile, "Budget");
                    File journalFile = new File(dir, "Journal.txt");
                    journalFile.createNewFile();
                    fileInitializer(journalFile, "Journal");
                    File fileFile = new File(dir, "Files.txt");
                    fileFile.createNewFile();
                    fileInitializer(fileFile, "Files");
                    // Load the custom PNG file
                    ImageIcon icon = new ImageIcon("src/project pete.png");
                    Image img = icon.getImage();
                    // Resize the image to 50x50 pixels
                    Image resizedImg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImg);

                    // Show success message with the resized custom icon
                    JOptionPane.showMessageDialog(null, "Project data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
                    myPCS.firePropertyChange("repaint", null, null);
                    dispose();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while saving the project data.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });

        JPanel createButtonPanel = new JPanel();

        createButtonPanel.add(createProjectBtn, BorderLayout.SOUTH);

        this.add(createButtonPanel);
    }

    public void addPropertyChangeListener(PropertyChangeListener actionListener) {
        myPCS.addPropertyChangeListener(actionListener);
    }

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
