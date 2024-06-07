package src.model;

import java.io.File;
import java.util.ArrayList;

/** Assosiates a string with a file. */
public class SingleFile {

    /** The file name. */
    private String myName;

    /** The file. */
    private File myFile;

    /**
     * The public constructor for the SingleFile class.
     * @param theName The file name
     * @param theFile The file
     */
    public SingleFile(String theName, File theFile) {
        myName = theName;
        myFile = theFile;
    }

    /**
     * Gets the name.
     * @return myName The name
     */
    public String getName() {
        return myName;
    }

    /**
     * Sets the name.
     */
    public void setName(String theName) {
        myName = theName;
    }

    /**
     * Gets the file.
     * @return myFile The file
     */
    public File getFile() {
        return myFile;
    }

    /**
     * Sets the file.
     */
    public void setFile(File theFile) {
        myFile = theFile;
    }



}
