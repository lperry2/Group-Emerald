package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.AddUser;

/**
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 *
 * Creates an empty GUI window.
 */
public class Main {
    // This is the version constant for the project
    public static String VERSION = "0.0";

    public static void main(String[] args) {
        JFrame emeraldFrame = new JFrame("Emerald");
        emeraldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AddUser dialog = new AddUser();
        JPanel loginButtonPanel = new JPanel();
        emeraldFrame.add(loginButtonPanel, BorderLayout.SOUTH);
        emeraldFrame.add(dialog, BorderLayout.NORTH);
        emeraldFrame.setVisible(true);
        emeraldFrame.setResizable(true);
        emeraldFrame.setSize(800, 600);
        JButton closeButton = new JButton("Close");
        class myListener implements ActionListener {
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }
        }

        closeButton.addActionListener(new myListener());
        loginButtonPanel.add(closeButton);
        System.out.println("");

        // Adding the About tab
        /*
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel aboutPanel = new JPanel();
        aboutPanel.add(new JLabel("Project: Emerald"));
        aboutPanel.add(new JLabel("Version: v0.00"));
        aboutPanel.add(new JLabel("Authors: Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar"));
        tabbedPane.addTab("About", aboutPanel);
        emeraldFrame.add(tabbedPane, BorderLayout.CENTER);
        */


        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "This app is registered to: " + dialog.getCurrentUser() +"\n" +
                        "This app provided by: Team Emerald\nAlexander Dean Ewing - Alex for short\nOwen Orlic - He's cool\n" +
                        "Lucas Perry - GitHub Guy\nDaniel Alberto Sanchez Aguilar - GUI Guy\nVersion: " + VERSION);
            }
        });
        loginButtonPanel.add(aboutButton);
    }
}