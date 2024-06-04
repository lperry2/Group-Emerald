package src.view;

import src.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BackButton extends JButton implements PropertyChangeListener {

    /** For watching if there are shapes currently drawn. */
    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    public BackButton(User theUser) {
        super("Back");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
     * Adds a listener for property change events from this class.
     *
     * @param theListener a PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a listener for property change events from this class.
     *
     * @param theListener a PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);

    }
}
