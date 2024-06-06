package src.model;

import java.util.ArrayList;

/** Represents a project */
public class Project {
    /** The name of the project. */
    private String myProjectName;
    /** The description of the project. */
    //private String myProjectDescription;
    /** The project's budget. */
    private Budget myBudget;

    private Journal myJournal;

    private String myPin;

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

    public Budget getBudget() {
        return myBudget;
    }

    public Journal getJournal() {
        return myJournal;
    }

    public boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean thePrivate) {
        isPrivate = thePrivate;
    }



    /**
     * Gets the project description.
     * @return myProjectDescription the description of the project
     * @author Alex Ewing
     */
//    public String getProjectDescription() {
//        return myProjectDescription;
//    }

    /**
     * Sets the project description.
     * @param theProjectDescription the new description of the project
     * @author Alex Ewing
     */
//    public void setProjectDescription(String theProjectDescription) {
//        this.myProjectDescription = theProjectDescription;
//    }

    @Override
    public String toString() {
        String result = "Project " + myProjectName;
        result += myBudget.toString();
        return result;
    }
}

