package src.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class OptionFrame extends JFrame {
    JMenuBar myMenu = new JMenuBar();
    String theProjName;
    public OptionFrame(String theProjectName) {
        this.setJMenuBar(myMenu);
        theProjName = theProjectName;
        setup();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setup() {
        menuMaker("Budget");
        menuMaker("Files");
        menuMaker("Journal");
    }

    public void menuMaker(String type) {
        JMenuItem menu = new JMenuItem(type);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("REaches here");
               File f =  new File("src/" + theProjName + "/" + type + ".txt");
                try {
                    new MenuReaderPopulation(f);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        myMenu.add(menu);
    }
}
