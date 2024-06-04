package src.view;

import src.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchFrame extends JFrame {
    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 3;
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    private User myCurrentUser;

    private String mySearch;

    public SearchFrame(User theCurrentUser, String theSearch) {
        super("Hi");
        myCurrentUser = theCurrentUser;
        mySearch = theSearch;

        setup();
    }

    private void setup() {
        this.setLocation(SCREEN_WIDTH / SCALE,
                SCREEN_HEIGHT / SCALE);
        this.setSize(SCREEN_WIDTH/ SCALE, SCREEN_HEIGHT / SCALE);
        makeSearchList();
        this.setVisible(true);
    }

    private void makeSearchList() {
        String[] projects = myCurrentUser.getProjectNames();
        JPanel projectList = new JPanel();

        int searchLen = mySearch.length();
        for (int i = 0; i < projects.length; i++) {
            String name = projects[i];
            int j = 0;
            while (j <= projects[i].length() - searchLen) {
                if (name.length() >= mySearch.length() && name.substring(j, j + searchLen).equals(mySearch)
                        || name.equals(mySearch)) {

                    String displayName = projects[i];
                    if (projects[i].substring(0, 1).equals("~")) {
                        displayName = displayName.substring(1, displayName.length());
                    }
                    JButton btn = new JButton(displayName);
                    addBtnAction(btn, projects[i]);
                    projectList.add(btn);
                    break;
                }
                j++;
            }

//            if (projects[i].equals(mySearch)) {
//                JButton btn = new JButton(projects[i]);
//                projectList.add(btn);
//            }

        }
        this.add(projectList, BorderLayout.CENTER);
    }

    private void addBtnAction(JButton theButton, String theProjectName) {

        String pin = "-1";

        System.out.println((theProjectName.charAt(0) == '~') + " " + theProjectName);
        if (theProjectName.charAt(0) == '~') {
            try (Scanner scan = new Scanner(new File("src/" + myCurrentUser.getName() + "/Projects.txt"))) {
                while (scan.hasNextLine()) {
                    String nextLine = scan.nextLine();
                    String[] bigSplit = nextLine.split("\t");
                    //splits the "Project Name: 'Name Here'" and takes 'Name Here'
                    String fullName = bigSplit[0].split(": ")[1];
                    System.out.println(fullName);
                    if (fullName.substring(0, theProjectName.length()).equals(theProjectName)) {
                        //takes the pin
                        pin = fullName.substring(fullName.length() - 4, fullName.length());
                    }

                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }

        char[] charProjName = new char[theProjectName.length()];
        String finalPin = pin;
        theButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean enteredCorrectly = false;
                while (!enteredCorrectly && '~' == charProjName[0]) {
                    String givenPin = JOptionPane.showInputDialog("Please Enter PIN");
                    if (givenPin.equals(finalPin)) {
                        enteredCorrectly = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "PIN Not Recognized. Please Enter Again.",
                                "Uh Oh", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());

                    }
                }

                //System.out.println(projName);
                new OptionFrame(myCurrentUser, theProjectName);
            }
        });
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

}
