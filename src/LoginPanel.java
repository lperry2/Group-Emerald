package src;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class LoginPanel extends JPanel{
    private final JTextField nameField;
    private final JTextField emailField;
    private String currentUser;
    private String currentEmail;

    public LoginPanel() {
        currentUser = "";
        currentEmail = "";
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        setup();
    }

    public void setup() {
        JLabel userNameLabel = new JLabel("Username");
        this.add(userNameLabel);
        this.add(nameField);

        JLabel userEmailLabel = new JLabel("Email");
        this.add(userEmailLabel);
        this.add(emailField);

        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                userLookUp();
            }
        });
        this.add(login);
    }

    public void userLookUp() {
        //Search through the text file eventually
        try {
            PrintStream myStream  = new PrintStream(new FileOutputStream("src/Users.txt", true));
            myStream.println(nameField.getText() + "|" + emailField.getText());
            myStream.close();
            currentUser = nameField.getText();
            currentEmail = emailField.getText();
            this.setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String getCurrentUser() {
        return currentUser;
    }

    public String getCurrentEmail(){return currentEmail;}
}
