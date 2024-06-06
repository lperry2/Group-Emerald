package src.model;

import java.util.ArrayList;

/** Represents a project */
public class Project {
    /** The name of the project. */
    private String myProjectName;

    /** The project's budget. */
    private Budget myBudget;

    /** The project's journal. */
    private Journal myJournal;

    /** The project's pin. */
    private String myPin;

    /** The state of the project's privacey. */
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
    public Project(String theProjectName, String thePin, Budget theBudget, Journal theJournal){
        myProjectName = theProjectName;
        myPin = thePin;
        myBudget = theBudget;
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
    public Project(String theProjectName, Budget theBudget, Journal theJournal){
        myProjectName = theProjectName;
        myPin = "-1";
        myBudget = theBudget;
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

    public String getPin() {
        return myPin;
    }

    public void setPin(String thePin) {
        myPin = thePin;
        System.out.println(thePin);
    }

    /**
     * Returns the project's budget.
     * @return myBudget the project's budget
     */
    public Budget getBudget() {
        return myBudget;
    }

    /**
     * Returns the project's Journal.
     * @return myJournal the project's journal
     */
    public Journal getJournal() {
        return myJournal;
    }

    /**
     * Returns whether the project is private or not.
     * @return isPrivate the state of the project's privacey
     */
    public boolean getPrivate() {
        return isPrivate;
    }

    /**
     * Sets the project's privacey.
     * @param thePrivate a boolean representing if the project is private or not
     */
    public void setPrivate(boolean thePrivate) {
        isPrivate = thePrivate;
    }

    @Override
    public String toString() {
        String result = "Project " + myProjectName;
        result += myBudget.toString();
        return result;
    }
}

