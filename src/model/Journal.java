package src.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A journal is used for tracking notes on a project. A journal is just a list of
 * JournalEntrys.
 *
 * @author Owen Orlic
 */
public class Journal {

    /** The journal entries. */
    private ArrayList<JournalEntry> myEntries;

    /**
     * The public constructor for the Journal class.
     * @param theEntries the journal entries
     */
    public Journal(ArrayList<JournalEntry> theEntries) {
        myEntries = theEntries;
    }

    /**
     * Retrieves the journal's entries.
     * @return myEntries the journal's entries
     */
    public ArrayList<JournalEntry> getEntries() {
        return myEntries;
    }

    /**
     * Creates a new JournalEntry, adds it to the myEntries list, and adds it to the
     * journal file.
     *
     * @param theTitle name of the entry
     * @param theContent content of the entry
     * @author Owen Orlic
     */
    public void addEntry(String theTitle, String theContent) {
        JournalEntry entry = new JournalEntry(theTitle, theContent);
        boolean alreadyAName = false;
        for (int i = 0; i < myEntries.size(); i++) {
            if (theTitle.equals(myEntries.get(i).getTitle())) {
                alreadyAName = true;
                break;
            }
        }
        if (alreadyAName) {
            JOptionPane.showMessageDialog(null, "There is already an entry with this title. Please choose a new title.",
                    "Error with Title", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
        } else {
            myEntries.add(entry);
        }

    }

    /**
     * Edit's the specified existing entry.
     * @param theTitle the name of the entry to edit
     * @param theContent the entrie's new content
     */
    public void editEntry(String theTitle, String theContent) {
        for (int i = 0; i < myEntries.size(); i++) {
            JournalEntry curr = myEntries.get(i);
            if (theTitle.equals(curr.getTitle())) {
                curr.setContent(theContent);
                break;
            }
        }
    }

    /**
     * Deletes a specified existing entry.
     * @param theTitle the title of the entry to delete
     */
    public void deleteEntry(String theTitle) {
        for (int i = 0; i < myEntries.size(); i++) {
            JournalEntry curr = myEntries.get(i);
            if (theTitle.equals(curr.getTitle())) {
                myEntries.remove(curr);
                break;
            }
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
}
