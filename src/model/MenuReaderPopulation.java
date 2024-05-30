package src.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuReaderPopulation extends JFrame{
    /**
     * A factor for scaling the size of the GUI relative to
     * the current screen size.
     */
    private static final int SCALE = 2;
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    private final File myContents;

    private final String myUser;

    private final String myProjName;

    private final String myType;

    private JPanel myLeftPanel;

    public MenuReaderPopulation(final File theFile, String theUser, String theProjName, String theType) throws FileNotFoundException {
        myContents = theFile;
        myUser = theUser;
        myProjName = theProjName;
        myType = theType;
        myLeftPanel = new JPanel();
        setup();
    }

    private void setup() throws FileNotFoundException {
        this.setLocation(SCREEN_WIDTH / 2 - this.getWidth() / 2,
                SCREEN_HEIGHT / 2 - this.getHeight() / 2);
        this.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);
        this.add(myLeftPanel, BorderLayout.WEST);

        // Initialize the Scanner with the provided myContents
        Scanner sc = new Scanner(myContents);
        myLeftPanel.setLayout(new BoxLayout(myLeftPanel, BoxLayout.Y_AXIS));
        while (sc.hasNextLine()) {
            String symbol = sc.nextLine();
            if ("+".equals(symbol)) {
                String titleLine = sc.nextLine();
                JLabel titleLabel = new JLabel(titleLine);
                titleLabel.setHorizontalAlignment(JLabel.CENTER); // Set label text alignment to center

                // Add the title label to the frame at the top center position
                this.add(titleLabel, BorderLayout.PAGE_START);
            } else if ("-".equals(symbol)) {
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                StringBuilder panelText = new StringBuilder();
                JLabel titleLabel = new JLabel(sc.nextLine());
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Customize font if needed
                titleLabel.setHorizontalAlignment(JLabel.CENTER);
                // Read button text lines until we encounter another "-" or end of file
                while (sc.hasNextLine()) {
                    String next = sc.nextLine();
                    if ("-".equals(next)) {
                        break;
                    } else {
                        if (panelText.length() > 0) {
                            panelText.append("<br>");
                        }
                        panelText.append(next);
                    }
                }

                JLabel contentLabel = new JLabel("<html>" + panelText.toString() + "</html>");
                contentLabel.setHorizontalAlignment(JLabel.CENTER);
                panel.add(titleLabel, BorderLayout.PAGE_START);
                panel.add(contentLabel, BorderLayout.CENTER);
                myLeftPanel.add(panel);
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

        myLeftPanel.add(addButton);
        this.setVisible(true);

    }
}
