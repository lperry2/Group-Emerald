package src.model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * FileGroup is a collection of SingleFiles for a project.
 *
 * @author Owen Orlic
 */
public class FileGroup {

    ArrayList<SingleFile> myFiles;

    public FileGroup(ArrayList<SingleFile> theFiles) {
        myFiles = theFiles;
    }

    /**
     * Getter method for all the files for the project.
     *
     * @return the myFiles arraylist
     */
    public ArrayList<SingleFile> getFiles() {
        return myFiles;
    }

    /**
     * Creates a new SingleFile, adds it to the myFiles list, and adds it to the
     * journal file. If a SingleFile is already in myFiles with theName, nothing will be added.
     *
     * @param theName name of the file
     * @param theFile the file itself
     * @author Owen Orlic
     */
    public void addFile(String theName, File theFile) {
        SingleFile file = new SingleFile(theName, theFile);
        boolean alreadyAName = false;
        for (int i = 0; i < myFiles.size(); i++) {
            if (theName.equals(myFiles.get(i).getName())) {
                alreadyAName = true;
                break;
            }
        }
        if (alreadyAName) {
            JOptionPane.showMessageDialog(null, "There is already a file with this name. Please choose a new name.",
                    "Error with Name", JOptionPane.INFORMATION_MESSAGE, makePeteSmall());
        } else {
            myFiles.add(file);
        }
    }

    /**
     * Edits a file by looking for the file with theName and replaces
     * its file with theFile.
     *
     * @param theName name of the file to be edited
     * @param theFile the new file
     */
    public void editFile(String theName, File theFile) {
        for (int i = 0; i < myFiles.size(); i++) {
            SingleFile curr = myFiles.get(i);
            if (theName.equals(curr.getName())) {
                curr.setFile(theFile);
                break;
            }
        }
    }

    /**
     * Deletes a file by looking for the one titled by theName and
     * removing it from myFiles.
     *
     * @param theName the name of the file to be deleted
     */
    public void deleteFile(String theName) {
        for (int i = 0; i < myFiles.size(); i++) {
            SingleFile curr = myFiles.get(i);
            if (theName.equals(curr.getName())) {
                myFiles.remove(curr);
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
