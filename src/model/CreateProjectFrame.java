package src.model;

import javax.swing.*;
import java.awt.*;

public class CreateProjectFrame extends JFrame {
    public CreateProjectFrame() {
        JLabel titleLabel = new JLabel("NEW PROJECT MENU:");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);
        this.setVisible(true);
    }
}
