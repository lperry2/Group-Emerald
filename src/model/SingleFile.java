package src.model;

import java.io.File;
import java.util.ArrayList;

public class SingleFile {

    /** The file name. */
    private String myName;

    private File myFile;

    public SingleFile(String theName, File theFile) {
        myName = theName;
        myFile = theFile;
    }

    public String getName() {
        return myName;
    }

    public void setName(String theName) {
        myName = theName;
    }

    public File getFile() {
        return myFile;
    }

    public void setFile(File theFile) {
        myFile = theFile;
    }



}
