package src.model;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuReaderPopulation extends JFrame {
    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 2;
    /**
     * A ToolKit.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The Dimension of the screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    private final File myContents;

    private final String myUser;

    private final String myProjName;

    private final String myType;

    private JPanel myPanel;

    public MenuReaderPopulation(final File theFile, String theUser, String theProjName, String theType) throws FileNotFoundException {
        myContents = theFile;
        myUser = theUser;
        myProjName = theProjName;
        myType = theType;
        myPanel = new JPanel();
        setup();
    }

    private void setup() throws FileNotFoundException {
        this.setLocation(SCREEN_WIDTH / 2 - this.getWidth() / 2,
                SCREEN_HEIGHT / 2 - this.getHeight() / 2);
        this.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);
        this.add(myPanel, BorderLayout.CENTER);

        // Initialize the Scanner with the provided contents
        Scanner sc = new Scanner(myContents);
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
//        while (sc.hasNextLine()) {
//            String next = sc.nextLine();
//            System.out.println(next);
//            if ("+".equals(next)) {
//                String title = sc.nextLine();
//                JLabel titleLabel = new JLabel(title);
//                myPanel.add(titleLabel, BorderLayout.NORTH);
//            } else if ("-".equals(next)) {
//                String entryName = sc.nextLine();
//                String entryDes = sc.nextLine();
//                sc.nextLine();
//                JLabel entry = new JLabel(entryName + ": " + entryDes);
//                myPanel.add(entry, FlowLayout.CENTER);
//            }
        while (sc.hasNextLine()) {
            JPanel panel = new JPanel();
            //JLabel entry = new JLabel();
            String symbol = sc.nextLine();
            if ("+".equals(symbol)) {
                String titleLine = sc.nextLine();
                JLabel titleLabel = new JLabel(titleLine);
                titleLabel.setHorizontalAlignment(JLabel.CENTER); // Set label text alignment to center

                // Add the title label to the frame at the top center position
                this.add(titleLabel, BorderLayout.PAGE_START);

            } else if ("-".equals(symbol)) {
                //JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                StringBuilder panelText = new StringBuilder();
                JLabel entry = new JLabel(sc.nextLine() + ": ");
                //JLabel entry = new JLabel();
                //String header = sc.nextLine();
                entry.setFont(new Font("Arial", Font.BOLD, 16)); // Customize font if needed
                entry.setHorizontalAlignment(JLabel.CENTER);
                entry.setVerticalAlignment(JLabel.CENTER);
                // Read button text lines until we encounter another "-" or end of file
                while (sc.hasNextLine()) {
                    String next = sc.nextLine();
                    //if sc hit the bottom -
                    if ("-".equals(next)) {
                        break;
                    } else {
                        if (panelText.length() > 0) {
                            panelText.append("<br>");
                            //panelText.append("\n");
                            //String info = sc.nextLine();
                            System.out.println(next);
                            //entry.setText(header + ": " + next);
                            //panelText.append("<br>");

                        }
                        panelText.append(next);
                    }
                }

                //color panel
                //panel.setBackground(Color.CYAN);
                //panel.setBorder(new SoftBevelBorder(1));

                JLabel contentLabel = new JLabel("<html>" + panelText.toString() + "</html>");
                contentLabel.setHorizontalAlignment(JLabel.CENTER);
                panel.add(entry, BorderLayout.PAGE_START);
                panel.add(contentLabel, BorderLayout.CENTER);
                myPanel.add(panel);
            }
        }

        sc.close();
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddItem(myContents, myUser, myProjName, myType);
                dispose();
            }
        });
        myPanel.add(addButton);
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed (final ActionEvent theEvent) {
//                //new AddItem(myContents, myUser, myProjName, myType);
//                //dispose();
//                String label = JOptionPane.showInputDialog("Please Enter a Label");
//                String descr = JOptionPane.showInputDialog("Please Enter a Description/Label");
//
//            }
//        });


        //myLeftPanel.add(addButton);
        //this.add(myPanel);
        this.setVisible(true);

    }

//        JButton addButton = new JButton("Add Item");
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("uhhh");
//            }
//        });
}

