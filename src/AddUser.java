package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddUser extends JPanel{
    private final JTextField userName = new JTextField(20);
    private String currentUser = "";
    public AddUser() {
        setup();
    }
    public void setup() {
        JLabel userNameLabel = new JLabel("Username");
        this.add(userNameLabel);
        this.add(userName);
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
            myStream.println(userName.getText());
            myStream.close();
            currentUser = userName.getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
