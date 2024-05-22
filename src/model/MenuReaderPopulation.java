package src.model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuReaderPopulation extends JFrame{
    private final File contents;

    private JPanel leftPanel;
    public MenuReaderPopulation(final File theFile) throws FileNotFoundException {
        contents = theFile;
        leftPanel = new JPanel();
        setup();
    }

    private void setup() throws FileNotFoundException {
        this.add(leftPanel, BorderLayout.WEST);

        // Initialize the Scanner with the provided contents
        Scanner sc = new Scanner(contents);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
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
                leftPanel.add(panel);
            }
        }

        sc.close();
        this.setVisible(true);

    }
}
