package src.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class OptionFrame extends JFrame {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    JMenuBar myMenu = new JMenuBar();
    String theProjName;

    String theUser;
    public OptionFrame(String theProjectName, String user) {
        this.setJMenuBar(myMenu);
        theProjName = theProjectName;
        theUser = user;
        setup();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setup() {
        menuMaker("Budget");
        menuMaker("Files");
        menuMaker("Journal");
        this.setLocation(0,0);
        this.setSize(SCREEN_WIDTH/4, SCREEN_HEIGHT/4);
    }

    public void menuMaker(String type) {
        JMenuItem menu = new JMenuItem(type);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("REaches here");
               File f =  new File("src/" + theUser + "/" + theProjName + "/" + type + ".txt");
                try {
                    new MenuReaderPopulation(f, theUser, theProjName, type);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        myMenu.add(menu);
    }
}
