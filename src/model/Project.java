package src.model;

import java.util.ArrayList;

/**
 * Represents a project.
 *
 * @author Alex Ewing
 */
public class Project {
    /** The name of the project. */
    private String myProjectName;

    /** The project's budget. */
    private Budget myBudget;

    /** The files for the project. */
    private FileGroup myFiles;

    /** The Journal for the project. */
    private Journal myJournal;

    /** The projects pin. */
    private String myPin;

    /** Denotes if the project is private. */
    private boolean isPrivate;

    /**
     * The constructor for the Project class. Pin gets set to "-1".
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param theBudget the budget of the project
     */
    public Project(String theProjectName, Budget theBudget){
        myProjectName = theProjectName;
        myPin = "-1";
        myBudget = theBudget;
        myFiles = new FileGroup(new ArrayList<SingleFile>());
        myJournal = new Journal(new ArrayList<JournalEntry>());
        isPrivate = false;
    }

    /**
     * The constructor for the Project class. Pin passed in as String,
     * not int.
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param thePin is the pin for the project.
     * @param theBudget the budget of the project
     */
    public Project(String theProjectName, String thePin, Budget theBudget){
        myProjectName = theProjectName;
        myPin = thePin;
        myBudget = theBudget;
        myFiles = new FileGroup(new ArrayList<SingleFile>());
        myJournal = new Journal(new ArrayList<JournalEntry>());
        isPrivate = true;
    }

    /**
     * The constructor for the Project class, with a Journal too.
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param theBudget the budget of the project
     * @param theJournal the journal for the project
     */
    public Project(String theProjectName, String thePin, Budget theBudget, FileGroup theFiles, Journal theJournal){
        myProjectName = theProjectName;
        myPin = thePin;
        myBudget = theBudget;
        myFiles = theFiles;
        myJournal = theJournal;
        isPrivate = true;
    }

    /**
     * The constructor for the Project class, with a Journal too.
     * myPin gets set to "-1".
     *
     * @author Alex Ewing
     * @param theProjectName the name of the project
     * @param theBudget the budget of the project
     * @param theJournal the journal for the project
     */
    public Project(String theProjectName, Budget theBudget, FileGroup theFiles, Journal theJournal){
        myProjectName = theProjectName;
        myPin = "-1";
        myBudget = theBudget;
        myFiles = theFiles;
        myJournal = theJournal;
        isPrivate = false;
    }

    /**
     * Gets the project name.
     *
     * @author Alex Ewing
     * @return myProjectName the name of the project
     */
    public String getProjectName() {
        return myProjectName;
    }

    /**
     * Sets the project name.
     *
     * @author Alex Ewing
     * @param  theProjectName the new name of the project
     */
    public void setProjectName(String theProjectName) {
        this.myProjectName = theProjectName;
    }

    /**
    public String getPin() {
        return myPin;
    }

    public void setPin(String thePin) {
        myPin = thePin;
        System.out.println(thePin);
    }

    /**
     * Gets the project's budget.
     * @return myBudget the project's budget
     */
    public Budget getBudget() {
        return myBudget;
    }

    /**
     * Gets the project's files
     * @return myFiles the project's files
     */
    public FileGroup getFiles() {
        return myFiles;
    }

    /**
     * Gets the project's journal
     * @return myJournal the project's journal
     */
    public Journal getJournal() {
        return myJournal;
    }

    /**
     * Gets the state of the project's privacey
     * @return isPrivate the boolean state of the project's privacey
     */
    public boolean getPrivate() {
        return isPrivate;
    }

    /**
     * Sets the state of the project's privacey
     * @param thePrivate the new boolean state of the project's privacey
     */
    public void setPrivate(boolean thePrivate) {
        isPrivate = thePrivate;
    }

    /**
     * String representation of a project that gives the project name followed
     * by the budget info.
     *
     * @return a string representation of the project
     */
    @Override
    public String toString() {
        String result = "Project " + myProjectName;
        result += myBudget.toString();
        return result;
    }
}

