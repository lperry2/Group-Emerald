package src;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alex Ewing, Owen Orlic, Lucas Perry, Daniel Alberto Sanchez Aguilar
 * @version v0.00
 * 
 * Creates an empty GUI window.
 */
public class Main {
    public static void main(String[] args) {
        JFrame emeraldFrame = new JFrame("Emerald");
        emeraldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        emeraldFrame.add(closeButton);
    }
}
// -Alex: For the moment this is just a comment to see if I can add to the project.