package src.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * A class that allows a user to add an item to their project whether being
 * in a budget, or journal frame
 * @author Daniel Sanchez
 */
public class AddItem extends JFrame {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    /**The file we will be writing to*/
    private File theFile;
    /**The user we are currently in*/
    private String theUser;
    /**The project of the user*/
    private String theProj;
    /**The type of frame we are in*/
    private String theType;
    /**The panel that holds the user input*/
    private JPanel questionare;
    /**The text area where the user while type to*/
    private JTextArea content;
    /**The label provided for the content*/
    private JTextField label;

    /**
     * The constructor for an addItem frame
     * @param file the file we are writing to
     * @param user the user of the project
     * @param proj the project we are editing
     * @param type the type of file we need to edit within the project
     * @author Daniel Sanchez
     */
    public AddItem(File file, String user, String proj, String type) {
        theFile = file;
        theUser = user;
        theProj = proj;
        theType = type;
        label = new JTextField();
        questionare = new JPanel();
        content = new JTextArea();

        setup();
        setVisible(true);
    }

    /**
     * Sets up the frame for users to add items to a specific type
     * of element within their projects
     * @author Daniel Sanchez
     */
    private void setup() {
        this.setLocation(SCREEN_WIDTH / 2 - this.getWidth() / 2,
                SCREEN_HEIGHT / 2 - this.getHeight() / 2);
        this.setSize(SCREEN_WIDTH/3, SCREEN_HEIGHT/2);
        this.setLayout(new BorderLayout());
        questionare.setLayout(new GridLayout(0, 1));
        content.setRows(5);
        content.setColumns(5);
        JLabel titleLabel = new JLabel("Set Label:");
        questionare.add(titleLabel);
        questionare.add(label);
        JLabel contentLabel = new JLabel("New Content:");
        questionare.add(contentLabel);
        questionare.add(content);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            /**
             * Writes to the corresponding file when pressed
             * @param e the event to be processed
             * @author Daniel Sanchez
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintStream myStream  = new PrintStream(new FileOutputStream(theFile, true));
                    myStream.println("\n----" + "\n" + label.getText() + "\n" + content.getText());
                    myStream.close();
                    dispose();
                    new MenuReaderPopulation(theFile, theUser, theProj, theType);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        questionare.add(submit);
        this.add(questionare, BorderLayout.NORTH);
    }
}
