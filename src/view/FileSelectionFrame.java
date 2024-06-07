package src.view;

import src.model.FileGroup;
import src.model.Journal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 * Implementation of an AbstractSelectionFrame that is used for editing and deleting
 * JournalEntries in the Journal.
 *
 * @author Owen Orlic
 */
public class FileSelectionFrame extends AbstractSelectionFrame {

    /** Fires to listeners if an expense needs edited or deleted. */
    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    /** The journal being changed. */
    private FileGroup myFiles;

    private JFileChooser myChooser;

    /**
     * Sends title and option to the AbstractSelectionFrame. theTitle will become the title
     * of the JFrame and theOption will decide whether the selection is for editing for deleting.
     * Uses theJournal to get all JournalEntrys in that journal.
     *
     * @param theFiles the journal whose items are being edited or deleted
     * @param theTitle the title of the JFrame
     * @param theOption choice between editing or deleting the selection
     */
    public FileSelectionFrame(FileGroup theFiles, String theTitle, int theOption) {
        super(theTitle, theOption);
        myFiles = theFiles;
        myChooser = new JFileChooser();
        setupBtnActions();
    }

    /**
     * Based on myOption, either sets up the Button Actions to allow for
     * editing or deletion.
     */
    private void setupBtnActions() {
        if (myOption == 0) {
            setupEditBtns();
        } else if (myOption == 1) {
            setupDeleteBtns();
        } else {
            System.out.println("Error with myOption selection. FileSelectionFrame.java");
        }
    }

    /**
     * Makes it so the selected expense can be edited.
     */
    private void setupEditBtns() {
        for (int i = 0; i < myFiles.getFiles().size(); i++) {
            JButton btn = new JButton(myFiles.getFiles().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser("./");
                    int result = chooser.showOpenDialog(null);
                    while (result != JFileChooser.APPROVE_OPTION) {
                        result = chooser.showOpenDialog(null);
                    }
                    File file = chooser.getSelectedFile();
                    //File newFile = myChooser.getSelectedFile();
                    //improper use of firePropertyChange(), just go with it
                    myPcs.firePropertyChange("repaintPageFileEdit", btn.getText(), file);
                    FileSelectionFrame.this.dispose(); //close frame
                }
            });
            myPanel.add(btn);
        }
    }

    /**
     * Makes it so the selected expense will be deleted. Prompts twice before deleting.
     */
    private void setupDeleteBtns() {
        for (int i = 0; i < myFiles.getFiles().size(); i++) {
            JButton btn = new JButton(myFiles.getFiles().get(i).getName());
            int finalI = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int firstChoice; //first verification
                    int secondChoice; //second verification
                    firstChoice = JOptionPane.showConfirmDialog(null, "Would Like To Delete This File?",
                            "Just Confirming", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                    if (firstChoice == 0) { //chose yes
                        secondChoice = JOptionPane.showConfirmDialog(null, "Are You Sure You Would Like To Delete This File?",
                                "Double Checking", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
                        if (secondChoice == 0) { //chose yes
                            //improper use of firePropertyChange(), just go with it
                            myPcs.firePropertyChange("repaintPageFileDelete", btn.getText(), "neverGetsUsed");
                        }
                    }
                    //close frame when button is pressed
                    FileSelectionFrame.this.dispose();
                }
            });
            myPanel.add(btn);
        }
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

