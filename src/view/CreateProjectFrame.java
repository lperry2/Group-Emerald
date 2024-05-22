package src.view;

import src.model.Budget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class CreateProjectFrame extends JFrame {
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

    public CreateProjectFrame() {
        super("Create New Project");
        //JLabel titleLabel = new JLabel("NEW PROJECT MENU:");
        //titleLabel.setHorizontalAlignment(JLabel.CENTER);
        //myFrame.add(titleLabel, BorderLayout.NORTH);
        creationPanel = new JPanel();
        newProjectName = "";
        newProjectBudget = "";
        nameField = new JTextField(15);
        budgetField = new JTextField(10);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Replace icon using the constant PAINT_ICON
        // replace the default JFrame icon
        //myFrame.setIconImage(new ImageIcon());

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
                    writer.write("Project Name: " + newProjectName + "\n");
                    writer.write("Project Budget: " + newProjectBudget + "\n\n");
                    JOptionPane.showMessageDialog(null, "Project data saved successfully!");
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

}
