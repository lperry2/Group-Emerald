package src.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddItem extends JFrame {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    private File theFile;
    private String theUser;
    private String theProj;
    private String theType;
    private JPanel questionare;
    private JTextArea content;

    private JTextField label;
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
